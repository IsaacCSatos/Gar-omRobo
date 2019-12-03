<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="en">
	<head>
	
		<title>Gerenciado de Mesa</Title>
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
	
			<h1>Mesa</h1>
			<a href="/mesa/nova">Nova</a>
			<table id="example" class="table" style="width:100%">
		        <thead>
		            <tr>
		                <td>ID</td>
		                <td>Numero</th>
		                <td>Disponibilidade</td>
		                <td>Excluida</td>
		                <td>Acoes</td>
		            </tr>
		        </thead>
					<c:forEach var="mesa" items="${mesas}">
						<c:if test="${mesa.excluida == false}">
							<tr>
								<td>
									${mesa.idMesa}
								</td>
								<td>
									${mesa.numero}
								</td>
								<td>
									${mesa.disponibilidade}
								</td>
								<td>
									${mesa.excluida}
								</td>
								<td>
									<a href="/mesa/edita?id=<c:out value='${mesa.idMesa}'/>" class="btn btn-labeled btn-warning" role="button"><span class="btn-label"><i class="fas fa-edit"></i></span>Edita </a>
									<a href="/mesa/excluir?id=<c:out value='${mesa.idMesa}'/>" class="btn btn btn-labeled btn-danger" role="button"><span class="btn-label"><i class="fas fa-edit"></i></span> Remove</a>							
								</td>
		            		</tr>  
						</c:if>
					</c:forEach>
			</table>
	</body>
</html>