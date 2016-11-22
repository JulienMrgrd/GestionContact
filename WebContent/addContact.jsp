<%@page import="domain.metier.ContactGroup"%>
<%@page import="java.util.List"%>
<%@page import="domain.metier.Account"%>
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
<title>Ajouter un contact</title>
</head>

<body>
	<div id="header"></div>

	<div class="container">
	<%
	
		Account acc = (Account) request.getSession().getAttribute("acc");
		if(acc==null){
			request.setAttribute("message", "Please login...");
			request.getRequestDispatcher("WelcomeServlet?SignInOrUp=in").forward(request, response);
			return;
		}
		
		ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		IContactGroupService service = (IContactGroupService) context.getBean("contactGroupService");
		List<ContactGroup> allGrp = service.findAll(acc);
		
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
		
		// récupération des éléments déjà renseignés (cas d'erreur)
		String firstname = request.getParameter("firstname")==null ? "" : request.getParameter("firstname");
		String lastname = request.getParameter("lastname")==null ? "" : request.getParameter("lastname");
		String email = request.getParameter("email")==null ? "" : request.getParameter("email");
		String siret = request.getParameter("siret")==null ? "" : request.getParameter("siret");
		
		String street = request.getParameter("street")==null ? "" : request.getParameter("street");
		String city = request.getParameter("city")==null ? "" : request.getParameter("city");
		String zip = request.getParameter("zip")==null ? "" : request.getParameter("zip");
		String country = request.getParameter("country")==null ? "" : request.getParameter("country");
	%>

	<div class="container col-xs-7">
		<form method=post action="NewContactServlet">
			<div class="from-group">
				<label for="firstname">First name</label> 
				<input type="text" class="form-control" name="firstname" value="<%=firstname %>"
					placeholder="First name">
			</div>
			<div class="from-group">
				<label for="lastname">Last name</label> 
				<input type="text" class="form-control" name="lastname" value="<%=lastname %>"
					placeholder="Last name">
			</div>
			<div class="from-group">
				<label for="email">Email address</label>
				<input type="email" class="form-control" name="email" value="<%=email %>"
					placeholder="Email">
			</div>
			<div class="from-group">
				<label for="siret">Siret number</label>
				<input type="number" class="form-control" name="siret" value="<%=siret %>"
					placeholder="(optionnal) Siret number">
			</div>
			
			<fieldset style="margin-top: 5%; margin-bottom: -2%;">
    			<legend style="color: #5826AB80;">Address</legend>
   			</fieldset>
			<div class="from-group">
				<label for="street">Street</label> 
				<input type="text" class="form-control" name="street" value="<%=street %>"
					placeholder="Street name">
			</div>
			<div class="from-group">
				<label for="city">City</label>
				<input type="text" class="form-control" name="city" value="<%=city %>"
					placeholder="City">
			</div>
			<div class="from-group">
				<label for="zip">Zip</label> 
				<input type="number" class="form-control" name="zip" value="<%=zip %>"
					placeholder="Zip">
			</div>
			<div class="from-group">
				<label for="country">Country</label> 
				<input type="text" class="form-control" name="country" value="<%=country %>"
					placeholder="City">
			</div>

			<fieldset style="margin-top: 5%;">
    			<legend style="color: #5826AB80;">Phones</legend>
   			</fieldset>

			<div class="phones">
				<div class="form-inline" style="margin-bottom:3px;">
					<div class="form-group">
						<div class="input-group">
							<input type="tel" class="form-control" name="tel1" id="tel1" maxlength="10" style="border-radius: 4px;">
						</div>
					</div>
				</div>
			</div>
			<button class="btn add_field_button">Add a phone</button> 
			
			<fieldset style="margin-top: 5%;">
    			<legend style="color: #5826AB80;">Contact groups</legend>
   			</fieldset>
   			<%
   				if(allGrp==null){ 
   			%>
   					<h3>Aucun groupe créer</h3>
   			<%
   				} else {
   					for(ContactGroup grp : allGrp){ %>
   						<div class="form-group">
						<div class="checkbox"><label> <input type="checkbox" name="grp<%=grp.getId_group()%>" 
							id="<%=grp.getId_group()%>" value="<%=grp.getId_group()%>"><%=grp.getGroupName() %></label></div>
						</div>
			<%
					}
				}
			%>
			
			<div class="from-group" style="margin-top:50px;">
				<button type="submit" class="btn btn-info">Submit</button>
				<button type="reset" class="btn btn-default">Reset</button>
			</div>
			
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
	
	<script>
	
	$(document).ready(function(){
		   
	    $( ".phones" ).keypress(function(e) {
	      if ( e.which == 13 ) {
	         e.preventDefault();
	      }
	    });
	    
	    var max_fields      = 10; //maximum input boxes allowed
	    var wrapper         = $(".phones"); //Fields wrapper
	    var add_button      = $(".add_field_button"); //Add button ID
	   
	    var x = 1; //initlal text box count
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