package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.web.PopupFeatures;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;


public class TabNavegadorController implements Initializable{
	
	String link = "https://sei.df.gov.br/sip/login.php?sigla_orgao_sistema=GDF&sigla_sistema=SEI";
	
	@FXML AnchorPane tabNavegador = new AnchorPane();
	
	@FXML
	Button btnSEI = new Button();
	@FXML
	Button btnGoogle = new Button();
	@FXML
	Button btnGo = new Button();
	
	//-- botão SEI --//
	public void btnSEIHab (ActionEvent event) {
		
		link = "http://treinamento3.sei.df.gov.br/sip/login.php?sigla_orgao_sistema=GDF&sigla_sistema=SEI";
		navegarWeb();
		
		
	}
	
	//-- botão Google --//
	public void btnGoogleHab (ActionEvent event) {
		
		link = "http://gis.adasa.df.gov.br/portal/home/index.html";
		navegarWeb();
	}
	
	//-- botão Go - atualizar navegador --//
	public void bntGoHab (ActionEvent event) {
		
		
	}
	
	//-- método navegar --//
	public void navegarWeb() {
		
		WebView wv1 = new WebView();
		wv1.setPrefSize(947,622);
		wv1.setLayoutY(78);
		
		wv1.getEngine().setCreatePopupHandler(new Callback<PopupFeatures, WebEngine>() {

		    public WebEngine call(PopupFeatures p) {
		            Stage stage = new Stage(StageStyle.UTILITY);
		            WebView wv2 = new WebView();
		            stage.setScene(new Scene(wv2, 800, 600));
		            stage.show();
		            return wv2.getEngine();
		        }
		    });

		    StackPane root = new StackPane();
		   
		    root.getChildren().add(wv1);
		    
		    wv1.getEngine().load(link);
		    
		    tabNavegador.getChildren().add(wv1);
		    
	}
	
	//-- INITIALIZE --//
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		
		// -- inicitalizar o mapa -- //
		Platform.runLater(() ->{
		navegarWeb();  
		});
		
	}

}
