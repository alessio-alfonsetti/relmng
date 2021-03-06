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
		
		<c:if test="${not empty record}">
			<table>
			 <thead>
			 <tr>
			  <th>Codice Materiale</th>
			  <th>Quantita</th>
			  <th>Azienda Provenienza</th>
			  <th>Azienda Smaltitrice</th>
			  <th>Data e Ora Inizio</th>
			  <th>Data e Ora Fine</th>
			  <th>Note</th>
			 </tr>
			 </thead>
			
			 <tbody>
			  <tr>
			   <td>${record.codice_materiale}</td>
			   <td>${record.quantita}</td>
			   <td>${record.azienda_provenienza}</td>
			   <td>${record.azienda_destinazione}</td>
			   <td>${record.data_inizio} ${record.ora_inizio}</td>
			   <td>${record.data_fine} ${record.ora_fine}</td>
			   <td>${record.note}</td>
			  </tr>
			 </tbody>
	 	
			</table>
		</c:if>

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
		
		<c:if test="${not empty searched_customer}">
			<c:choose>
				<c:when test="${searched_customer.id == '0'}">
				  <p style="font-size:30px;font-weight: bold;margin-left:10%;margin-top:10%">Il Listino Prezzi e' vuoto.</p>
				</c:when>
				
				<c:otherwise>
					
				<table>
				 <thead>
				 <tr>
				  <th>ID</th>
				  <th>UUID</th>
				  <th>Nome</th>
				  <th>Cognome</th>
				  <th>Ragione Sociale</th>
				  <th>Email</th>
				  <th>Codice Fiscale</th>
				  <th>Indirizzo</th>
				  <th>Numero Cellulare</th>
				  <th>Data Inserimento</th>
				  <th>Iban</th>
				  <th>Nota</th>
				  <th></th>
				 </tr>
				 </thead>
				
				 <tbody>
				  <tr>
				   <td>${searched_customer.id}</td>
				   <td>${searched_customer.uuid}</td>
				   <td>${searched_customer.firstname}</td>
				   <td>${searched_customer.lastname}</td>
				   <td>${searched_customer.ragione_sociale}</td>
				   <td>${searched_customer.email}</td>
				   <td>${searched_customer.codice_fiscale}</td>
				   <td>${searched_customer.indirizzo}</td>
				   <td>${searched_customer.numero_cellulare}</td>
				   <td>${searched_customer.data_inserimento}</td>
				   <td>${searched_customer.iban}</td>
				   <td>${searched_customer.nota}</td>
				   <td>
				   	<div class="form">
						<form id="contactform" method="POST" action="listino-prezzi?uuid=${searched_customer.uuid}&ragione_sociale=${searched_customer.ragione_sociale}&lastname=${searched_customer.lastname}&firstname=${searched_customer.firstname}">
				   			<input class="button" name="submit" id="submit" tabindex="1" value="Listino Prezzi Nuovo" type="submit" tabindex="1">
				   		</form>
				   	</div>
				   </td>
				  </tr>
				 </tbody>
		 	
				</table>
					
				</c:otherwise>
			</c:choose>
			
		</c:if>
		
	</div>
	
</body>
</html>