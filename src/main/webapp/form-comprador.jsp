<jsp:directive.page contentType="text/html; charset=UTF-8" />
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="pt-br">
	<head>
		<%@include file="base-head.jsp"%>
	</head>
	<body>
		<%@include file="nav-menu.jsp"%>
			
		<div id="container" class="container-fluid">
			<h3 class="page-header">Adicionar Comprador</h3>

			<form action="${pageContext.request.contextPath}/compradores/${action}" method="POST">
				<input type="hidden" value="${comprador.getId()}" name="compradorId">
				<div class="row">
					<div class="form-group col-md-4">
					<label for="name">Nome</label>
						<input type="text" class="form-control" id="name" name="name" 
							   autofocus="autofocus" placeholder="Nome do comprador" 
							   required oninvalid="this.setCustomValidity('Por favor, informe o nome do comprador.')"
							   oninput="setCustomValidity('')"
							   value="${comprador.getName()}">
					</div>

					<div class="form-group col-md-4">
						<label for="address">Endereço</label>
						<input type="text" class="form-control" id="address" name="address" 
							   autofocus="autofocus" placeholder="Endereço do comprador" 
							   required oninvalid="this.setCustomValidity('Por favor, informe o Endereço do comprador.')"
							   oninput="setCustomValidity('')"
							   value="${comprador.getAddress()}">
					</div>
					
					<div class="form-group col-md-4">
					<label for="mail">E-mail</label>
						<input type="email" class="form-control" id="mail" name="mail" 
							   autofocus="autofocus" placeholder="E-mail do comprador" 
							   required oninvalid="this.setCustomValidity('Por favor, informe o email do comprador.')"
							   oninput="setCustomValidity('')"
							   value="${comprador.getEmail()}">
					</div>
				</div>
				<hr />
				<div id="actions" class="row pull-right">
					<div class="col-md-12">
						<a href="${pageContext.request.contextPath}/compradores" class="btn btn-default">Cancelar</a>
						<button type="submit" class="btn btn-primary">${not empty comprador ? "Alterar Comprador" : "Cadastrar Comprador"}</button>
					</div>
				</div>
			</form>
		</div>

		<script src="js/jquery.min.js"></script>
		<script src="js/bootstrap.min.js"></script>
	</body>
</html>
