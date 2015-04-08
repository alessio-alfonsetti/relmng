<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
			<a href="/home">Home</a>
				<span class="right">
					<a href="http://www.freshdesignweb.com/beautiful-registration-form-with-html5-and-css3.html">
						<strong>Contatta il Supporto</strong>
					</a>
				</span>
			<div class="clr"></div>
		</div><!--/ freshdesignweb top bar -->
		
		<h1><span></span> ${message}</h1>
	   
		<div  class="form">
			<form id="contactform" method="POST" action="/inserisci-fattura">
				<p class="contact"><label for="nome-azienda">Partita Iva</label></p>
				<input id="partita-iva" path="partita-iva" name="partita-iva" placeholder="Partita Iva" required="" tabindex="1" type="text">
	                
				<p class="contact"><label for="nome">Descrizione</label></p>
				<input id="descrizione" path="descrizione" name="descrizione" placeholder="Descrizione" required="" tabindex="2" type="text">
	 
				<p class="contact"><label for="importo_totale">Importo Totale</label></p>
				<input id="importo_totale" path="importo_totale" name="importo_totale" placeholder="Importo Totale" required="" tabindex="3" type="text">
	 
				<p class="contact"><label for="nome_cantiere">Nome Cantiere</label></p>
				<input id="nome_cantiere" name="nome_cantiere" placeholder="Nome Cantiere" required="" tabindex="4" type="email">
		            
				<input class="buttom" name="submit" id="submit" tabindex="5" value="Inserisci Cliente" type="submit" tabindex="11">   
		   	</form>
		</div>
	   
	</div>

</body>
</html>