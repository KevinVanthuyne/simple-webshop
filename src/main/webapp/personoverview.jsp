<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1" />
<meta charset="UTF-8">
<title>Overview</title>
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
				<jsp:param name="page" value="personOverview" />
			</jsp:include>
			<h2>User Overview</h2>

		</header>
		<main>
		<table>
			<tr>
				<th>User id</th>
				<th>Role</th>
				<th>E-mail</th>
				<th>First Name</th>
				<th>Last Name</th>
				<th>Delete</th>
				<th>Check password</th>
			</tr>
			<c:forEach var="persoon" items="${personen}">
				<tr>
					<td><c:out value="${persoon.userid}" /></td>
					<td><c:out value="${persoon.role}" /></td>
					<td><c:out value="${persoon.email}"/></td>
					<td><c:out value="${persoon.firstName}"/></td>
					<td><c:out value="${persoon.lastName}"/></td>
					<td><a href="Controller?action=confirmDeletePerson&id=${persoon.userid}">x</a></td>
					<td><a href="Controller?action=showCheckPassword&id=${persoon.userid}">Check</a></td>
					<td><a href="Controller?action=showUpdatePerson&id=${persoon.userid}">Edit</a></td>
				</tr>
			</c:forEach>

			<caption>Users Overview</caption>
		</table>
		</main>
		<jsp:include page="fragments/footer.jsp">
			<jsp:param name="page" value="overview" />
		</jsp:include>
	</div>
</body>
</html>