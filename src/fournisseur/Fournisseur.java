package fournisseur;

public class Fournisseur {

	private int id ; 
	private String code ;
	private String nom_societe ;
	private String telephone ; 
	private String email ;
	private String adresse ;
	 
	
	public Fournisseur(int id,String code , String nom_societe, String telephone, String email,String adresse) {
		super();
		this.id = id;
		this.code = code ;
		this.nom_societe = nom_societe;
		this.telephone = telephone;
		this.email = email;
		this.adresse=adresse ;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getNom_societe() {
		return nom_societe;
	}


	public void setNom_societe(String nom_societe) {
		this.nom_societe = nom_societe;
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


	public String getAdresse() {
		return adresse;
	}


	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}


	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}
	
	
	
	
}
