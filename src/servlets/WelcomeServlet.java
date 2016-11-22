package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WelcomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public WelcomeServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("WelcomeServlet doPost");
		
		String signInButton = request.getParameter("signIn");
		String signUpButton = request.getParameter("signUp");
		if(signInButton!=null && signUpButton==null){
			request.setAttribute("SignInOrUp", "in");
		} else if (signInButton==null && signUpButton!=null){
			request.setAttribute("SignInOrUp", "up");
		} else {
			request.setAttribute("message", "No button has been clicked...");
			RequestDispatcher dispatcher = request.getRequestDispatcher("accueil.jsp");
			dispatcher.forward(request, response);
			return;
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("sign.jsp");
		dispatcher.forward(request, response);
	}

}
