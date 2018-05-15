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
				<jsp:param name="page" value="index" />
			</jsp:include>
			<h2>Home</h2>

		</header>
		<main> 
			<c:if test="${message != null}">
				<p style="color: red"><c:out value="${message}"></c:out></p>
			</c:if>
			<p>Welcome to <em>K22 Music Store</em>, the biggest online webshop for buying musical equipment.
			Our store has more than 1.500 products of more than 20 different companies like 
			Pioneer, Sennheiser, Beyerdynamic, Reloop, American DJ, Ayra and many more!</p>
			<p>We strive to offer our customers the lowest price and the highest quality.
			If you come across a shop that sells the same product cheaper, let us know so we can cut our prices.</p>
			<p>K22 Music Store is based in Belgium and we offer free shipping when an order costs more than €50. 
			Orders placed before 22:00 are delivered to your address the next day.</p>
			<p>If you haven't yet, log in below to start browsing our catalog.</p>

			<form action="Controller?action=login" method="post">
				<fieldset>
					<legend>Login</legend>
					<c:if test="${error != null}">
						<div class="alert-danger">
							<p><c:out value="${error}"></c:out></p>
						</div>
					</c:if>
					<c:choose>
						<c:when test="${userid == null}">
							<p>Don't have an account yet? <a href="Controller?action=signUp">Sign up here.</a></p>
							<p>
								<label for="userid">User id:</label><input type="text" name="userid" id="userid"/>
							</p>
							<p>
								<label for="password">Password:</label><input type="password" name="password" id="password"/>
							</p>
							<input type="submit" name="type" value="Login" />
						</c:when>
						<c:otherwise>
							<p>Welcome <c:out value="${firstName}!" /></p>
							<input type="submit" name="type" value="Log out" />
						</c:otherwise>
					</c:choose>
					
				</fieldset>
			</form>
		</main>
		<jsp:include page="fragments/footer.jsp">
			<jsp:param name="page" value="index" />
		</jsp:include>
	</div>
</body>
</html>