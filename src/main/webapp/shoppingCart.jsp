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
			<h2>Shopping Cart</h2>

		</header>
		<main> 
			<c:if test="${shoppingCartMessage != null}">
				<p><c:out value="${shoppingCartMessage}"/></p>
			</c:if>
			<c:if test="${shoppingCartSize != null && shoppingCartSize != 0}">
				<table class="productOverview">
					<tr>
						<th>Name</th>
						<th>Description</th>
						<th>Price</th>
						<th>Quantity</th>
						<th>Remove</th>
					</tr>
					<c:forEach var="item" items="${shoppingCartItems}">
					<tr>
						<td><c:out value="${item.product.name}"/></td>
						<td><c:out value="${item.product.description}"/></td>
						<td><c:out value="${item.product.price}"/></td>
						<td>
							<form action="Controller?action=changeQuantityOfShoppingCartItem" method="post">
								<input type="hidden" name="productId" value="<c:out value="${item.product.productId}"/>" />
								<input type="number" name="quantity" value="<c:out value="${item.quantity}"/>" />
								<input type="submit" value="change" />
							</form>
						</td>
						<td>
							<form action="Controller?action=deleteItemFromCart" method="post">
								<input type="hidden" name="productId" value="<c:out value="${item.product.productId}"/>" />
								<input type="submit" value="Remove" />
							</form>
						</td>
					</tr>
					</c:forEach>
				</table>
				<h3>Total: <c:out value="${shoppingCartTotal}" /></h3>
				<form action="Controller?action=showOrderOverview" method="post">
					<input type="submit" value="Order now" />
				</form>
			</c:if>
		</main>
		<jsp:include page="fragments/footer.jsp">
			<jsp:param name="page" value="showShoppingCart" />
		</jsp:include>
	</div>
</body>
</html>