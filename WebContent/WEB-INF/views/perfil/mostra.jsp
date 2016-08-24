<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
	<h3>Alterar perfil - ${perfil.id}</h3>
	<form action="alteraPerfil" method="post">
		<input type="hidden" name="id" value="${perfil.id}"/>
		Descrição: <input type="text" name="descricao"><br />
		${perfil.usuario}<input type="hidden" name="usuario" value="${perfil.usuario}">
		<input type="submit" value="Alterar"/>
	</form>
</body>
</html>