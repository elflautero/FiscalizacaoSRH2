package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class TabInterfController implements Initializable {

    @FXML private Pane tabInterferencia;
    
    @FXML AnchorPane aPaneInt = new AnchorPane();
    
    //-- Botões do crud --//
 	@FXML Button btnIntLatLon = new Button();
 	@FXML Button btnIntAtualizar = new Button();
	@FXML Button btnCapturar = new Button();
	@FXML Button btnIntNovo = new Button();
	@FXML Button btnIntSalvar = new Button();
	@FXML Button btnIntEdit = new Button();
	@FXML Button btnIntExc = new Button();
	@FXML Button btnIntCan = new Button();
	@FXML Button btnIntPesq = new Button();
	@FXML Button btnBuscarInt = new Button();
	
	@FXML Label lblEnd = new  Label();
	
	//-- Tab interferência latitude e longitude --//
	@FXML TextField tfIntLat = new TextField();
	@FXML TextField tfIntLon = new TextField();
	@FXML TextField tfLinkInt = new TextField();
	@FXML TextField tfIntPesq = new TextField();
	
	public void btnBuscarIntHab (ActionEvent event) {
		
	}
 		
	public void btnLatLongHab (ActionEvent event) {
		
	}
	
	public void btnIntAtualizarHab (ActionEvent event) {
		
	}
	public void btnIntNovoHab (ActionEvent event) {
		
	}
	public void btnIntSalvarHab (ActionEvent event) {
		
	}
	
	public void btnIntEditHab (ActionEvent event) {
		
	}
	public void btnIntExcHab (ActionEvent event) {
		
	}
	public void btnIntCanHab (ActionEvent event) {
		
	}
	public void btnIntPesqHab (ActionEvent event) {
		
	}

	public void btnCapturarCroqui (ActionEvent event) {
	
	
	}
	
	public void btnEndLatLonHab (ActionEvent event) {
			
		String linkCoord = (tfLinkInt.getText());
		
		int latIni= linkCoord.indexOf("@");
		String lat = linkCoord.substring(latIni);
		
		int latF = lat.indexOf(",");
		String latitude = lat.substring(1, latF);
		
		String longitude = lat.substring(latF + 1, latF + latitude.length());
		
		tfIntLat.setText(latitude);
		tfIntLon.setText(longitude);
		
	}

	public void initialize(URL url, ResourceBundle rb) {
		
	}		
			
}


/*
File output = new File("‪C:\\Users\\fabricio\\Documents\\croqui.png");

try {
	//ImageIO.write(i, "png", output); 
	ImageIO.write(SwingFXUtils.fromFXImage(i, null), "png", output);
} catch (IOException e){
	e.printStackTrace();
}
*/
	