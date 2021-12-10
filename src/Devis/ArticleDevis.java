package Devis;

public class ArticleDevis {

    private int id ;
    private int code ;
    private String designation ;
    private String prixUHT;
    private String PoidsUni ;
    private String numeroBon ;
    private String qte ;
    private String tva ;
    private int totaleHT ;
    private int totaleTTC ;



    public ArticleDevis(int id, int code, String designation, String prixUHT, String poidsUni, String numeroBon,
                     String qte, String tva, int totaleHT, int totaleTTC) {
        super();
        this.id = id;
        this.code = code;
        this.designation = designation;
        this.prixUHT = prixUHT;
        this.PoidsUni = poidsUni;
        this.numeroBon = numeroBon;
        this.qte = qte;
        this.tva = tva;
        this.totaleHT = totaleHT;
        this.totaleTTC = totaleTTC;
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
    public String getPrixUHT() {
        return prixUHT;
    }
    public void setPrixUHT(String prixUHT) {
        this.prixUHT = prixUHT;
    }
    public String getPoidsUni() {
        return PoidsUni;
    }
    public void setPoidsUni(String poidsUni) {
        PoidsUni = poidsUni;
    }
    public String getNumeroBon() {
        return numeroBon;
    }
    public void setNumeroBon(String numeroBon) {
        this.numeroBon = numeroBon;
    }
    public String getQte() {
        return qte;
    }
    public void setQte(String qte) {
        this.qte = qte;
    }
    public String getTva() {
        return tva;
    }
    public void setTva(String tva) {
        this.tva = tva;
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
}
