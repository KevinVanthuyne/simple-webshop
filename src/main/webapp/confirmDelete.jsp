<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1" />
<meta charset="UTF-8">
<title>Confirm Delete</title>
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
			<h2>Confirm Delete</h2>

		</header>
		<main>
			<c:if test="${type == 'product'}">
				<p>Are you sure you want to delete the following product?</p>
				<ul>
					<li><c:out value="${id}"/></li>
					<li><c:out value="${name}"/></li>
					<li><c:out value="${description}"/></li>
					<li><c:out value="${price}"/></li>
				</ul>
				<form action="Controller?action=deleteProductConfirmed" method="post">
					<input type="hidden" name="id" value="<c:out value='${id}'></c:out>" />
					<input name="confirmButton" type="submit" value="No"/>
					<input name="confirmButton" type="submit" value="Yes"/>
				</form>
			</c:if>
			<c:if test="${type == 'person'}">
				<p>Are you sure you want to delete the following person?</p>
				<ul>
					<li><c:out value="${id}"/></li>
					<li><c:out value="${firstName}"/></li>
					<li><c:out value="${lastName}"/></li>
					<li><c:out value="${email}"/></li>
				</ul>
				<form action="Controller?action=deletePersonConfirmed" method="post">
					<input type="hidden" name="id" value="<c:out value='${id}'></c:out>" />
					<input name="confirmButton" type="submit" value="No"/>
					<input name="confirmButton" type="submit" value="Yes"/>
				</form>
			</c:if>
		</main>
		<jsp:include page="fragments/footer.jsp">
			<jsp:param name="page" value="confirmDeletePerson" />
		</jsp:include>
	</div>
</body>
</html>