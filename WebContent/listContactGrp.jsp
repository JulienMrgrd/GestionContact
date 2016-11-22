<%@page import="domain.metier.ContactGroup"%>
<%@page import="domain.metier.Contact"%>
<%@page import="domain.metier.PhoneNumber" %>
<%@page import="java.util.List"%>
<%@page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="utils/bootstrap.min.css" rel="stylesheet">
<link href="utils/general.css" rel="stylesheet">
<title>Groups contacts</title>
</head>

<body>
	<div id="header"></div>

	<div class="container">
	<div class="col-md-5">
	
	<%
		String message = ((String)request.getAttribute("message"));
		Boolean success = ((Boolean)request.getAttribute("success"));
		if(message != null){
			if(success==null || !success){ %>
				<div class="alert alert-dismissable alert-danger">
			<% } else { %>
				<div class="alert alert-dismissable alert-success">
			<% }%>
				<button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
				<h4><%=message %></h4> 
			
			<% if(success==null || !success){ %>
				</div>
			<% } else { %>
				</div>
			<% }%>
		<%}%>
	<fieldset style="margin-top: 5%;">
 		<legend style="color: #5826AB80;">Your contact groups</legend>
	</fieldset>
	<%
		@SuppressWarnings("unchecked")
		List<ContactGroup> contactsGrp = ((List<ContactGroup>) request.getAttribute("contactsGrp"));
		if(contactsGrp != null && !contactsGrp.isEmpty()){
		%>	
			<ul>
		<%	for (ContactGroup c : contactsGrp) {
		%>
				<li><%=c.getGroupName() %></li>
		
		<%	} %>
			</ul>
	<%	} %>
	<br>
	<form class="form-inline" action="NewContactGroupServlet" method="get">
	  <div class="form-group">
	    <div class="input-group">
	      <input type="text" class="form-control" name="grp" placeholder="Group name">
	    </div>
	  </div>
	  <button type="submit" class="btn btn-primary">Create group</button>
	</form>
	</div>
	</div>	
	
</body>
<script src="utils/jquery.min.js"></script>
<% if(request.getSession().getAttribute("acc")==null){ %>
<script> (function() { $("#header").load("header/headerNotConnected.html"); })(); </script>
<% } else { %>
<script> (function() { $("#header").load("header/headerConnected.html"); })(); </script>
<% } %>

</html>