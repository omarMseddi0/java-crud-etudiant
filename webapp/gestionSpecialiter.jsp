<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Gestion Spécialité</title>
</head>
<body>
    <h1>Gestion Spécialité</h1>

    <c:if test="${not empty specialite}">
        <c:set var="action" value="update" />
        <c:set var="id" value="${specialite.id}" />
    </c:if>
    <c:if test="${empty specialite}">
        <c:set var="action" value="add" />
    </c:if>

    <form method="post" action="GestionSpecialiterServlet">
        <input type="hidden" name="action" value="${action}">
        <c:if test="${not empty id}">
            <input type="hidden" name="id" value="${id}">
        </c:if>
        <p>
            <label for="nom">Nom:</label>
            <input type="text" name="nom" value="${specialite.nom}" required>
        </p>
        <p>
            <input type="submit" value="${action == 'add' ? 'Ajouter' : 'Modifier'}">
        </p>
    </form>

    <h2>Liste des spécialités</h2>
    <table border="1">
        <tr>
            <th>Nom</th>
            <th>Action</th>
        </tr>
        <c:forEach var="specialite" items="${specialites}">
            <tr>
                <td>${specialite.nom}</td>
                <td>
                    <a href="GestionSpecialiterServlet?action=edit&id=${specialite.id}">Modifier</a>
                    <a href="GestionSpecialiterServlet?action=delete&id=${specialite.id}">Supprimer</a>
                </td>
            </tr>
        </c:forEach>
    </table>

    <p><a href="GestionSpecialiterServlet?action=add">Ajouter une spécialité</a></p>

</body>
</html>
