package BonReception;

import java.util.Date;

public class BonDeReception  {

	private int id ; 
	private String numeroBon ; 
	private String fournisseur ; 
	private String reference ; 
	private Date dateCreation ;
	private int TotaleHTGen ; 
	private int TotaleTTCGen ; 

	public BonDeReception(int id, String numeroBon, String fournisseur, String reference, Date dateCreation) {
		super();
		this.id = id;
		this.numeroBon = numeroBon;
		this.fournisseur = fournisseur;
		this.reference = reference;
		this.dateCreation = dateCreation;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getnumeroBon() {
		return numeroBon;
	}
	public void setnumeroBon(String numeroBon) {
		this.numeroBon = numeroBon;
	}
	public String getFournisseur() {
		return fournisseur;
	}
	public void setFournisseur(String fournisseur) {
		this.fournisseur = fournisseur;
	}
	public String getReference() {
		return reference;
	}
	public void setReference(String reference) {
		this.reference = reference;
	}
	public Date getDateCreation() {
		return dateCreation;
	}
	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

		public int getTotaleHTGen() {
		return TotaleHTGen;
	}


	public void setTotaleHTGen(int totaleHTGen) {
		TotaleHTGen = totaleHTGen;
	}


	public int getTotaleTTCGen() {
		return TotaleTTCGen;
	}


	public void setTotaleTTCGen(int totaleTTCGen) {
		TotaleTTCGen = totaleTTCGen;
	}
	
	
	
}
