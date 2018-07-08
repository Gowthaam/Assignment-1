<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html lang="en">
<head>

HI ${name} , YOUR ORDERS ::
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
<br />
<br />

<c:forEach var="x" items="${invoices}">
<h3>${x.hotelname}</h3> <br />
Order Id : ${x.id} <br />
<table>
<tr>
<th>Item-Name</th>
<th>Item-Quantity</th>
<th>Item-Price</th>
<tr>
<c:forEach var="y" items="${x.items}">
<tr>
<td>${y.name}</td>
<td>${y.quantity}</td>
<td>${y.price}</td>
</tr>
</c:forEach>
</table>
<h4>Total paid : ${x.total}</h4>
</c:forEach>

<br /><br />
<form  method="post" action="/app-logout" >
<input type="submit" value="Logout">
</form>
</body>
</html>