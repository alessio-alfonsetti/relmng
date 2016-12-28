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
 
		<c:if test="${not empty prices}">
			<table>
			 <thead>
			 <tr>
			  <th>CER</th>
			  <th>Descrizione CER</th>
			  <th>Cliente</th>
			  <th>Imponibile</th>
			  <th>IVA</th>
			  <th>Totale</th>
			  <th>Data Nota</th>
			  <th>Note</th>
			 </tr>
			 </thead>
			
			 <tbody>
			  <tr>
			   <td>${prices.cer}</td>
			   <td>${prices.cer_descr}</td>
			   <td>${prices.uuid_cliente}</td>
			   <td>${prices.imponibile}</td>
			   <td>${prices.iva}</td>
			   <td>${prices.totale}</td>
			   <td>${prices.last_update}</td>
			   <td>${prices.nota_update}</td>
			  </tr>
			 </tbody>
	 	
			</table>
		</c:if>
		
	</div>

</body>
</html>