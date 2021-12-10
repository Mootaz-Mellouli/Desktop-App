package Article;


public class Article {
	
	private int id ;
	private int code ; 
	private String designation ;
	private int prix_achat ;
	private String prix_vente_HT;
	private String PoidsUni ; 
	private int stock ; 
	private String numeroBon ;
	
	public Article(int code)
	{
		this.code=code ;
	}
	
	
	public Article(int id, int code, String designation, int prix_achat , int stock ) {
		super();
		this.id = id;
		this.code = code;
		this.designation = designation;
		this.prix_achat = prix_achat;
		this.stock = stock ;
	}
	
	


	public Article(int id, int code, String designation, int prix_achat, String prix_vente_HT, String poidsUni,
			int stock) {
		super();
		this.id = id;
		this.code = code;
		this.designation = designation;
		this.prix_achat = prix_achat;
		this.prix_vente_HT = prix_vente_HT;
		PoidsUni = poidsUni;
		this.stock = stock;
	}

 


	public String getNumeroBon() {
		return numeroBon;
	}




	public void setNumeroBon(String numeroBon) {
		this.numeroBon = numeroBon;
	}




	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getCode() {
		return code;
	}


	public void setCode(int code) {
		this.code = code;
	}


	public String getDesignation() {
		return designation;
	}


	public void setDesignation(String designation) {
		this.designation = designation;
	}


	public int getPrix_achat() {
		return prix_achat;
	}


	public void setPrix_achat(int prix_achat) {
		this.prix_achat = prix_achat;
	}


	public String getPrix_vente_HT() {
		return prix_vente_HT;
	}


	public void setPrix_vente_HT(String prix_vente_HT) {
		this.prix_vente_HT = prix_vente_HT;
	}


	public String getPoidsUni() {
		return PoidsUni;
	}


	public void setPoidsUni(String poidsUni) {
		PoidsUni = poidsUni;
	}


	public int getStock() {
		return stock;
	}


	public void setStock(int stock) {
		this.stock = stock;
	}
	


	
	
	
	

}
