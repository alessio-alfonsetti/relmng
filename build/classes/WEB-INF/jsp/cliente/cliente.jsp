<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
<title>${title}</title>

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
	
	<header>
		<h1><span></span>${message}</h1>
	</header>
	
   <h2 style="margin-left:30%;font-weight:bold">${title2}</h2>
   <div  class="form">
   		<p style="font-size:10px;text-align:center" >I campi contrassegnato con il simbolo * sono obbligaori</p><br /><br />
   		
            <form id="contactform" method="POST" action="/inserisci-cliente">
                <p class="contact"><label for="ragione_sociale">Ragione Sociale *</label></p>
                <input id="ragione_sociale" name="ragione_sociale" placeholder="Gruppo Alessandri" tabindex="1" type="text" required />
                
                <p class="contact"><label for="firstname">Nome *</label></p>
                <input id="firstname" name="firstname" placeholder="Nome" tabindex="2" type="text" required />
 
 				<p class="contact"><label for="lastname">Cognome *</label></p>
                <input id="lastname" name="lastname" placeholder="Cognome" tabindex="3" type="text" required />
 
                <p class="contact"><label for="email">Email</label></p>
                <input id="email" name="email" placeholder="example@domain.com" tabindex="4" type="email">
                
                <p class="contact"><label for="partita_iva">Partita Iva *</label></p>
                <input id="partita_iva" name="partita_iva" placeholder="1234567890" tabindex="5" type="text" required />
                
                <p class="contact"><label for="codice_fiscale">Codice Fiscale *</label></p>
                <input id="codice_fiscale" name="codice_fiscale" placeholder="LFNLSS82D16A345I" tabindex="6" type="text" required />
 
 				<p class="contact"><label for="nota">Nota</label></p>
                <input id="nota" name="nota" placeholder="Inserisci una nota" autocomplete="on" maxlength="250" tabindex="7" type="text">
 
                <!-- p class="contact"><label for="password">Create a password</label></p>
                <input type="password" id="password" name="password" required="" type="text">
                <p class="contact"><label for="repassword">Confirm your password</label></p>
                <input type="password" id="password" name="password" required="" type="text"-->

               <!--fieldset>
                 <label>Giorno<input class="birthday" maxlength="2" name="BirthDay"  placeholder="Giorno" required="" tabindex="5"></label>
                 
                 <label>Mese</label>
                  <label class="mese" tabindex="6">
                  <select class="select-style" name="mese">
                  <option value="00">Mese</option>
                  <option value="01">Gennaio</option>
                  <option value="02">Febbraio</option>
                  <option value="03" >Marzo</option>
                  <option value="04">Aprile</option>
                  <option value="05">Maggio</option>
                  <option value="06">Giugno</option>
                  <option value="07">Luglio</option>
                  <option value="08">Augosto</option>
                  <option value="09">Settembre</option>
                  <option value="10">Ottobre</option>
                  <option value="11">Novembre</option>
                  <option value="12" >Dicembre</option>
                  </label>
                 </select>   
                
                <label>Anno<input class="anno" maxlength="4" name="anno" placeholder="Anno Nascita" required="" tabindex="7"></label>
              </fieldset-->

            <!-- select class="select-style gender" name="genere" tabindex="8">
            <option value="select"></option>
            <option value="m">Maschio</option>
            <option value="f">Femmina</option>
            </select><br><br-->
            
            <p class="contact"><label for="numero_cellulare" tabindex="8">Cellulare</label></p>
            <input id="numero_cellulare" name="numero_cellulare" placeholder="3342691052" type="text" tabindex="9">
            
            <p class="contact"><label for="iban">IBAN</label></p>
			<input id="iban" name="iban" placeholder="Inserisci IBAN" autocomplete="on" maxlength="250" tabindex="10" type="text">
 
 			<p class="contact"><label for="indirizzo">Indirizzo</label></p>
			<input id="indirizzo" name="indirizzo" placeholder="Inserisci Indirizzo" autocomplete="on" maxlength="250" tabindex="11" type="text">
            
            <input type="hidden" name="id_ruolo" value="3">
            
            <input class="buttom" name="submit" id="submit" value="Inserisci Cliente" type="submit" tabindex="12">   
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