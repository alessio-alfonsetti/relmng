<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Inserisci una nuova Fattura</title>

<link href="<c:url value="/resources/ga/css/style.css" />" rel="stylesheet">
<link href="<c:url value="/resources/ga/css/demo.css" />" rel="stylesheet">

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
		
		<h1><span></span> ${message}</h1>
		
		<div class="form">
			<form id="contactform" method="POST" action="/genera-fattura">
				<input class="buttom" name="submit" id="submit" tabindex="1" value="Genera Fattura" type="submit" tabindex="1">   
		   	</form>
		</div>	   
	   
		<div class="form">
			<form id="contactform" method="POST" action="/inserisci-fattura">
				<p class="contact"><label for="partita_iva">Partita Iva</label></p>
				<input id="partita_iva" name="partita_iva" placeholder="Partita Iva" required="" tabindex="1" type="text">
	                
				<p class="contact"><label for="descrizione">Descrizione</label></p>
				<input id="descrizione" name="descrizione" placeholder="Descrizione" required="" tabindex="2" type="text">
	 
				<p class="contact"><label for="importo">Importo</label></p>
				<input id="importo" name="importo" placeholder="Importo" required="" tabindex="3" type="text">
				
				<p class="contact"><label for="iva">Iva</label></p>
				<input id="iva" name="iva" placeholder="Iva" required="" tabindex="4" type="text">
				
				<p class="contact"><label for="importo_totale">Importo Totale</label></p>
				<input id="importo_totale" name="importo_totale" placeholder="Importo Totale" required="" tabindex="5" type="text">
	 
				<p class="contact"><label for="nome_cantiere">Nome Cantiere</label></p>
				<input id="nome_cantiere" name="nome_cantiere" placeholder="Nome Cantiere" required="" tabindex="6" type="text">
		            
				<input class="buttom" name="submit" id="submit" tabindex="5" value="Inserisci Fattura" type="submit" tabindex="7">   
		   	</form>
		</div>
	   
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