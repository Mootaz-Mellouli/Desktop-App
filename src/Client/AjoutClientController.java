package Client;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.scene.control.TableView;

import javafx.scene.control.TableColumn;

public class AjoutClientController implements Initializable {
	@FXML
	private TextField tfcode;
	@FXML
	private TextField tfnom;
	@FXML
	private TextField tfprenom;
	@FXML
	private TextField tftel;
	@FXML
	private TextField tfmail;
	@FXML
	private TableView<Client> tvclient;
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
	private TableColumn<Client, String> colcode;
	@FXML
	private Button btnajout;

	// Event Listener on Button[#btnajout].onAction
	@FXML
	public void ajouter(MouseEvent event) {
		
		if(event.getSource() == btnajout)
		{
			
			
			insertClient();
		}
	}

	 
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		VoirClients();
		
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
	
	public ObservableList<Client> getClientsListe()
	{
		ObservableList<Client> ClientListe = FXCollections.observableArrayList();
	Connection conn = getConnection();
	String query = "SELECT * from client";
	Statement st ;
	ResultSet rs ;
	
	try {
		st = conn.createStatement();
		rs = st.executeQuery(query);
		Client client ;
		while(rs.next())
		{
			client = new Client(rs.getInt("id"),rs.getString("nom"),rs.getString("prenom"),rs.getString("code"),rs.getString("telephone"),rs.getString("email"));
			ClientListe.add(client);
		}
		 
		}catch(Exception e) {
			 e.printStackTrace();
		}
	
	return ClientListe ;
	
	}
	
	public void VoirClients()
	{
		ObservableList<Client> list = getClientsListe();
		colnom.setCellValueFactory(new PropertyValueFactory<Client,String>("nom"));
		colprenom.setCellValueFactory(new PropertyValueFactory<Client,String>("prenom"));
		coltel.setCellValueFactory(new PropertyValueFactory<Client,String>("telephone"));
		colmail.setCellValueFactory(new PropertyValueFactory<Client,String>("email"));
		colid.setCellValueFactory(new PropertyValueFactory<Client,Integer>("id"));
		colcode.setCellValueFactory(new PropertyValueFactory<Client,String>("code"));
		
		tvclient.setItems(list);
	
	}
	

	public void insertClient()
	{
        String query = "INSERT INTO client( nom, prenom,code, telephone, email) VALUES ('" + tfnom.getText() + "','" + tfprenom.getText() + "','" + tfcode.getText() + "','" + tftel.getText() + "','" +tfmail.getText()+   "')";
		executeQuery(query);
		VoirClients();
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
		}
		
	}
	
	
}
