package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import domain.metier.Contact;
import domain.services.interfaces.IContactService;

/**
 * Servlet implementation class NewContact
 */
public class SearchContactServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchContactServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("SearchContact doGet");
		
		String firstName = request.getParameter("firstname");
		String lastName = request.getParameter("lastname");
		String email = request.getParameter("email");
		
		boolean okFirstName = firstName!=null && !firstName.isEmpty();
		boolean okLastName = lastName!=null && !lastName.isEmpty();
		boolean okEmail = email!=null && !email.isEmpty();
		
		if(!okFirstName && !okLastName && !okEmail){
			request.setAttribute("message", "Please fill at least one field");
			RequestDispatcher dispatcher = request.getRequestDispatcher("searchContact.jsp");
			dispatcher.forward(request, response);
		} else {
			ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
			IContactService contactService = (IContactService) context.getBean("contactService");
			
			List<Contact> contacts = contactService.searchContact(firstName, lastName, email);
			if(contacts==null || contacts.isEmpty()){
				request.setAttribute("message", "No contacts found...");
			} else {
				request.setAttribute("message", "Results :");
				request.setAttribute("contacts", contacts);
			}
			RequestDispatcher dispatcher = request.getRequestDispatcher("searchContact.jsp");
			dispatcher.forward(request, response);
		}
			
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
