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
			  <th>Codice Dipendente</th>
			  <th>Nome</th>
			  <th>Cognome</th>
			  <th>Codice Fiscale</th>
			  <th>Indirizzo</th>
			  <th>Numero di Cellulare</th>
			  <th>Email</th>
			  <th>IBAN</th>
			  <th>Data Creazione Profilo</th>
			  <th>Disattiva</th>
			  <th>Gestisci</th>
			 </tr>
			 </thead>
			<c:forEach items="${list_employers_by_lastname}" begin="0" end="100" var="employers">
			 <tbody>
			  <tr>
			   <td>${employers.uuid}</td>
			   <td>${employers.firstname}</td>
			   <td>${employers.lastname}</td>
			   <td>${employers.codice_fiscale}</td>
			   <td>${employers.indirizzo}</td>
			   <td>${employers.numero_cellulare}</td>
			   <td>${employers.email}</td>
			   <td>${employers.iban}</td>
			   <td>${employers.data_inserimento}</td>
			   <td>
			   	<div class="form">
			   		<form id="contactform" method="POST" action="cancella-dipendente" >
			   			<input type="hidden" name="uuid" value="${employers.uuid}" />
			   			<input class="button" name="submit" id="submit" value="Disattiva" type="submit" tabindex="1" />   
			   		</form>
			   	</div>
			   </td>
			   <td>
			   	<div class="form">
			   		<form id="contactform" method="POST" action="gestisci-dipendente" >
			   			<input type="hidden" name="uuid" value="${employers.uuid}" />
			   			<input class="button" name="submit" id="submit" value="Gestisci" type="submit" tabindex="2" />   
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