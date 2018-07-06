<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html lang="en">
<head>
<h1>Welcome to ${place} restaurant!!</h1>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
<br />
<a href="/view-reviews">View Ratings and Reviews</a>
<table>
<tr>
<th>Name</th>
<th>Price</th>
<th>Quantity</th>
<th>Add</th>
</tr>
<c:forEach var="x" items="${menu}">
<tr>
<td>${x.item}</td> 
<td>${x.price}</td>
<form method="post" action="/addItem">
<input type="hidden" name="item" value="${x.item}">
<td><input type="number" name="quantity" value="0"></td>
<input type="hidden" name="price" value="${x.price}">
<td><input type="submit" value="Add" >
</form>
</tr> 
</c:forEach>

</table>

<br />
<h4>Your Food Basket</h4>

<table>
<tr>
<th>Name</th>
<th>Total Price</th>
<th>Quantity</th>
<th>Remove</th>
</tr>
<c:forEach var="x" items="${cart}">
<tr>
<td>${x.item}</td> 
<td>${x.price}</td>
<td>${x.quantity}</td>
<form method="post" action="/removeItem">
<input type="hidden" name="item" value="${x.item}">
<td><input type="submit" value="Remove" >
</form>
</tr> 
</c:forEach>

</table>
<br />

<h3>Total Bill : ${totalBill}</h3>
<c:if test = "${totalBill > 0}" >
<br />
<form method="post" action="/checkout">
<input type="submit" value="Checkout">
</form>
</c:if>

<br />
<form  method="post" action="/app-logout" >
<input type="submit" value="Logout">
</form>
</body>
</html>