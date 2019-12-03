<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html lang="en">
	<head>
		<title>Gerenciador de Categoria</Title>
		<meta charset="UTF-8">
		<meta name= "viewport" content="width=device-width,initial-scale=1.0">
	</head>
	<body>
		<table>
			<c:if test="${categoria.idCategoria != 0 }">
			
					<form action="altera" method="post">
					<h1>Alterar Categoria</h1>
						<tr>
							<td>Id:</td> 
							<td>${categoria.idCategoria}</td>
						</tr>
					
					<input type="hidden" name="id" value="<c:out value='${categoria.idCategoria}' />" >
				
			</c:if>
	
			<c:if test="${categoria.idCategoria == 0}">
				<h1>Cadastro</h1>
				<form action="adiciona" method="post">
			</c:if>		
		
			<tr>
				<td>Nome:</td>
				<td><input type="text" name="nome" /></td>
			</tr>
			
			<td><button type="submit">Gravar</button></td>
		</from>
	</body>
</html>