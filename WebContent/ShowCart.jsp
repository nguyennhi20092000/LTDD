<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
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
<%
            
            String name=(String)session.getAttribute("username");
			
        %>
	<jsp:include page="Newheader.jsp"></jsp:include>
	<div class="row">
 				<div class="col-md-4"><!-- left -->
 						<div class="list-group"><!-- products -->
						  <a href="Controller?page=MyAccount&username=<%= name %>" class="list-group-item" style="background:  #d6d4d3;">
						    Edit Information
						  </a>
						  <a href="Controller?page=edit-Account" class="list-group-item">Edit Account</a>
						  <a href="Controller?page=showcart" class="list-group-item">Show My Card</a>
						  
						</div> 
 				</div><!-- left -->
		<div class="container">
		<table style="table-layout: fixed;width: 100%;">
		<tr>
			<th>Item Name</th>
			<th>Price</th>
			<th>Category</th>
			<th>So Luong</th>
			<th>Thanh Tien</th>
			
		</tr>
	</table>
		<c:set var="total" value="0"></c:set>
			<c:forEach items="${CartList }" var="listcard">
			<tr>
				<h3>Date:<c:out value="${listcard.getDate()}"/> MaHD=<c:out value="${listcard.getIdCard()}"/> </h3>
				<c:forEach items="${ctcartList}" var="listidctcard">				
					<c:if test="${listcard.getIdCard() == listidctcard.getIdCard() }">
						<c:forEach items="${list}" var="Product">
							<c:if test="${Product.getId() == listidctcard.getIdProduct() }">
								<table style="table-layout: fixed;width: 100%;">
								<tr>
									<td style="width: 100px;"><img src="${Product.getImage()}" height="100" width="150" >  (<c:out value="${Product.getName()}"/>)</td>
									<td style="width: 50px;"><c:out value="${Product.getPrice()}"/></td>
									<td style="width: 100px;"><c:out value="${Product.getCategory()}"/></td>
									
									<td style="width: 100px;"><input type="text" name="soluong" value=<c:out value="${listidctcard.getSoluong()}"/>></td>
									<td style="width: 100px;"><input type="text" name="txtthanhtien" value=<c:out value="${ listidctcard.getSoluong()*Product.getPrice()}"/>></td>
								</tr>
								</table>
								
						</c:if>
						</c:forEach>
					</c:if>
					
				</c:forEach>
				<h4 style="margin-top: 40px;margin-bottom: 40px;">Order Total: (<c:out value="${listcard.getThanhTien()}"></c:out>)&#x20AB;</h4>
				</tr>
			</c:forEach>
		
		
		
		
		<a href="Controller?page=index"><input type="button" value="Continue Shopping" class="btn btn-warning" style="width:100%;padding:8px;font-size:16px;"></a>
		
		
		</div>
	
	 </form>
</body>
</html>