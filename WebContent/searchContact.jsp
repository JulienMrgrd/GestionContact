<%@page import="domain.metier.Address"%>
<%@page import="domain.metier.Contact"%>
<%@page import="domain.metier.ContactGroup"%>
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
<title>Search contact</title>
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
		<%}
		
		@SuppressWarnings("unchecked")
		List<Contact> contacts = ((List<Contact>) request.getAttribute("contacts"));
		if(contacts != null && !contacts.isEmpty()){
			long id = 0;
			long version = 0;
			String firstName = "";
			String lastName = "";
			String update="";
			String delete="";
			Address add;
			String rue,ville, pays, zip;
			
			%> <div class="panel-group" id="panel-results">  <%
			for (Contact c : contacts) {
				rue="";
				ville="";
				pays="";
				zip="";
				version=c.getVersion();
				id = c.getId();
				firstName = c.getFirstName();
				lastName = c.getLastName();
				if(c.getAdd() != null){
					add=c.getAdd();
					rue = add.getStreet();
					ville = add.getCity();
					zip = add.getZip();
					pays = add.getCountry();
				}
				add=c.getAdd();
				update="updateContact.jsp?id="+id;
				delete="DeleteContactServlet?id="+id+"&version="+version;
				%>
				<div class="panel panel-default">
					<div class="panel-heading">
						<a class="panel-title text-center" data-toggle="collapse" data-parent="#panel-results" 
							href="#panel-element-<%=id %>"><%=firstName+" "+lastName%></a>
							<div style="float: right;">
								<a id="<%=id%>" class="btn btn-warning btn-outline btn-sm good"
									href=<%=delete %>>Delete</a>
								<a id="<%=id%>" class="btn btn-success btn-outline btn-sm good"
									href=<%=update %>>Update</a>
							</div>
					</div>
				<fieldset>
				<%
					String allNumber = "";
					if(c.getPhones()!=null && c.getPhones().size()>0){
						for(PhoneNumber pn: c.getPhones()){
							allNumber+= pn.getPhoneKind()+" : "+pn.getPhoneNumber()+"\n";
						}
					}else {
						allNumber="No phone number";
					}
					String allGroup = "";
					if(c.getBooks()!=null && c.getBooks().size()>0){
						for(ContactGroup cg: c.getBooks()){
							allGroup+= cg.getGroupName()+"\n";
						}
					}else {
						allGroup="No group";
					}
				%>
				
					<div id="panel-element-<%=id%>" class="panel-collapse collapse">
						<div class="panel-body">
						<fieldset>
							<legend class="text-center">Address :</legend>
							<pre class="text-center"><%=rue +" \n"+zip+" "+ville+" \n"+pays%></pre>
						</fieldset>
						<fieldset>
							<legend class="text-center">PhoneNumber:</legend>
							<pre class="text-center"><%=allNumber %></pre>
						</fieldset>
						<fieldset>
							<legend class="text-center">ContactGroup:</legend>
							<pre class="text-center"><%=allGroup %></pre>
						</fieldset>
						</div>
					</div>
				
				
				</fieldset>
				</div>
<%				}
			%></div>
		<% } %>
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