<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Gestion Note</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
    integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
    <div class="container">
        <h1>Gestion Note</h1>

        <c:if test="${not empty note}">
            <c:set var="action" value="update" />
            <c:set var="id" value="${note.idNote}" />
        </c:if>
        <c:if test="${empty note}">
            <c:set var="action" value="add" />
        </c:if>

        <form method="post" action="${pageContext.request.contextPath}/GestionNoteServlet">
            <input type="hidden" name="action" value="${action}">
            <c:if test="${not empty id}">
                <input type="hidden" name="id" value="${id}">
            </c:if>
            <div class="form-group">
                <label for="etudiant">Specialiter:</label>
                <select class="form-control" name="spesialiter" required>
                    <c:forEach var="specialiter" items="${specialiters}">
                        <option value="${spesialiter.idSpecialite}" ${note.module.specialiter.idSpecialite == specialiter.idSpecialite ? 'selected' : ''}>
                            ${spesialiter.nomSpecialite} 
                        </option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group">
                <label for="etudiant">Etudiant:</label>
                <select class="form-control" name="etudiant" required>
                    <c:forEach var="etudiant" items="${etudiants}">
                        <option  value="${etudiant.id}" ${note.etudiant.idEtudiant == etudiant.idEtudiant ? 'selected' : ''} >
                            ${etudiant.nom} ${etudiant.prenom}
                        </option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group">
                <label for="module">Module:</label>
                <select class="form-control" name="module" required>
                    <c:forEach var="module" items="${modules}">
                        <option value="${module.id}" ${note.module.id_Module == module.id_Module ? 'selected' : ''} >
                            ${module.nom}
                        </option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group">
                <label for="note">Note:</label>
                <input type="number" name="note" value="${note.note}" class="form-control" min="0" max="20" step="0.1"
                    required>
            </div>
            <div class="form-group">
                <input type="submit" value="${action == 'add' ? 'Ajouter' : 'Modifier'}" class="btn btn-primary">
            </div>
        </form>
        

        <h2>Liste des notes</h2>
        <table class="table">
            <thead>
                <tr>
                    <th>Etudiant</th>
                    <th>Module</th>
                    <th>Note</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="note" items="${notes}">
                    <tr>
                       <td>   ${note.module.specialiter.nom}   </td>
                        <td>${note.etudiant.nom} ${note.etudiant.prenom}</td>
                        <td>${note.module.nom}</td>
                        <td>${note.note}</td>
                         <td>
                    <a href="GestionNoteServlet?action=edit&id=${note.id}">Modifier</a>
                    <a href="GestionNoteServlet?action=delete&id=${note.id}" >Supprimer</a>
                    </td>
                   </tr>
                   </c:forEach>
                   </tbody>
</table>
<p><a href="GestionNoteServlet?action=add">Ajouter une note</a></p>
</body>
</html>
