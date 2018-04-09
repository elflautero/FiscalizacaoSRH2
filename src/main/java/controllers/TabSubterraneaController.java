package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.Pane;

public class TabSubterraneaController implements Initializable {
	
	@FXML Pane tabSubterranea;
	
	@FXML
	ChoiceBox<String> cbSubSis = new ChoiceBox<String>();
		
		ObservableList<String> olSubSis = FXCollections
			.observableArrayList(
					
					"R3/Q3",
					"R4",
					"A",
					"PPC",
					"S/A",
					"Araxá",
					"Bambuí",
					"F",
					"F/Q/M",
					"P1",
					"P2",
					"P3",
					"P4"
					
					); 
		
	
		@FXML
		ChoiceBox<String> cbTipoCaptacao = new ChoiceBox<String>();
			ObservableList<String> olTipoCaptacao = FXCollections
				.observableArrayList(
						"Poço Tubular", 
						"Poço Manual"
						); 
			
			@FXML
			ChoiceBox<String> cbSubCaesb = new ChoiceBox<String>();
				ObservableList<String> olSubCaesb = FXCollections
					.observableArrayList(
							"Sim", 
							"Não"
							); 
		
	

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		cbTipoCaptacao.setItems(olTipoCaptacao);
		cbSubCaesb.setItems(olSubCaesb);
	
		cbSubSis.setItems(olSubSis);
		
	}

}
