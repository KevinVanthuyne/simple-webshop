<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1" />
<meta charset="UTF-8">
<title>Update Person</title>
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
				<jsp:param name="page" value="updatePerson" />
			</jsp:include>
			<h2>Update Person</h2>

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

		<form method="post" action="Controller?action=updatePerson" novalidate="novalidate">
			<!-- novalidate in order to be able to run tests correctly -->
			<p>
				<label for="id">Id</label><input type="text" id="id"
					name="id" readonly required value="<c:out value='${person.userid}' />" />
			</p>
			<p>
				<label for="firstName">First name</label><input type="text" id="firstName"
					name="firstName" required value="<c:out value='${person.firstName}'/>">
			</p>
			<p>
				<label for="lastName">Last name</label><input type="text" id="lastName"
					name="lastName" required value="<c:out value='${person.lastName}'/>">
			</p>
			<p>
				<label for="email">Email</label><input type="text" id="email"
					name="email" required value="<c:out value='${person.email}'/>">
			</p>
			<p>
				<label for="role">Role</label>
				<select name="role" id="role">
					<c:forEach var="R" items="${roles}">
						<option value="${R}" <c:if test="${person.role.equals(R)}">selected</c:if>>${R}</option>
					</c:forEach>
				</select>
			</p>
			<p>
				<input type="submit" id="updatePerson" value="Update person">
			</p>

		</form>
		</main>
		<jsp:include page="fragments/footer.jsp">
			<jsp:param name="page" value="showUpdatePerson" />
		</jsp:include>
	</div>
</body>
</html>
