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
</head>
<body>

	<jsp:include page="Newheader.jsp"></jsp:include>
	
	<div class="container">
	
	<c:choose> 
		<c:when test="${x == 1}">
						<h4 style="margin-top: 40px;">My shopping bag(<c:out value="${x}"/> item)</h4>
		</c:when>
		<c:when test="${x > 1}">
						<h4 style="margin-top: 40px;">My shopping bag(<c:out value="${x}"/> items)</h4>
		</c:when>
		<c:otherwise >
				<h4 style="margin-top: 40px;">Your Shopping Bag is Empty</h4>
		</c:otherwise>
	
	</c:choose>
	<table style="table-layout: fixed;width: 100%;">
		<tr>
			<th>Item Name</th>
			<th>Price</th>
			<th>Category</th>
			<th>So Luong</th>
			<th>Thanh Tien</th>
			<th>Remove Item</th>
		</tr>
	</table>
	
		<c:set var="total" value="0"></c:set>		
	<c:forEach items="${cartlist }" var="i">
			<c:forEach items="${list }" var="Product">
				<c:if test="${i.getIdProduct() == Product.getId() }">	
					<c:set var="total" value="${total + Product.getPrice()*i.getSoluong() }"></c:set>	
									
											<table style="table-layout: fixed;width: 100%;">							
											<tr>
												<td style="width: 100px;"><img src="${Product.getImage()}" height="100" width="150" >  (<c:out value="${Product.getName()}"/>)</td>
												<td style="width: 50px;"><c:out value="${Product.getPrice()}"/></td>
												<td style="width: 100px;"><c:out value="${Product.getCategory()}"/></td>													
												
													<td style="width: 100px;"><form method="post" action=" Controller?page=editsoluong&id=<c:out value="${ Product.getId()}"/>"style="border: none">
													<input type="number" name="soluong" min="1" max="10" step="1" value=<c:out value="${ i.getSoluong()}"/>>
													<input type="submit" name="submit" value="Update">
													</form></td>													
													<td style="width: 100px;"><input type="text" name="txtthanhtien" value=<c:out value="${ i.getSoluong()*Product.getPrice()}"/>></td>							
												
												
												<td style="width: 100px;"><a href="Controller?page=remove&id=<c:out value="${Product.getId()}"/>"><span class="btn btn-danger">X</span></a></td>
												
											</tr>
											</table>	
				</c:if>
		</c:forEach>
	</c:forEach>	
	<h4 style="margin-top: 40px;margin-bottom: 40px;">Order Total: (<c:out value="${ total}"></c:out>)&#x20AB;</h4>
	
	<a href="Controller?page=success&total=<c:out value="${ total}"/>"><input type="button" value="Proceed to Checkout" class="btn btn-success" style="width:100%;padding:8px;font-size:16px;"></a><br>
	<a href="Controller?page=index"><input type="button" value="Continue Shopping" class="btn btn-warning" style="width:100%;padding:8px;font-size:16px;"></a>
	
	
	</div>

	
	<jsp:include page="footer.jsp"></jsp:include>

</body>
</html>