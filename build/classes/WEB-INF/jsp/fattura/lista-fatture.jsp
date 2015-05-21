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
<title>${title}</title>

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
			<h1><span></span>${message}</h1>
		</header>
	
	<table>
			 <thead>
			 <tr>
			  <th>ID</th>
			  <th>UUID</th>
			  <th>Partita Iva</th>
			  <th>Data Emissione</th>
			  <th>Descrizione</th>
			  <th>Importo</th>
			  <th>Iva</th>
			  <th>Importo Totale</th>
			  <th>Stato Pagamento</th>
			  <th>Nome Cantiere</th>
			  <th>Stampa Fattura</th>
			 </tr>
			 </thead>
		<c:forEach items="${list_ten_invoices_by_date_emission}" begin="0" end="10" var="last_ten_invoices">
			 <tbody>
			  <tr>
			   <td>${last_ten_invoices.id}</td>
			   <td>${last_ten_invoices.uuid}</td>
			   <td>${last_ten_invoices.partita_iva}</td>
			   <td>${last_ten_invoices.data_emissione}</td>
			   <td>${last_ten_invoices.descrizione}</td>
			   <td>${last_ten_invoices.importo}</td>
			   <td>${last_ten_invoices.iva}</td>
			   <td>${last_ten_invoices.importo_totale}</td>
			   <td>${last_ten_invoices.stato_pagamento}</td>
			   <td>${last_ten_invoices.nome_cantiere}</td>
			   <td>
			   	<div class="form">
			   		<form id="contactform" method="POST" action="report/pdf?id=${last_ten_invoices.id}">
			   			<input class="button" name="submit" id="submit" tabindex="1" value="Stampa Fattura" type="submit" tabindex="1">   
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