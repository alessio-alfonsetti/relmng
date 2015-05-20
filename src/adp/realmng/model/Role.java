package adp.realmng.model;

/**
 * This method models the Ruoli table on the database
 * 
 * @author ADPNet Consulting
 *
 */
public class Role {

	private int id;
	private int id_utente;
	private String nome_ruolo;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getId_utente() {
		return id_utente;
	}
	public void setId_utente(int id_utente) {
		this.id_utente = id_utente;
	}
	public String getNome_ruolo() {
		return nome_ruolo;
	}
	public void setNome_ruolo(String nome_ruolo) {
		this.nome_ruolo = nome_ruolo;
	}
	
}
