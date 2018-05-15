<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1" />
<meta charset="UTF-8">
<title>Overview</title>
<link rel="stylesheet" type="text/css" href="<c:out value='${stylesheet}' />">
<link href="https://fonts.googleapis.com/css?family=Oswald|Raleway" rel="stylesheet">
</head>
<body>
	<div id="container">
		<header>
			<h1>
				<span>Web shop</span>
			</h1>
			<jsp:include page="fragments/navigation.jsp">
				<jsp:param name="page" value="productOverview" />
			</jsp:include>
			<h2>Product Overview</h2>

		</header>
		<main>
		<p><a href="Controller?action=showShoppingCart">Shopping cart (<c:out value="${shoppingCartSize}" />)</a></p>
		<table class="productOverview">
			<tr>
				<th>ID</th>
				<th>Name</th>
				<th>Description</th>
				<th>Price</th>
				<th>Purchase</th>
				<c:if test="${role == 'ADMINISTRATOR'}">
					<th>Delete</th>
				</c:if>
			</tr>
			<c:forEach var="product" items="${producten}">
				<tr>
					<td><c:out value="${product.productId}"/></td>
					<td>
						<c:if test="${role == 'ADMINISTRATOR'}">
							<a href="Controller?action=showUpdateProduct&id=${product.productId}">
						</c:if>
						<c:out value="${product.name}"/>
						<c:if test="${role == 'ADMINISTRATOR'}">
							</a>
						</c:if>
					</td>
					<td><c:out value="${product.description}"/></td>
					<td><c:out value="${product.price}"/></td>
					<td>
						<form action="Controller?action=addProductToCart" method="post">
							<input type="hidden" name="productId" value="${product.productId}" />
							<input type="number" name="amount" value="1"/>
							<input type="submit" value="Add to cart" />
						</form>
					</td>
					<c:if test="${role == 'ADMINISTRATOR'}">
						<td><a href="Controller?action=confirmDeleteProduct&id=${product.productId}">x</a></td>
					</c:if>
				</tr>
			</c:forEach>

			<caption>Product Overview</caption>
		</table>
		</main>
		<jsp:include page="fragments/footer.jsp">
			<jsp:param name="page" value="productOverview" />
		</jsp:include>
	</div>
</body>
</html>