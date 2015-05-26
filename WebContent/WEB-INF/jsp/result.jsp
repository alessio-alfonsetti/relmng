<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

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
			<h1><span></span>${message}</h1>
			<h2><span></span>${message_detail}</h2>
		</header>
 
		<c:if test="${not empty customer}">
			<table>
			 <thead>
			 <tr>
			  <th>ID</th>
			  <th>UUID</th>
			  <th>Nome</th>
			  <th>Cognome</th>
			  <th>Email</th>
			  <th>Codice Fiscale</th>
			  <th>Indirizzo</th>
			  <th>Numero Cellulare</th>
			  <th>Data Inserimento</th>
			  <th>Iban</th>
			  <th>Nota</th>
			 </tr>
			 </thead>
			
			 <tbody>
			  <tr>
			   <td>${customer.id}</td>
			   <td>${customer.uuid}</td>
			   <td>${customer.firstname}</td>
			   <td>${customer.lastname}</td>
			   <td>${customer.email}</td>
			   <td>${customer.codice_fiscale}</td>
			   <td>${customer.indirizzo}</td>
			   <td>${customer.numero_cellulare}</td>
			   <td>${customer.data_inserimento}</td>
			   <td>${customer.iban}</td>
			   <td>${customer.nota}</td>
			  </tr>
			 </tbody>
	 	
			</table>
		</c:if>
 
 		<c:if test="${not empty worksite}">
			<table>
			 <thead>
			 <tr>
			  <th>UUID</th>
			  <th>Nome Cantiere</th>
			  <th>Indirizzo</th>
			  <th>Nota</th>
			  <th>Gestisci Cantiere</th>
			 </tr>
			 </thead>
			
			 <tbody>
			  <tr>
			   <td>${worksite.uuid}</td>
			   <td>${worksite.nome_cantiere}</td>
			   <td>${worksite.indirizzo}</td>
			   <td>${worksite.nota}</td>
			   <td>
			   	<div class="form">
			   		<form id="contactform" method="POST" action="gestisci-cantiere">
			   			<input type="hidden" name="uuid" value="${worksite.uuid}" />
			   			<input class="button" name="submit" id="submit" tabindex="1" value="Gestisci Cantiere" type="submit" tabindex="1">   
			   		</form>
			   	</div>
			   </td>
			  </tr>
			 </tbody>
	 	
			</table>
		</c:if>

	</div>
	
</body>
</html>