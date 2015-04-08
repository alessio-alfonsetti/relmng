<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
<title>Inserisci nuovo Cliente</title>

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
            <form id="contactform" method="POST" action="/inserisci-cliente">
                <p class="contact"><label for="nome-azienda">Nome Azienda</label></p>
                <input id="nome-azienda" path="nome-azienda" name="nome-azienda" placeholder="Nome Azienda" required="" tabindex="1" type="text">
                
                <p class="contact"><label for="nome">Nome</label></p>
                <input id="nome" path="nome" name="nome" placeholder="Nome" required="" tabindex="2" type="text">
 
 				<p class="contact"><label for=""cognome"">Cognome</label></p>
                <input id="cognome" name=""cognome"" placeholder="Cognome" required="" tabindex="3" type="text">
 
                <p class="contact"><label for="email">Email</label></p>
                <input id="email" name="email" placeholder="example@domain.com" required="" tabindex="4" type="email">
 
                <!-- p class="contact"><label for="password">Create a password</label></p>
                <input type="password" id="password" name="password" required="" type="text">
                <p class="contact"><label for="repassword">Confirm your password</label></p>
                <input type="password" id="password" name="password" required="" type="text"-->

               <fieldset>
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
              </fieldset>

            <select class="select-style gender" name="genere" tabindex="8">
            <option value="select"></option>
            <option value="m">Maschio</option>
            <option value="f">Femmina</option>
            </select><br><br>
            <p class="contact"><label for="cellulare" tabindex="9">Cellulare</label></p>
            <input id="cellulare" name="cellulare" placeholder="Cellulare" required="" type="text" tabindex="10"> <br>
            <input class="buttom" name="submit" id="submit" tabindex="5" value="Inserisci Cliente" type="submit" tabindex="11">   
   </form>
</div>
   
</div>
 
</body>
</html>