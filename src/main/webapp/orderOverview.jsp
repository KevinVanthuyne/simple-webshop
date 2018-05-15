<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>Home</title>
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
				<jsp:param name="page" value="shoppingCart" />
			</jsp:include>
			<h2>Order overview</h2>

		</header>
		<main> 
			<c:if test="${shoppingCartSize != 0}">
				<p>You can find the list of products you want to order below. 
				Please check if all products and their quantities are correct before continuing with the payment.</p>
				<table class="productOverview">
					<tr>
						<th>Name</th>
						<th>Description</th>
						<th>Price</th>
						<th>Quantity</th>
						<th>Total</th>
					</tr>
					<c:forEach var="item" items="${shoppingCartItems}">
					<tr>
						<td><c:out value="${item.product.name}"/></td>
						<td><c:out value="${item.product.description}"/></td>
						<td><c:out value="${item.product.price}"/></td>
						<td><c:out value="${item.quantity}"/></td>
						<td><c:out value="${item.quantity * item.product.price}"/></td>
					</tr>
					</c:forEach>
					<tr>
						<td></td>
						<td></td>
						<td></td>
						<td>Total:</td>
						<td><c:out value="${shoppingCartTotal}" /></td>
					</tr>
				</table>
				<form action="Controller?action=confirmOrder" method="post">
					<input type="submit" value="Pay now" />
				</form>
			</c:if>
		</main>
		<jsp:include page="fragments/footer.jsp">
			<jsp:param name="page" value="showOrderOverview" />
		</jsp:include>
	</div>
</body>
</html>