<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@page import="adp.realmng.dao.CustomerDaoImpl"%>
<%@page import="adp.realmng.model.Customer"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@page import="org.springframework.context.support.ClassPathXmlApplicationContext"%>

<html>
<head>
<title>Home</title>

<link href="<c:url value="/resources/ga/css/style.css" />" rel="stylesheet">
<link href="<c:url value="/resources/ga/css/demo.css" />" rel="stylesheet">
<link href="<c:url value="/resources/ga/css/table.css" />" rel="stylesheet">

</head>
<body>
	<div class="container">
		<!-- freshdesignweb top bar -->
		<div class="freshdesignweb-top">
			<a href="#">Benvenuto<strong> ${username}</strong></a>
			<a href="<c:url value="j_spring_security_logout" />" >Logout</a>			
			<span class="right">
				<a href="home">Home</a>
				<a href="http://www.adpnet.it">
					<strong>Contatta il Supporto</strong>
				</a>
			</span>
			<div class="clr"></div>
		</div><!--/ freshdesignweb top bar -->
		
		<header>
			<h1><span></span> ${message}</h1>
		</header>
	
		<table>
			 <thead>
			 <tr>
			  <th>Codice Cantiere</th>
			  <th>Nome Cantiere</th>
			  <th>Indirizzo Cantiere</th>
			  <th>Data Inizio</th>
			  <th>Data Fine</th>
			  <th>Nota</th>
			 </tr>
			 </thead>
		<c:forEach items="${list_worksites_by_end_date}" begin="0" end="10" var="worksites">
			 <tbody>
			  <tr>
			   <td>${worksites.uuid}</td>
			   <td>${worksites.nome_cantiere}</td>
			   <td>${worksites.indirizzo}</td>
			   <td>${worksites.data_inizio_cantiere}</td>
			   <td>${worksites.data_fine_cantiere}</td>
			   <td>${worksites.nota}</td>
			  </tr>
			 </tbody>
	 	</c:forEach>
	</table>
	   
	</div>
</body>
</html>