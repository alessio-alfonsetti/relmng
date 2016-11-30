<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
<title>${title}</title>

<link href="<c:url value="/resources/ga/css/style.css" />" rel="stylesheet">
<link href="<c:url value="/resources/ga/css/demo.css" />" rel="stylesheet">

<script type="text/javascript" src="/resources/ga/js/validate.js"></script>

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
	</header>
   
   <div  class="form">
            <form id="contactform" method="POST" action="/inserisci-trasporto">
                <p class="contact"><label for="codice_materiale">Codice Materiale Trasportato</label></p>
                <input id="codice_materiale" name="codice_materiale" placeholder="CER1234567890" tabindex="1" type="text">
                
                <p class="contact"><label for="descr_codice_materiale">Descrizione CER</label></p>
                <input id="descr_codice_materiale" name="descr_codice_materiale" placeholder="Descrizione CER1234567890" maxlength="250" tabindex="2" type="text">
                
                <p class="contact"><label for="quantita">Quantit&aacute;</label></p>
                <input id="quantita" name="quantita" placeholder="10" tabindex="3" type="text"> KG
 
 				<p class="contact"><label for="azienda_provenienza">Azienda di Provenienza del materiale</label></p>
                <input id="azienda_provenienza" name="azienda_provenienza" placeholder="Azienda Provenienza" tabindex="4" type="text">
 
				<p class="contact"><label for="unita_locale">Unita' locale</label></p>
                <input id="unita_locale" name="unita_locale" placeholder="Unita' Locale" tabindex="5" type="text">
 
                <p class="contact"><label for="azienda_destinazione">Azienda Smaltitrice del materiale</label></p>
                <input id="azienda_destinazione" name="azienda_destinazione" placeholder="Azienda Smaltitrice" tabindex="6" type="text">
                
                <p class="contact"><label for="data_inizio">Data di inizio</label></p>
                <input id="data_inizio" name="data_inizio" placeholder="01/01/2017" tabindex="7" type="date">
                
                <p class="contact"><label for="ora_inizio">Ora di inizio</label></p>
                <input id="ora_inizio" name="ora_inizio" placeholder="16:00" tabindex="8" type="time">
                
                <p class="contact"><label for="data_fine">Data di fine</label></p>
                <input id="data_fine" name="data_fine" placeholder="01/01/2017" tabindex="9" type="date">
 
 				<p class="contact"><label for="ora_fine">Ora di fine</label></p>
                <input id="ora_fine" name="ora_fine" placeholder="17:00" tabindex="10" type="time">
 
 				<p class="contact"><label for="cup_cig">CUP/CIG</label></p>
                <input id="cup_cig" name="cup_cig" placeholder="Inserisci CUP/CIG" autocomplete="on" tabindex="11" type="text">
 
 				<p class="contact"><label for="nota">Nota</label></p>
                <input id="nota" name="nota" placeholder="Inserisci una nota" autocomplete="on" maxlength="250" tabindex="12" type="text">
            
           		<input class="buttom" name="submit" id="submit" value="Inserisci" type="submit" tabindex="13">   
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
 
 	<script>
 	// ValidateJS form validation
 	var validator = new FormValidator('contactform', [{
	    name: 'codice_materiale',
	    display: 'required',
	    rules: 'required'
	}, {
	    name: 'quantita',
	    rules: 'required|numeric'
	}, {
	    name: 'azienda_provenienza',
	    rules: 'required'
	}, {
	    name: 'azienda_destinazione',
	    display: 'password confirmation',
	    rules: 'required'
	}, {
	    name: 'data_inizio',
	    display: 'password confirmation',
	    rules: 'required'
	}, {
	    name: 'ora_inizio',
	    display: 'min length',
	    rules: 'required'
	}, {
	    name: 'data_fine',
	    display: 'password confirmation',
	    rules: 'required'
	}, {
	    name: 'ora_fine',
	    display: 'min length',
	    rules: 'required'
	}], function(errors, event) {
	    if (errors.length > 0) {
	        // Show the errors
	    	window.alert("1: ".errors.length);
	    	window.alert("2: ".event);
	    }
	}); 	
 	</script>
 
</body>
</html>