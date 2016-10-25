package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import domain.metier.Account;
import domain.metier.Address;
import domain.services.interfaces.IAddressService;
import domain.services.interfaces.IContactService;

/**
 * Servlet implementation class NewContact
 */
public class NewContactServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewContactServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("NewContact doGet");
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("NewContact doPost");
	
		String firstName = request.getParameter("firstname");
		String lastName = request.getParameter("lastname");
		String email = request.getParameter("email");
		
		String street = request.getParameter("street");
		String city = request.getParameter("city");
		String zip = request.getParameter("zip");
		String country = request.getParameter("country");
		Account acc = (Account) request.getSession().getAttribute("id");
		
		ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		IAddressService addressService = (IAddressService) context.getBean("addressService");
		Address add= addressService.createAddress(street, city, zip, country);
		
		/*TODO: vérification confirmité des champs*/
		boolean okFirstName = firstName!=null && firstName.length()>0; //&& not exists in DB
		boolean okLastName = lastName!=null && lastName.length()>0; //&& not exists in DB
		boolean okEmail = email!=null && email.length()>0; //&& not exists in DB

		if(okFirstName && okLastName && okEmail){
			IContactService contactService = (IContactService) context.getBean("contactService");
			contactService.createContact(firstName, lastName, email,add,acc);
			request.setAttribute("message", firstName+" "+lastName+" has been added correctly !");
			RequestDispatcher dispatcher = request.getRequestDispatcher("task.jsp");
			dispatcher.forward(request, response);
		} else {
			request.setAttribute("message", "Error with one field...");
			RequestDispatcher dispatcher = request.getRequestDispatcher("addContact.jsp");
			dispatcher.forward(request, response);
		}
	}

}
