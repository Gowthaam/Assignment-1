<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
<br />
<h2> Thank you! You're order has been placed!</h2>
<br />
<form method="post" action="/view-orders">
<input type="submit" value="View-Orders">
</form>
<br />
PLEASE PROVIDE RATING AND REVIEW TO HELP OTHER CUSTOMERS!<br />
<form method="post" action="/rating">
Enter your Rating on a scale 0-5 : <input type=number name="rating" min=0 max=5><br />
Enter your Review : <textarea name="review" rows="5" cols="30"></textarea><br />
<input type=submit value="Post"><br />
</form>
<br /><br />
<form  method="post" action="/app-logout" >
<input type="submit" value="Logout">
</form>

</body>
</html>