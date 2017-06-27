package adp.realmng.model;

/**
 * This method models the Categoria table on the database
 * 
 * @author ADPNet Consulting
 *
 */
public class Categoria {

	private int id;
	private String uuid;
	private String nome;
	private String nota;
	
	// Constructors
	public Categoria() { }

	/* Getters and Setters */
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
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNota() {
		return nota;
	}

	public void setNota(String nota) {
		this.nota = nota;
	}
	
}
