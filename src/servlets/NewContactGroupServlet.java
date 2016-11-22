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
import domain.services.interfaces.IContactGroupService;

/**
 * Servlet implementation class NewContact
 */
public class NewContactGroupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public NewContactGroupServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("NewContactGroupServlet doPost");
		
		String name = request.getParameter("grp");
		
		Account acc = (Account) request.getSession().getAttribute("acc");
		
		if(acc==null){
			request.setAttribute("message", "Please login");
		} else if(name!=null && !name.isEmpty()){
			ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
			IContactGroupService contactGroupService = (IContactGroupService) context.getBean("contactGroupService");
			contactGroupService.createContactGroup(name, acc);
			
			request.setAttribute("message", "Group "+name+" has been correctly added !");
			
			request.setAttribute("contactsGrp", contactGroupService.findAll(acc));
		} else {
			request.setAttribute("message", "Error with the field...");
			
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("MyGroupsServlet");
		dispatcher.forward(request, response);
	}

}
