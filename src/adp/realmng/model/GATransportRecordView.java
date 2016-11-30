package adp.realmng.model;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * This class is meant to represent record built for the activity of transporting
 * products from one site to another
 * 
 * @author Alessio Alfonsetti (ADPNet Consulting)
 *
 */
public class GATransportRecordView {

	private int id;
	private String uuid;
	private String codice_materiale;
	private String quantita;
	private String azienda_provenienza;
	private String azienda_destinazione;
	private String data_inizio;
	private String ora_inizio;
	private String data_fine;
	private String ora_fine;
	private String nota;	
	
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
	public String getCodice_materiale() {
		return codice_materiale;
	}
	public void setCodice_materiale(String codice_materiale) {
		this.codice_materiale = codice_materiale;
	}
	public String getQuantita() {
		return quantita;
	}
	public void setQuantita(String quantita) {
		this.quantita = quantita;
	}
	public String getAzienda_provenienza() {
		return azienda_provenienza;
	}
	public void setAzienda_provenienza(String azienda_provenienza) {
		this.azienda_provenienza = azienda_provenienza;
	}
	public String getAzienda_destinazione() {
		return azienda_destinazione;
	}
	public void setAzienda_destinazione(String azienda_destinazione) {
		this.azienda_destinazione = azienda_destinazione;
	}
	public String getData_inizio() {
		return data_inizio;
	}
	public void setData_inizio(String data_inizio) {
		this.data_inizio = data_inizio;
	}
	public String getOra_inizio() {
		return ora_inizio;
	}
	public void setOra_inizio(String ora_inizio) {
		this.ora_inizio = ora_inizio;
	}
	public String getData_fine() {
		return data_fine;
	}
	public void setData_fine(String data_fine) {
		this.data_fine = data_fine;
	}
	public String getOra_fine() {
		return ora_fine;
	}
	public void setOra_fine(String ora_fine) {
		this.ora_fine = ora_fine;
	}
	public String getNota() {
		return nota;
	}
	public void setNota(String nota) {
		this.nota = nota;
	}
	
}
