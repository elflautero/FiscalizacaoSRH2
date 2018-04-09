package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.Pane;

public class TabSuperficialController implements Initializable{
	
	@FXML Pane tabSuperficial;
	@FXML
	ChoiceBox<String> cbCaesb = new ChoiceBox<String>();
		ObservableList<String> olCaesb = FXCollections
			.observableArrayList(
					"Sim" , 
					"Não"
					); 
		
		@FXML
		ChoiceBox<String> cbCaptacao = new ChoiceBox<String>();
			ObservableList<String> olCaptacao = FXCollections
				.observableArrayList(
						"Canal" , 
						"Rio",
						"Reservatório",
						"Lago Natural",
						"Nascente",
						"Outro"
						
						); 

			@FXML
			ChoiceBox<String> cbFormaCaptacao = new ChoiceBox<String>();
				ObservableList<String> olFormaCaptacao = FXCollections
					.observableArrayList(
							"Gravidade" , 
							"Bombeamento",
							"Outro"
							); 
			

	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		cbCaesb.setItems(olCaesb);
		cbCaptacao.setItems(olCaptacao);
		cbFormaCaptacao.setItems(olFormaCaptacao);
	}
	 
		

}
