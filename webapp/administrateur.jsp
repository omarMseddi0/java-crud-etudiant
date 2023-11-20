<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
 <%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin Panel</title>
</head>
<body>
<%
String username = (String) request.getSession().getAttribute("username");
String password = (String) request.getSession().getAttribute("password");
%>
    <h1>Welcome to Admin Panel <%= username %></h1>
    <p>Please select an option below:</p>
    <form action="admin" method="post">
        <button type="submit" name="action" value="gestionEtudiant">Gestion Etudiant</button>
        <button type="submit" name="action" value="gestionModule">Gestion Module</button>
        <button type="submit" name="action" value="gestionSpecialite">Gestion Specialite</button>
        <button type="submit" name="action" value="gestionNote">Gestion Note</button>
    </form>
</body>
</html>