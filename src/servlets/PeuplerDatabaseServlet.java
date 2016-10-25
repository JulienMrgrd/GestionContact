package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import domain.metier.Account;
import domain.metier.Contact;
import domain.services.interfaces.IContactService;

/**
 * Servlet implementation class PeuplerDatabaseServlet
 */
public class PeuplerDatabaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PeuplerDatabaseServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Account acc = (Account) request.getSession().getAttribute("acc");
		
		if(acc == null){
			request.setAttribute("message", "Veuillez d'abord vous connecter ou vous inscrire...");
			request.getRequestDispatcher("demo.jsp").forward(request, response);
		} else {
		
			ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
			IContactService contactService = (IContactService) context.getBean("contactService");
	
			Contact c1 = (Contact) context.getBean("fakeContact1");
			Contact c2 = (Contact) context.getBean("fakeContact2");
			Contact c3 = (Contact) context.getBean("fakeContact3");
			
			System.out.println("insertion c1");
			contactService.createContact(c1.getFirstName(), c1.getLastName(), 
					c1.getEmail(), c1.getAdd(), acc);
			System.out.println("c1 inséré");
			contactService.createContact(c2.getFirstName(), c2.getLastName(), 
					c2.getEmail(), c2.getAdd(), acc);
			System.out.println("c2 inséré");
			contactService.createContact(c3.getFirstName(), c3.getLastName(), 
					c3.getEmail(), c3.getAdd(), acc);
			System.out.println("c3 inséré");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
