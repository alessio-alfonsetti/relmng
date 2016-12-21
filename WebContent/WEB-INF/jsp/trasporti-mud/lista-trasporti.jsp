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
			<h1><span></span>${message}</h1>
		</header>
	
		<div class="freshdesignweb-bottom">
			<a href="trasporti-mud">Inserisci</a>
			<a href="#">Modifica</a>
			<span class="right">
				<a href="#">
				<strong>Deattiva</strong>
				</a>
			</span>
			<span class="right">
				<a href="#">
				<strong>Esporta</strong>
				</a>
			</span>
			
			<div class="clr"></div>
		</div>
	
		<table>
			<thead>
			 <tr>
			  <th>Codice Viaggio</th>
			  <th>Codice Materiale</th>
			  <th>Azienda Produttrice</th>
			  <th>Azienda Smaltitrice</th>
			  <th>Data/Ora Inizio</th>
			  <th>Data/Ora Fine</th>
			  <th>Note</th>
			 </tr>
			 </thead>
			<c:forEach items="${list_trasports}" begin="0" end="10" var="transports">
			 <tbody>
			  <tr>
			   <td>${transports.uuid}</td>
			   <td>${transports.idmaterialetrasportato}</td>
			   <td>${transports.idaziendaproduttrice}</td>
			   <td>${transports.idaziendasmaltitrice}</td>
			   <td>${transports.datainiziotrasporto}/${transports.orainiziotrasporto}</td>
			   <td>${transports.datafinetrasporto}/${transports.orafinetrasporto}</td>
			   <td>${transports.note}</td>
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