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
<link rel="icon" href="../../favicon.ico">
<link href="bootstrap/bootstrap.min.css" rel="stylesheet">
<title>Search contact</title>
</head>

<body>
	<div id="header"></div>

	<%
		String message = ((String)request.getAttribute("message"));
		if(message != null){
			out.print("<b><font color=\"red\">"+message+"</font></b><br><br>");
		}
		
		@SuppressWarnings("unchecked")
		List<Contact> contacts = ((List<Contact>) request.getAttribute("contacts"));
		if(contacts != null && !contacts.isEmpty()){
			long id = 0;
			String firstName = "";
			String lastName = "";
			String ref="";
			for (Contact c : contacts) {
				id = c.getId();
				firstName = c.getFirstName();
				lastName = c.getLastName();
				ref="UpdateContactServlet?id="+id;
				%>
				<div class="panel panel-default">
					<div class="panel-heading">
						<a class="panel-title" data-toggle="collapse" data-parent="#panel-results" 
							href="#panel-element-<%=id %>"><%=firstName+" - "+lastName%></a>
							<div style="float: right;">
								<a id="<%=id%>" class="btn btn-success btn-outline btn-sm good"
									href=<%=ref %>>Update !</a>
							</div>
					</div>
				<%
					String allNumber = "";
					for(PhoneNumber pn: c.getPhones()){
						allNumber+= pn.getPhoneKind()+" : "+pn.getPhoneNumber()+"\n";
					}
				%>
					<div id="panel-element-<%=id%>" class="panel-collapse collapse">
						<div class="panel-body">
							<pre><%=allNumber %></pre>
						</div>
					</div>
				</div>
<%				}
	
		}
		%>
	<form method=get action="SearchContactServlet">
		
	</form>
	
</body>
<script src="bootstrap/jquery.min.js"></script>
<% if(request.getSession().getAttribute("acc")==null){ %>
<script> (function() { $("#header").load("header/headerNotConnected.html"); })(); </script>
<% } else { %>
<script> (function() { $("#header").load("header/headerConnected.html"); })(); </script>
<% } %>

</html>