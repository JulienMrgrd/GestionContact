<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Accueil</title>
</head>
<body>
	<form method=post action="SignServlet">
	<%
		String message = ((String)request.getAttribute("message"));
		if(message != null){
			out.print("<b><font color=\"red\">"+message+"</font></b><br><br>");
		}
		
		// récupération des éléments déjà renseignés (cas d'erreur)
		String login = request.getParameter("login")==null ? "" : request.getParameter("login");
		String password = request.getParameter("password")==null ? "" : request.getParameter("password");
		
		// SignIn ou SignUp
		String sign = (String) request.getAttribute("SignInOrUp");
		if(sign==null){
			out.print("<b><font color=\"red\">Ni connexion, ni inscription...</font></b><br><br>");

		} else if(sign.equals("up")){
	%>
			<h1>Inscription</h1>
			<input type="hidden" name="SignInOrUp" value="up" />
			Login : <input type="text" name="login" value="<%=login %>"><br>
			Password : <input type="text" name="password" value="<%=password %>"><br> 
			Repeated password : <input type="text" name="secondPassword"><br> 
			
			<br/> 
			<input class="button" type="submit" value="Sign up"> 
			<input class="button" type="reset" value="Reset">
			
	<%		
		} else if (sign.equals("in")){
	%>
			<h1>Connexion</h1>
			<input type="hidden" name="SignInOrUp" value="in" />
			Login : <input type="text" name="login" value="<%=login %>"><br>
			Password : <input type="text" name="password" value="<%=password %>"><br> 
			
			<br/> 
			<input class="button" type="submit" value="Sign in"> 
			<input class="button" type="reset" value="Reset">
	<%
		}
	%>
	</form>
</body>
</html>