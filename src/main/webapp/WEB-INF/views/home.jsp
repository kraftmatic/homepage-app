<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %><%--
--%><%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %><%--
--%><%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
	<script src="<spring:url value="/resources/scripts/jquery-2.1.4.min.js" />"></script> 
	<script src="<spring:url value="/resources/scripts/bootstrap.min.js" />"></script>
	<link rel="stylesheet" href="<spring:url value="/resources/styles/bootstrap.min.css" />"/>
</head>

<body>
<h1>
	Hello ${userName}!  
</h1>
<P>  The time on the server is ${serverTime}. </P>

<!--  Article Display -->

<div class="btn-group" role="group" aria-label="...">
  <button type="button" class="btn btn-default">Left</button>
  <button type="button" class="btn btn-default">Middle</button>
  <button type="button" class="btn btn-default">Right</button>
</div>

<c:if test="${not empty nytArticles}">

	<table class="table">
	
		<thead>
			<tr>
				<th>Date</th>
				<th>Title</th>
				<th>Snippet</th>
			</tr>
		</thead>
		
		<tbody>
		<c:forEach var="article" items="${nytArticles}">
			<tr>
				<td>${article.date}</td>
				<td><a href="${article.url}">${article.title}</a></td>
				<td>${article.snippet}<td>
			<tr>
		</c:forEach>
		</tbody>
	</table>

</c:if>
</body>
</html>
