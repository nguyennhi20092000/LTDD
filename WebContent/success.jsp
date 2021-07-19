<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="com.beans.Product"%>
<%@page import="java.util.ArrayList"%>
<%@page import="javax.swing.JOptionPane"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous"> 
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<link href="GiaoDien.css" rel="stylesheet" type="text/css"/>
<%if (request.getAttribute("message") != null) {%>
<% String message = (String)request.getAttribute("alertMsg");%>
<script type="text/javascript">
    var msg = "<%=message%>";
    alert(msg);
</script>
<%} %>
</head>
<body>
	<jsp:include page="Newheader.jsp"></jsp:include>
	<c:choose>
		<c:when test="${session == null}">
				<%
				request.setAttribute("alertMsg", "Please Login first");
				request.getRequestDispatcher("login.jsp").forward(request, response);
				%>
		</c:when>
		
		<c:when test="${x == 0}">
				
				<%
				request.setAttribute("alertMsg", "Your shopping bag is empty");
				request.getRequestDispatcher("cart.jsp").forward(request, response);
				%>
		</c:when>
		
		<c:when test="${session != null}">
		<div class="container" style="margin-top: 60px;margin-bottom: 60px;">
					<h4>Thank you <font color="#6bb1f8"><c:out value="${name }"></c:out></font> for Shopping with us. We received your order and it will be delivered to <font color="#6bb1f8"><c:out value="${address }"></c:out></font> with in 4-5 working days.</h4>
					<h4>Confirmation mail will be sent shortly to <font color="#6bb1f8"><c:out value="${email }"></c:out></font>.</h4>
					
					
					
					<br><br>
					<a href="Controller?page=ok"><input type="button" value="Continue Shopping" class="btn btn-warning" style="width:100%;padding:8px;font-size:16px;"></a>
		</div>
			
				
				
				
				
		</c:when>
	
	</c:choose>

	<footer style="position: fixed;bottom: 0;left: 0;width: 100%;">
		<div class="footer"> &copy; 2020 Copyright:
	      <a href="Controller?page=index"> HoaCo.com</a>
	    </div>
	</footer>
</body>
</html>