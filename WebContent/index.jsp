<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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
	
	<div class="HoaCoo-content">
 	<div class="container">
 		<div class="row">
 				<div class="col-md-4"><!-- left -->
 						<div class="list-group"><!-- products -->
						  <a href="Controller?page=all-products" class="list-group-item" style="background:  #d6d4d3;">
						    All Products
						  </a>
						   <a href="Controller?page=NuocGiaiKhat" class="list-group-item">Nuoc Giai Khat</a>
						  <a href="Controller?page=SinhTo" class="list-group-item">Sinh To</a>
						  <a href="Controller?page=NuocEp" class="list-group-item">Nuoc Ep</a>
						  <a href="Controller?page=TraSua" class="list-group-item">Tra Sua</a>
						  <a href="Controller?page=AnVat" class="list-group-item">AnVat</a>
						</div> 
 				</div><!-- left -->

 				<div class="col-md-8"><!-- right -->
 					<h2 style="text-align: center;">Featured Products</h2><br>
 					
					<h5>Sort by(Price):</h5>
					<form action="Controller" method="get" style="border: none;margin:0px;padding: 0px;margin-bottom: 20px;">
					<input type="hidden" name="page" value="price-sort">
					<input type="hidden" name="action" value="index">
					<select name="sort">
					<option value="low-to-high">Low to high</option>
					<option value="high-to-high">high to low</option>
					</select>
					<input type="submit" value="Go!">
					</form>
					
 					<c:forEach items="${list }" var="product">
 						
 						<c:if test="${product.getFeatured() == 'yes' }">
 							
		 					<div class="col-md-4">
		 						<img src="${product.getImage() }" class="img-responsive" ><br>
		 						<div class="text-center"><a style="color: black;"><c:out value="${product.getName() }"></c:out></a></div>	
		 						<p style="text-align: center;"><c:out value="${ product.getPrice() }"></c:out>&#x20AB;</p>
		 						<div class="text-center">  <a class="btn btn-primary" href="Controller?page=addtocart&action=index&id=<c:out value="${product.getId()}"/>">Add to Cart</a> </div><br>
		 					</div>
 							
 						</c:if>
 						
 						
 					</c:forEach>
 					
 				</div>
 				
 			</div>
 			<div class="paging" style="display: flex; justify-content: center; align-items: center;">
		 	<c:forEach begin="1" end="${endPage }" var="i">
		 		<a href="Controller?page=index&index=${i}" 
		 					 style="float: left;
									padding: 8px 16px;
									border: 1px solid #000000;
									border-radius: 15px;
									color: black;
									box-shadow: 0 5px 10px rgba(0,0,0,.2);">${i }</a>
		 	</c:forEach>
		 </div>
 		</div>
 	</div>
	
	
	<jsp:include page="footer.jsp"></jsp:include>
	
</body>
</html>