<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="en">
	<head>
	
		<title>Gerenciado de Categoria</Title>
		<meta charset="UTF-8">
		<meta name= "viewport" content="width=device-width,initial-scale=1.0">
		<style>
			table, th, td {
  			border: 1px solid black;
			}
		</style>
		
	</head>
	<body>
				<a href="/mesa">Mesa</a>
				<a href="/categoria">Categoria</a> 
				<a href="/pedido">Pedido</a>
				<a href="/produto">Produto</a>
				<a href="/produtosPedido">ProdutosPedido</a>
		</div>
	
			<h1>Categoria</h1>
			<a href="/categoria/nova">Nova</a>
			<table id="example" class="table table-striped table-bordered" style="width:100%">
		        <thead>
		            <tr>
		                <td>Id</td>
		                <td>Nome</td>
		                <td>excluida</td>
		                <td>Acoes</td>
		            </tr>
		        </thead>
					<c:forEach var="categoria" items="${categorias}">
						<c:if test="${categoria.excluida == false}">
							<tr>
								<td>
									${categoria.idCategoria}
								</td>
								<td>
									${categoria.nome}
								</td>
								<td>
									${categoria.excluida}
								</td>
								<td>
									<a href="/categoria/edita?id=<c:out value='${categoria.idCategoria}'/>" class="btn btn-labeled btn-warning" role="button"><span class="btn-label"><i class="fas fa-edit"></i></span>Edita </a>
								</td>	
								<td>
									<a href="/categoria/excluir?id=<c:out value='${categoria.idCategoria}'/>" class="btn btn btn-labeled btn-danger" role="button"><span class="btn-label"><i class="fas fa-edit"></i></span> Remove</a>							
								</td>	
		            		</tr>  
		            	</c:if>
					</c:forEach>
			</table>
	</body>
</html>