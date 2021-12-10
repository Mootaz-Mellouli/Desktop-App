package Client;

public class Client {

	
	private int id ;
	private String nom;
	private String prenom ;
	private String code ;
	private String telephone ;
	private String email ;
	
	public Client(int id , String nom,String prenom,String code , String telephone, String email) {
		super();
		this.id= id ;
		this.nom = nom;
		this.prenom = prenom;
		this.code = code ;
		this.telephone = telephone;
		this.email = email;
	}

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String Prenom) {
		this.prenom = Prenom;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}
	
	
	
}
