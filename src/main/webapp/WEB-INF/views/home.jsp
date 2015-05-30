<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %><%--
--%><%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %><%--
--%><%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
	<script src="<spring:url value="/resources/scripts/jquery-2.1.4.min.js" />"></script> 
	<script src="<spring:url value="/resources/scripts/jquery-ui.min.js" />"></script> 
	<script src="<spring:url value="/resources/scripts/bootstrap.min.js" />"></script>
	<link rel="stylesheet" href="<spring:url value="/resources/styles/jquery-ui.min.css" />"/>
	<link rel="stylesheet" href="<spring:url value="/resources/styles/bootstrap.min.css" />"/>
	<link rel="stylesheet" href="<spring:url value="/resources/styles/mainpage.css" />"/>
	<script>
	  $(function() {
	    $( "#datepickerstart" ).datepicker({ maxDate: '0'});
	  });
	  $(function() {
		$( "#datepickerend" ).datepicker({ maxDate: '0'});
	  });
  	</script>
</head>

<body style="background-color: aliceblue">
<div class="container" style="width:730;background-color: white;border-left: 1px black solid;border-right: 1px black solid;border-bottom: 1px black solid;">
<P>  The time on the server is ${serverTime}. </P>

<form class="form-inline" action="/homepage/home">

<select class="form-control" name="query">
  <option>Android</option>
  <option>Astronomy</option>
  <option>Guitar</option>
</select>
<div class="form-group">
    <label class="sr-only" for="datepickerstart">Start date</label>
    <input type="text" class="form-control" id="datepickerstart" placeholder="Start date" name="startdate">
  </div>
  <div class="form-group">
    <label class="sr-only" for="datepickerend">End date</label>
    <input type="text" class="form-control" id="datepickerend" placeholder="End date" name="enddate">
  </div>
	<button type="submit" class="btn btn-primary pull-right">Search</button>
</form>


<c:if test="${not empty nytArticles}">
	<hr>
	<h3>
		New York Times search results for: ${userName}
	</h3>

	<c:forEach var="article" items="${nytArticles}">
		<div class="bg-info article-header"><a href="${article.url}">${article.title}</a>
		<div class="pull-right">${article.date}</div></div>
		<pre>${article.snippet}</pre>

	</c:forEach>

</c:if>
	<div class="disclaimer-text">
		<div class="copyright-text">© HandKraftedSoftware.com ${thisYear}
			All Rights Reserved</div>
		No part of this website or any of its contents may be reproduced, copied, modified or adapted, without the prior written consent of the author, unless otherwise indicated for stand-alone materials.</div>
	</div>
</body>
</html>
