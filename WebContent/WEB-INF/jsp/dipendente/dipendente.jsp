<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
<title>Inserisci Nuovo Dipendente</title>

<link href="<c:url value="/resources/ga/css/style.css" />" rel="stylesheet">
<link href="<c:url value="/resources/ga/css/demo.css" />" rel="stylesheet">

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
   
	<div  class="form">
		<form id="contactform" method="POST" action="/inserisci-dipendente">
                
				<p class="contact"><label>Ruolo</label>
                  <label class="id_ruolo" tabindex="1">
                  <select class="select-style" name="id_ruolo">
                  <option value="1">Amministratore</option>
                  <option value="2">Operatore</option>
                  <option value="3">Cliente</option>
                  <option value="4" >Operaio</option>
                  </label>
                 </select>
                </p>
                
                <p class="contact"><label for="firstname">Nome</label></p>
                <input id="firstname" name="firstname" placeholder="Nome" tabindex="2" type="text">
 
 				<p class="contact"><label for="lastname">Cognome</label></p>
                <input id="lastname" name="lastname" placeholder="Cognome" tabindex="3" type="text">
 
                <p class="contact"><label for="email">Email</label></p>
                <input id="email" name="email" placeholder="example@domain.com" tabindex="4" type="email">
                
                <p class="contact"><label for="codice_fiscale">Codice Fiscale</label></p>
                <input id="codice_fiscale" name="codice_fiscale" placeholder="LFNLSS82D16A345I" tabindex="5" type="text">
 
            	<p class="contact"><label for="numero_cellulare" tabindex="8">Cellulare</label></p>
            	<input id="numero_cellulare" name="numero_cellulare" placeholder="3342691052" type="text" tabindex="6"> <br>
            
            	<p class="contact"><label for="iban">IBAN</label></p>
				<input id="iban" name="iban" placeholder="Inserisci IBAN" maxlength="250" tabindex="7" type="text">
 
 				<p class="contact"><label for="indirizzo">Indirizzo</label></p>
				<input id="indirizzo" name="indirizzo" placeholder="Inserisci Indirizzo" maxlength="250" tabindex="8" type="text">
            
            	<p class="contact"><label for="nota">Nota</label></p>
                <input id="nota" name="nota" placeholder="Inserisci una nota" autocomplete="on" maxlength="250" tabindex="9" type="text">            
            
            <input class="buttom" name="submit" id="submit" value="Inserisci Cliente" type="submit" tabindex="10">   
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