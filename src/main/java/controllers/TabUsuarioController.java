package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class TabUsuarioController implements Initializable {
	@FXML
	Pane tabUsuario = new Pane();
	@FXML
	TextField tfUsuario = new TextField ();
	@FXML
	Button btnTeste = new Button();
	
	public void  btnTestar (ActionEvent evetn) {
		String usuario = tfUsuario.getText();
		System.out.println("O nome do usuário é: " + usuario);
	}
	
	
	//TAB USUÁRIO - BOX DE ESCOLHA PESSOA FÍSICA E JURÍDICA
		@FXML
		ChoiceBox<String> cbTipoPessoa = new ChoiceBox<String>();
			ObservableList<String> olTipoPessoa = FXCollections
				.observableArrayList("Física" , "Jurídica"); // box - seleção pessoa físcia ou jurídica

			@Override
			public void initialize(URL url, ResourceBundle rb) {
				

				cbTipoPessoa.setValue("Física");
				cbTipoPessoa.setItems(olTipoPessoa);
				
				
			}
}
