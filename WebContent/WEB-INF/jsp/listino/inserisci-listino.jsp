<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>${title}</title>

<link href="<c:url value="/resources/ga/css/style.css" />" rel="stylesheet">
<link href="<c:url value="/resources/ga/css/demo.css" />" rel="stylesheet">

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
			<form id="contactform" method="POST" action="/inserisci-listino">
	        	
	        	<p class="contact"><label for="cer">CER</label></p>
				<input id="cer" name="cer" placeholder="CER1234567890" tabindex="1" type="text">
				
				<p class="contact"><label for="cer_descr">Descrizione CER</label></p>
				<input id="cer_descr" name="cer_descr" placeholder="Descrizione CER1234567890" maxlength="250" tabindex="2" type="text">
				
				<p class="contact"><label for="uuid_cliente">Ragione Sociale o Cognome</label></p>
				<input id="uuid_cliente" name="uuid_cliente" placeholder="Ragione Sociale o Cognome" maxlength="250" tabindex="3" type="text" value="${rag_soc_cogn}" >
				
				<p class="contact"><label for="imponibile">Imponibile</label></p>
				<input id="imponibile" name="imponibile" placeholder="imponibile" tabindex="4" type="number"> EURO
				
				<p class="contact"><label for="iva">IVA</label></p>
				<input id="iva" name="iva" placeholder="iva" tabindex="5" type="number" > %
				
				<p class="contact"><label for="totale">Totale</label></p>
				<input id="totale" name="totale" placeholder="totale" tabindex="6" type="number" value="" onfocus="doIVA();" > EURO
	
				<input class="button" name="submit" id="submit" value="Inserisci" type="submit" tabindex="7" />
	        	
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