<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav>
	<ul>
		<li <c:if test="${param.page == 'index'}">class="actual"</c:if>><a href="Controller?action=index">Home</a></li>
		<c:if test="${role == 'ADMINISTRATOR'}">
			<li <c:if test="${param.page == 'personOverview'}">class="actual"</c:if>><a href="Controller?action=overview">Users</a></li>
			<li <c:if test="${param.page == 'productOverview'}">class="actual"</c:if>><a href="Controller?action=productOverview">Products</a></li>
			<li <c:if test="${param.page == 'shoppingCart'}">class="actual"</c:if>><a href="Controller?action=showShoppingCart">Shopping Cart</a></li>
			<li <c:if test="${param.page == 'addProduct'}">class="actual"</c:if>><a href="Controller?action=showAddProduct">Add product</a></li>
		</c:if>
		<c:if test="${role == 'CUSTOMER'}">
			<li <c:if test="${param.page == 'productOverview'}">class="actual"</c:if>><a href="Controller?action=productOverview">Products</a></li>
			<li <c:if test="${param.page == 'shoppingCart'}">class="actual"</c:if>><a href="Controller?action=showShoppingCart">Shopping Cart</a></li>
		</c:if>
		<li <c:if test="${param.page == 'signUp'}">class="actual"</c:if>><a href="Controller?action=signUp">Sign up</a></li>
	</ul>
</nav>