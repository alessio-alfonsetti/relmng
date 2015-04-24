package adp.realmng.model;

import java.sql.Timestamp;

/**
 * This method models the Cliente table on the database
 * 
 * @author ADPNet Consulting
 *
 */
public class Customer {

	private int id;
	private String uuid;
	private String username;
	private String firstname;
	private String middlename;
	private String lastname;
	private String password;
	private String ragione_sociale;
	private String partita_iva;
	private String codice_fiscale;
	private String indirizzo;
	private String numero_telefono;
	private String numero_cellulare;
	private String numero_fax;
	private String email;
	private String nota;
	private boolean enabled;
	private int id_ruolo;
	private Timestamp data_inserimento;
	private boolean notifica_creazione_incompleta;
	private String iban;
	
	// Constructors
	public Customer(int uuid, String email, int tipo_uuid) {
		
	}

	public Customer(String email, String nome, String uuid) {
		setUuid(uuid);
		setFirstname(nome);
		setEmail(email);
	}

	public Customer() { }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	/* Getters and Setters */
	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getMiddlename() {
		return middlename;
	}

	public void setMiddlename(String middlename) {
		this.middlename = middlename;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRagione_sociale() {
		return ragione_sociale;
	}

	public void setRagione_sociale(String ragione_sociale) {
		this.ragione_sociale = ragione_sociale;
	}

	public String getPartita_iva() {
		return partita_iva;
	}

	public void setPartita_iva(String partita_iva) {
		this.partita_iva = partita_iva;
	}

	public String getCodice_fiscale() {
		return codice_fiscale;
	}

	public void setCodice_fiscale(String codice_fiscale) {
		this.codice_fiscale = codice_fiscale;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public String getNumero_telefono() {
		return numero_telefono;
	}

	public void setNumero_telefono(String numero_telefono) {
		this.numero_telefono = numero_telefono;
	}

	public String getNumero_cellulare() {
		return numero_cellulare;
	}

	public void setNumero_cellulare(String numero_cellulare) {
		this.numero_cellulare = numero_cellulare;
	}

	public String getNumero_fax() {
		return numero_fax;
	}

	public void setNumero_fax(String numero_fax) {
		this.numero_fax = numero_fax;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNota() {
		return nota;
	}

	public void setNota(String nota) {
		this.nota = nota;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public int getId_ruolo() {
		return id_ruolo;
	}

	public void setId_ruolo(int id_ruolo) {
		this.id_ruolo = id_ruolo;
	}

	public Timestamp getData_inserimento() {
		return data_inserimento;
	}

	public void setData_inserimento(Timestamp data_inserimento) {
		this.data_inserimento = data_inserimento;
	}

	public boolean isNotifica_creazione_incompleta() {
		return notifica_creazione_incompleta;
	}

	public void setNotifica_creazione_incompleta(
			boolean notifica_creazione_incompleta) {
		this.notifica_creazione_incompleta = notifica_creazione_incompleta;
	}

	public String getIban() {
		return iban;
	}

	public void setIban(String iban) {
		this.iban = iban;
	}

	
}
