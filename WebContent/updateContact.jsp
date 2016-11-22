<%@page import="domain.metier.PhoneNumber"%>
<%@page import="domain.metier.Contact"%>
<%@page import="domain.metier.Account"%>
<%@page import="domain.services.interfaces.IContactService"%>
<%@page import="domain.metier.ContactGroup"%>
<%@page import="java.util.List"%>
<%@page import="domain.services.interfaces.IContactGroupService"%>
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<style type="text/css">
.from-group {
	margin: 10px;
}

.entry:not (:first-of-type ) {
	margin-top: 10px;
}

.glyphicon {
	font-size: 12px;
}
</style>

<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="utils/bootstrap.min.css" rel="stylesheet">
<link href="utils/general.css" rel="stylesheet">
<title>Update contact</title>
</head>

<body>
	<div id="header"></div>
	
	<div class="container">
	<%
		boolean noContent = false;
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
		
		if(request.getParameter("id")==null){
			out.print("<b><font color=\"red\">Unknown id...</font></b><br><br>");
			noContent=true;
		}
		
		ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		IContactService contactService = (IContactService) context.getBean("contactService");
		
		Long id = Long.parseLong(request.getParameter("id"));
		Contact cont = contactService.getContactById(id);
		
		if(cont==null){
			out.print("<b><font color=\"red\">Unknown contact...</font></b><br><br>");
			noContent=true;
		} 
		
		if(!noContent){
	%>

	<div class="container col-xs-7">
		<form method=post action="UpdateContactServlet">
			<input type="hidden" name="idContact" value="<%=cont.getId()%>">
			<input type="hidden" name="version" value="<%=cont.getVersion()%>">
			<div class="from-group">
				<label for="firstname">First name</label> 
				<input type="text" class="form-control" name="firstname" placeholder="<%=cont.getFirstName()%>">
			</div>
			<div class="from-group">
				<label for="lastname">Last name</label> 
				<input type="text" class="form-control" name="lastname" placeholder="<%=cont.getLastName()%>">
			</div>
			<div class="from-group">
				<label for="email">Email address</label>
				<input type="email" class="form-control" name="email" placeholder="<%=cont.getEmail()%>">
			</div>
			
			<fieldset style="margin-top: 5%; margin-bottom: -2%;">
    			<legend style="color: #5826AB80;">Address</legend>
   			</fieldset>
   			
   			<input type="hidden" name="idAdd" value="<%=cont.getAdd().getId()%>">
			<div class="from-group">
				<label for="street">Street</label> 
				<input type="text" class="form-control" name="street" placeholder="<%=cont.getAdd().getStreet()%>">
			</div>
			<div class="from-group">
				<label for="city">City</label>
				<input type="text" class="form-control" name="city" placeholder="<%=cont.getAdd().getCity()%>">
			</div>
			<div class="from-group">
				<label for="zip">Zip</label> 
				<input type="number" class="form-control" name="zip" placeholder="<%=cont.getAdd().getZip()%>">
			</div>
			<div class="from-group">
				<label for="country">Country</label> 
				<input type="text" class="form-control" name="country" placeholder="<%=cont.getAdd().getCountry()%>">
			</div>

			<fieldset style="margin-top: 5%;">
    			<legend style="color: #5826AB80;">Phones</legend>
   			</fieldset>
   			
   			<%
   				if(cont.getBooks()==null){ 
   			%>
   					<h3>No groups</h3>
   			<%
   				} else { 
   					int cpt = 1;	
   			%>
   				<div class="phones">
   			<%		for(PhoneNumber phone : cont.getPhones()){ %>
   						<div class="form-inline" style="margin-bottom:3px;">
							<div class="form-group">
								<div class="input-group">
									<input type="tel" class="form-control phone" name="<%="tel"+cpt%>" id="<%="tel"+cpt%>" 
										maxlength="10" style="border-radius: 4px;" placeholder="<%=phone.getPhoneNumber()%>">
								</div>
							</div>
						</div>
			<%
					cpt++; 
   					} %>
				</div>
			<%	} %>
			<button class="btn add_field_button">Add a phone</button> 
			
			<fieldset style="margin-top: 5%;">
    			<legend style="color: #5826AB80;">Contact groups</legend>
   			</fieldset>
   			<%
   				if(cont.getBooks()==null){ 
   			%>
   					<h3>No groups</h3>
   			<%
   				} else {
   					for(ContactGroup grp : cont.getBooks()){ %>
   						<div class="form-group">
							<input type="checkbox" checked="checked" name="grp<%=grp.getId_group()%>" 
								id="<%=grp.getId_group()%>" value="<%=grp.getGroupName()%>"><%=grp.getGroupName()%>
						</div>
			<%
					}
				}
			%>
			
			<div class="from-group" style="margin-top:50px;">
				<button type="submit" class="btn btn-info">Update</button>
				<button type="reset" class="btn btn-default">Reset</button>
			</div>
			
	</form>
	</div>	
	<%} %>
	</div>

</body>

<script src="utils/jquery.min.js"></script>
<% if(request.getSession().getAttribute("acc")==null){ %>
<script> (function() { $("#header").load("header/headerNotConnected.html"); })(); </script>
<% } else { %>
<script> (function() { $("#header").load("header/headerConnected.html"); })(); </script>
<% } %>

<script>

$(document).ready(function(){
	   
    $( ".phones" ).keypress(function(e) {
      if ( e.which == 13 ) {
         e.preventDefault();
      }
    });
    
    var max_fields      = 10;
    var wrapper         = $(".phones");
    var add_button      = $(".add_field_button");
   
    var x = $(".phone").length;
    $(add_button).click(function(e){
        e.preventDefault();
        if(x < max_fields){ //max input box allowed
            x++; //text box increment
            $(wrapper).append('<div class="form-inline" style="margin-bottom:3px;">'+
								'<div class="form-group">'+
									'<div class="input-group">'+
										'<input type="tel" class="form-control" name="tel'+x+'" id="tel'+x+'" maxlength="10">'+
										'<div class="input-group-addon btn btn-danger remove_field">-</div>'+
										'</div>'+
									'</div>'+
								'</div>');
        }
    });
   
    $(wrapper).on("click",".remove_field", function(e){ //user click on remove text
        e.preventDefault(); 
    	$(this).parent('div').parent('div').parent('div').remove(); 
    	x--;
    });
    
});

</script>

</html>