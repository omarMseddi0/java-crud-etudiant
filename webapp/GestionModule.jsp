<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Gestion Module</title>
</head>
<body>
    <h1>Gestion Module</h1>

    <c:if test="${not empty module}">
        <c:set var="action" value="update" />
        <c:set var="id" value="${module.id}" />
    </c:if>
    <c:if test="${empty module}">
        <c:set var="action" value="add" />
    </c:if>

    <form method="post" action="GestionModuleServlet">
        <input type="hidden" name="action" value="${action}">
        <c:if test="${not empty id}">
            <input type="hidden" name="id" value="${id}">
        </c:if>
        <div class="form-group">
                <label for="etudiant">Specialiter:</label>
                <select class="form-control" name="spesialiter" required>
                    <c:forEach var="specialiter" items="${specialiters}">
                        <option value="${spesialiter.idSpecialite}" >
                            ${spesialiter.nom} 
                        </option>
                    </c:forEach>
                </select>
            </div>
        <p>
            <label for="nom">Nom:</label>
            <input type="text" name="nom" value="${module.nom}" required>
        </p>
        <p>
            <label for="coeficient">Coefficient:</label>
            <input type="text" name="coeficient" value="${module.coeficient}" required>
        </p>
        <p>
            <input type="submit" value="${action == 'add' ? 'Ajouter' : 'Modifier'}">
        </p>
    </form>

    <h2>Liste des modules</h2>
    <table border="1">
        <tr>
            <th>Nom</th>
            <th>Coefficient</th>
            <th>Action</th>
        </tr>
        <c:forEach var="module" items="${modules}">
            <tr> <td>${module.specialiter.nom}</td>
                <td>${module.nom}</td>
                <td>${module.coeficient}</td>
                <td>
                    <a href="GestionModuleServlet?action=edit&id=${module.id}">Modifier</a>
                    <a href="GestionModuleServlet?action=delete&id=${module.id}">Supprimer</a>
                </td>
            </tr>
        </c:forEach>
    </table>

    <p><a href="GestionModuleServlet?action=add">Ajouter un module</a></p>

</body>
</html>
