package Client;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import java.util.ResourceBundle;

import BonLivraison.ListeBLController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class trouverClientController implements Initializable {
	@FXML
	private TextField tfcode;
	@FXML
	private TableColumn<Client, Integer> colid;
	@FXML
	private TableColumn<Client, String> colnom;
	@FXML
	private TableColumn<Client, String> colprenom;
	@FXML
	private TableColumn<Client, String> coltel;
	@FXML
	private TableColumn<Client, String> colmail;
	@FXML
	private TableView<Client> tvclient;
	@FXML
	private TextField tfid;
	@FXML
	private TextField tfnom;
	@FXML
	private TextField tfprenom;
	@FXML
	private TextField tftel;
	@FXML
	private TextField tfmail;
	@FXML
	private TableColumn<Client, String> colcode;
	@FXML
	private Button btnmodifier;
	@FXML
	private Button btnsuprrimer;
	@FXML
	private Button btnListeBL;

	public String getData()
	{
		return tfnom.getText() ;
	}
	
	public static trouverClientController instance ;
	
	 public trouverClientController() {
		 instance = this ;
	 }
	 
	 public static trouverClientController getInstance()
	 {
		 return instance ;
	 }
	@FXML
	void afficherListe(MouseEvent event) throws IOException {

		FXMLLoader loader = new FXMLLoader(getClass().getResource("../BonLivraison/ListeBL.fxml"));
		
		Parent root = loader.load();
		
		ListeBLController bdlcontroller = loader.getController() ;
		
		bdlcontroller.showInfo(tfnom.getText());
		
		
		Stage stage = new Stage() ; 
		stage.setScene(new Scene(root));
		stage.setTitle("Scene 2 window");
		  //stage.initStyle(StageStyle.UTILITY); 
		  stage.show();
		
		/*
		 * String nom = tfnom.getText(); FXMLLoader loader = new
		 * FXMLLoader(getClass().getResource("ListeBL.fxml"));
		 * 
		 * ListeBLController bdlcontroller = loader.getController() ;
		 * bdlcontroller.display(nom);
		 * 
		 * 
		 * try { //Parent parent = loader.load(); Parent parent =
		 * FXMLLoader.load(getClass().getResource("ListeBL.fxml")); Scene scene = new
		 * Scene(parent); Stage stage = new Stage() ; stage.setScene(scene);
		 * stage.initStyle(StageStyle.UTILITY); stage.show(); } catch (IOException e) {
		 * // TODO Auto-generated catch block e.printStackTrace(); }
		 */
	}

	// Event Listener on Button[#btnmodifier].onMouseClicked
	@FXML
	public void modifier(MouseEvent event) {
		if (event.getSource() == btnmodifier) {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setHeaderText("Êtes-vous sûr de vouloir modifier ce client");
			Optional<ButtonType> result = alert.showAndWait();
			if (result.isPresent() && result.get() == ButtonType.OK) {
				modifierClient();
				clear();
			}
		}
	}

	// Event Listener on Button[#btnsuprrimer].onMouseClicked
	@FXML
	public void supprimer(MouseEvent event) {
		if (event.getSource() == btnsuprrimer) {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setHeaderText("Êtes-vous sûr de vouloir supprimer ce client");
			Optional<ButtonType> result = alert.showAndWait();
			if (result.isPresent() && result.get() == ButtonType.OK) {
				deleteClient();
				clear();
			}
		}
	}

	// Event Listener on Button[#btnmodifier].onMouseClicked
	@FXML
	public void selectClient(MouseEvent event) {
		Client client = tvclient.getSelectionModel().getSelectedItem();
		tfid.setText("" + client.getId());
		tfnom.setText(client.getNom());
		tfprenom.setText(client.getPrenom());
		tftel.setText(client.getTelephone());
		tfmail.setText(client.getEmail());
	}

	public String getNomClient() {
		String nom;
		nom = tfnom.getText();
		return nom;
	}

	public ObservableList<Client> getClientsListe() {
		ObservableList<Client> ClientListe = FXCollections.observableArrayList();
		Connection conn = getConnection();
		String query = "SELECT * from client";
		Statement st;
		ResultSet rs;

		try {
			st = conn.createStatement();
			rs = st.executeQuery(query);
			Client client;
			while (rs.next()) {
				client = new Client(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"), rs.getString("code"),
						rs.getString("telephone"), rs.getString("email"));
				ClientListe.add(client);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return ClientListe;

	}

	public void VoirClients() {
		ObservableList<Client> list = getClientsListe();
		colnom.setCellValueFactory(new PropertyValueFactory<Client, String>("nom"));
		colprenom.setCellValueFactory(new PropertyValueFactory<Client, String>("prenom"));
		coltel.setCellValueFactory(new PropertyValueFactory<Client, String>("telephone"));
		colmail.setCellValueFactory(new PropertyValueFactory<Client, String>("email"));
		colid.setCellValueFactory(new PropertyValueFactory<Client, Integer>("id"));
		colcode.setCellValueFactory(new PropertyValueFactory<Client, String>("code"));

		tvclient.setItems(list);

	}

	public Connection getConnection() {
		Connection conn;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/atec", "root", "");
			return conn;
		} catch (SQLException e) {
			System.out.println("Error" + e.getMessage());
			return null;
		}
	}

	private void executeQuery(String query) {

		Connection conn = getConnection();
		Statement st;

		try {
			st = conn.createStatement();
			st.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void modifierClient() {
		String query = "UPDATE client SET nom  = '" + tfnom.getText() + "', prenom = '" + tfprenom.getText()
				+ "', telephone = '" + tftel.getText() + "', code = '" + tfcode.getText() + "', email = '"
				+ tfmail.getText() + "' WHERE  id = " + tfid.getText() + "";
		executeQuery(query);
		VoirClients();

	}

	public void deleteClient() {
		String query = "DELETE  FROM client where id =" + tfid.getText() + "";
		executeQuery(query);
		VoirClients();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		VoirClients();

	}

	/***************************************** CLEAR FUNCTION *********************************************************/
	public void clear()
	{
		tfcode.clear();
		tfid.clear();
		tfmail.clear();
		tfnom.clear();
		tfprenom.clear();
		tftel.clear();

	}
}
