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
	
	<title>Ajouter un contact</title>

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
		
		// récupération des éléments déjà renseignés (cas d'erreur)
		String firstname = request.getParameter("firstname")==null ? "" : request.getParameter("firstname");
		String lastname = request.getParameter("lastname")==null ? "" : request.getParameter("lastname");
		String email = request.getParameter("email")==null ? "" : request.getParameter("email");
		
		String street = request.getParameter("street")==null ? "" : request.getParameter("street");
		String city = request.getParameter("city")==null ? "" : request.getParameter("city");
		String zip = request.getParameter("zip")==null ? "" : request.getParameter("zip");
		String country = request.getParameter("country")==null ? "" : request.getParameter("country");
	%>

	<form method=post action="NewContactServlet">
		First Name : <input type="text" name="firstname" value="<%=firstname %>"><br>
		Last Name : <input type="text" name="lastname" value="<%=lastname %>"><br> 
		Email : <input type="text" name="email" value="<%=email %>"><br> 
		Street : <input type="text" name="street" value="<%=street %>"><br>
		City : <input type="text" name="city" value="<%=city %>"><br> 
		Zip : <input type="text" name="zip" value="<%=zip %>"><br> 
		Country : <input type="text" name="country" value="<%=country %>"><br> 
		
		<br/> 
		<input class="button" type="submit" value="Submit"> 
		<input class="button" type="reset" value="Reset">

	</form>
	
	<br/>

<!-- Bootstrap core JavaScript
    ================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script src="bootstrap/bootstrap.min.js"></script>
<script src="../../assets/js/ie10-viewport-bug-workaround.js"></script>
</body>
</html>