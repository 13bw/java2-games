<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Plataformas</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
    integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">

</head>

<body>
<div class="container">
    <h1>Plataformas</h1>
    <a href="/plataforma/insert" class="btn btn-primary">Nova Plataforma</a>
    <table class="table">
        <tr>
            <th>ID</th>
            <th>Nome</th>
            <th>&nbsp;</th>
        </tr>
        <c:forEach items="${plataformas}" var="item">
            <tr>
                <td>${item.id}</td>
                <td>${item.nome}</td>
                <td>
                    <a href="/plataforma/update?id=${item.id}">Editar</a>
                    <a href="/plataforma/delete?id=${item.id}">Excluir</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>

</html>