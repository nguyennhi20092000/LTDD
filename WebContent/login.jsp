<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login page</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">

<link href="GiaoDien.css" rel="stylesheet" type="text/css"/>
</head>
<body>

<jsp:include page="Newheader.jsp"></jsp:include>
	<div class="signup-header">
	 	<h2>Login to <mark>HoaCo</mark></h2>
	</div>

	 <form method="post" action="Controller">
	 
	 <input type="hidden" name="page" value="login-form">
	 
	 	<!-- Validations errors -->
	 	<font color="#F24638"><c:out value="${msg }"></c:out></font>
	 
	 	<div class="signup-group">
	 		<label>Username</label>
	 		<input type="text" name="username" placeholder="Your Username" value="<c:out value="${username }"></c:out>">
	 	</div>
	 	<div class="signup-group">
	 		<label>Password</label>
	 		<input type="password" name="password" placeholder="Enter password">
	 	</div>
	 	<div class="signup-group">
	 		<button type="submit" name="login" class="signup-btn">Log in</button>
	 	</div>
	 	<p>
	 		New to HoaCo? <a href="Controller?page=sign-up" style="color:#F24638;">Create Account</a>
	 	</p>
	 </form>
	<br><br><br>
	<footer style="position: fixed;bottom: 0;left: 0;width: 100%;">
		<div class="footer"> &copy; 2020 Copyright:
	      <a href="Controller?page=index"> HoaCo.com</a>
	    </div>
	</footer>


</body>
</html>