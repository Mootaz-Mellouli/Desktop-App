package fournisseur;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class TrouverFounisseurController implements Initializable{
	@FXML
	private TableColumn<Fournisseur, Integer> colid;
	@FXML
	private TableColumn<Fournisseur, String> colsociete;
	@FXML
	private TableColumn<Fournisseur, String> colcode;
	@FXML
	private TableColumn<Fournisseur, String> coltel;
	@FXML
	private TableColumn <Fournisseur, String>colmail;
	@FXML
	private TableColumn<Fournisseur, String> coladresse;
	@FXML
	private TableView<Fournisseur> tvfournisseur;
	@FXML
	private TextField tfid;
	@FXML
	private TextField tfsociete;
	@FXML
	private TextField tfcode;
	@FXML
	private TextField tftel;
	@FXML
	private TextField tfmail;
	@FXML
	private TextField tfadresse;
	@FXML
	private Button btnmodifier;
	@FXML
	private Button btnsupprimer;

	// Event Listener on Button[#btnmodifier].onMouseClicked
	@FXML
	public void modifier(MouseEvent event) {
		if(event.getSource()==btnmodifier)
		{
			Alert alert = new Alert (AlertType.CONFIRMATION) ;
			alert.setHeaderText("Êtes-vous sûr de vouloir modifier ce fournisseur");
			Optional<ButtonType> result = alert.showAndWait();
			if(result.isPresent()&&result.get()==ButtonType.OK)
			{
				modifierFournisseur();
				clear();
			}
		}
	}
	// Event Listener on Button[#btnsupprimer].onMouseClicked
	@FXML
	public void suprrimer(MouseEvent event) {
		if(event.getSource()==btnsupprimer) 
		{
			Alert alert = new Alert (AlertType.CONFIRMATION) ;
			alert.setHeaderText("Êtes-vous sûr de vouloir supprimer ce client");
			Optional<ButtonType> result = alert.showAndWait();
			if(result.isPresent()&&result.get()==ButtonType.OK)
			{
				deleteFournisseur();
				clear();
			}
		}
	}
	
	@FXML
	public void selectFournisseur(MouseEvent event) {
	Fournisseur fournisseur =	tvfournisseur.getSelectionModel().getSelectedItem();
	tfid.setText(""+fournisseur.getId());
	tfadresse.setText(fournisseur.getAdresse());
	tfsociete.setText(fournisseur.getNom_societe());
	tftel.setText(fournisseur.getTelephone());
	tfmail.setText(fournisseur.getEmail());
	}
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
	@FXML
	public void close(MouseEvent event) {
		(((Node) event.getSource())).getScene().getWindow().hide();

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
			colid.setCellValueFactory(new PropertyValueFactory<Fournisseur,Integer>("id"));
			colcode.setCellValueFactory(new PropertyValueFactory<Fournisseur,String>("code"));
			colsociete.setCellValueFactory(new PropertyValueFactory<Fournisseur,String>("nom_societe"));
			coltel.setCellValueFactory(new PropertyValueFactory<Fournisseur,String>("telephone"));
			colmail.setCellValueFactory(new PropertyValueFactory<Fournisseur,String>("email"));
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
	
	public void modifierFournisseur() {
		  String query = "UPDATE fournisseur SET adresse  = '" +tfadresse.getText() + "', nom_societe = '" + tfsociete.getText() + "', telephone = '" +tftel.getText() + "', code = '" +tfcode.getText() + "', email = '" + tfmail.getText() +"' WHERE  id = " +tfid.getText() + "";
	executeQuery(query);
	VoirFournisseur();

	}

	public void deleteFournisseur()
	{
		  String query="DELETE  FROM fournisseur where id ="+tfid.getText()+"";
		  executeQuery(query);
		  VoirFournisseur();
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		VoirFournisseur();
		
	}
	/***************************************** CLEAR FUNCTION *********************************************************/
	public void clear()
	{
		tfadresse.clear();
		tfid.clear();
		tfcode.clear();
		tfmail.clear();
		tfsociete.clear();
		tftel.clear();

	}
	
}
