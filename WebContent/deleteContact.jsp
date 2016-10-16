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
		
	%>

	<form method=post action="DeleteContact">
		Id : <input type="text" name="idContact"><br>
	
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