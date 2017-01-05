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
	
		<c:choose>
			<c:when test="${empty latest_prices}">
			  <p style="font-size:30px;font-weight: bold;margin-left:10%;margin-top:10%">Il Cliente cercato non esiste.</p>
			</c:when>
			
			<c:when test="${latest_prices == 'modificare'}">
			  		  
			  <div  class="form">
				<form id="contactform" method="POST" action="/modifica-prezzo">
		        	
		        	<p class="contact"><label for="uuid">UUID Prezzo</label></p>
					<input id="uuid" name="uuid" tabindex="1" type="text" value="${u}" readonly >
					
					<p class="contact"><label for="cer">CER</label></p>
					<input id="cer" name="cer" tabindex="2" type="text" value="${c}" >
					
					<p class="contact"><label for="cer_descr">Descrizione CER</label></p>
					<input id="cer_descr" name="cer_descr" maxlength="250" tabindex="3" type="text" value="${cd}" >
					
					<p class="contact"><label for="uuid_cliente">UUID Cliente</label></p>
					<input id="uuid_cliente" name="uuid_cliente" tabindex="4" type="text" value="${uc}" readonly>
					
					<p class="contact"><label for="imponibile">Imponibile</label></p>
					<input id="imponibile" name="imponibile" tabindex="5" type="number" value="${im}" > EURO
					
					<p class="contact"><label for="iva">IVA</label></p>
					<input id="iva" name="iva" tabindex="6" type="number" value="${iv}" > %
					
					<p class="contact"><label for="totale">Totale</label></p>
					<input id="totale" name="totale" tabindex="7" type="number" value="${t}" onfocus="doIVA();"> EURO
			
					<p class="contact"><label for="nota_update">Nota Update</label></p>
					<input id="nota_update" name="nota_update" tabindex="8" type="text" value="${nu}" >		
		
					<input class="button" name="submit" id="submit" value="Modifica" type="submit" tabindex="9" />
			   </form>
			</div>
			  
			</c:when>
			
			<c:otherwise>
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
				<c:forEach items="${latest_prices}" begin="0" end="10" var="prices">
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
			</c:otherwise>
		</c:choose>
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