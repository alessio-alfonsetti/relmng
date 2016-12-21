package adp.realmng.model;

import java.sql.Timestamp;

/**
 * This class is a representation of the List of Prices available for every customer
 * 
 * @author Alfonsetti
 *
 */
public class Prices {

	private int id;
	private String uuid;
	private String uuid_cliente;
	private String cer;
	private String cer_descr;
	private String imponibile;
	private String iva;
	private String totale;
	private Timestamp last_update;
	private String nota_update;
	
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
	public String getUuid_cliente() {
		return uuid_cliente;
	}
	public void setUuid_cliente(String uuid_cliente) {
		this.uuid_cliente = uuid_cliente;
	}
	public String getCer() {
		return cer;
	}
	public void setCer(String cer) {
		this.cer = cer;
	}
	public String getCer_descr() {
		return cer_descr;
	}
	public void setCer_descr(String cer_descr) {
		this.cer_descr = cer_descr;
	}
	public String getImponibile() {
		return imponibile;
	}
	public void setImponibile(String imponibile) {
		this.imponibile = imponibile;
	}
	public String getIva() {
		return iva;
	}
	public void setIva(String iva) {
		this.iva = iva;
	}
	public String getTotale() {
		return totale;
	}
	public void setTotale(String totale) {
		this.totale = totale;
	}
	public Timestamp getLast_update() {
		return last_update;
	}
	public void setLast_update(Timestamp last_update) {
		this.last_update = last_update;
	}
	public String getNota_update() {
		return nota_update;
	}
	public void setNota_update(String nota_update) {
		this.nota_update = nota_update;
	}
	
}
