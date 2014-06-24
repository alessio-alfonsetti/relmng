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
				<a href="/home">Home</a>
					<span class="right">
						<a href="http://www.freshdesignweb.com/beautiful-registration-form-with-html5-and-css3.html">
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
			  <th>Nome Società</th>
			  <th>Nome</th>
			  <th>Cognome</th>
			  <th>Partita Iva</th>
			  <th>Codice Fiscale</th>
			 </tr>
			 </thead>
		<c:forEach items="${list_ten_customers_by_surname}" begin="0" end="10" var="best_ten_customer">
			 <tbody>
			  <tr>
			   <td>${best_ten_customer.nome_societa}</td>
			   <td>${best_ten_customer.nome}</td>
			   <td>${best_ten_customer.cognome}</td>
			   <td>${best_ten_customer.pi}</td>
			   <td>${best_ten_customer.cf}</td>
			  </tr>
			 </tbody>
	 	</c:forEach>
	</table>
	   
	</div>
</body>
</html>