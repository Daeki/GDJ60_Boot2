<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> 
<!DOCTYPE html>
<html>
<head>
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Modern Business - Start Bootstrap Template</title>
		<!-- css favicon -->
		<c:import url="../temp/style.jsp"></c:import>
		<!-- css favicon -->
    </head>
</head>
<body class="d-flex flex-column h-100">
	<main class="flex-shrink-0">
		<!-- Navigation-->
		<c:import url="../temp/header.jsp"></c:import>
        <!-- Header-->
        
        <section class="bg-light py-5">
        	<div class="container px-5 my-5">
            	<div class="text-center mb-5">
                	<h1 class="fw-bolder">${board} List</h1>
                    	<p class="lead fw-normal text-muted mb-0">With our no hassle pricing plans</p>
                </div>
                
                <div class="row gx-5">
                	<table class="table table-hover">
                		<thead>
                			<tr>
                				<th>Num</th>
                				<th>Title</th>
                				<th>Writer</th>
                				<th>Date</th>
                				<th>Hit</th>
                			</tr>
                		</thead>
                		<tbody>
                		<c:forEach items="${list}" var="boardVO">
                			<tr>
                				<td>${boardVO.num}</td>
                				<td><a href="./detail?num=${boardVO.num}">${boardVO.title}</a></td>
                				<td>${boardVO.writer}</td>
                				<td>${boardVO.regDate}</td>
                				<td>${boardVO.hit}</td>
                			</tr>
                		</c:forEach>	
                		</tbody>
                	</table>
                	
                	
                	<nav aria-label="Page navigation example">
					  <ul class="pagination justify-content-center">
					  
					  <c:if test="${pager.pre}">
					    <li class="page-item">
					      <a class="page-link" href="./list?page=${pager.startNum-1}&kind=${pager.kind}&search=${pager.search}" aria-label="Previous">
					        <span aria-hidden="true">&laquo;</span>
					      </a>
					    </li>
                	</c:if>
					    
                	<c:forEach begin="${pager.startNum}" end="${pager.lastNum}" var="i">
                		<li class="page-item"><a class="page-link" href="./list?page=${i}&kind=${pager.kind}&search=${pager.search}">${i}</a></li>
                	</c:forEach>
                	<c:if test="${pager.next}">
					    <li class="page-item">
					      <a class="page-link" href="./list?page=${pager.lastNum+1}&kind=${pager.kind}&search=${pager.search}" aria-label="Next">
					        <span aria-hidden="true">&raquo;</span>
					      </a>
					    </li>
                	</c:if>
					  </ul>
					</nav>
                	
                	
                	
                	
          <div >
                	
                	<form action="./list">
                	
                		<select name="kind">
                			<option value="title">Title</option>
                			<option value="contents">Contents</option>
                			<option value="writer">Writer</option>
                		</select>
                		
                		<input type="text" name="search">
                		<button type="submit">Search</button>
                	</form>
           </div>     	
                </div>
             </div>
         </section>
        <sec:authorize access="hasRole('ADMIN')">
        <a href="./add">WRITE</a>
		</sec:authorize>
	</main>
	<!-- Footer 적용  -->
	<c:import url="../temp/footer.jsp"></c:import>
	<!-- Footer 끝 -->
</body>
</html>