<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html lang="en">
	<head>
		<title>Gerenciador de Pedido</Title>
		<meta charset="UTF-8">
		<meta name= "viewport" content="width=device-width,initial-scale=1.0">
	</head>
	<body>
		<table>
			<c:if test="${produtosPedido.idProdutosPedido != 0 }">
			
					<form action="altera" method="post">
					<h1>Alterar Produtos Pedido</h1>
						<tr>
							<td>Id:</td> 
							<td>${produtosPedido.idProdutosPedido}</td>
						</tr>
					
					<input type="hidden" name="id" value="<c:out value='${produtosPedido.idProdutosPedido}' />" >
				
			</c:if>
	
			<c:if test="${produtosPedido.idProdutosPedido == 0}">
				<h1>Cadastro</h1>
				<form action="adiciona" method="post">
			</c:if>		
			<tr>
				<td>Quantidade:</td>
				<td><input type="text" name="quantidade" /></td>
			</tr>
			<tr>
				<td>Valor:</td>
				<td><input type="text" name="valorUnidade" /></td>
			</tr>
			<tr>
				<td>Obeservacao:</td>
				<td><input type="text" name="obeservacao" /></td>
			</tr>
			
			<tr>
				<td><br>Pedido:</td>
				<td><input list="listarpedido" name="idPedido"></td>
  					<datalist id="listarpedido">
			  	 			<c:forEach var="pedido"   items="${pedidos}" >
        							<option value="${pedido.idPedido}">
     						</c:forEach>
    					</datalist>
			</tr>
			
			<tr>
				<td><br>Produto:</td>
				<td><input list="listarproduto" name="idProduto"></td>
  					<datalist id="listarproduto">
			  	 		<c:forEach var="produto"	items="${produtos}" >
        						<option value="${produto.idProduto}">${produto.nome}</option>
     					</c:forEach>
    				</datalist>
			</tr>
		
			<td><button type="submit">Gravar</button></td>
		</from>
	</body>
</html>