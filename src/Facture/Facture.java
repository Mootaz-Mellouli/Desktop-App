package Facture;

import java.util.Date;

public class Facture {

    private int numeroFacture ;
    private String reference ;
    private String client ;
    private Date dateCreation ;
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

    public Facture(int numeroFacture, String reference, String client,  Date dateCreation, int TotaleHTGen , int TotaleTTCGen) {
        super();
        this.numeroFacture = numeroFacture;
        this.reference = reference;
        this.client = client;
        this.dateCreation = dateCreation;
        this.TotaleHTGen=TotaleHTGen;
        this.TotaleTTCGen=TotaleTTCGen;
    }
    public int getNumeroDevis() {
        return numeroFacture;
    }
    public void setNumeroDevis(int numeroFacture) {
        this.numeroFacture = numeroFacture;
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
    public Date getDateCreation() {
        return dateCreation;
    }
    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }
}
