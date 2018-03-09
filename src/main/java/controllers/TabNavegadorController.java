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
	
	String link = "http://treinamento3.sei.df.gov.br/sip/login.php?sigla_orgao_sistema=GDF&sigla_sistema=SEI";
	
	@FXML
	AnchorPane tabNavegador = new AnchorPane();
	
	@FXML
	Button btnSEI = new Button();
	@FXML
	Button btnGoogle = new Button();
	@FXML
	Button btnGo = new Button();
	
	
	public void btnSEIHab (ActionEvent event) {
		link = "https://sei.df.gov.br/sip/login.php?sigla_orgao_sistema=GDF&sigla_sistema=SEI";
		navegarWebInitialize()  ;
	}
	public void btnGoogleHab (ActionEvent event) {
		link = "http://gis.adasa.df.gov.br/portal/home/index.html";
		//link = "http://gis.adasa.df.gov.br/portal/home/webmap/viewer.html?webmap=ae675a7f879845a1a16a9a3134284384";
		//link = "https://www.google.com.br/search?q=adasa&safe=active&source=lnms&sa=X&ved=0ahUKEwiO6ZPN_9fZAhXFGZAKHT0uDbgQ_AUICSgA&biw=1360&bih=647&dpr=1";
		
		
		navegarWebInitialize();
	}
	public void bntGoHab (ActionEvent event) {
		
	}
	
	public void navegarWebInitialize() {
		
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
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		
		// -- inicitalizar o mapa -- //
		Platform.runLater(() ->{
		navegarWebInitialize();  
		});
		
	}

}
