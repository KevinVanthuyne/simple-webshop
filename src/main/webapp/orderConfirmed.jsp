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
			<jsp:include page="fragments/navigation.jsp" />
			<h2>Order confirmed</h2>

		</header>
		<main> 
			<p>Your payment has been received.</p>
			<p>Thank you for your order.</p>
			<p><a href="Controller?action=productOverview">Back to overview</a></p>
		</main>
		<jsp:include page="fragments/footer.jsp">
			<jsp:param name="page" value="index" />
		</jsp:include>
	</div>
</body>
</html>