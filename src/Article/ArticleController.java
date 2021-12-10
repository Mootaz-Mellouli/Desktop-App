package Article;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import Article.Article;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ArticleController implements Initializable {
	@FXML
	private TextField tfcode;
	@FXML
	private TextField tfdesi;
	@FXML
	private TextField tfprix;
	@FXML
	private TextField tfqtestock;
	@FXML
	private TableView<Article> tvarticle;
	@FXML
	private TableColumn<Article, Integer> colcode;
	@FXML
	private TableColumn<Article, String> coldesi;
	@FXML
	private TableColumn<Article, Integer> colprix;
	@FXML
	private TableColumn<Article, Integer> colstock;
	@FXML
	private Button btnajout;
	@FXML
	private Button btnannuler;
	
	// Event Listener on Button[#btnajout].onMouseClicked
	@FXML
	public void ajouter(MouseEvent event) {
		if(event.getSource() == btnajout)
		{			
			insertArticle();
			clear();
		}
	}
	// Event Listener on Button[#btnannuler].onMouseClicked
	@FXML
	public void annuler(MouseEvent event) {
		(((Node) event.getSource())).getScene().getWindow().hide();

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
	
	public ObservableList<Article> getArticleListe()
	{
		ObservableList<Article> ArticleListe = FXCollections.observableArrayList();
	Connection conn = getConnection();
	String query = "SELECT * from article";
	Statement st ;
	ResultSet rs ;
	
	try {
		st = conn.createStatement();
		rs = st.executeQuery(query);
		Article article ;
		while(rs.next())
		{
			article = new Article(rs.getInt("id"),rs.getInt("code"),rs.getString("designation"),rs.getInt("prix_achat"),rs.getInt("stock"));
			ArticleListe.add(article);
		}
		 
		}catch(Exception e) {
			 e.printStackTrace();
		}
	
	return ArticleListe ;
	
	}
	
	public void VoirArticle()
	{
		ObservableList<Article> list = getArticleListe();
		colcode.setCellValueFactory(new PropertyValueFactory<Article,Integer>("code"));
		coldesi.setCellValueFactory(new PropertyValueFactory<Article,String>("designation"));
		colprix.setCellValueFactory(new PropertyValueFactory<Article,Integer>("prix_achat"));
		colstock.setCellValueFactory(new PropertyValueFactory<Article,Integer>("stock"));
		
		tvarticle.setItems(list);
	
	}
	
	public void insertArticle()
	{
        String query = "INSERT INTO article( code, designation, prix_achat,stock) VALUES ('" + tfcode.getText() + "','" + tfdesi.getText() + "','" + tfprix.getText() +"','"+tfqtestock.getText()+"')";
		executeQuery(query);
		VoirArticle();
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
	public void clear()
	{
		tfcode.clear();
		tfdesi.clear();
		tfprix.clear();
		tfqtestock.clear();
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		VoirArticle();		
	}
}
