package adp.realmng.model;

import java.sql.Timestamp;

public class Worksite {
	
	private int id;
	private String uuid;
	private String nome_cantiere;
	private String indirizzo;
	private String nota;
	private Timestamp data_inizio_cantiere;
	private Timestamp data_fine_cantiere;
	
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
	public String getNome_cantiere() {
		return nome_cantiere;
	}
	public void setNome_cantiere(String nome_cantiere) {
		this.nome_cantiere = nome_cantiere;
	}
	public String getIndirizzo() {
		return indirizzo;
	}
	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}
	public String getNota() {
		return nota;
	}
	public void setNota(String nota) {
		this.nota = nota;
	}
	public Timestamp getData_inizio_cantiere() {
		return data_inizio_cantiere;
	}
	public void setData_inizio_cantiere(Timestamp data_inizio_cantiere) {
		this.data_inizio_cantiere = data_inizio_cantiere;
	}
	public Timestamp getData_fine_cantiere() {
		return data_fine_cantiere;
	}
	public void setData_fine_cantiere(Timestamp data_fine_cantiere) {
		this.data_fine_cantiere = data_fine_cantiere;
	}
	
}
