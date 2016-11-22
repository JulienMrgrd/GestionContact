package servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import domain.metier.Account;
import domain.metier.Contact;
import domain.services.interfaces.IAddressService;
import domain.services.interfaces.IContactGroupService;
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
		String idContact = request.getParameter("idContact");
		String version = request.getParameter("version");
		String firstName = request.getParameter("firstname");
		String lastName = request.getParameter("lastname");
		String email = request.getParameter("email");
		
		String idAddress = request.getParameter("idAdd");
		String street = request.getParameter("street");
		String city = request.getParameter("city");
		String zip = request.getParameter("zip");
		String country = request.getParameter("country");
		
		Map<String, String>  selectedGrp = new HashMap<>();
		Map<String, String> phones = new HashMap<>();
		Set<String> names = request.getParameterMap().keySet();
		for(String name : names){
			if(name.startsWith("tel")) phones.put(name.replace("tel", ""), request.getParameter(name));
			else if(name.startsWith("grp")) selectedGrp.put(name.replace("grp", ""), request.getParameter(name));
		}
		
		Account acc = (Account) request.getSession().getAttribute("acc");
		
		if(acc==null){
			request.setAttribute("message", "Please login...");
			request.setAttribute("success", false);
			RequestDispatcher dispatcher = request.getRequestDispatcher("addContact.jsp");
			dispatcher.forward(request, response);
			
		} else if(!request.getParameterMap().isEmpty()){
			ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
			IContactService contactService = (IContactService) context.getBean("contactService");
			Contact c = contactService.getContactById(Long.parseLong(idContact));
			if(c.getVersion()!=Long.parseLong(version)){
				request.setAttribute("success", false);
				request.setAttribute("message", "Contact not updated (not up-to-date).");
			
			} else {
			
				IAddressService addressService = (IAddressService) context.getBean("addressService");
				boolean add = addressService.updateAddress(Long.parseLong(idAddress), street, city, zip, country);
				boolean res = contactService.updateContact(Long.parseLong(idContact), firstName, lastName, email, null);
				
				IPhoneNumberService phoneService = (IPhoneNumberService) context.getBean("phoneNumberService");
				for(String id : phones.keySet()){
					phoneService.updatePhoneNumber(Long.parseLong(id), "mobile", phones.get(id), 
							contactService.getContactById(Long.parseLong(idContact)));
				}
				
				IContactGroupService grpService = (IContactGroupService) context.getBean("contactGroupService");
				for(String id : selectedGrp.keySet()){
					grpService.updateContactGroup(Long.parseLong(id), selectedGrp.get(id));
				}
				
				if(res && add){
					request.setAttribute("success", true);
					request.setAttribute("message", "Contact updated !");
				} else if(!add && !res){
					request.setAttribute("success", false);
					request.setAttribute("message", "Contact not updated...");
				} else if(!add && res){
					request.setAttribute("success", false);
					request.setAttribute("message", "Contact updated but not his address...");
				} else if(add && !res){
					request.setAttribute("success", false);
					request.setAttribute("message", "Address updated but not the other values of this contact...");
				}
			}
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("MyContactServlet");
			dispatcher.forward(request, response);
		} else {
			request.setAttribute("success", false);
			request.setAttribute("message", "Change at least a field...");
			RequestDispatcher dispatcher = request.getRequestDispatcher("addContact.jsp");
			dispatcher.forward(request, response);
		}
	}

}
