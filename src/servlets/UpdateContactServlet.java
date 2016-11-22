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
import domain.services.interfaces.IPhoneNumberService;

/**
 * Servlet implementation class NewContact
 */
public class UpdateContactServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UpdateContactServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("NewContact doPost");
	
		String firstName = request.getParameter("firstname");
		String lastName = request.getParameter("lastname");
		String email = request.getParameter("email");
		
		String idAddress = request.getParameter("idAdd");
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
		
		if(!request.getParameterMap().isEmpty()){
			ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
			
			IAddressService addressService = (IAddressService) context.getBean("addressService");
			boolean res = addressService.updateAddress(Long.parseLong(idAddress), street, city, zip, country);
			
			//TODO
//			IContactService contactService = (IContactService) context.getBean("contactService");
//			Contact c = contactService.createContact(firstName, lastName, email, add, acc);
//			
//			IPhoneNumberService phoneService = (IPhoneNumberService) context.getBean("phoneNumberService");
//			List<PhoneNumber> phonesNumber = new ArrayList<>(phones.size());
//			for(String val : phones){
//				PhoneNumber p = phoneService.createPhoneNumber("mobile", val, c);
//				if(p!=null) phonesNumber.add(p);
//			}
			
			request.setAttribute("success", true);
			request.setAttribute("message", "Updated contact !");
			RequestDispatcher dispatcher = request.getRequestDispatcher("task.jsp");
			dispatcher.forward(request, response);
		} else {
			request.setAttribute("success", false);
			request.setAttribute("message", "Change at least a field...");
			RequestDispatcher dispatcher = request.getRequestDispatcher("addContact.jsp");
			dispatcher.forward(request, response);
		}
	}

}
