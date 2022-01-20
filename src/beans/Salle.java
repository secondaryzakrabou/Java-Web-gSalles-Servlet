package beans;

public class Salle {

	private int id;
	private String type;
	private String libelle;
	
	public Salle(int id, String type, String libelle) {
		this.id = id;
		this.type = type;
		this.libelle = libelle;
	}

	public Salle(String type, String libelle) {
		super();
		this.type = type;
		this.libelle = libelle;
	}
	
	public Salle(int  id, String libelle) {
		super();
		this.id = id;
		this.libelle = libelle;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	@Override
	public String toString() {
		return "Salle [id=" + id + ", type=" + type + ", libelle=" + libelle + "]";
	}
	
	
	
	
}
