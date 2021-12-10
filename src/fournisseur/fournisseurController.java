package fournisseur;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;



import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TableColumn;

public class fournisseurController implements Initializable {
	@FXML
	private TextField tfnomsociete;
	@FXML
	private TextField tfcodesociete;
	@FXML
	private TextArea tfadresse;
	@FXML
	private TextField tftelsociete;
	@FXML
	private TextField tfemailsociete;
	@FXML
	private TableView<Fournisseur> tvfournisseur;
	@FXML
	private TableColumn<Fournisseur, String> colnomsociete;
	@FXML
	private TableColumn <Fournisseur, String> coltelsociete;
	@FXML
	private TableColumn  <Fournisseur, String>colemailsociete;
	@FXML
	private TableColumn <Fournisseur, String>coladresse;
	@FXML
	private TableColumn <Fournisseur, String>colcodefour;
	@FXML
	private TableColumn <Fournisseur, Integer>colidfour;
	@FXML
	private Button btnajout;

	
    
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
    
	// Event Listener on Button[#btnajout].onAction
	@FXML
	public void ajouter(ActionEvent event) {
		if(event.getSource() == btnajout)
		{
			
			
			insertFournisseur();
		}
	}
	
	
public void insertFournisseur()
{
    String query = "INSERT INTO fournisseur( nom_societe, adresse,code, telephone, email) VALUES ('" + tfnomsociete.getText() + "','" + tfadresse.getText() + "','" + tfcodesociete.getText() + "','" + tftelsociete.getText() + "','" +tfemailsociete.getText()+   "')";
	executeQuery(query);
	VoirFournisseur();
}

private void executeQuery(String query) {
	
	Connection conn = getConnection();	
	Statement st ;
	
	try {
		st = conn.createStatement();
		st.executeUpdate(query);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}}
	public void VoirFournisseur()
	{
		ObservableList<Fournisseur> list = getFournisseurListe();
		colidfour.setCellValueFactory(new PropertyValueFactory<Fournisseur,Integer>("id"));
		colcodefour.setCellValueFactory(new PropertyValueFactory<Fournisseur,String>("code"));
		colnomsociete.setCellValueFactory(new PropertyValueFactory<Fournisseur,String>("nom_societe"));
		coltelsociete.setCellValueFactory(new PropertyValueFactory<Fournisseur,String>("telephone"));
		colemailsociete.setCellValueFactory(new PropertyValueFactory<Fournisseur,String>("email"));
		coladresse.setCellValueFactory(new PropertyValueFactory<Fournisseur,String>("adresse"));
		
		
		tvfournisseur.setItems(list);
	
	}
	public ObservableList<Fournisseur> getFournisseurListe()
	{
		ObservableList<Fournisseur> FournisseurListe = FXCollections.observableArrayList();
	Connection conn = getConnection();
	String query = "SELECT * from fournisseur";
	Statement st ;
	ResultSet rs ;
	
	try {
		st = conn.createStatement();
		rs = st.executeQuery(query);
		Fournisseur fournisseur ;
		while(rs.next())
		{
			fournisseur = new Fournisseur(rs.getInt("id"),rs.getString("code"),rs.getString("nom_societe"),rs.getString("telephone"),rs.getString("email"),rs.getString("adresse"));
			FournisseurListe.add(fournisseur);
		}
		 
		}catch(Exception e) {
			 e.printStackTrace();
		}
	
	return FournisseurListe ;
	
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		VoirFournisseur();
		
	}
	/*
	 * public void modifierClient() { String query =
	 * "UPDATE fournisseur SET nom_societe  = '" +tfnomsociete.getText() +
	 * "', code = '" + tfcodesociete.getText() + "', telephone = '"
	 * +tftelsociete.getText() + "', adresse = '" +tfadresse.getText() +
	 * "', email = '" + tfemailsociete.getText() +"' WHERE  id = " +tfid.getText() +
	 * ""; executeQuery(query); VoirFournisseur();
	 * 
	 * }
	 * 
	 * public void deleteClient() { String
	 * query="DELETE  FROM fournisseur where id ="+tfid.getText()+"";
	 * executeQuery(query); VoirFournisseur(); }
	 */
	
}
