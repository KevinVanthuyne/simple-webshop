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
			<h2>Check Password</h2>

		</header>
		<main>
			<c:if test="${message != null}">
				<div class="alert-danger">
					<p><c:out value="${message}" /></p>
				</div>
			</c:if>
			<p>Fill in your password:</p>
			<form action="Controller?action=checkPassword&id=${id}" method="post">
				<input type="text" name="password" id="password" />
				<input type="submit" value="check" />
			</form>
		</main>
		<jsp:include page="fragments/footer.jsp">
			<jsp:param name="page" value="checkPassword" />
		</jsp:include>
	</div>
</body>
</html>