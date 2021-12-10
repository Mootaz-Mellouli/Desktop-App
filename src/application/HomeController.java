package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

import javafx.application.Platform;
import javafx.event.ActionEvent;

public class HomeController {

	// Event Listener on MenuItem.onAction
	@FXML
	public void addClient(ActionEvent event) {
		try {
			Parent parent = FXMLLoader.load(getClass().getResource("../Client/AjoutClient.fxml"));
			Scene scene = new Scene(parent);
			Stage stage = new Stage() ;
			stage.setScene(scene);
			stage.initStyle(StageStyle.UTILITY);
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	// Event Listener on MenuItem.onAction
	@FXML
	public void trouverClient(ActionEvent event) {
		try {
			Parent parent = FXMLLoader.load(getClass().getResource("../Client/trouverClient.fxml"));
			Scene scene = new Scene(parent);
			Stage stage = new Stage() ;
			stage.setScene(scene);
			stage.initStyle(StageStyle.UTILITY);
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// Event Listener on MenuItem.onAction
		@FXML
		public void AjouterFournisseur(ActionEvent event) {
			try {
				Parent parent = FXMLLoader.load(getClass().getResource("../fournisseur/fournisseur.fxml"));
				Scene scene = new Scene(parent);
				Stage stage = new Stage() ;
				stage.setScene(scene);
				stage.initStyle(StageStyle.UTILITY);
				stage.show();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		@FXML
		public void trouverFournisseur(ActionEvent event) {
			try {
				Parent parent = FXMLLoader.load(getClass().getResource("../fournisseur/TrouverFounisseur.fxml"));
				Scene scene = new Scene(parent);
				Stage stage = new Stage() ;
				stage.setScene(scene);
				stage.initStyle(StageStyle.UTILITY);
				stage.show();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		@FXML
		public void ajouterArticle(ActionEvent event) {
			try {
				Parent parent = FXMLLoader.load(getClass().getResource("../Article/Article.fxml"));
				Scene scene = new Scene(parent);
				Stage stage = new Stage() ;
				stage.setScene(scene);
				stage.initStyle(StageStyle.UTILITY);
				stage.show();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		@FXML
		public void trouverArticle(ActionEvent event) {
			try {
				Parent parent = FXMLLoader.load(getClass().getResource("../Article/TrouverArticle.fxml"));
				Scene scene = new Scene(parent);
				Stage stage = new Stage() ;
				stage.setScene(scene);
				stage.initStyle(StageStyle.UTILITY);
				stage.show();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		@FXML
		public void CreerBonLivraison(ActionEvent event) {
			try {
				Parent parent = FXMLLoader.load(getClass().getResource("../BonLivraison/BonLivraison.fxml"));
				Scene scene = new Scene(parent);
				Stage stage = new Stage() ;
				stage.setScene(scene);
				stage.initStyle(StageStyle.UTILITY);
				stage.show();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		@FXML
		public void CreerBonReception(ActionEvent event) {
			try {
				Parent parent = FXMLLoader.load(getClass().getResource("../BonReception/BonDeReception.fxml"));
				Scene scene = new Scene(parent);
				Stage stage = new Stage() ;
				stage.setScene(scene);
				stage.initStyle(StageStyle.UTILITY);
				stage.show();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	@FXML
	public void CreerDevis(ActionEvent event) {
		try {
			Parent parent = FXMLLoader.load(getClass().getResource("../Devis/Devis.fxml"));
			Scene scene = new Scene(parent);
			Stage stage = new Stage() ;
			stage.setScene(scene);
			stage.initStyle(StageStyle.UTILITY);
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	public void CreerFacture(ActionEvent event) {
		try {
			Parent parent = FXMLLoader.load(getClass().getResource("../Facture/Facture.fxml"));
			Scene scene = new Scene(parent);
			Stage stage = new Stage() ;
			stage.setScene(scene);
			stage.initStyle(StageStyle.UTILITY);
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		@FXML
		public void close(ActionEvent event) {
			Platform.exit();
}}
