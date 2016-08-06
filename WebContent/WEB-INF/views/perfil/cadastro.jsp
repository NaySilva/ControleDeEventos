<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h3>Adicionar perfil</h3>
	<form:errors path="perfil.descricao"/>
	<form action="adicionaPerfil" method="post">
		Descrição: <input type="text" name="descricao"><br />
		<input type="submit" value="Adicionar">
	</form>

</body>
</html>