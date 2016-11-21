<%@page import="java.util.Enumeration"%>
<%@page import="domain.metier.Account"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<style>
.demo-long {
	margin-top: 100px;
	margin-bottom: 200px;
}
</style>

<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link href="utils/bootstrap.min.css" rel="stylesheet">
</head>

<body>
	<div id="header"></div>
	
	<div class="container">
	</div>

</body>

<script src="utils/jquery.min.js"></script>
<% if(request.getSession().getAttribute("acc")==null){ %>
<script> (function() { $("#header").load("header/headerNotConnected.html"); })(); </script>
<% } else { %>
<script> (function() { $("#header").load("header/headerConnected.html"); })(); </script>
<% } %>

</html>