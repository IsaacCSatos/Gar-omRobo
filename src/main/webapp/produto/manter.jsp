<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html lang="en">
	<head>
		<title>Gerenciador de Produto</Title>
		<meta charset="UTF-8">
		<meta name= "viewport" content="width=device-width,initial-scale=1.0">
	</head>
	<body>
		<table>
			<c:if test="${produto.idProduto != 0 }">
			
					<form action="altera" method="post">
					<h1>Alterar Produto</h1>
						<tr>
							<td>Id:</td> 
							<td>${produto.idProduto}</td>
						</tr>
					
					<input type="hidden" name="id" value="<c:out value='${produto.idProduto}' />" >
				
			</c:if>
	
			<c:if test="${produto.idProduto == 0}">
				<h1>Cadastro</h1>
				<form action="adiciona" method="post">
			</c:if>		
		
			<tr>
				<td>Nome:</td>
				<td><input type="text" name="nome" /></td>
			</tr>
			
			<tr>
				<td>Valor:</th>
				<td><input type="text" name="valor" /></td>
			</tr>
			
			<tr>
				<td>Quantidade no Estoque:</td>
				<td><input type="text" name="qtdestoque" /></td>
			</tr>
			
			<tr>
				<td>Categoria</td>
  				<td><input list="listarcategoria" name="idCategoria"></td>
  					<datalist id="listarcategoria">
			  	 			<c:forEach var="categoria"   items="${categorias}" >
			  	 				<c:if test="${categoria.excluida == false}">
        							<option value="${categoria.idCategoria}">${categoria.nome}</option>
        						</c:if>
     						</c:forEach>
    					</datalist>
			</tr>
		
			<td><button type="submit">Gravar</button></td>
		</from>
	</body>
</html>