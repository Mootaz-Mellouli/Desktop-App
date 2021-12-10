package BonLivraison;

import java.util.Date;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.control.CheckBox;



public class BonDeLivraison {

	private int numeroBon ; 
	private String client ; 
	private String reference ; 
	private String adresse_livraison ; 
	private String info_supp ; 
	private Date date_creation ;
	private Date date_livraison ;
	private String code_article ;
	private String designation ;
	private String qte ; 
	private String prixUHT ;
	private int tva ;
	private int Poids_uni ;
	private String remise ;
	private int totaleHT;
	private int totaleTTC;
	private int id ;
	
	private CheckBox select ;
	
	/*
	 * private SimpleBooleanProperty checked = new SimpleBooleanProperty(false);
	 * 
	 * 
	 * public SimpleBooleanProperty checkedProperty() { return this.checked; }
	 * 
	 * public java.lang.Boolean getChecked() { return this.checkedProperty().get();
	 * }
	 * 
	 * public void setChecked(final java.lang.Boolean checked) {
	 * this.checkedProperty().set(checked); }
	 */
	
	
	
	
	
	
	
	public CheckBox getSelect() {
		return select;
	}
	public void setSelect(CheckBox select) {
		this.select = select;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getNumeroBon() {
		return numeroBon;
	}
	public void setNumeroBon(int numeroBon) {
		this.numeroBon = numeroBon;
	}
	public String getClient() {
		return client;
	}
	public void setClient(String client) {
		this.client = client;
	}
	public String getNeference() {
		return reference;
	}
	public void setNeference(String reference) {
		this.reference = reference;
	}
	public String getAdresse_livraison() {
		return adresse_livraison;
	}
	public void setAdresse_livraison(String adresse_livraison) {
		this.adresse_livraison = adresse_livraison;
	}
	public String getInfo_supp() {
		return info_supp;
	}
	public void setInfo_supp(String info_supp) {
		this.info_supp = info_supp;
	}
	public Date getDate_creation() {
		return date_creation;
	}
	public void setDate_creation(Date date_creation) {
		this.date_creation = date_creation;
	}
	public Date getDate_livraison() {
		return date_livraison;
	}
	public void setDate_livraison(Date date_livraison) {
		this.date_livraison = date_livraison;
	}
	
	
	public String getReference() {
		return reference;
	}
	public void setReference(String reference) {
		this.reference = reference;
	}
	public String getCode_article() {
		return code_article;
	}
	public void setCode_article(String code_article) {
		this.code_article = code_article;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getQte() {
		return qte;
	}
	public void setQte(String qte) {
		this.qte = qte;
	}
	public String getPrixUHT() {
		return prixUHT;
	}
	public void setPrixUHT(String prixUHT) {
		this.prixUHT = prixUHT;
	}
	public int getTva() {
		return tva;
	}
	public void setTva(int tva) {
		this.tva = tva;
	}
	public int getPoids_uni() {
		return Poids_uni;
	}
	public void setPoids_uni(int poids_uni) {
		Poids_uni = poids_uni;
	}
	public String getRemise() {
		return remise;
	}
	public void setRemise(String remise) {
		this.remise = remise;
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
	public BonDeLivraison(int numeroBon, String client, String reference, String adresse_livraison, String info_supp,
			Date date_creation, Date date_livraison, String code_article, String designation, String qte,
			String prixUHT, int tva, int poids_uni,  String remise, int totaleHT,
			int totaleTTC) {
		super();
		this.numeroBon = numeroBon;
		this.client = client;
		this.reference = reference;
		this.adresse_livraison = adresse_livraison;
		this.info_supp = info_supp;
		this.date_creation = date_creation;
		this.date_livraison = date_livraison;
		this.code_article = code_article;
		this.designation = designation;
		this.qte = qte;
		this.prixUHT = prixUHT;
		this.tva = tva;
		this.Poids_uni = poids_uni;
		this.remise = remise;
		this.totaleHT = totaleHT;
		this.totaleTTC = totaleTTC;
	}
	public BonDeLivraison(String code_article, String designation, String qte, String prixUHT, int tva, int poids_uni,String remise,int totaleHT,int totaleTTC,int id) {
		super();
		
		this.code_article = code_article;
		this.designation = designation;
		this.qte = qte;
		this.prixUHT = prixUHT;
		this.tva = tva;
		this.Poids_uni = poids_uni;
		this.remise = remise;
		this.totaleHT=totaleHT;
		this.totaleTTC=totaleTTC;
		this.id=id;
		
	}
	public BonDeLivraison(int numeroBon, String designation, Date date_livraison,CheckBox select) {
		super();
		
		this.numeroBon=numeroBon;
		this.designation = designation;
		this.date_livraison=date_livraison;
		this.select=select;
	
	}
	
	public BonDeLivraison(int numeroBon, String designation, Date date_livraison) {
		super();
		
		this.numeroBon=numeroBon;
		this.designation = designation;
		this.date_livraison=date_livraison;
	
	
	}
	
	
	
	
	
	
}
