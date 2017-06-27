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
			<h1><span>${message} <b>${nome_cliente}</b></span></h1>
		</header>
		<h2 style="margin-left:30%;font-weight:bold">${title2}</h2>
		<div class="freshdesignweb-bottom">
			<a href="listino-prezzi">Inserisci Nuovo Prezzo</a>
						
			<div class="clr"></div>
		</div>
	
		<table>
			<thead>
			 <tr>
			  <th>Codice Prezzo</th>
			  <th>Codice Cliente</th>
			  <th>CER</th>
			  <th>Descrizione CER</th>
			  <th>Imponibile</th>
			  <th>IVA</th>
			  <th>Totale</th>
			  <th>Data Aggiornamento</th>
			  <th>Nota Aggiornamento</th>
			  <th></th>
			 </tr>
			 </thead>
			<c:forEach items="${list_prices_by_customer}" begin="0" end="10" var="prices">
			 <tbody>
			  <tr>
			   <td>${prices.uuid}</td>
			   <td>${prices.uuid_cliente}</td>
			   <td>${prices.cer}</td>
			   <td>${prices.cer_descr}</td>
			   <td>${prices.imponibile}</td>
			   <td>${prices.iva}</td>
			   <td>${prices.totale}</td>
			   <td>${prices.last_update}</td>
			   <td>${prices.nota_update}</td>
			   <td>
				   	<div class="form">
						<!-- form id="contactform" method="POST" action="modifica-listino?u=${prices.uuid}&uc=${prices.uuid_cliente}&c=${prices.cer}&cd=${prices.cer_descr}&im=${prices.imponibile}&iv=${prices.iva}&t=${prices.totale}&lu=${prices.last_update}&nu=${prices.nota_update}">
				   			<input class="button" name="submit" id="submit" tabindex="1" value="Modifica" type="submit" tabindex="1">
				   		</form-->
				   		<form id="contactform" method="POST" action="modifica-listino">
				   			<input type="hidden" name="u" value="${prices.uuid}" />
				   			<input type="hidden" name="uc" value="${prices.uuid_cliente}" />
				   			<input type="hidden" name="c" value="${prices.cer}" />
				   			<input type="hidden" name="cd" value="${prices.cer_descr}" />
				   			<input type="hidden" name="im" value="${prices.imponibile}" />
				   			<input type="hidden" name="iv" value="${prices.iva}" />
				   			<input type="hidden" name="t" value="${prices.totale}" />
				   			<input type="hidden" name="lu" value="${prices.last_update}" />
				   			<input type="hidden" name="nu" value="${prices.nota_update}" />
				   			<input class="button" name="submit" id="submit" tabindex="1" value="Modifica" type="submit" tabindex="1" />
				   		</form>
				   	</div>
				   </td>
			  </tr>
			 </tbody>
	 		</c:forEach>
		</table>
		
	</div>
	
	<script>
	// Include the UserVoice JavaScript SDK (only needed once on a page)
	UserVoice=window.UserVoice||[];(function(){var uv=document.createElement('script');uv.type='text/javascript';uv.async=true;uv.src='//widget.uservoice.com/fQJ1QfGiU5yCxP4hK5txmA.js';var s=document.getElementsByTagName('script')[0];s.parentNode.insertBefore(uv,s)})();
	
	//
	// UserVoice Javascript SDK developer documentation:
	// https://www.uservoice.com/o/javascript-sdk
	//
	
	// Set colors
	UserVoice.push(['set', {
	  accent_color: '#448dd6',
	  trigger_color: 'white',
	  trigger_background_color: 'rgba(46, 49, 51, 0.6)'
	}]);
	
	// Identify the user and pass traits
	// To enable, replace sample data with actual user traits and uncomment the line
	UserVoice.push(['identify', {
	  //email:      'john.doe@example.com', // User’s email address
	  //name:       'John Doe', // User’s real name
	  //created_at: 1364406966, // Unix timestamp for the date the user signed up
	  //id:         123, // Optional: Unique id of the user (if set, this should not change)
	  //type:       'Owner', // Optional: segment your users by type
	  //account: {
	  //  id:           123, // Optional: associate multiple users with a single account
	  //  name:         'Acme, Co.', // Account name
	  //  created_at:   1364406966, // Unix timestamp for the date the account was created
	  //  monthly_rate: 9.99, // Decimal; monthly rate of the account
	  //  ltv:          1495.00, // Decimal; lifetime value of the account
	  //  plan:         'Enhanced' // Plan name for the account
	  //}
	}]);
	
	// Add default trigger to the bottom-right corner of the window:
	UserVoice.push(['addTrigger', { mode: 'contact', trigger_position: 'bottom-right' }]);
	
	// Or, use your own custom trigger:
	//UserVoice.push(['addTrigger', '#id', { mode: 'contact' }]);
	
	// Autoprompt for Satisfaction and SmartVote (only displayed under certain conditions)
	UserVoice.push(['autoprompt', {}]);
	</script>
	
</body>
</html>