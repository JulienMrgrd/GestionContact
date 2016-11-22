package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import domain.metier.Account;
import domain.metier.Address;
import domain.metier.Contact;
import domain.metier.PhoneNumber;
import domain.services.interfaces.IAddressService;
import domain.services.interfaces.IContactService;
import domain.services.interfaces.IEntrepriseService;
import domain.services.interfaces.IPhoneNumberService;

/**
 * Servlet implementation class NewContact
 */
public class NewContactServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public NewContactServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("NewContact doPost");
	
		String firstName = request.getParameter("firstname");
		String lastName = request.getParameter("lastname");
		String email = request.getParameter("email");
		String siret = request.getParameter("siret");
		
		String street = request.getParameter("street");
		String city = request.getParameter("city");
		String zip = request.getParameter("zip");
		String country = request.getParameter("country");
		
		List<String> selectedGrp = new ArrayList<>();
		List<String> phones = new ArrayList<>();
		Set<String> names = request.getParameterMap().keySet();
		for(String name : names){
			if(name.startsWith("tel")) phones.add(request.getParameter(name));
			else if(name.startsWith("grp")) selectedGrp.add(request.getParameter(name));
		}
		
		Account acc = (Account) request.getSession().getAttribute("acc");
		
		boolean okFirstName = firstName!=null && firstName.length()>0;
		boolean okLastName = lastName!=null && lastName.length()>0;
		boolean okEmail = email!=null && email.length()>5 && email.contains("@") && email.contains(".");
		
		boolean okStreet = street!=null && street.length()>0;
		boolean okZip = zip!=null && zip.length()>0;
		boolean okCity = city!=null && city.length()>0;
		boolean okCountry = country!=null && country.length()>0;
		
		if(okFirstName && okLastName && okEmail && okStreet && okZip && okCity && okCountry){
			ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
			
			IAddressService addressService = (IAddressService) context.getBean("addressService");
			Address add = addressService.createAddress(street, city, zip, country);
			
			IContactService contactService = (IContactService) context.getBean("contactService");
			Contact c;
			if(siret!=null && !siret.isEmpty()){
				IEntrepriseService entrepriseService = (IEntrepriseService) context.getBean("entrepriseService");
				c = entrepriseService.createEntreprise(firstName, lastName, email, add, Long.parseLong(siret), acc);
			} else {
				c = contactService.createContact(firstName, lastName, email, add, acc);
			}
			
			IPhoneNumberService phoneService = (IPhoneNumberService) context.getBean("phoneNumberService");
			List<PhoneNumber> phonesNumber = new ArrayList<>(phones.size());
			for(String val : phones){
				PhoneNumber p = phoneService.createPhoneNumber("mobile", val, c);
				if(p!=null) phonesNumber.add(p);
			}
			
			for(String id : selectedGrp){
				contactService.addContactInGroup(c.getId(), Long.parseLong(id));
			}
			
			request.setAttribute("success", true);
			request.setAttribute("message", "Contact created !");
			RequestDispatcher dispatcher = request.getRequestDispatcher("MyContactServlet");
			dispatcher.forward(request, response);
		} else {
			request.setAttribute("success", false);
			request.setAttribute("message", "Error with one field...");
			RequestDispatcher dispatcher = request.getRequestDispatcher("addContact.jsp");
			dispatcher.forward(request, response);
		}
	}

}
