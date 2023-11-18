<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib uri="jakarta.tags.core" prefix="c" %>

<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport"
        content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <title>Editar Jogo</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
        crossorigin="anonymous">
</head>

<body>
    <div class="container">
        <h1>Editar Jogo</h1>
        <form action="/jogo/update" method="post">
            <input type="hidden" name="id" value="${jogo.id}">
            <div class="form-group">
                <label for="titulo">Titulo</label>
                <input type="text" class="form-control" id="titulo" name="titulo" value="${jogo.titulo}">
            </div>
            <div class="form-group">
                <label for="categoria">Categoria</label>
                <select name="categoria" id="" class="form-select">
                    <c:forEach var="c" items="${categorias}">
                        <option value="${c.id}" ${jogo.categoria.id==c.id ? 'selected' : '' }>${c.nome}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group">
                <label for="plataforma">Plataforma(s):</label>
                <c:forEach var="p" items="${plataformas}">
                    <div class="custom-control custom-checkbox">
                        <input type="checkbox" class="custom-control-input" id="${p.id}" name="plataformas"
                            value="${p.id}" ${jogo.plataformas.contains(p) ? 'checked' : '' }>
                        <label class="custom-control-label" for="${p.id}">${p.nome}</label>
                    </div>
                </c:forEach>
            </div>
            <br>
            <a href="/jogo/list" class="btn btn-primary">Voltar</a>
            <button type="submit" class="btn btn-primary">Salvar</button>
        </form>
    </div>
</body>
</html>
