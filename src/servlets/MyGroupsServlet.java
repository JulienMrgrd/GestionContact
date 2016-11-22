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
import domain.metier.ContactGroup;
import domain.services.interfaces.IContactGroupService;

/**
 * Servlet implementation class MyContactServlet
 */
public class MyGroupsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MyGroupsServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("MyGroupsServlet doGet");
		
		HttpSession session = request.getSession();
		System.out.println(session.getAttributeNames().toString());
		Account acc = (Account) request.getSession().getAttribute("acc");
		
		if(acc == null){
			request.setAttribute("message", "Please login...");
		
		} else {
			ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
			IContactGroupService groupService = (IContactGroupService) context.getBean("contactGroupService");
			
			List<ContactGroup> contactsGrp = groupService.findAll(acc);
			if(contactsGrp==null || contactsGrp.isEmpty()){
				request.setAttribute("message", "No contact groups found...");
			} else {
				request.setAttribute("contactsGrp", contactsGrp);
			}
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("listContactGrp.jsp");
		dispatcher.forward(request, response);
	}

}
