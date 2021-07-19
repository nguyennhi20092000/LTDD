<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath}/GiaoDien.css" rel="stylesheet" type="text/css"/> 
</head>
<body>
	<c:set var="x" value="0"></c:set>
		<c:forEach items="${cartlist }" var="i">
		<c:set var="x" value="${x+1 }"></c:set>
	</c:forEach>
	 <%
            
            String name=(String)session.getAttribute("username");
        %>
         
        <div class="top">
                <ul>
                    <div class="homepage">                    
                        <ul>
                            <a href="Controller?page=index" > <img src="img/1.png" alt="Home page"></a> 
                        </ul>
                        
                    </div>                
                    <li><form class="search" action=Controller?page=search method="post">                                                    
                           <input type="text" class="search_input" name="txtsearch">                                
                           <input type="image" src="img/search.png"  class="search_bt">  
                    </form></li>
                    <div class="thanhtrangthai">
                        <ul>
                        
                        <% 
                                
                                if(name!=null){
                            %>
                            
                                <li><a href="Controller?page=MyAccount&username=<%= name %>" class="Login"><%= name%> </a></li>
                                <li><a href="Controller?page=logout" class="Registration" >LOGOUT</a></li>
                                 <li><a href="Controller?page=cart"><i class="Giohang" aria-hidden="true"></i>CARD(<c:out value="${x}"/>)</a></li>
                                
                            <%
                                }
                                else{%>
                                    <li><a href="Controller?page=login" class="Login"> LOGIN </a>
                                        
                                   
                                    <li><a href="Controller?page=sign-up" class="Registration" >REGESTER</a></li>
                                    <li><a href="Controller?page=cart"><i class="Giohang" aria-hidden="true"></i>CARD(<c:out value="${x}"/>)</a></li>
                                <%}%>
                        
                            
                        </ul>
                       
                        
                    </div>
                </ul>
                
             </div>
</body>
</html>