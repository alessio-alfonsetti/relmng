package adp.realmng.constants;

public class Constants {

	
	/********************************************************* CUSTOMERS ***********************************************************/
	
	/** ID */
	public static final String Customer_ID 										= "id";
	
	/** UUID */
	public static final String Customer_UUID 									= "uuid";

	/** USERNAME */
	public static final String Customer_USERNAME 								= "username";
	
	/** FIRSTNAME */
	public static final String Customer_FIRSTNAME		 						= "firstname";
	
	/** MIDDLENAME */
	public static final String Customer_MIDDLENAME								= "middlename";
	
	/** LASTNAME */
	public static final String Customer_LASTNAME 								= "lastname";
	
	/** PASSWORD */
	public static final String Customer_PASSWORD								= "password";
	
	/** RAGIONE_SOCIALE */
	public static final String Customer_RAGIONE_SOCIALE							= "ragione_sociale";
	
	/** PARTITA_IVA */
	public static final String Customer_PARTITA_IVA								= "partita_iva";
	
	/** CODICE_FISCALE */
	public static final String Customer_CODICE_FISCALE							= "codice_fiscale";
	
	/** INDIRIZZO */
	public static final String Customer_INDIRIZZO								= "indirizzo";
	
	/** NUMERO_TELEFONO */
	public static final String Customer_NUMERO_TELEFONO							= "numero_telefono";
	
	/** NUMERO_CELLULARE */
	public static final String Customer_NUMERO_CELLULARE						= "numero_cellulare";
	
	/** NUMERO_FAX */
	public static final String Customer_NUMERO_FAX								= "numero_fax";
	
	/** EMAIL */
	public static final String Customer_EMAIL 									= "email";
	
	/** NOTA */
	public static final String Customer_NOTA 									= "nota";
	
	/** ENABLED */
	public static final String Customer_ENABLED 								= "enabled";
	
	/** ID_RUOLO */
	public static final String Customer_IDRUOLO 								= "id_ruolo";
	
	/** DATA_INSERIMENTO */
	public static final String Customer_DATA_INSERIMENTO						= "data_inserimento";
	
	/** NOTIFICA_CREAZIONE_INCOMPLETA */
	public static final String Customer_NOTIFICA_CREAZIONE_INCOMPLETA			= "notifica_creazione_incompleta";
	
	/** IBAN */
	public static final String Customer_IBAN									= "iban";

	/********************************************************* INVOICES ************************************************************/
	
	/** ID */
	public static final String Invoice_ID 										= "id";

	/** UUID */
	public static final String Invoice_UUID 									= "uuid";
	
	/** PARTITA_IVA */
	public static final String Invoice_PARTITA_IVA								= "partita_iva";
	
	/** ID_UTENTE*/
	public static final String Invoice_ID_UTENTE								= "id_utente";
	
	/** DATA_EMISSIONE */
	public static final String Invoice_DATA_EMISSIONE							= "data_emissione";
	
	/** DESCRIZIONE */
	public static final String Invoice_DESCRIZIONE								= "descrizione";
	
	/** IMPORTO */
	public static final String Invoice_IMPORTO									= "importo";
	
	/** IVA */
	public static final String Invoice_IVA										= "iva";
	
	/** IMPORTO_TOTALE */
	public static final String Invoice_IMPORTO_TOTALE							= "importo_totale";
	
	/** STATO_PAGAMENTO */
	public static final String Invoice_STATO_PAGAMENTO							= "stato_pagamento";
	
	/** NOME_CANTIERE */
	public static final String Invoice_NOME_CANTIERE							= "nome_cantiere";
	
	public static final String Invoice_stato_pagamento 							= "non_pagato";
	
	
	/********************************************************* WORKSITES ***********************************************************/
	
	/** ID */
	public static final String Worksite_ID 										= "id";
	
	/** UUID */
	public static final String Worksite_UUID 									= "uuid";
	
	/** FIRSTNAME */
	public static final String Worksite_NAME			 						= "nome_cantiere";
	
	/** INDIRIZZO */
	public static final String Worksite_INDIRIZZO								= "indirizzo";
	
	/** NOTA */
	public static final String Worksite_NOTA 									= "nota";
	
	/********************************************************* PRICES *************************************************************/
	
	/** ID */
	public static final String Price_List_ID									= "id";
	
	/** UUID */
	public static final String Price_List_UUID 									= "uuid";
	
	/** CER */
	public static final String Price_List_CER			 						= "cer";
	
	/** DESCRIZIONE CER */
	public static final String Price_List_CER_DESCR								= "cer_descr";
	
	/** UUID CLIENTE */
	public static final String Price_List_UUID_CLIENTE							= "uuid_cliente";
	
	/** IMPONIBILE */
	public static final String Price_List_IMPONIBILE							= "imponibile";
	
	/** IVA */
	public static final String Price_List_IVA									= "iva";
	
	/** TOTALE */
	public static final String Price_List_TOTALE								= "totale";
	
	/** LAST UPDATE */
	public static final String Price_List_LAST_UPDATE							= "last_update";
	
	/** NOTA UPDATE */
	public static final String Price_List_NOTA_UPDATE							= "nota_update";
	
	/** Note Attivita' */
	public static final String Price_DESCR_PRIMA_CREAZIONE							= "Prima creazione del prezzo nel listino";
	public static final String Price_DESCR_MODIFICA_PREZZO							= "Modifica del prezzo nel listino";
	
	/********************************************************* ERROR CODES *********************************************************/

	/** Too many customers for one partita_iva */
	public static final String ERR_TOO_MANY_CUSTOMERS_FOR_ONE_PARTITA_IVA		= "ERR_1";
	
	/** Too many customers for one id_utente */
	public static final String ERR_TOO_MANY_CUSTOMERS_FOR_ONE_ID_UTENTE			= "ERR_2";
}
