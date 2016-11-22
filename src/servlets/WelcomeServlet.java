package servlets;

import java.io.IOException;
import java.lang.management.ManagementFactory;

import javax.management.MBeanServer;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.management.ManagementService;

public class WelcomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public WelcomeServlet() {
        super();
        System.out.println("====================> welcome servlet");
        CacheManager manager = new CacheManager();
        MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
        ManagementService.registerMBeans(manager, mBeanServer, false, false, false, true);
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
