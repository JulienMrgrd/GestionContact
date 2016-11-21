package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import domain.metier.Account;
import domain.metier.Contact;
import domain.services.interfaces.IContactService;

/**
 * Servlet implementation class MyContactServlet
 */
public class MyContactServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyContactServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("SearchContact doGet");
		
		HttpSession session = request.getSession();
		System.out.println(session.getAttributeNames().toString());
		Account acc = (Account) request.getSession().getAttribute("acc");
		
		if(acc == null){
			request.setAttribute("message", "Veuillez vous connecter");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/");
			dispatcher.forward(request, response);
		} else {
			ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
			IContactService contactService = (IContactService) context.getBean("contactService");
			
			List<Contact> contacts = contactService.getContactByCreator(acc);
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
