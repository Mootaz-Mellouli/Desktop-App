package Facture;

import java.net.URL;
import java.util.ResourceBundle;

import BonLivraison.ListeBLController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import javafx.scene.control.DatePicker;

import javafx.scene.control.TableView;

import javafx.scene.control.TableColumn;

public class Facture0Controller implements Initializable {
	
	@FXML
	private TextField tfFacture;
	@FXML
	private TextField tfnum;
	@FXML
	private TextField tfref;
	@FXML
	private TextField tfClient;
	@FXML
	private DatePicker tfDateCreation;
	@FXML
	private TableView tvFacture;
	@FXML
	private TableColumn tvNumFacture;
	@FXML
	private TableColumn tvNumBL;
	@FXML
	private TableColumn tvTotaleHT;
	@FXML
	private TableColumn tvTotaleTTC;
	

	
	
	// en appyuant sur facture : une facture s'ouvert avec les item selectionne
	public void show(boolean x)
	{
		String str ;
		 str = String.valueOf(x);
		tfFacture.setText(str);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Boolean t = ListeBLController.getInstance().getData();
		String st = String.valueOf(t);
		tfnum.setText(st);
		
	}

}
