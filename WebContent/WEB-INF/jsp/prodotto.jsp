<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
<title>Inserisci Prodotto</title>



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
            <form id="contactform">
                <p class="contact"><label for="name">Nome</label></p>
                <input id="name" name="name" placeholder="Nome" required="" tabindex="1" type="text">
 
 				<p class="contact"><label for="note">Note</label></p>
                <input id="note" name="note" placeholder="Scrivi una Nota" required="" tabindex="2" type="text">
 
                <p class="contact"><label for="prezzo">Prezzo</label></p>
                <input id="prezzo" name="prezzo" placeholder="Prezzo" required="" type="prezzo">
 
               <fieldset>
                <label>Anno<input class="birthyear" maxlength="4" name="BirthYear" placeholder="Year" required=""></label>
              </fieldset>

            <br><br>
            <p class="contact"><label for="descrizione">Descrizione</label></p>
            <input id="descrizione" maxlength="250" name="descrizione" placeholder="Descrizione" required="" type="text"> <br>
            
            <input class="buttom" name="submit" id="submit" tabindex="5" value="Sign me up!" type="submit">   
	   </form>
	</div>
</div>
   
</body>
</html>