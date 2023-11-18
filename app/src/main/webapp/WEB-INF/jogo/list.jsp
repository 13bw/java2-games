<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Jogos</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
    integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">

</head>
<body>
<div class="container">
    <h1>Jogos</h1>
    <a href="/jogo/insert" class="btn btn-primary">Novo Jogo</a>
    <table class="table">
        <tr>
            <th>ID</th>
            <th>Titulo</th>
            <th>Categorias</th>
            <th>Plataformas</th>
            <th>&nbsp;</th>
        </tr>
        <c:forEach items="${jogos}" var="item">
            <tr>
                <td>${item.id}</td>
                <td>${item.titulo}</td>
                <td>${item.categoria.nome}</td>
            <td>
                <c:forEach items="${item.plataformas}" var="p" varStatus="s">
                    ${s.getCount() > 1? ", " : ""}
                    ${p.nome}
                </c:forEach>
            </td>
                <td>
                    <a href="/jogo/update?id=${item.id}" class="btn btn-primary">Editar</a>
                    <a href="/jogo/delete?id=${item.id}" class="btn btn-danger">Excluir</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>