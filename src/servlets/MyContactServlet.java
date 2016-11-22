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
import domain.metier.Entreprise;
import domain.services.interfaces.IContactService;
import domain.services.interfaces.IEntrepriseService;

/**
 * Servlet implementation class MyContactServlet
 */
public class MyContactServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MyContactServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("SearchContact doGet");
		
		HttpSession session = request.getSession();
		System.out.println(session.getAttributeNames().toString());
		Account acc = (Account) request.getSession().getAttribute("acc");
		
		if(acc == null){
			request.setAttribute("message", "Please login...");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/");
			dispatcher.forward(request, response);
		} else {
			ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
			IContactService contactService = (IContactService) context.getBean("contactService");
			
			context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
			IEntrepriseService entrepriseService = (IEntrepriseService) context.getBean("entrepriseService");
			List<Entreprise> enrtreprises = entrepriseService.getEntrepriseByCreator(acc);
			
			List<Contact> contacts = contactService.getContactByCreator(acc);
			if(contacts==null || contacts.isEmpty()){
				request.setAttribute("message", "You have no contacts.");
			} else {
				request.setAttribute("contacts", contacts);
				request.setAttribute("enrtreprises", enrtreprises);
			}
			RequestDispatcher dispatcher = request.getRequestDispatcher("searchContact.jsp");
			dispatcher.forward(request, response);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
