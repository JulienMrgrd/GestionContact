package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.services.AccountService;
import util.GestionContactUtils;

public class SignServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public SignServlet() { }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("SignServlet doPost");
		AccountService service = new AccountService();
		boolean okForTask = false;
		
		String signInOrUp = request.getParameter("SignInOrUp");
		request.setAttribute("SignInOrUp", signInOrUp); // en cas de rechargement de page
		
		if(signInOrUp!=null && signInOrUp.equals("up")){ // Inscription
			String login = request.getParameter("login");
			String password = request.getParameter("password");
			String secondPassword = request.getParameter("secondPassword");
			
			if(login!=null && !login.isEmpty() && password!=null && !password.isEmpty() 
					&& secondPassword!=null && !secondPassword.isEmpty()){ // Champs correctement remplis
				if(!password.equals(secondPassword)){
					request.setAttribute("message", "Not the same passwords...");
				} else if(service.containsLogin(login)){
					request.setAttribute("message", "Login already exists...");
				} else {
					
					long id = service.createAccount(login, password);
					if(id==GestionContactUtils.BAD_ID) request.setAttribute("message", "Sorry. An error occured during the account creation...");
					else {
						request.getSession().setAttribute("id", id);
						request.setAttribute("message", "Welcome "+login+" !");
						okForTask = true;
					}
				}
				
			} else {
				request.setAttribute("message", "One field is empty...");
			}
			
		} else if(signInOrUp!=null && signInOrUp.equals("in")){ // Connexion
			String login = request.getParameter("login");
			String password = request.getParameter("password");
			
			if(login!=null && !login.isEmpty() && password!=null && !password.isEmpty()){ // Champs correctement remplis
				if(!service.containsLogin(login)){
					request.setAttribute("message", "Unknown login...");
				} else {
					
					long id = service.checkConnection(login, password);
					if(id==GestionContactUtils.BAD_ID) request.setAttribute("message", "Bad password...");
					else {
						request.getSession().setAttribute("id", id);
						request.setAttribute("message", "Welcome "+login+" !");
						okForTask = true;
					}
				}
				
			} else {
				request.setAttribute("message", "One field is empty...");
			}
			
		} else {
			request.setAttribute("message", "Neither a subscription nor connection. Strange...");
		}
		
		RequestDispatcher dispatcher;
		if(okForTask) dispatcher = request.getRequestDispatcher("task.jsp");
		else dispatcher = request.getRequestDispatcher("sign.jsp");
		dispatcher.forward(request, response);
		
	}

}
