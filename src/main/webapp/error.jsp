<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isErrorPage="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>Error</title>
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
			<h2>Something went wrong</h2>
		</header>
		<main>
			<h3>Error ${pageContext.errorData.statusCode}</h3>
			<p>${pageContext.exception}</p>
		</main>
		<jsp:include page="fragments/footer.jsp">
			<jsp:param name="page" value="overview" />
		</jsp:include>
	</div>
</body>
</html>