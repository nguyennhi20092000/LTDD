<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous"> 
<link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
	<header>
		<h1>
			Welcome admin
		</h1>
		<nav>
			<ul>
				<li><a href="admin?page=index&role=${username}">Home</a></li>
				<li><a href="admin?page=addproduct&role=${username}">Add Product</a></li>
				<li><a href="#">Settings</a></li>
				<li><a href="#">Pages</a></li>
			</ul>
		</nav>
	</header>
	 <sql:setDataSource user="root" password="12345" driver="com.mysql.jdbc.Driver" url="jdbc:mysql://localhost:3306/myproject" var="ds"/>
	 
	  <sql:query var="result" dataSource="${ds }">
 
		 select * from (select ROW_NUMBER() over(order by id) as stt, id, name, price, category, image from myproject.product) as b where stt between ? and ?
		 
		 <sql:param value="${index }"/>
		 <sql:param value="${index2 }"/>
	   </sql:query>
	   
	  
	
	<div class="container">
	<h2>Products List: </h2>
		 <table style="table-layout: fixed;width: 100%;">
			<tr>
			<th>Item id</th>
			<th>Name</th>
			<th>Price</th>
			<th>Category</th>
			<th>Image</th>
			<th>Option</th>
		</tr>
	</table>
		
		 <c:forEach items="${result.rows }" var="row">
		  <table style="table-layout: fixed;width: 100%;">
		  	
				<tr>
					<td style="width: 50px;"><c:out value="${row.stt }"></c:out></td>
					<td style="width: 100px;"><c:out value="${row.name }"></c:out></td>
					<td style="width: 100px;"><c:out value="${row.price }"></c:out></td>
					<td style="width: 100px;"><c:out value="${row.category}"/></td>
					<td style="width: 100px;"><img src="${row.image}" height="100" width="100" ></td>
					<td style="width: 100px;"><a href="<%= request.getContextPath() %>/admin?page=edit&id=${row.id}&role=${username}" style="color: #6bb1f8;">edit</a> ||
					<a href="<%= request.getContextPath() %>/admin?page=delete&id=${row.id}&role=${username}" style="color:#6bb1f8;">delete</a></td>
				</tr>
			</table>
		 </c:forEach>
		 <div class="paging" style="display: flex; justify-content: center; align-items: center;">
		 	<c:forEach begin="1" end="${endPage }" var="i">
		 		<a href="<%= request.getContextPath() %>/admin?page=index&role=${username}&index=${i}" 
		 					 style="float: left;
									padding: 8px 16px;
									border: 1px solid #000000;
									border-radius: 15px;
									color: black;
									box-shadow: 0 5px 10px rgba(0,0,0,.2);">${i }</a>
		 	</c:forEach>
		 </div>
		 </div>
		 <br>
	 <footer>
		<div class="footer"> &copy; 2020 Copyright:
	      HoaCo.com
	    </div>
	</footer>
	
</body>
</html>