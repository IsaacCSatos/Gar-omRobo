<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<meta name="viewport"
			content="width=device-width, initial-scale=1, shrink-to-fit=no">
		<meta name="description" content="">
		<meta name="author"
			content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
			
		<title>Lista Mesas</title>
		
		<!-- Bootstrap core CSS -->
		<link href="/webjars/bootstrap/4.3.1/css/bootstrap.min.css"
			rel="stylesheet" />
		
		
		<style>
		.bd-placeholder-img {
			font-size: 1.125rem;
			text-anchor: middle;
			-webkit-user-select: none;
			-moz-user-select: none;
			-ms-user-select: none;
			user-select: none;
		}
		
		@media ( min-width : 768px) {
			.bd-placeholder-img-lg {
				font-size: 3.5rem;
			}
		}
		</style>
	</head>
	<body>
		<div
			class="d-flex flex-column flex-md-row align-items-center p-3 px-md-4 mb-3 bg-white border-bottom shadow-sm">
			<h5 class="my-0 mr-md-auto font-weight-normal">Company name</h5>
			<nav class="my-2 my-md-0 mr-md-3">
				<a class="p-2 text-dark" href="#">Features</a> <a
					class="p-2 text-dark" href="#">Enterprise</a> <a
					class="p-2 text-dark" href="#">Support</a> <a class="p-2 text-dark"
					href="#">Pricing</a>
			</nav>
			<a class="btn btn-outline-primary" href="#">Sign up</a>
		</div>
----------------------------------------------------------------------------------------------------------
		<div class="container">
			<h1 class="display-4">Mesas</h1>
			<table id="example" class="table table-striped table-bordered" style="width:100%">
			
			<a href="/mesa/adiciona.jsp" class="btn btn-labeled btn-success" role="button"><i class="fas fa-plus-square"></i> Nova</a>
				
		        <thead>
		            <tr>
		                <th>Código</th>
		                <th>Numero</th>
		                <th>Disponibilidade</th>
		            </tr>
		        </thead>
		     
		        <tbody>
		        
					<c:forEach var="mesa" items="${mesas}">
					<tr>
						<td>${mesa.idMesa}</td>
						<td>${mesa.numero}</td>
						<td>${mesa.disponibilidade}</td>
					</tr>  
					</c:forEach>

	            </tbody>

			</table>
		</div>
	-------------------------------------------------------------------------------------------------------------   
		<div class="container">
			<footer class="pt-4 my-md-5 pt-md-5 border-top">
				<div class="row">
					<div class="col-12 col-md">
							<small
							class="d-block mb-3 text-muted">&copy; 2017-2019</small>
				</div>
			</footer>
		</div>
	</body>
</html>