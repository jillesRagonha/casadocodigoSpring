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
<form:form action="${s:mvcUrl('PC#grava').build()}" method="post" commandName="produto" enctype="multipart/form-data">
    <div>
        <label>Título</label>
        <form:input path="titulo" />
        <form:errors path="titulo"/>
    </div>
    <div>
        <label>Descrição</label>
        <form:textarea path="descricao" cols="20" rows="10" />
        <form:errors path="descricao"/>

    </div>
    <div>
        <label>Páginas</label>
        <form:input path="paginas" />
        <form:errors path="paginas"/>

    </div>
    <div>
        <label>Lançamento</label>
        <form:input path="dataLancamento" />
        <form:errors path="dataLancamento"/>

    </div>
    <c:forEach items="${tipos}" var="tipoPreco" varStatus="status">
        <div>
            <label>${tipoPreco}</label>
            <form:input path="precos[${status.index}].valor" />
            <form:hidden path="precos[${status.index}].tipo" value="${tipoPreco}" />
        </div>
    </c:forEach>
    <label>Sumário</label>
    <input type="file" name="sumario"/>

    <button type="submit"> Cadastrar</button>


</form:form>

</body>
</html>
