<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello ${userName}!  
</h1>
<P>  The time on the server is ${serverTime}. </P>

<!--  Article Display -->

<c:if test="${not empty nytArticles}">

	<table>
	
		<thead>
			<tr>
				<th>Date</th>
				<th>Title</th>
				<th>Snippet</th>
			</tr>
		</thead>
		
		<tbody>
		<c:forEach var="article" items="${nytArticles.docs}">
			<tr>
				<td>${article.pub_date}</td>
				<td><a href="${article.web_url}">${article.headline.main}</a></td>
				<td>${article.snippet}<td>
			<tr>
		</c:forEach>
		</tbody>
	</table>

</c:if>


</body>
</html>
