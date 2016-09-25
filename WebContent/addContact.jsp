<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add contact</title>
</head>
<body>

	<%
		String message = ((String)request.getAttribute("message"));
		if(message != null){
			out.print("<b><font color=\"red\">"+message+"</font></b><br><br>");
		}
		
		// récupération des éléments déjà renseignés (cas d'erreur)
		String firstname = request.getParameter("firstname")==null ? "" : request.getParameter("firstname");
		String lastname = request.getParameter("lastname")==null ? "" : request.getParameter("lastname");
		String email = request.getParameter("email")==null ? "" : request.getParameter("email");
		
	%>

	<form method=post action="NewContact">
		First Name : <input type="text" name="firstname" value="<%=firstname %>"><br>
		Last Name : <input type="text" name="lastname" value="<%=lastname %>"><br> 
		Email : <input type="text" name="email" value="<%=email %>"><br> 
		
		<br/> 
		<input class="button" type="submit" value="Submit"> 
		<input class="button" type="reset" value="Reset">

	</form>
	
	<br/>
	<a href="accueil.jsp">
		<button type="submit">Home</button>
	</a>

</body>
</html>