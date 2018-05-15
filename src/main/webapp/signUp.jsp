<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1" />
<meta charset="UTF-8">
<title>Sign Up</title>
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
				<jsp:param name="page" value="signUp" />
			</jsp:include>
			<h2>Sign Up</h2>

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

		<form method="post" action="Controller?action=register" novalidate="novalidate">
			<!-- novalidate in order to be able to run tests correctly -->
			<p>
				<label for="userid">User id</label><input type="text" id="userid"
					name="userid" required value="<c:out value='${useridPreviousValue}'/>">
			</p>
			<p>
				<label for="firstName">First Name</label><input type="text"
					id="firstName" name="firstName" required value="<c:out value='${firstNamePreviousValue}' />">
			</p>
			<p>
				<label for="lastName">Last Name</label><input type="text"
					id="lastName" name="lastName" required value="<c:out value='${lastNamePreviousValue}'/>">
			</p>
			<p>
				<label for="email">Email</label><input type="email" id="email"
					name="email" required value="<c:out value='${emailPreviousValue}' />">
			</p>
			<p>
				<label for="password">Password</label><input type="password"
					id="password" name="password" required>
			</p>
			<p>
				<input type="submit" id="signUp" value="Sign Up">
			</p>

		</form>
		</main>
		<jsp:include page="fragments/footer.jsp">
			<jsp:param name="page" value="signUp" />
		</jsp:include>
	</div>
</body>
</html>
