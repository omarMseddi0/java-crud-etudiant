<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Gestion Etudiant</title>
</head>
<body>
    <h1>Gestion Etudiant</h1>

    <c:if test="${not empty etudiant}">
        <c:set var="action" value="update" />
        <c:set var="id" value="${etudiant.idEtudiant}" />
    </c:if>
    <c:if test="${empty etudiant}">
        <c:set var="action" value="add" />
    </c:if>

    <form method="post" action="${pageContext.request.contextPath}/GestionEtudiantServlet">
        <input type="hidden" name="action" value="${action}">
        <c:if test="${not empty id}">
            <input type="hidden" name="id" value="${id}">
        </c:if>
        <div class="form-group">
                <label for="etudiant">Specialiter:</label>
                <select class="form-control" name="specialiter" required>
                    <c:forEach var="specialite" items="${specialiter}">
                        <option value="${specialite.idSpecialite}" >
                            ${specialite.nomSpecialite} 
                        </option>
                    </c:forEach>
                </select>
            </div>
        <p>
            <label for="nom">Nom:</label>
            <input type="text" name="nom" value="" required>
        </p>
        <p>
            <label for="prenom">Prenom:</label>
            <input type="text" name="prenom" value="" required>
        </p>
        
        <p>
            <label for="motDePasse">Mot de passe:</label>
            <input type="password" name="motDePasse" value="" required>
        </p>
        <p>
            <input type="submit" value="${action == 'add' ? 'Ajouter' : 'Modifier'}">
        </p>
    </form>

    <h2>Liste des etudiants</h2>
    <table border="1">
        <tr>
            <th>Nom</th>
            <th>Prenom</th>
            
            <th>Action</th>
        </tr>
        <c:forEach var="etudiant" items="${etudiants}">
            <tr>
                <td>${etudiant.nom}</td>
                <td>${etudiant.prenom}</td>
                
                <td>
                    <a href="GestionEtudiantServlet?action=edit&id=${etudiant.idEtudiant}">Modifier</a>
                    <a href="GestionEtudiantServlet?action=delete&id=${etudiant.idEtudiant}">Supprimer</a>
                </td>
            </tr>
        </c:forEach>
    </table>

    <p><a href="GestionEtudiantServlet?action=add">Ajouter un etudiant</a></p>

</body>
</html>
