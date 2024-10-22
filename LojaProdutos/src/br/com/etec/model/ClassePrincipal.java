package br.com.etec.model;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

public class ClassePrincipal implements Initializable {
	
	@FXML
	ComboBox<String> cmbTipo;
	
	@FXML
	ComboBox<String> cmbMarca;
	
	@FXML
	ComboBox<String> cmbModelo;
	
	@FXML
	Button btnTipo;
	
	@FXML
	Button btnMarca;
	
	@FXML
	Button btnModelo;
	
	public void initialize(URL url, ResourceBundle rb) {
		
		ObservableList<String> lista = FXCollections.observableArrayList("Chinelo" , "Tênis");
		cmbTipo.setItems(lista);
		
		ObservableList<String> lista2 = FXCollections.observableArrayList("Nike" , "Adidas");
		cmbTipo.setItems(lista2);
		
		ObservableList<String> lista3 = FXCollections.observableArrayList("TEN001" ,"CHN" ,"TEA002" , "CHA001");
		cmbTipo.setItems(lista3);
		
		
		
	}

}
