<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author"
	content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
<title>Editoria</title>

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

	<div class="container">
		<h1 class="display-4">Inclusão de Editoria</h1>
		<c:if test="${editoria != null}">
			<form action="altera" method="post">
				<input type="hidden" name="id" value="<c:out value='${editoria.idEditoria}' />" >
		</c:if>
		<c:if test="${editoria == null}">
			<form action="adiciona" method="post">
		</c:if>		
				<div class="form-group row">
					<label for="inputPassword" class="col-sm-2 col-form-label">Editoria</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" name="nome" id="inputNome" value="<c:out value='${editoria.nome}'/>"
							placeholder="Editoria">
					</div>
				</div>
				<button type="submit" class="btn btn-primary mb-2">Gravar</button>
			</form>
	</div>

	<div class="container">
		<footer class="pt-4 my-md-5 pt-md-5 border-top">
			<div class="row">
				<div class="col-12 col-md">
					<small class="d-block mb-3 text-muted">&copy; 2017-2019</small>
				</div>
				<div class="col-6 col-md">
					<h5>Features</h5>
					<ul class="list-unstyled text-small">
						<li><a class="text-muted" href="#">Cool stuff</a></li>
						<li><a class="text-muted" href="#">Random feature</a></li>
						<li><a class="text-muted" href="#">Team feature</a></li>
						<li><a class="text-muted" href="#">Stuff for developers</a></li>
						<li><a class="text-muted" href="#">Another one</a></li>
						<li><a class="text-muted" href="#">Last time</a></li>
					</ul>
				</div>
				<div class="col-6 col-md">
					<h5>Resources</h5>
					<ul class="list-unstyled text-small">
						<li><a class="text-muted" href="#">Resource</a></li>
						<li><a class="text-muted" href="#">Resource name</a></li>
						<li><a class="text-muted" href="#">Another resource</a></li>
						<li><a class="text-muted" href="#">Final resource</a></li>
					</ul>
				</div>
				<div class="col-6 col-md">
					<h5>About</h5>
					<ul class="list-unstyled text-small">
						<li><a class="text-muted" href="#">Team</a></li>
						<li><a class="text-muted" href="#">Locations</a></li>
						<li><a class="text-muted" href="#">Privacy</a></li>
						<li><a class="text-muted" href="#">Terms</a></li>
					</ul>
				</div>
			</div>
		</footer>
	</div>
</body>
</html>