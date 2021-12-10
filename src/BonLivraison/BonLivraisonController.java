package BonLivraison;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.scene.control.ComboBox;

import javafx.scene.control.TextArea;

import javafx.scene.input.MouseEvent;

import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class BonLivraisonController implements Initializable {
	@FXML
	private TextField tfnum;
	@FXML
	private TextField tfref;
	@FXML
	private ComboBox tfclient;
	@FXML
	private TextField tfadresse;
	@FXML
	private TextField tfdatecr;
	@FXML
	private DatePicker tfdateliv;
	@FXML
	private TextArea tfinfo;
	@FXML
	private Button btnvalider;
	@FXML
	private TextField tcCodeArticle;
	@FXML
	private TextField tcDesignation;
	@FXML
	private TextField tcPVUVHT;
	@FXML
	private TextField tcQte;
	@FXML
	private TextField tcPoidsU;
	@FXML
	private TextField tcTVA;
	@FXML
	private TextField tcRemise;
	@FXML
	private TextField tcTotaleHT;
	@FXML
	private TextField tcTotalTTC;
	
	@FXML
	private TextField tfid;
	
	@FXML
    private TableView<BonDeLivraison> article;

    @FXML
    private TableColumn CodeArticle;

    @FXML
    private TableColumn Designation;

    @FXML
    private TableColumn PrixUHT;

    @FXML
    private TableColumn Qte;

    @FXML
    private TableColumn PoidsU;

    @FXML
    private TableColumn tva;

    @FXML
    private TableColumn Remise;
    
    @FXML
    private TableColumn totalHT;

    @FXML
    private TableColumn totalTTC;
    
    @FXML
    private TableColumn id;
    
    @FXML
    private Button insererArticle;
    
    @FXML
    private Button modifierArticle;

    @FXML
    private Button supprimerArticle;

    @FXML
    void supprimerArticle(MouseEvent event) {

    	if(event.getSource()==supprimerArticle) 
		{
			Alert alert = new Alert (AlertType.CONFIRMATION) ;
			alert.setHeaderText("�tes-vous s�r de vouloir supprimer cet article");
			Optional<ButtonType> result = alert.showAndWait();
			if(result.isPresent()&&result.get()==ButtonType.OK)
			{
				deleteArticle();	
				 clear();
			}
		}
    }

    @FXML
    void modifierArticle(MouseEvent event) {

    	if(event.getSource()==modifierArticle)
		{
			Alert alert = new Alert (AlertType.CONFIRMATION) ;
			alert.setHeaderText("�tes-vous s�r de vouloir modifier cet article");
			Optional<ButtonType> result = alert.showAndWait();
			if(result.isPresent()&&result.get()==ButtonType.OK)
			{
				modifierArticle();	
				clear();
			}
		}
    }
    
    @FXML
    void selectArticle(MouseEvent event) {
    	BonDeLivraison bdl =	article.getSelectionModel().getSelectedItem();
    	tcCodeArticle.setText(""+bdl.getCode_article());
    	tcDesignation.setText(""+bdl.getDesignation());
    	tcPVUVHT.setText(""+bdl.getPrixUHT());
    	tcQte.setText(""+bdl.getQte());
    	tcPoidsU.setText(""+bdl.getPoids_uni());
    	tcTVA.setText(""+bdl.getTva());
    	tcRemise.setText(""+bdl.getRemise());
    	tfid.setText(""+bdl.getId());
    	
    	
    }
    
    

    @FXML
    void ajouterArticle(MouseEvent event) {
    	InsererArticleTableau();
    	VoirArticle();	
		//clear();
		totalHT();
    }

	// Event Listener on ComboBox[#tfclient].onAction
	@FXML
	public void selectClient(ActionEvent event) {
		// TODO Autogenerated
	}
	// Event Listener on Button[#btnvalider].onMouseClicked
	@FXML
	public void validerBL(MouseEvent event) {
		 if(event.getSource() == btnvalider)
			{	
			 Alert alert = new Alert (AlertType.CONFIRMATION) ;
				alert.setHeaderText("�tes-vous s�r de vouloir valider ce bon de livraison");
				Optional<ButtonType> result = alert.showAndWait();
				if(result.isPresent()&&result.get()==ButtonType.OK)
				{
			InsererArticle();
			moinsStock();
			

			 (((Node) event.getSource())).getScene().getWindow().hide();
				}
			}
	}
	/***************************************** VIDER LA TABLE ***********************************/
	public void viderTable()
	{
		String query2="TRUNCATE TABLE articlebl";
		executeQuery(query2);
	}
	/******************************************** CONNECTION ***********************************************************************/
	public Connection getConnection()
	{
		Connection conn ;
		try {
			conn =DriverManager.getConnection("jdbc:mysql://localhost:3306/atec","root","");
		return conn ;
		} catch (SQLException e) {
			System.out.println("Error" + e.getMessage());
			return null ;
		}
	}
/*********************************************** REMPLIR COMBO BOX *******************************************************/	
	public void remplirComboBox()
	{
		Connection conn = getConnection();
		String query = "SELECT nom,prenom FROM client" ; 
		Statement st ;
		ResultSet rs ;
		
		try {
			st = conn.createStatement();
			rs = st.executeQuery(query);
			
			while(rs.next())
			{
				String nom = rs.getString("nom");
				String prenom = rs.getString("prenom");
				String item = nom+" "+prenom ;
				tfclient.getItems().add(item); 
				
			}
			 
			}catch(Exception e) {
				 e.printStackTrace();
			}
	}
	/*************************** EFFACER ARTICLE DE LA TABLE ARTICLE *******************************************************************************/
	public void deleteArticle()
	{
		  String query="DELETE  FROM articlebl where id ="+tfid.getText()+"";
		  executeQuery(query);
		  VoirArticle();
	}
	/**************************** MODIFIER ARTICLE DE LA TABLE ARTICLE**********************************************************************************************/
	public void modifierArticle()
	{
		String query = "UPDATE articlebl SET code_article  = '" +tcCodeArticle.getText() + "', designation = '" + tcDesignation.getText() + "', qte = '" +tcQte.getText() + "', prixUHT = '" +tcPVUVHT.getText() + "', tva = '" +tcTVA.getText() +"', Poids_uni = '" +tcPoidsU.getText() +"', remise = '" +tcRemise.getText() +"' WHERE  id = " +tfid.getText() + "";
		  executeQuery(query);
		  VoirArticle();
	}
	
	
	/******************************************* SET NUMERO BON LIVRAISON ******************************************************/
	public void setNumero()
	{
		Connection conn = getConnection();
		String query = "SELECT numeroBon FROM bonlivraison" ; 
		Statement st ;
		ResultSet rs ;
		
		try {
			st = conn.createStatement();
			rs = st.executeQuery(query);
			
			while(rs.next())
			{
				tfnum.setText("BL"+rs.getInt("numeroBon"));
			}
			 
			}catch(Exception e) {
				 e.printStackTrace();
			}
		
	}
	
	/*************************************** DATE CREATION ******************************************************************/
	public void setDateCreation()
	{
		Date cudate = new Date();
		SimpleDateFormat dateformat = new SimpleDateFormat();
		tfdatecr.setText(""+dateformat.format(cudate));
	}
	
	/**************************************** EXECUTE QUERY ****************************************************************/
	private void executeQuery(String query) {
		
		Connection conn = getConnection();	
		Statement st ;
		
		try {
			st = conn.createStatement();
			st.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/******************************************************* AJOUTER UN ARTICE DANS LE TABLEAU ********************************************************/
	public void InsererArticleTableau()
	{
		int x = 1 ;
		int y = 1 ;
		
		String query="INSERT INTO articlebl(code_article, designation, qte, prixUHT, tva, 	Poids_uni,  remise,totaleHT,totaleTTC ) VALUES ('"+tcCodeArticle.getText()+"','"+tcDesignation.getText()+"','"+tcQte.getText()+"','"+tcPVUVHT.getText()+"','"+tcTVA.getText()+"','"+tcPoidsU.getText()+"','"+tcRemise.getText()+"',"+x+","+y+")" ;
		executeQuery(query);
		/*
		 * int res = 10 ; int x = 20 ; String
		 * query="INSERT INTO articlebl(totaleHT,totaleTTC) VALUES ('"+res+"','"+x+"')"
		 * ; executeQuery(query);
		 */
	}
	/*************************************************** VALIDER LE BON DE LIVRAISON************************************************************************/
	public void InsererArticle()
	{
	
		//WHERE id='"+MAX(id)+"' ";
		//String query="INSERT INTO bonlivraison( client, reference, adresse_livraison, info_supp, date_creation, date_livraison) VALUES ('"+tfclient.getValue()+"','"+tfref.getText()+"','"+tfadresse.getText()+"','"+tfinfo.getText()+"','"+tfdatecr.getText()+"','"+tfdateliv.getValue()+"')" ;
		String query="INSERT INTO bonlivraison( client, reference, adresse_livraison, info_supp, date_creation, date_livraison) VALUES ('"+tfclient.getValue()+"','"+tfref.getText()+"','"+tfadresse.getText()+"','"+tfinfo.getText()+"','"+tfdatecr.getText()+"','"+tfdateliv.getValue()+"')" ;
		executeQuery(query);
		int id = getID();
		String query2 ="INSERT INTO bonlivraison (code_article, designation, qte, remise,tva,prixUHT,poidsU,totaleHT,totaleTTC)  SELECT code_article, designation, qte,remise,tva,prixUHT,Poids_uni,totaleHT,totaleTTC FROM articlebl WHERE id='"+id+"' ";
		executeQuery(query2);
		//String query21="UPDATE article SET stock=stock-'"+qte+"' WHERE code='"+code+"'";
			
		
	}
	/********************************  MOINS STOCK **********************************************************************************/
	public void moinsStock()
	{
		String code = null ;
		String qte = null ;
		Connection conn = getConnection();
		String query = "SELECT code_article,qte from articlebl";
		Statement st ;
		ResultSet rs ;
		try {
			st = conn.createStatement();
			rs = st.executeQuery(query);
		while(rs.next())
		{
			  code = rs.getString("code_article");
			  qte = rs.getString("qte");
		}
		}catch(Exception e) {
			 e.printStackTrace();
		}
		String query2="UPDATE article SET stock=stock-'"+qte+"' WHERE code='"+code+"'";
		executeQuery(query2);
	}
	/***************************************** AFFICHAGE DANS LE TABLEAU ********************************************************/
	
	public ObservableList<BonDeLivraison> getArticleListe()
	{
	
	ObservableList<BonDeLivraison> ArticleListe = FXCollections.observableArrayList();
	Connection conn = getConnection();
	String query = "SELECT * from articlebl";
	Statement st ;
	ResultSet rs ;
	
	try {
		st = conn.createStatement();
		rs = st.executeQuery(query);
		BonDeLivraison bdl ;
		while(rs.next())
		{
			bdl = new BonDeLivraison(rs.getString("code_article"),rs.getString("designation"),rs.getString("qte"),rs.getString("prixUHT"),rs.getInt("tva"),rs.getInt("Poids_uni"),rs.getString("remise"),rs.getInt("totaleHT"),rs.getInt("totaleTTC"),rs.getInt("id"));
			ArticleListe.add(bdl);
		}
		 
		}catch(Exception e) {
			 e.printStackTrace();
		}
	
	return ArticleListe ;
	
	}
	
	public void VoirArticle()
	{
		ObservableList<BonDeLivraison> list = getArticleListe();
		CodeArticle.setCellValueFactory(new PropertyValueFactory("code_article"));
		Designation.setCellValueFactory(new PropertyValueFactory("designation"));
		PrixUHT.setCellValueFactory(new PropertyValueFactory("prixUHT"));
		Qte.setCellValueFactory(new PropertyValueFactory("qte"));
		PoidsU.setCellValueFactory(new PropertyValueFactory("Poids_uni"));
		tva.setCellValueFactory(new PropertyValueFactory("tva"));
		Remise.setCellValueFactory(new PropertyValueFactory("remise"));
		totalHT.setCellValueFactory(new PropertyValueFactory("totaleHT"));
		totalTTC.setCellValueFactory(new PropertyValueFactory("totaleTTC"));
		id.setCellValueFactory(new PropertyValueFactory("id"));

		article.getItems().clear();

		article.setItems(list);
	
	}
	/************************************** GET ID **********************************************************************/
	public int  getID()
	{
		int res = 0 ;
		Connection conn = getConnection();
		String query = "SELECT id FROM articlebl ORDER BY ID DESC LIMIT 1";
		Statement st ;
		ResultSet rs ;
		try {
			st = conn.createStatement();
			rs = st.executeQuery(query);
		while(rs.next())
		{
			   res = rs.getInt("id");
		}
		}catch(Exception e) {
			 e.printStackTrace();
		}
		
		return res ;
		 
		
	}
	/***************************************** CLEAR FUNCTION *********************************************************/
	public void clear()
	{
		tcCodeArticle.clear();
		tcDesignation.clear();
		tcPoidsU.clear();
		tcPVUVHT.clear();
		tcQte.clear();
		tcRemise.clear();
		tcTVA.clear();
		
	}
	
	/************************************* CALCUL TOTAL HT,TTC************************************************************************/
	public void totalHT()
	{
		int prixuht = 0;
		int qte = 0 ; 
		int tva = 0;
		int remise = 0 ; 
		String x = null ; 
		try {
			 x = tcPVUVHT.getText();
		 prixuht = Integer.parseInt(x);
		}catch(NumberFormatException e){
		       System.out.println("not a number");
		}
		try {
			String y = tcQte.getText();
		 qte = Integer.parseInt(y);
		}catch(NumberFormatException e){
		       System.out.println("not a number");
		}
		
		try {
			String z = tcTVA.getText();
		 tva = Integer.parseInt(z);
		}catch(NumberFormatException e){
		       System.out.println("not a number");
		}
		try {
			String t = tcRemise.getText();
		 remise = Integer.parseInt(t);
		}catch(NumberFormatException e){
		       System.out.println("not a number");
		}
		
		 int remis = (prixuht*remise)/100;
		int res = (prixuht*qte)-remis ;
		tva=(res*tva)/100;
		int r = 0 ; 
		
		r=res+tva ;

		int id = getID();

		String query = "UPDATE articlebl SET totaleHT  = '" +res + "', totaleTTC = '" + r + "'  WHERE  id = " +id + "";
		  executeQuery(query);
		  VoirArticle();
		
	}
	/********************************** REMPLIR DESIGNATION ***********************************************************************************/
	public void remplirDesignation()
	{
		String code = null ;
		Connection conn = getConnection();
		String query = "SELECT designation FROM articlebl WHERE code='"+code+"' " ; 
		Statement st ;
		ResultSet rs ;
		
		try {
			st = conn.createStatement();
			rs = st.executeQuery(query);
			
			while(rs.next())
			{
				String nom = rs.getString("designation");
				code = rs.getString("code_article");
				//tfclient.getItems().add(item); 
				 // tf4.setText(String.valueOf(totalamount));
				  tcDesignation.setText(nom);
				
			}
			 
			}catch(Exception e) {
				 e.printStackTrace();
			}
	}
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		viderTable();
		remplirComboBox();	
		setNumero();
		setDateCreation();
	
	}
}