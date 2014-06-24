package adp.realmng.model;

/**
 * This method models the Cliente table on the database
 * 
 * @author ADPNet Consulting
 *
 */
public class Customer {

	private int uuid;
	private String email;
	private String password;
	private String nome;
	private String cognome;
	private int indirizzo_uuid;
	private int tipo_uuid;
	private String note;
	private String partita_iva;
	private String codice_fiscale;
	private String num_tel;
	private String num_fax;
	private String num_cell;
	
	// Constructors
	public Customer(int uuid, String email, int tipo_uuid) {
		
	}

	public Customer(String email, String nome, int uuid) {
		setUuid(uuid);
		setNome(nome);
		setEmail(email);
	}

	public Customer() {
	}

	//getter and setter methods
	public int getUuid() {
		return uuid;
	}

	public void setUuid(int uuid) {
		this.uuid = uuid;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public int getIndirizzo_uuid() {
		return indirizzo_uuid;
	}

	public void setIndirizzo_uuid(int indirizzo_uuid) {
		this.indirizzo_uuid = indirizzo_uuid;
	}

	public int getTipo_uuid() {
		return tipo_uuid;
	}

	public void setTipo_uuid(int tipo_uuid) {
		this.tipo_uuid = tipo_uuid;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
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
	
	public String getNum_tel() {
		return num_tel;
	}

	public void setNum_tel(String num_tel) {
		this.num_tel = num_tel;
	}

	public String getNum_fax() {
		return num_fax;
	}

	public void setNum_fax(String num_fax) {
		this.num_fax = num_fax;
	}

	public String getNum_cell() {
		return num_cell;
	}

	public void setNum_cell(String num_cell) {
		this.num_cell = num_cell;
	}
}
