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

<script type="text/javascript">
    function doIVA()
    {
        // Capture the entered values of two input boxes
        var imponibile = document.getElementById('imponibile').value;
        var iva = document.getElementById('iva').value;

        var iva2 = (imponibile/100)*iva;
        
        // Add them together and display
        var sum = parseInt(imponibile) + parseInt(iva2);
        //document.write(sum);
        document.getElementById("totale").value = sum;
        //console.log(sum);
    }
</script>


</head>
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
		
	    <div  class="form">
		  <form id="contactform" method="POST" action="/modifica-prezzo">
		        	
		        	<p class="contact"><label for="uuid">UUID Cliente</label></p>
					<input id="uuid" name="uuid" tabindex="1" type="text" value="${customer.uuid}" readonly />
					
					<p class="contact"><label for="nome">Nome</label></p>
					<input id="nome" name="nome" tabindex="2" type="text" value="${customer.firstname}" />
					
					<p class="contact"><label for="cognome">Cognome</label></p>
					<input id="cognome" name="cognome" maxlength="250" tabindex="3" type="text" value="${customer.lastname}" />
					
					<p class="contact"><label for="email">Email</label></p>
					<input id="email" name="email" tabindex="4" type="text" value="${customer.email}" readonly>
					
					<p class="contact"><label for="codice_fiscale">Codice Fiscale</label></p>
					<input id="codice_fiscale" name="codice_fiscale" tabindex="5" type="number" value="${customer.codice_fiscale}" />
					
					<p class="contact"><label for="indirizzo">Indirizzo</label></p>
					<input id="indirizzo" name="indirizzo" tabindex="6" type="number" value="${customer.indirizzo}" />
					
					<p class="contact"><label for="cell">Numero di Cellulare</label></p>
					<input id="cell" name="cell" tabindex="7" type="number" value="${customer.numero_cellulare}" />
			
					<p class="contact"><label for="iban">IBAN</label></p>
					<input id="iban" name="iban" tabindex="8" type="text" value="${customer.iban}" />
					
					<p class="contact"><label for="nota">Nota</label></p>
					<input id="nota" name="nota" tabindex="9" type="text" value="${customer.nota}" />
					
					<p class="contact"><label for="data_inserimento">Data Inserimento</label></p>
					<input id="data_inserimento" name="data_inserimento" tabindex="10" type="text" value="${customer.data_inserimento}" readonly/>
					
					<input class="button" name="submit" id="submit" value="Modifica" type="submit" tabindex="9" />
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