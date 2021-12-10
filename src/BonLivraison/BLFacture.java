package BonLivraison;

import java.util.Date;

public class BLFacture {
	
	private int id ;
	private Boolean checked;
	private String reference ; 
	private Date  dateCreation ;
	private int totaleHT ; 
	private int totaleTTC ; 
	private int numeroBL ;
	
	
	
	public BLFacture(int id, Boolean checked, String reference, Date dateCreation, int totaleHT, int totaleTTC,
			int numeroBL) {
		super();
		this.id = id;
		this.checked= checked;
		this.reference = reference;
		this.dateCreation = dateCreation;
		this.totaleHT = totaleHT;
		this.totaleTTC = totaleTTC;
		this.numeroBL = numeroBL;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Boolean getChecked() {
		return checked;
	}
	public void setChecked(Boolean checked) {
		this.checked= checked;
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
	public int getTotaleHT() {
		return totaleHT;
	}
	public void setTotaleHT(int totaleHT) {
		this.totaleHT = totaleHT;
	}
	public int getTotaleTTC() {
		return totaleTTC;
	}
	public void setTotaleTTC(int totaleTTC) {
		this.totaleTTC = totaleTTC;
	}
	public int getNumeroBL() {
		return numeroBL;
	}
	public void setNumeroBL(int numeroBL) {
		this.numeroBL = numeroBL;
	}
	
	
	

}
