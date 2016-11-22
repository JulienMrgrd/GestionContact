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
<link href="utils/general.css" rel="stylesheet">
</head>

<body>
	<div id="header"></div>
	
	<div class="container">
	
	<div class="row">
				<div class="col-md-4">
					<div class="thumbnail">
						<img alt="Bootstrap Thumbnail First" src="http://lorempixel.com/output/people-q-c-600-200-1.jpg" />
						<div class="caption">
							<h3>
								Avec GestionContact, c'est facile !
							</h3>
							<p>
								Gérer vos contacts, vos groupes, etc... sans efforts !
							</p>
						</div>
					</div>
				</div>
				<div class="col-md-4">
					<div class="thumbnail">
						<img alt="Bootstrap Thumbnail Second" src="http://lorempixel.com/output/city-q-c-600-200-1.jpg" />
						<div class="caption">
							<h3>
								Tout votre répertoire, stocké en ligne
							</h3>
							<p>
								Plus besoin de carnet, de papier que l'on perd en 2 minutes...
							</p>
						</div>
					</div>
				</div>
				<div class="col-md-4">
					<div class="thumbnail">
						<img alt="Bootstrap Thumbnail Third" src="http://lorempixel.com/output/sports-q-c-600-200-1.jpg" />
						<div class="caption">
							<h3>
								Et puis....
							</h3>
							<br>
							<p>
								c'est gratuit* ! Essayez-vite, le premier mois est offert !
							</p>
						</div>
					</div>
				</div>
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