<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Plataformas</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
        crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
        crossorigin="anonymous"></script>
</head>

<body>
    <div class="container">
        <h1>Plataformas</h1>
        <a href="/plataformas/insert" class="btn btn">Nova Plataforma</a>
        <table class="table">
            <tr>
                <th>ID</th>
                <th>Nome</th>
                <th>&nbsp;</th>
            </tr>
            <c:forEach items="${plataformas}" var="plataforma">
                <tr>
                    <td>${plataforma.id}</td>
                    <td>${plataforma.nome}</td>
                    <td>
                        <a href="/plataformas/update?id=${plataforma.id}">Editar</a>
                        <a href="/plataformas/delete?id=${plataforma.id}">Excluir</a>
                    </td>
                </tr>
        </table>
    </div>
</body>

</html>