package Devis;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;

import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class DevisController implements Initializable{
	
	@FXML
	private TextField tfnumDevis;
	@FXML
	private TextField tfref;
	@FXML
	private ComboBox tfClient;
	@FXML
	private TextField tfContact;
	@FXML
	private TextField tfcode;
	@FXML
	private TextField tfdesi;
	@FXML
	private TextField tfPUHT;
	@FXML
	private TextField tfQte;
	@FXML
	private TextField tfPoidsU;
	@FXML
	private TextField tfTVA;
	@FXML
	private TextField tfID;
	@FXML
	private DatePicker tfDateCreation;
	@FXML
	private DatePicker tfDateValidation;
	@FXML
	private Button btnInsererArticle;
	@FXML
	private Button btnModifierArticle;
	@FXML
	private Button btnSupprimerArticle;
	@FXML
	private TableView<ArticleDevis> tvArticle;
	@FXML
	private TableColumn tvCode;
	@FXML
	private TableColumn tvDesi;
	@FXML
	private TableColumn tvPUHT;
	@FXML
	private TableColumn tvQte;
	@FXML
	private TableColumn tvTva;
	@FXML
	private TableColumn tvRemise;
	@FXML
	private TableColumn tvTotaleHT;
	@FXML
	private TableColumn tvTotaleTTC;
	@FXML
	private TableColumn tvID;
	@FXML
	private TextField tfTotaleHT;
	@FXML
	private TextField tfTotaleTTC;
	@FXML
	private Button btnValider;
	@FXML
	private TextArea tfInfo;


	// Event Listener on ComboBox[#tfClient].onAction
	@FXML
	public void selectClient(ActionEvent event) {
		// TODO Autogenerated
	}
	// Event Listener on Button[#btnInsererArticle].onMouseClicked
	@FXML
	public void ajouterArticle(MouseEvent event) {
		InsererArticleTableau();
		VoirArticle();
		totaleHTTTC();
		totaleGeneral();
	}
	// Event Listener on Button[#btnModifierArticle].onMouseClicked
	@FXML
	public void modifierArticle(MouseEvent event) {
		if(event.getSource()==btnModifierArticle)
		{
			Alert alert = new Alert (Alert.AlertType.CONFIRMATION) ;
			alert.setHeaderText("�tes-vous s�r de vouloir modifier cet article");
			Optional<ButtonType> result = alert.showAndWait();
			if(result.isPresent()&&result.get()==ButtonType.OK)
			{
				modifierArticle();
				clear();
			}
		}
	}
	// Event Listener on Button[#btnSupprimerArticle].onMouseClicked
	@FXML
	public void supprimerArticle(MouseEvent event) {
		if(event.getSource()==btnSupprimerArticle)
		{
			Alert alert = new Alert (Alert.AlertType.CONFIRMATION) ;
			alert.setHeaderText("Etes-vous sur de vouloir supprimer cet article");
			Optional<ButtonType> result = alert.showAndWait();
			if(result.isPresent()&&result.get()==ButtonType.OK)
			{
				deleteArticle();
				clear();
			}
		}
	}

	// Event Listener on Button[#btnValider].onMouseClicked
	@FXML
	public void ValiderDevis(MouseEvent event) {
		if(event.getSource() == btnValider)
		{
			Alert alert = new Alert (Alert.AlertType.CONFIRMATION) ;
			alert.setHeaderText("�tes-vous s�r de vouloir valider ce bon de reception");
			Optional<ButtonType> result = alert.showAndWait();
			if(result.isPresent()&&result.get()==ButtonType.OK)
			{
				ValierDevis();
				(((Node) event.getSource())).getScene().getWindow().hide();
			}
		}
	}
	@FXML
	public void selectArticle(MouseEvent event) {
		ArticleDevis article =  tvArticle.getSelectionModel().getSelectedItem();
		tfcode.setText(""+article.getCode());
		tfdesi.setText(""+article.getDesignation());
		tfPUHT.setText(""+article.getPrixUHT());
		tfQte.setText(""+article.getQte());
		tfPoidsU.setText(""+article.getPoidsUni());
		tfTVA.setText(""+article.getTva());
		tfID.setText(""+article.getId());
	}
	
	/***************************** INITIALIZE *************************************/
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		viderTable();
		remplirComboBox();
		setNumero();
		setDateCreation();

	}

	/************************************ CONNECTION ********************************************************/
	public Connection getConnection()
	{
		Connection conn ;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/atec","root","");
			return conn ;
		} catch (SQLException e) {
			System.out.println("Error" + e.getMessage());
			return null ;
		}
	}
	/************************************ EXECUTE QUERY ********************************************************/
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
				tfClient.getItems().add(item);

			}

		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	/************************************ SET NUMERO BON *********************************************************/
	public void setNumero()
	{
		Connection conn = getConnection();
		String query = "SELECT numeroBon FROM devis" ;
		Statement st ;
		ResultSet rs ;

		try {
			st = conn.createStatement();
			rs = st.executeQuery(query);

			while(rs.next())
			{
				tfnumDevis.setText(""+rs.getInt("numeroBon"));
			}

		}catch(Exception e) {
			e.printStackTrace();
		}

	}
	/*************************************** DATE CREATION ******************************************************************/
	public void setDateCreation()
	{
		tfDateCreation.setValue(LocalDate.now());
	}

	/******************************************************* AJOUTER UN ARTICE DANS LE TABLEAU ********************************************************/
	public void InsererArticleTableau()
	{
		int x = 1 ;
		int y = 1 ;
		String numero = tfnumDevis.getText();
		String query="INSERT INTO articledevis(code, designation, qte, prixUHT, tva, 	PoidsUni, totaleHT,totaleTTC,numeroBon ) VALUES ('"+tfcode.getText()+"','"+tfdesi.getText()+"','"+tfQte.getText()+"','"+tfPUHT.getText()+"','"+tfTVA.getText()+"','"+tfPoidsU.getText()+"',"+x+","+y+",'"+numero+"')" ;
		executeQuery(query);
		String query2="INSERT INTO articledevistemp(code, designation, qte, prixUHT, tva, 	PoidsUni, totaleHT,totaleTTC,numeroBon ) VALUES ('"+tfcode.getText()+"','"+tfdesi.getText()+"','"+tfQte.getText()+"','"+tfPUHT.getText()+"','"+tfTVA.getText()+"','"+tfPoidsU.getText()+"',"+x+","+y+",'"+numero+"')" ;
		executeQuery(query2);
	}
	/*************************************************** VALIDER LE DEVIS************************************************************************/
	public void ValierDevis()
	{
		String query="INSERT INTO devis( client, reference,contact, dateCreation,dateValidation,TotaleHTGen,TotaleTTCGen) VALUES ('"+tfClient.getValue()+"','"+tfref.getText()+"','"+tfContact.getText()+"','"+tfDateCreation.getValue()+"','"+tfDateValidation.getValue()+"','"+tfTotaleHT.getText()+"','"+tfTotaleTTC.getText()+"')" ;
		executeQuery(query);
	}
	/***************************************** AFFICHAGE DANS LE TABLEAU ********************************************************/

	public ObservableList<ArticleDevis> getArticleListe()
	{

		ObservableList<ArticleDevis> ArticleListe = FXCollections.observableArrayList();
		Connection conn = getConnection();
		String query = "SELECT * from articledevistemp";
		Statement st ;
		ResultSet rs ;

		try {
			st = conn.createStatement();
			rs = st.executeQuery(query);
			ArticleDevis bdl ;
			while(rs.next())
			{
				bdl = new ArticleDevis(rs.getInt("id"),rs.getInt("code"),rs.getString("designation"),rs.getString("prixUHT"),rs.getString("poidsUni"),rs.getString("numeroBon"),rs.getString("qte"),rs.getString("tva"),rs.getInt("totaleHT"),rs.getInt("totaleTTC"));
				ArticleListe.add(bdl);
			}

		}catch(Exception e) {
			e.printStackTrace();
		}

		return ArticleListe ;

	}

	public void VoirArticle()
	{
		ObservableList<ArticleDevis> list = getArticleListe();
		tvCode.setCellValueFactory(new PropertyValueFactory("code"));
		tvDesi.setCellValueFactory(new PropertyValueFactory("designation"));
		tvPUHT.setCellValueFactory(new PropertyValueFactory("prixUHT"));
		tvQte.setCellValueFactory(new PropertyValueFactory("qte"));
		//tv.setCellValueFactory(new PropertyValueFactory("poidsUni"));
		tvTva.setCellValueFactory(new PropertyValueFactory("tva"));
		tvTotaleHT.setCellValueFactory(new PropertyValueFactory("totaleHT"));
		tvTotaleTTC.setCellValueFactory(new PropertyValueFactory("totaleTTC"));
		tvID.setCellValueFactory(new PropertyValueFactory("id"));
		//tv.setCellValueFactory(new PropertyValueFactory("numeroBon"));

		tvArticle.getItems().clear();

		tvArticle.setItems(list);

	}

	/************************************* CALCUL TOTAL HT,TTC************************************************************************/
	public void totaleHTTTC()
	{
		int prixuht = 0;
		int qte = 0 ;
		int tva = 0;
		String x = null ;
		try {
			x = tfPUHT.getText();
			prixuht = Integer.parseInt(x);
		}catch(NumberFormatException e){
			System.out.println("not a number");
		}
		try {
			String y = tfQte.getText();
			qte = Integer.parseInt(y);
		}catch(NumberFormatException e){
			System.out.println("not a number");
		}

		try {
			String z = tfTVA.getText();
			tva = Integer.parseInt(z);
		}catch(NumberFormatException e){
			System.out.println("not a number");
		}

		int res = (prixuht*qte) ;
		tva=(res*tva)/100;
		int r = 0 ;
		r=res+tva ;
		int id = getID();

		String query = "UPDATE articledevis SET totaleHT  = '" +res + "', totaleTTC = '" + r + "'  WHERE  id = " +id + "";
		executeQuery(query);
		String query2 = "UPDATE articledevistemp SET totaleHT  = '" +res + "', totaleTTC = '" + r + "'  WHERE  id = " +id + "";
		executeQuery(query2);
		VoirArticle();

	}

	/************************************** GET ID **********************************************************************/
	public int  getID()
	{
		int res = 0 ;
		Connection conn = getConnection();
		String query = "SELECT id FROM articledevistemp ORDER BY ID DESC LIMIT 1";
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

	/***************************** TOTALE GENERAL ************************************************/
	public  void totaleGeneral()
	{
		int resht = 0 ;
		int resttc = 0 ;
		Connection conn = getConnection();
		String query = "SELECT SUM(totaleHT),SUM(totaleTTC) FROM articledevistemp INNER JOIN devis ON articledevistemp.numeroBon=devis.numeroBon WHERE articledevistemp.numeroBon=(SELECT MAX(articledevistemp.numeroBon)FROM devis)";
		Statement st ;
		ResultSet rs ;
		try {
			st = conn.createStatement();
			rs = st.executeQuery(query);
			while(rs.next())
			{
				resht = rs.getInt("SUM(totaleHT)");
				resttc = rs.getInt("SUM(totaleTTC)");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		tfTotaleTTC.setText(""+resht);
		tfTotaleHT.setText(""+resttc);
	}
	/*************************** EFFACER ARTICLE DE LA TABLE ARTICLE *******************************************************************************/
	public void deleteArticle()
	{
		String query="DELETE  FROM articledevis where id ="+tfID.getText()+"";
		executeQuery(query);
		String query2="DELETE  FROM articledevistemp where id ="+tfID.getText()+"";
		executeQuery(query2);
		VoirArticle();
	}
	/**************************** MODIFIER ARTICLE DE LA TABLE ARTICLE**********************************************************************************************/
	public void modifierArticle()
	{
		String query = "UPDATE articledevis SET code  = '" +tfcode.getText() + "', designation = '" + tfdesi.getText() + "', qte = '" +tfQte.getText() + "', prixUHT = '" +tfPUHT.getText() + "', tva = '" +tfTVA.getText() +"', PoidsUni = '" +tfPoidsU.getText() +"' WHERE  id = " +tfID.getText() + "";
		executeQuery(query);
		String query2 = "UPDATE articledevistemp SET code  = '" +tfcode.getText() + "', designation = '" + tfdesi.getText() + "', qte = '" +tfQte.getText() + "', prixUHT = '" +tfPUHT.getText() + "', tva = '" +tfTVA.getText() +"', PoidsUni = '" +tfPoidsU.getText() +"' WHERE  id = " +tfID.getText() + "";
		executeQuery(query2);
		VoirArticle();
	}
	/***************************************** CLEAR FUNCTION *********************************************************/
	public void clear()
	{
		tfcode.clear();
		tfdesi.clear();
		tfID.clear();
		tfPUHT.clear();
		tfQte.clear();
		tfPoidsU.clear();
		tfTVA.clear();
		tfTotaleHT.clear();
		tfTotaleTTC.clear();
	}
	/***************************************** VIDER LA TABLE ARTICLE TEMP***********************************/
	public void viderTable()
	{
		String query="TRUNCATE TABLE articledevistemp";
		executeQuery(query);
	}









}
