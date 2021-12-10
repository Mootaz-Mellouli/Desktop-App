package Devis;

import java.util.Date;

public class Devis {
	
	private int numeroDevis ; 
	private String reference ; 
	private String client ; 
	private String contact ; 
	private Date dateCreation ; 
	private Date dateValidation ; 
	private String info ;
	private int TotaleHTGen ;
	private int TotaleTTCGen ;

	public int getTotaleHTGen() {
		return TotaleHTGen;
	}

	public int getTotaleTTCGen() {
		return TotaleTTCGen;
	}

	public void setTotaleHTGen(int totaleHTGen) {
		TotaleHTGen = totaleHTGen;
	}

	public void setTotaleTTCGen(int totaleTTCGen) {
		TotaleTTCGen = totaleTTCGen;
	}

	public Devis(int numeroDevis, String reference, String client, String contact, Date dateCreation,
				 Date dateValidation, String info, int TotaleHTGen , int TotaleTTCGen) {
		super();
		this.numeroDevis = numeroDevis;
		this.reference = reference;
		this.client = client;
		this.contact = contact;
		this.dateCreation = dateCreation;
		this.dateValidation = dateValidation;
		this.info = info;
		this.TotaleHTGen=TotaleHTGen;
		this.TotaleTTCGen=TotaleTTCGen;
	}
	public int getNumeroDevis() {
		return numeroDevis;
	}
	public void setNumeroDevis(int numeroDevis) {
		this.numeroDevis = numeroDevis;
	}
	public String getReference() {
		return reference;
	}
	public void setReference(String reference) {
		this.reference = reference;
	}
	public String getClient() {
		return client;
	}
	public void setClient(String client) {
		this.client = client;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public Date getDateCreation() {
		return dateCreation;
	}
	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}
	public Date getDateValidation() {
		return dateValidation;
	}
	public void setDateValidation(Date dateValidation) {
		this.dateValidation = dateValidation;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	} 
	
	

}
