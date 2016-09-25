<%@page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
</head>
<body>
	<form method=post action="LoginServlet">
		<%
			String message = ((String)request.getAttribute("message"));
			
			if(message != null){
				out.print("<b><font color=\"red\">"+message+"</font></b><br><br>");
			}
		
		%>
		
		Name : <input type="text" name="name"><br>
		Password : <input type="password" name="password">
	
		<br/><br/>
		
		<input class="button" type="submit" value="submit">
		<input class="button" type="reset" value="reset">
	
	</form>
</body>
</html>