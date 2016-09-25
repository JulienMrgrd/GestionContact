<%@page import="com.sun.istack.internal.NotNull"%>
<%@page import="domain.metier.Contact"%>
<%@page import="domain.services.ContactService"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>search contact</title>
</head>
<body>

	<%
		String message = ((String)request.getAttribute("message"));
		if(message != null){
			out.print("<b><font color=\"red\">"+message+"</font></b><br><br>");
		}
		
		@SuppressWarnings("unchecked")
		List<Contact> contacts = ((List<Contact>) request.getAttribute("contacts"));
		
		if(contacts != null && !contacts.isEmpty()){
	%>
			<table cellpadding="10" cellspacing="2" border="1">
				<tr>
					<th>First name</th>
					<th>Last name</th>
					<th>Email</th>
				</tr>
	<%
				for (Contact c : contacts) {
					out.print("<tr>");
					out.print("<td>" + c.getFirstName() + "</td>");
					out.print("<td>" + c.getLastName() + "</td>");
					out.print("<td>" + c.getEmail() + "</td>");
					out.print("</tr>");
				}
	%>
			</table><br><br>  <!-- obligatoire car il y a une balise <table> au dessus -->
			<hr><br><b>New search :</b><br><br>
	<%
		}
	%>

	<form method=get action="SearchContact">
		First Name : <input type="text" name="firstname"><br>
		And/or Last Name : <input type="text" name="lastname"><br>
		And/or Email : <input type="text" name="email"><br>
	
		<br/>
		
		<input class="button" type="submit" value="Search">
		<input class="button" type="reset" value="Reset">
		
	</form>
	
	<br/>
	<a href="accueil.jsp">
		<button type="submit">Home</button>
	</a>
</body>
</html>