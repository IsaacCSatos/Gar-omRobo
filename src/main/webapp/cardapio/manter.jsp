<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html lang="en">
	<head>
		<title>Gerenciador de Categoria</Title>
		<meta charset="UTF-8">
		<meta name= "viewport" content="width=device-width,initial-scale=1.0">
		<style>
			table, th, td {
  			border: 1px solid black;
			}
		</style>
	</head>
	<body>
		<table>
			<tr>
		        	<td>Nome</td>
		            <td>Valor</td>
		            <td>Pedir</td>
		    </tr>
			<c:forEach var="produto" items="${produtos}">
				<c:if test="${categoria.idCategoria == produto.categoria.getIdCategoria()}">
					<tr>
						<td>
							${produto.nome}
						</td>
						<td>
							${produto.valor}
						</td>
						<td>
		         			<a href="/cardapio/addproduto?id=<c:out value="${produto.idProduto}" />">Pedir</a>
		         		</td>	
		         	</tr>  
				</c:if>	
			</c:forEach>
		</table>
	</body>
</html>