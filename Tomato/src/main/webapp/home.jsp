<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html lang="en">
<head>

HI ${name} , WELCOME TO TOMATO!!
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
<br />
<br />
<form method="post" action="/">

<select name="name">
<c:forEach var="x" items="${locations}">
<option value=${x} name="name"> ${x} </option>
</c:forEach>
</select>
<br /><br />

<input type="submit" value="submit"><br /><br />
</form>
<form method="post" action="/view-orders">
<input type="submit" value="View-Orders">
</form>
<br /><br />
<form  method="post" action="/app-logout" >
<input type="submit" value="Logout">
</form>
</body>
</html>