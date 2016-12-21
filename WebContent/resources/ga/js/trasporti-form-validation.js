// Wait for the DOM to be ready
$(function() {
  // Initialize form validation on the registration form.
  // It has the name attribute "registration"
  $("form[name='contactform']").validate({
	  
	  console.log("sono nel javascript");
	  
    // Specify validation rules
    rules: {
      // The key name on the left side is the name attribute
      // of an input field. Validation rules are defined
      // on the right side
      codice_materiale: "required",
      descr_codice_materiale: "required",
      quantita: "required",
      azienda_provenienza: "required",
      unita_locale: "required",
      azienda_destinazione: "required",
      data_inizio: "required",
      ora_inizio: "required",
      data_fine: "required",
      ora_fine: "required",
      cup_cig: "required",
      //email: {
      //  required: true,
        // Specify that email should be validated
        // by the built-in "email" rule
      //  email: true
      //},
      //password: {
      //  required: true,
      //  minlength: 5
      //}
    },
    // Specify validation error messages
    messages: {
      codice_materiale: "Per favore insierisci il codice materiale",
      descr_codice_materiale: "Per favore insierisci la descrizione del materiale",
      quantita: "Per favore insierisci la quantita'",
      azienda_provenienza: "Per favore insierisci l'azienda di provenienza",
      unita_locale: "Per favore insierisci il cantiere di provenienza del materiale",
      azienda_destinazione: "Per favore insierisci l'azienda smaltitrice",
      data_inizio: "Per favore insierisci la data di inizio del viaggio",
      ora_inizio: "Per favore insierisci l'ora di inizio del viaggio",
      data_fine: "Per favore insierisci la data di fine del viaggio",
      ora_fine: "Per favore insierisci l'ora di fine del viaggio",
      cup_cig: "Per favore insierisci il CUP/CIG",
      //password: {
      //  required: "Please provide a password",
      //  minlength: "Your password must be at least 5 characters long"
      //},
      //email: "Please enter a valid email address"
    },
    // Make sure the form is submitted to the destination defined
    // in the "action" attribute of the form when valid
    submitHandler: function(form) {
      form.submit();
    }
  });
});