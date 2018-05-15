<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1" />
<meta charset="UTF-8">
<title>Update Product</title>
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
				<jsp:param name="page" value="updateProduct" />
			</jsp:include>
			<h2>Update Product</h2>

		</header>
		<main>
		<c:if test="${errors != null}">
			<div class="alert-danger">
				<ul>
					<c:forEach items="${errors}" var="error">
						<li>${error}</li>
					</c:forEach>
				</ul>
			</div>
		</c:if>

		<form method="post" action="Controller?action=updateProduct" novalidate="novalidate">
			<!-- novalidate in order to be able to run tests correctly -->
			<p>
				<label for="productid">Id</label><input type="number" id="productid"
					name="productid" readonly required value="<c:out value='${product.productId}' />" />
			</p>
			<p>
				<label for="name">Name</label><input type="text" id="name"
					name="name" required value="<c:out value='${product.name}'/>">
			</p>
			<p>
				<label for="description">Description</label><input type="text" id="description"
					name="description" required value="<c:out value='${product.description}'/>">
			</p>
			<p>
				<label for="price">Price</label><input type="number" id="price"
					name="price" required value="<c:out value='${product.price}'/>">
			</p>
			<p>
				<input type="submit" id="updateProduct" value="Update product">
			</p>

		</form>
		</main>
		<jsp:include page="fragments/footer.jsp">
			<jsp:param name="page" value="showUpdateProduct" />
		</jsp:include>
	</div>
</body>
</html>
