package controllers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import com.lynden.gmapsfx.javascript.object.MapTypeIdEnum;
import com.lynden.gmapsfx.javascript.object.Marker;
import com.lynden.gmapsfx.javascript.object.MarkerOptions;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Pane;



	public class TabInterfController implements Initializable, MapComponentInitializedListener  {

    @FXML private Pane tabInterferencia;
    
    // TAB ENDEREÇO - GOOGLE MAPS ////WEB BROWSER
	
 		// colocar alerta de webview sem conexao
 					
 					
 	Double latCoord = -15.7754084; // latitude inicial do mapa - ADASA
 	Double longCoord = -47.9411395; // longitude inicial do mapa - ADASA
 	
 	String linkSEI = "http://treinamento3.sei.df.gov.br/sip/login.php?sigla_orgao_sistema=GDF&sigla_sistema=SEI";
 	
 	@FXML
 	Button btnAtualizar = new Button();
 	@FXML
 	Button btnObterCoord = new Button();
 	@FXML
 	Button btnCapturar = new Button();
 	@FXML
 	TabPane tpTelaInicial = new TabPane();

	// TAB ENDEREÇO - LATITUDE E LONGITUDE
	@FXML
	TextField tfLat = new TextField();
	@FXML
	TextField tfLon = new TextField();
	@FXML
	TextField tfInserirLink = new TextField();
 		
	// TAB ENDEREÇO - BOTÕES
	@FXML 
	Button btnHome = new Button();
	@FXML
	ImageView imgVHome = new ImageView();


	//TAB ENDEREÇO - GOOGLE MAPS - GMAPSFX
	@FXML
	private GoogleMapView mapView; 
	
	private GoogleMap map;
	
	// BOTÃO - OBTER COORDENADAS E ATUALIZAR GMAPSFX
	public void btnAtualizarLatLong (ActionEvent event) {
		latCoord = Double.parseDouble(tfLat.getText());
		longCoord = Double.parseDouble(tfLon.getText());
		
		mapInitialized();
		
	}
			
	// BOTÃO - CAPTURAR TELA DO MAPA PARA O CROQUI
	public void btnCapturarCorqui (ActionEvent event) {  // consertar o nome do método, a palavra é croqui
	
	WritableImage i = mapView.snapshot(new SnapshotParameters(), null);
	
	File output = new File("C:\\Users\\fabmu\\OneDrive\\Documentos\\mapViewInicial.png");
	
	try {
		//ImageIO.write(i, "png", output); 
		ImageIO.write(SwingFXUtils.fromFXImage(i, null), "png", output);
	} catch (IOException e){
		e.printStackTrace();
	}
	}
	
	public void btnObterCoordEvento (ActionEvent event) {
			
		String linkCoord = (tfInserirLink.getText());
		
		int latIni= linkCoord.indexOf("@");
		String lat = linkCoord.substring(latIni);
		
		int latF = lat.indexOf(",");
		String latitude = lat.substring(1, latF);
		
		String longitude = lat.substring(latF + 1, latF + 1 + latitude.length());
		
		tfLat.setText(latitude);
		tfLon.setText(longitude);
		
	}

	// INICIALIZE DO GOOGLE MAPS
	public void mapInitialized() {
	
	// Propriedades e opcoes do mapa //
	MapOptions mapaOpcoes = new MapOptions();
	
	mapaOpcoes.center(new LatLong(latCoord,longCoord))
    .mapType(MapTypeIdEnum.SATELLITE)
    .overviewMapControl(false)
    .panControl(false)
    .rotateControl(false)
    .scaleControl(false)
    .streetViewControl(false)
    .zoomControl(true)
    .zoom(12)
    ;
	
	map = mapView.createMap(mapaOpcoes);

		// ADICIONAR MARCADOR AO MAPA
	    MarkerOptions marcador = new MarkerOptions();
	    
	    marcador.position(new LatLong(latCoord,longCoord));
	    
	    Marker markerMap = new Marker(marcador);
	    
	    map.addMarker( markerMap );  
		}

	public void initialize(URL url, ResourceBundle rb) {
		
		//MAPA
		mapView.addMapInializedListener(this);
		
	}		
			
}