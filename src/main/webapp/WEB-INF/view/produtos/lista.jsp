<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html>
<head>
    <title>Livro de Java - Android - Iphone e muito mais</title>
</head>
<body>
<h1> Lista de produtos</h1>

<div>${sucesso}</div>
<table>
    <tr>
        <td>
            Título
        </td>
        <td>Descrição</td>
        <td>Páginas</td>

    </tr>
    <c:forEach items="${produtos}" varStatus="status" var="produto">
        <tr>
            <td><a href="${s:mvcUrl('PC#detalhe').arg(0, produto.id).build()}">${produto.titulo}</a></td>
            <td>${produto.descricao}</td>
            <td>${produto.paginas}</td>
        </tr>
    </c:forEach>

</table>
</body>
</html>
