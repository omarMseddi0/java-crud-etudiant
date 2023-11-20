<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
     <%@page import="java.util.List"%>
     <%@page import="yekhdem.Note"%>
      <%@page import="yekhdem.NoteDAO"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Espace Etudiant</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
   <%
String username = (String) request.getSession().getAttribute("username");
String password = (String) request.getSession().getAttribute("password");
%>
	<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="#">Espace Etudiant</a>
			</div>
			<ul class="nav navbar-nav">
				<li class="active"><a href="#">Accueil</a></li>
				<li><a href="#">Notes</a></li>
				<li><a href="#">Absences</a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="#"><span class="glyphicon glyphicon-user"></span> Mon Compte</a></li>
				<li><a href="logout.jsp"><span class="glyphicon glyphicon-log-out"></span> Déconnexion</a></li>
			</ul>
		</div>
	</nav>

	<div class="container">
		<h3>Bienvenue <%= username %> </h3>
		<br>

		<table class="table table-striped">
			<thead>
				<tr>
					<th>Module</th>
					<th>Note</th>
					<th>Absences</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="module" items="${modules}">
					<tr>
						<td>${module.nom}</td>
						<c:set var="note" value="${Beane.getNotesByModuleAndEtudiant(module, etudiant).stream().findFirst().orElse(null)}" />
						<td>${note != null ? note.getNote() : "-"}</td>
						
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

</body>
</html>
