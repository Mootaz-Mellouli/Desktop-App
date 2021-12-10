package Article;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import java.util.ResourceBundle;

import Article.Article;
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

import javafx.scene.control.TableView;

import javafx.scene.control.TableColumn;

public class TrouverArticleController implements Initializable {
	@FXML
	private TableView<Article> tvarticle;
	@FXML
	private TableColumn<Article, Integer> colid;
	@FXML
	private TableColumn<Article, Integer> colcode;
	@FXML
	private TableColumn<Article, String> coldesi;
	@FXML
	private TableColumn <Article, Integer>colprix;
	@FXML
	private TextField tfid;
	@FXML
	private TextField tfcode;
	@FXML
	private TextField tfdesi;
	@FXML
	private TextField tfprix;
	@FXML
	private Button btnmodifier;
	@FXML
	private Button btneffaccer;
	@FXML
	private Button btnannuler;

	// Event Listener on Button[#btnmodifier].onMouseClicked
	@FXML
	public void modifier(MouseEvent event) {
		if(event.getSource()==btnmodifier)
		{
			Alert alert = new Alert (AlertType.CONFIRMATION) ;
			alert.setHeaderText("Êtes-vous sûr de vouloir modifier cet article");
			Optional<ButtonType> result = alert.showAndWait();
			if(result.isPresent()&&result.get()==ButtonType.OK)
			{
				modifierArticle();
				clear();
			}
		}
	}
	// Event Listener on Button[#btneffaccer].onMouseClicked
	@FXML
	public void effacer(MouseEvent event) {
		if(event.getSource()==btneffaccer) 
		{
			Alert alert = new Alert (AlertType.CONFIRMATION) ;
			alert.setHeaderText("Êtes-vous sûr de vouloir supprimer ce client");
			Optional<ButtonType> result = alert.showAndWait();
			if(result.isPresent()&&result.get()==ButtonType.OK)
			{
				deleteArticle();
				clear();
			}
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
	@FXML
	public void selectArticle(MouseEvent event) {
		Article article =	tvarticle.getSelectionModel().getSelectedItem();
		tfid.setText(""+article.getId());
		tfcode.setText(""+article.getCode());
		tfdesi.setText(article.getDesignation());
		tfprix.setText(""+article.getPrix_achat());
	
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
		colid.setCellValueFactory(new PropertyValueFactory<Article,Integer>("Id"));
		
		tvarticle.setItems(list);
	
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
public void modifierArticle() {
	  String query = "UPDATE article SET code  = '" +tfcode.getText() + "', designation = '" + tfdesi.getText() + "', prix_achat = '" +tfprix.getText() + "' WHERE  id = " +tfid.getText() + "";
	  executeQuery(query);
	  VoirArticle();

}

public void deleteArticle()
{
	  String query="DELETE  FROM article where id ="+tfid.getText()+"";
	  executeQuery(query);
	  VoirArticle();
}
@Override
public void initialize(URL location, ResourceBundle resources) {
	VoirArticle();
	
}
	/***************************************** CLEAR FUNCTION *********************************************************/
	public void clear()
	{
		tfcode.clear();
		tfdesi.clear();
		tfid.clear();
		tfprix.clear();

	}
}
