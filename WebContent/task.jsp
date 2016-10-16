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
	
	<title>Mes contacts</title>

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
      <a class="navbar-brand" href="">GestionContact</a>
    </div>
    <div class="collapse navbar-collapse" id="myNavbar">
       <%
		if(request.getSession().getAttribute("id")==null){
		%>
			<ul class="nav navbar-nav navbar-right">
	        	<li><a href="sign.jsp?SignInOrUp=up"><span class="glyphicon"></span>Sign up</a></li>
	        	<li><a href="sign.jsp?SignInOrUp=in"><span class="glyphicon"></span>Login</a></li>
        	</ul>
	    <%
		} else {
	    %>
	    	<ul class="nav navbar-nav">
				<li class="dropdown"><a class="dropdown-toggle"
					data-toggle="dropdown" href="#">Mes contacts <span
						class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="#">Gérer mes groupes</a></li>
						<li><a href="#">Gérer mes contacts</a></li>
						<li><a href="#">Autres</a></li>
					</ul></li>
				<li><a href="#">Chercher des contacts</a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
	        	<li>
					<form method=post action="DisconnectServlet">
		        		<button type="submit" class="btn btn-default navbar-btn">Déconnexion</button>
	       			</form>
       			</li>
        	</ul>
	    <% } %>
    </div>
  </div>
</nav>

	<%
		String message = ((String)request.getAttribute("message"));
		if(message != null){
			out.print("<b><font color=\"red\">"+message+"</font></b><br><br>");
		}
	%>

	<ul>
		<li><a href="addContact.jsp">Add contact</a></li>
		<li><a href="deleteContact.jsp">Remove contact</a></li>
		<li><a href="updateContact.jsp">Update contact</a></li>
		<li><a href="searchContact.jsp">Search contact</a></li>
	</ul>
	
<!-- Bootstrap core JavaScript
    ================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script src="bootstrap/bootstrap.min.js"></script>
<script src="../../assets/js/ie10-viewport-bug-workaround.js"></script>
</body>
</html>