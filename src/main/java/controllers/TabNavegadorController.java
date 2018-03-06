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
		navegarWebInitialize();
	}
	public void btnGoogleHab (ActionEvent event) {
		link = "https://www.google.com.br/search?q=adasa&safe=active&source=lnms&sa=X&ved=0ahUKEwiO6ZPN_9fZAhXFGZAKHT0uDbgQ_AUICSgA&biw=1360&bih=647&dpr=1";
		navegarWebInitialize();
	}
	public void bntGoHab (ActionEvent event) {
		
	}
	
	public void navegarWebInitialize() {
		
		WebView wv1 = new WebView();
		wv1.setPrefSize(947,622);
		
		wv1.setLayoutY(78);
		
		
		
		//String linkSEI = "https://sei.df.gov.br/sei/controlador.php?acao=procedimento_controlar&acao_origem=principal&acao_retorno=principal&inicializando=1&infra_sistema=100000100&infra_unidade_atual=110009258&infra_hash=688e8ef9fede6855054f40e147899f5fac4b7abf3ec418381d989e65cf55b902";
		
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
		    
		    System.out.println("Chamou  o WebView initialize");
	}
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		 
		Platform.runLater(() ->{
		navegarWebInitialize();
		});
		
	}

}
