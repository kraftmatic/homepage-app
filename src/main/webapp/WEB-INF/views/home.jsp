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
	<div class="container main-container">

	<div style="margin: 10;">
		<a href="http://kraftvgs.ignorelist.com/homepage/">
		<img src="<spring:url value="/resources/styles/images/hksoft_logo.png" />" width="400"/>
		</a>
	</div>
	<hr />
	
	<c:if test="${empty nytArticles}">
	<div class="description">This website is an ongoing example development playground to help both hone my skills involving certain tech stacks as well as showcase some of my web development skills.  This page is a Java/Spring-driven article search engine (hosted on a Raspberry Pi) that uses the New York Times RESTful API to query a few select search terms I find interesting.  The source code for this site can be found at <a href="http://www.github.com/kraftmatic">my github page</a>.</div>
	</c:if>
	
	<form class="form-inline form-input" action="/homepage/home">

		<select class="form-control" name="query">
		  <option>Android</option>
		  <option>Astronomy</option>
		  <option>Guitar</option>
		  <option>Tottenham</option>
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
		<div class="article">${article.snippet}</div>

	</c:forEach>

</c:if>
	<div class="disclaimer-text">
		<div class="copyright-text">© HandKraftedSoftware.com ${thisYear}
			All Rights Reserved</div>
		No part of this website or any of its contents may be reproduced, copied, modified or adapted, without the prior written consent of the author, unless otherwise indicated for stand-alone materials.
	</div>
	<hr />
	<div style="text-align: center;">
		Powered by<br />
		<img src="<spring:url value="/resources/styles/images/java_logo.png" />" width="140" class="center">
		<img src="<spring:url value="/resources/styles/images/spring_logo.png" />" width="120" class="center">
		<img src="<spring:url value="/resources/styles/images/jquery_logo.png" />" width="120" class="center">
		<img src="<spring:url value="/resources/styles/images/nyt_logo.png" />" width="120" height="70" class="center">
		<img src="<spring:url value="/resources/styles/images/pi_logo.png" />" width="120" height="70" class="center">

	</div>

</div>
</body>
</html>
