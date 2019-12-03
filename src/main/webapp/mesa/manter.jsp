<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html lang="en">
	<head>
		<title>Gerenciador de Mesa</Title>
		<meta charset="UTF-8">
		<meta name= "viewport" content="width=device-width,initial-scale=1.0">
	</head>
	<body>
		<table>
			<c:if test="${mesa.idMesa != 0 }">
			
					<form action="altera" method="post">
					<h1>Alterar Mesa</h1>
						<tr>
							<td>Id:</td> 
							<td>${mesa.idMesa}</td>
						</tr>
					
					<input type="hidden" name="id" value="<c:out value='${mesa.idMesa}' />" >
				
			</c:if>
	
			<c:if test="${mesa.idMesa == 0}">
				<form action="adiciona" method="post">
			</c:if>		
		
			<tr>
				<td>Numero:</td>
				<td><input type="number" name="numero" /></td>
			</tr>
		
			<tr>
				<td>Disponibilidade:</td> 
				<td><input type="text" name="disponibilidade" /></td>
			</tr>
		
			<td><button type="submit">Gravar</button></td>
		</from>
	</body>
</html>