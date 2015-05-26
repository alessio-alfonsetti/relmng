package adp.realmng.model;

import java.util.Date;

public class Invoice {
	
	private int id;
	private String uuid;
	private String partita_iva;
	private int id_utente;
	private Date data_emissione;
	private String descrizione;
	private String importo;
	private String iva;
	private String importo_totale;
	private String stato_pagamento;
	private String nome_cantiere;
	
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
	public String getPartita_iva() {
		return partita_iva;
	}
	public void setPartita_iva(String partita_iva) {
		this.partita_iva = partita_iva;
	}
	public int getId_utente() {
		return id_utente;
	}
	public void setId_utente(int id_utente) {
		this.id_utente = id_utente;
	}
	public Date getData_emissione() {
		data_emissione = new Date();
		return data_emissione;
	}
	public void setData_emissione(Date data_emissione) {
		this.data_emissione = data_emissione;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public String getImporto() {
		return importo;
	}
	public void setImporto(String importo) {
		this.importo = importo;
	}
	public String getIva() {
		return iva;
	}
	public void setIva(String iva) {
		this.iva = iva;
	}
	public String getImporto_totale() {
		return importo_totale;
	}
	public void setImporto_totale(String importo_totale) {
		this.importo_totale = importo_totale;
	}
	public String getStato_pagamento() {
		return stato_pagamento;
	}
	public void setStato_pagamento(String stato_pagamento) {
		this.stato_pagamento = stato_pagamento;
	}
	public String getNome_cantiere() {
		return nome_cantiere;
	}
	public void setNome_cantiere(String nome_cantiere) {
		this.nome_cantiere = nome_cantiere;
	}
	@Override
	public String toString() {
		return "Invoice [uuid=" + uuid + ", partita_iva=" + partita_iva
				+ ", id_utente=" + id_utente + ", data_emissione="
				+ data_emissione + ", descrizione=" + descrizione
				+ ", importo_totale=" + importo_totale + ", stato_pagamento="
				+ stato_pagamento + ", nome_cantiere=" + nome_cantiere + "]";
	}
	
}
