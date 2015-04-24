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
			  <th>Codice Cliente</th>
			  <th>Ragione Sociale</th>
			  <th>Nome</th>
			  <th>Cognome</th>
			  <th>Partita Iva</th>
			  <th>Codice Fiscale</th>
			  <th>Numero di Telefono</th>
			  <th>Email</th>
			  <th>IBAN</th>
			  <th>Emetti Fattura</th>
			 </tr>
			 </thead>
		<c:forEach items="${list_customers_by_date_creation}" begin="0" end="10" var="customers">
			 <tbody>
			  <tr>
			   <td>${customers.uuid}</td>
			   <td>${customers.ragione_sociale}</td>
			   <td>${customers.firstname}</td>
			   <td>${customers.middlename}</td>
			   <td>${customers.partita_iva}</td>
			   <td>${customers.codice_fiscale}</td>
			   <td>${customers.numero_telefono}</td>
			   <td>${customers.email}</td>
			   <td>${customers.iban}</td>
			   <td>
			   	<div class="form">
			   		<form id="contactform" method="POST" action="fattura?uuid=${customers.uuid}">
			   			<input class="button" name="submit" id="submit" tabindex="1" value="Genera Fattura" type="submit" tabindex="1">   
			   		</form>
			   	</div>
			   </td>
			  </tr>
			 </tbody>
	 	</c:forEach>
	</table>
	   
	</div>
</body>
</html>