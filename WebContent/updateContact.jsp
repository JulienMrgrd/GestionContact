<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>update contact</title>
</head>
<body>

	<%
		String message = ((String)request.getAttribute("message"));
		if(message != null){
			out.print("<b><font color=\"red\">"+message+"</font></b><br><br>");
		}
		
	%>

	<form method=post action="UpdateContact">
		First Name : <input type="text" name="firstname"><br>
		Last Name : <input type="text" name="lastname"><br>
		Email : <input type="text" name="email"><br>
	
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