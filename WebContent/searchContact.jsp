<%@page import="domain.metier.Contact"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="description" content="">
	<meta name="author" content="">
	<link rel="icon" href="../../favicon.ico">
	<link href="bootstrap/bootstrap.min.css" rel="stylesheet">
	
	<title>Home</title>

</head>

<body>
<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>                        
      </button>
      <a class="navbar-brand" href="index.jsp">GestionContact</a>
    </div>
    <div class="collapse navbar-collapse" id="myNavbar">
      <ul class="nav navbar-nav">
        <li class="dropdown">
          <a class="dropdown-toggle" data-toggle="dropdown" href="#">Mes contacts <span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li><a href="#">Gérer mes groupes</a></li>
            <li><a href="#">Gérer mes contacts</a></li>
            <li><a href="#">Autres</a></li>
          </ul>
        </li>
        <li><a href="#">Chercher des contacts</a></li>
      </ul>
      <ul class="nav navbar-nav navbar-right">
        <li><a href="sign.jsp?SignInOrUp=up"><span class="glyphicon"></span>Sign up</a></li>
        <li><a href="sign.jsp?SignInOrUp=in"><span class="glyphicon"></span>Login</a></li>
      </ul>
    </div>
  </div>
</nav>

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
	
<!-- Bootstrap core JavaScript
    ================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script src="bootstrap/bootstrap.min.js"></script>
<script src="../../assets/js/ie10-viewport-bug-workaround.js"></script>
</body>
</html>