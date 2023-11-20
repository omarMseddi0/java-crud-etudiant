<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>




<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Login</title>

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	/>

<!-- Custom CSS -->
<style>
/* Custom styles */
header {
	background-color: #007bff;
}

.navbar-brand {
	color: #fff;
	font-size: 24px;
	font-weight: bold;
}

.nav-link {
	color: #fff;
}

footer {
	background-color: #f8f9fa;
	padding: 20px 0;
}

.legal-info {
	font-size: 14px;
	color: #999;
	margin-bottom: 10px;
}

.policy {
	color: #007bff;
	font-weight: bold;
}

.form-container {
	border: 1px solid #ddd;
	padding: 20px;
}

.form-group label {
	font-weight: bold;
}

</style>
</head>
<body>

<!-- Navigation -->
<header>
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<a class="navbar-brand" href="#">Université</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarNav" aria-controls="navbarNav"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarNav">
			<ul class="navbar-nav ml-auto">
				<li class="nav-item active"><a class="nav-link"
					href="#">Actualités</a></li>
				<li class="nav-item"><a class="nav-link" href="#">Contact</a></li>
				<li class="nav-item"><a class="nav-link" href="#">Login</a></li>
			</ul>
		</div>
	</nav>
</header>

<!-- Main content -->
<main class="container mt-5">
	<h1>Connexion</h1>
	<div class="row">
		<div class="col-md-6">
			<form action="${pageContext.request.contextPath}/login" method="POST" class="form-container">

				<div class="form-group">
					<label for="username">Nom d'utilisateur</label> <input
						type="text" name="username" id="username" class="form-control"
						required>
				</div>
				<div class="form-group">
					<label for="password">Mot de passe</label> <input
						type="password" name="password" id="password" class="form-control"
						required>
				</div>
				<button type="submit" class="btn btn-primary">Se connecter</button>
			</form>
		</div>
	</div>
	</main>
	<c:if test="${param.error eq '1'}">
    <p class="text-danger">Invalid username or password. Please try again.</p>
</c:if>