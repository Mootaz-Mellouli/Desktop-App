package BonLivraison;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.ResourceBundle;

import Client.trouverClientController;
import Facture.Facture0Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class ListeBLController implements Initializable {
	@FXML
    private TableView<BonDeLivraison> tvblListe;
	@FXML
	private TableColumn tvnumero;
	@FXML
	private TableColumn tvref;
	@FXML
	private TableColumn tvTotaleHT;
	@FXML
	private TableColumn tvTotaleTTC;
	@FXML
	private TableColumn tvDateLivraison;
	@FXML
	private Label labelnom ;
	@FXML 
	private TextField tfnom ;
	@FXML
	private TableColumn<BonDeLivraison,CheckBox> tvselect;
	
	@FXML
	private CheckBox select ;
	//@FXML
	//private TableColumn checkBoxTableColumn;
	private BonDeLivraison bdl;
	
	 	@FXML
	    private Button btnDelete;
	 	
	 	@FXML
	    private Button btnAjouter;
	 	
	 	@FXML
	    void ajouterAuFacture(ActionEvent event)
	    {
	 		ouhalazebi();
	    }
	 	
	 	

	    @FXML
	    void delete(ActionEvent event) throws IOException {

	    	for(BonDeLivraison bb :tvblListe.getItems())
	    	{
	    		if(bb.getSelect().isSelected())
	    		{
	    			//function qui va passer "bb" en parametre pour facturation
	    			//bdlcontroller.showInfo(bb);
	    			FXMLLoader loader = new FXMLLoader(getClass().getResource("../Facture/Facture0.fxml"));
	    			
	    			Parent root = loader.load();
	    			
	    			Facture0Controller bdlcontroller = loader.getController() ;
	    			boolean x ;
	    			x=bb.getSelect().isSelected();
	    			bdlcontroller.show(x);
	    			
	    			
	    			Stage stage = new Stage() ; 
	    			stage.setScene(new Scene(root));
	    			stage.setTitle("Scene 2 window");
	    			  //stage.initStyle(StageStyle.UTILITY); 
	    			  stage.show();
	    			//tvblListe.getItems().remove(bb);
	    		}
	    	}
	    }
	
	/*********************************************************/
	    public Boolean getData()
		{
	    	boolean x = false ;
	    	for(BonDeLivraison bb :tvblListe.getItems())
	    	{
	    		if(bb.getSelect().isSelected())
	    		{
	    			x=bb.getSelect().isSelected();
	    		}
	    	}
			return x;
		}
		public static ListeBLController instance ;
		
		 public ListeBLController() {
			 instance = this ;
		 }
		 
		 public static ListeBLController getInstance()
		 {
			 return instance ;
		 }
	

	
	/********************************************* INITIALIZABLE ***********************************/
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		VoirBLListe();
		//facture();
		
	}

	/************************************ CONNECTION ********************************************************/
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
	
	/**************************************** AFFICHAGE TABLEAU *******************************************/

	
	public ObservableList<BonDeLivraison> getBLListe()
	{
		ObservableList<BonDeLivraison> BonLivraisonListe = FXCollections.observableArrayList();
	Connection conn = getConnection();
	String t = trouverClientController.getInstance().getData();
	String query = "SELECT numeroBon ,reference,date_livraison FROM bonlivraison INNER JOIN client ON bonlivraison.client=client.nom  WHERE client.nom='"+t+"' ";
	Statement st ;
	ResultSet rs ;
	
	try {
		st = conn.createStatement();
		rs = st.executeQuery(query);
		BonDeLivraison bdl ;
		while(rs.next())
		{
			CheckBox ch = new CheckBox("");
			bdl = new BonDeLivraison(rs.getInt("numeroBon"),rs.getString("reference"),rs.getDate("date_livraison"),ch);
			BonLivraisonListe.add(bdl);
			
	

		}
		 
		}catch(Exception e) {
			 e.printStackTrace();
		}
	
	return BonLivraisonListe ;
	
	}
	
	public void VoirBLListe()
	{
		ObservableList<BonDeLivraison> list = getBLListe();

		tvnumero.setCellValueFactory(new PropertyValueFactory("numeroBon"));
		tvref.setCellValueFactory(new PropertyValueFactory("reference"));
		tvDateLivraison.setCellValueFactory(new PropertyValueFactory("date_livraison"));
		tvselect.setCellValueFactory(new PropertyValueFactory<BonDeLivraison, CheckBox>("select"));
		
	
			
			
			
			
		
		
		tvblListe.setItems(list);
	
	}
	
	
	/*
	 * public void display() { FXMLLoader loader = new
	 * FXMLLoader(getClass().getResource("ListeBL.fxml")); trouverClientController
	 * trouverClientCon = loader.getController() ;
	 * trouverClientCon.getClientsListe(); //labelnom.setText(""+x); }
	 */
	 
	
	public void showInfo(String nom)
	{
		tfnom.setText(nom);

	}

	
	/*************************************************** FACTURATION *******************************/
	
	public void ouhalazebi()
	{
		int nom = 0 ;
		String ref = null;
		Date dateLiv = null  ;
	Connection conn = getConnection();
	String t =trouverClientController.getInstance().getData();
	String query = "SELECT numeroBon ,reference,date_livraison FROM bonlivraison INNER JOIN client ON bonlivraison.client=client.nom  WHERE client.nom='"+t+"' ";
	Statement st ;
	ResultSet rs ;
	
	try {
		st = conn.createStatement();
		rs = st.executeQuery(query);
		//BonDeLivraison bdl ;
		while(rs.next())
		{
			
			  nom = rs.getInt("numeroBon");
			  ref = rs.getString("reference");
			 dateLiv = rs.getDate("date_livraison");

		}
		 
		}catch(Exception e) {
			 e.printStackTrace();
		}
	String query2 = "INSERT INTO blfacture( numeroBL, reference,totaleHT, totaleTTC, dateCreation,checked) VALUES ('" + nom + "','" + ref + "','" + 3  + "','" + 3 + "','" +dateLiv+ "','" + 1 + "'  )";
	executeQuery(query2);
	
	}
	public void facture()
	{
		//for(int i = 0 ; i<10;i++) {
			//String a=tvblListe.getColumns().get(1).getCellObservableValue(1).getValue().toString();
		//}
			String t =trouverClientController.getInstance().getData();


			
			//VoirClients();
		
	}
	}
	
	
