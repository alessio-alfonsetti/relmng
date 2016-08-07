package adp.realmng.model;

/**
 * The entity for the Transport activity of the products can be:
 * 	- Cliente (persona fisica o azienda)
 * 	- Produttore di materiali di scarto - puo' anche essere il Cliente
 * 	- Smaltitore di materiali di scarto
 * 
 * @author Alessio Alfonsetti
 *
 */
public class GAEntityView {

	private int id;
	private String uuid;
	private String ragioneSociale;
	private String nomePersona;
	private String firstname;
	private String middlename;
	private String lastname;
	private String billingAddress;
	private String partitaIVA;
	private String paymentType;
	// The attribute defines if this is a produttore (and it is a company), or smaltitore 
	private String entityType;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getRagioneSociale() {
		return ragioneSociale;
	}
	public void setRagioneSociale(String ragioneSociale) {
		this.ragioneSociale = ragioneSociale;
	}
	public String getNomePersona() {
		return nomePersona;
	}
	public void setNomePersona(String nomePersona) {
		this.nomePersona = nomePersona;
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
	public String getBillingAddress() {
		return billingAddress;
	}
	public void setBillingAddress(String billingAddress) {
		this.billingAddress = billingAddress;
	}
	public String getPartitaIVA() {
		return partitaIVA;
	}
	public void setPartitaIVA(String partitaIVA) {
		this.partitaIVA = partitaIVA;
	}
	public String getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
	public String getEntityType() {
		return entityType;
	}
	public void setEntityType(String entityType) {
		this.entityType = entityType;
	}
	
	
	
	
}
