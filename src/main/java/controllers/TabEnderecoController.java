package controllers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javax.script.ScriptException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import dao.EnderecoDao;
import entity.Denuncia;
import entity.Endereco;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Worker;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import principal.GoogleMap;
import tabela.EnderecoTabela;

public class TabEnderecoController implements Initializable {

	
	// --- Controller Principal - MainController --- //
	@FXML private MainController main;
	
	@FXML Pane tabEndereco = new Pane();
	
	@FXML Button btnBuscarDoc = new Button();
	
	@FXML TextField tfEnd = new TextField();
	@FXML TextField tfEndRA = new TextField();
	
	@FXML TextField tfEndCep = new TextField();
	@FXML TextField  tfEndCid = new TextField();
	@FXML TextField tfEndUF = new TextField();
	
	@FXML public Label lblDoc; // público para receber valor do MainController, método pegarDoc()

	@FXML TextField tfLinkEnd = new TextField();
	@FXML TextField tfEndLat = new TextField();
	@FXML TextField tfEndLon = new TextField();
	
	@FXML Button btnEndNovo = new Button();
	@FXML Button btnEndSalvar = new Button();
	@FXML Button btnEndEditar = new Button();
	@FXML Button btnEndExc = new Button();
	@FXML Button btnEndCan = new Button();
	@FXML Button btnEndPesq = new Button();
	
	@FXML Button btnEndMaps = new Button ();

	@FXML Button btnDenAtualizar = new Button();
	@FXML TextField tfEndPesq = new TextField();
	
	@FXML Button btnEndLatLon = new Button();
	@FXML Button btnEndAtualizar = new Button(); 
	
	@FXML AnchorPane aPaneEnd = new AnchorPane();
	
	//-- TableView Endereço --//
	@FXML private TableView <EnderecoTabela> tvListaEnd;
	
	@FXML TableColumn<EnderecoTabela, String> tcDesEnd;
	@FXML TableColumn<EnderecoTabela, String> tcEndRA;
	@FXML TableColumn<EnderecoTabela, String> tcEndCid;
	
	// capturar endereço para a TabInterfController
	private static Endereco eGeral;
	
	//-- botão para chamar o mapa javascript --//
	@FXML Image imgGetCoord = new Image(getClass().getResourceAsStream("../images/getCoord.png"));
	@FXML Button btnCoord = new Button ();
	
	//-- coordenadas do mapa javascript --//
	static String latDec;
	static String lngDec;
	
	//-- string para chamar as coordenadas corretas do mapa --//
	String linkEndMap;
		
	// --- objeto para passar os valor pelo MainControoler para outro controller --- //
	public Denuncia dGeralEnd;
	
	//-- String de pesquisa de endereços --//
	String strPesquisaEnd = "";
	
 	// --- método para listar endereços --- //
 	public void listarEnderecos (String strPesquisa) {
 		
 	// --- conexão - listar endereços --- //
	EnderecoDao enderecoDao = new EnderecoDao();
	List<Endereco> enderecoList = enderecoDao.listEndereco(strPesquisaEnd);
	ObservableList<EnderecoTabela> obsListEnderecoTabela = FXCollections.observableArrayList();
	
	
	if (!obsListEnderecoTabela.isEmpty()) {
		obsListEnderecoTabela.clear();
	}
	
		for (Endereco endereco : enderecoList) {
			
		EnderecoTabela endTab = new EnderecoTabela(
				endereco.getCod_Endereco(),
				endereco.getDesc_Endereco(),
				endereco.getRA_Endereco(),
				endereco.getCEP_Endereco(), 
				endereco.getCid_Endereco(),
				endereco.getUF_Endereco(),
				endereco.getLat_Endereco(),
				endereco.getLon_Endereco(),
				endereco.getListDenuncias()
				);
			
			obsListEnderecoTabela.add(endTab);
 					
	}
		
		tcDesEnd.setCellValueFactory(new PropertyValueFactory<EnderecoTabela, String>("Desc_Endereco")); 
		tcEndRA.setCellValueFactory(new PropertyValueFactory<EnderecoTabela, String>("RA_Endereco")); 
		tcEndCid.setCellValueFactory(new PropertyValueFactory<EnderecoTabela, String>("CEP_Endereco")); 
		
		tvListaEnd.setItems(obsListEnderecoTabela); 
	
 	}
 	
 	// método selecionar endereço -- //
 	public void selecionarEndereco () {
	
		tvListaEnd.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Object>() {
			public void changed(ObservableValue<?> observable , Object oldValue, Object newValue) {
			
			EnderecoTabela endTab = (EnderecoTabela) newValue;
			
			if (endTab == null) {
				
				tfEnd.setText("");
				tfEndRA.setText("");
				tfEndCep.setText("");
				tfEndCid.setText("");
				tfEndUF.setText("");
				tfEndLat.setText("");
				tfEndLon.setText("");
				
				btnEndNovo.setDisable(true);
				btnEndSalvar.setDisable(true);
				btnEndEditar.setDisable(false);
				btnEndExc.setDisable(false);
				btnEndCan.setDisable(false);
				
			} else {

				// -- preencher os campos -- //
				tfEnd.setText(endTab.getDesc_Endereco());
				tfEndRA.setText(endTab.getRA_Endereco());
				tfEndCep.setText(endTab.getCEP_Endereco());
				tfEndCid.setText(endTab.getCid_Endereco());
				tfEndUF.setText(endTab.getUF_Endereco());
				tfEndLat.setText(endTab.getLat_Endereco().toString());
				tfEndLon.setText(endTab.getLon_Endereco().toString());
				
				// -- habilitar e desabilitar botões -- //
				btnEndNovo.setDisable(true);
				btnEndSalvar.setDisable(true);
				btnEndEditar.setDisable(false);
				btnEndExc.setDisable(false);
				btnEndCan.setDisable(false);
				
				
				//PRECISA MELHORAR, ESTÁ DANDO NULLPOINTEXCEPTION...
				
				/*
				if (endTab.getenderecoObjetoTabelaFK() != null) {
					
					lblDenEnd.setText(denTab.getenderecoObjetoTabelaFK().getDesc_Endereco() + ", " + denTab.getenderecoObjetoTabelaFK().getRA_Endereco());
					lblDenEnd.setTextFill(Color.BLACK);
				} else {
					lblDenEnd.setText("Sem endereço cadastrado!");
					lblDenEnd.setTextFill(Color.RED);
					
				}
				*/
				
				Endereco eGeral = new Endereco(endTab);
				System.out.println(eGeral.getCEP_Endereco());
				System.out.println(eGeral.getCid_Endereco());
				System.out.println(eGeral.getCod_Endereco());
				System.out.println(eGeral.getDesc_Endereco());
				System.out.println(eGeral.getRA_Endereco());
				System.out.println(eGeral.getUF_Endereco());
				System.out.println(eGeral.getLat_Endereco());
				System.out.println(eGeral.getLon_Endereco());
				System.out.println(eGeral.getListDenuncias());
				main.pegarEnd(eGeral);
			}
			}
		});
	}
 	
 	// -- botão novo - - //
	public void btnEndNovoHab (ActionEvent event) {
		
		tfEnd.setText("");
		tfEndRA.setText("");
		tfEndCep.setText("");
		tfEndCid.setText("");
		tfEndUF.setText("");
		tfLinkEnd.setText("");
		tfEndLat.setText("");
		tfEndLon.setText("");
		
		
		tfEnd.setDisable(false);
		tfEndRA.setDisable(false);
		tfEndCep.setDisable(false);
		tfEndCid.setDisable(false);
		tfEndUF.setDisable(false);
		tfEndLat.setDisable(false);
		tfEndLon.setDisable(false);
		tfLinkEnd.setDisable(false);
		
		btnEndSalvar.setDisable(false);
		btnEndEditar.setDisable(true);
		btnEndExc.setDisable(true);
		btnEndEditar.setDisable(true);
		
	}
	
	/*
	public static final class MyMapSearch {
		
	//-- obter as coordenadas do javascript --//
	public void getEndCoord (String lat, String lon) {
			
			System.out.println("Classe MyMapSearch " + lat + " e " + lon);
				
		}
	
	}
	*/
	
	// --  botão salvar -- //
	public void btnEndSalvarHab (ActionEvent event) {
			
		if (tfEndLat.getText().isEmpty() || tfEndLon.getText().isEmpty()) {
			
			Alert aLat = new Alert (Alert.AlertType.ERROR);
			aLat.setTitle("Alerta!!!");
			aLat.setContentText("Coordenadas inválidas!!!");
			aLat.setHeaderText(null);
			aLat.show();
			
		} else {
			
		Endereco endereco = new Endereco();
			
			endereco.setDesc_Endereco(tfEnd.getText());
			endereco.setRA_Endereco(tfEndRA.getText());
			endereco.setCEP_Endereco(tfEndCep.getText());
			endereco.setCid_Endereco(tfEndCid.getText());
			endereco.setUF_Endereco(tfEndUF.getText());
			endereco.setLat_Endereco(Double.parseDouble(tfEndLat.getText()));
			endereco.setLon_Endereco(Double.parseDouble(tfEndLon.getText()));
		
		Denuncia denuncia = new Denuncia();
			
			denuncia.setCod_Denuncia(dGeralEnd.getCod_Denuncia());
			denuncia.setDoc_Denuncia(dGeralEnd.getDoc_Denuncia());
			denuncia.setDoc_SEI_Denuncia(dGeralEnd.getDoc_SEI_Denuncia());
			denuncia.setProc_SEI_Denuncia(dGeralEnd.getProc_SEI_Denuncia());
			denuncia.setDesc_Denuncia(dGeralEnd.getDesc_Denuncia());
			
			denuncia.setEnderecoFK(endereco);
		
			endereco.getListDenuncias().add(denuncia);
		
		EnderecoDao enderecoDao = new EnderecoDao();
		
				System.out.println("endereço antes do merge: " + endereco.getCod_Endereco());
		
			//enderecoDao.mergeEnd(endereco);
				enderecoDao.salvaEndereco(endereco);
				enderecoDao.mergeEnd(endereco);
			
				System.out.println("endereço depois do merge: " + endereco.getCod_Endereco());
			
			//-- Alerta de endereço salvo --//
			Alert aSalvo = new Alert (Alert.AlertType.CONFIRMATION);
			aSalvo.setTitle("Parabéns!");
			aSalvo.setContentText("O endereço salvo com sucesso!");
			aSalvo.setHeaderText(null);
			aSalvo.show();
			
			// pegar o valor, levar para o MainController  e depois para o label lblEnd no InterfController
			eGeral = endereco;
			main.pegarEnd(eGeral);
			
			
			//-- Modular botões --//
			modularBotoesInicial ();
			
		}
			
	}
	
	// -- botão editar -- //
	public void btnEndEditarHab (ActionEvent event) {
		
		if (tfEnd.isDisable()) {
			
			tfEnd.setDisable(false);
			tfEndRA.setDisable(false);
			tfEndCep.setDisable(false);
			tfEndCid.setDisable(false);
			tfEndUF.setDisable(false);
			tfEndLat.setDisable(false);
			tfEndLon.setDisable(false);
			tfLinkEnd.setDisable(false);
				
		} else {
		
		EnderecoTabela enderecoTabelaEditar = tvListaEnd.getSelectionModel().getSelectedItem();
		Endereco endereco = new Endereco(enderecoTabelaEditar);
		
		endereco.setDesc_Endereco(tfEnd.getText());
		endereco.setRA_Endereco(tfEndRA.getText());
		endereco.setCEP_Endereco(tfEndCep.getText());
		endereco.setCid_Endereco(tfEndCid.getText());
		endereco.setUF_Endereco(tfEndUF.getText());
		endereco.setLat_Endereco(Double.parseDouble(tfEndLat.getText()));
		endereco.setLon_Endereco(Double.parseDouble(tfEndLon.getText()));
	
		Denuncia denuncia = new Denuncia();
		
		denuncia.setCod_Denuncia(dGeralEnd.getCod_Denuncia());
		denuncia.setDoc_Denuncia(dGeralEnd.getDoc_Denuncia());
		denuncia.setDoc_SEI_Denuncia(dGeralEnd.getDoc_SEI_Denuncia());
		denuncia.setProc_SEI_Denuncia(dGeralEnd.getProc_SEI_Denuncia());
		denuncia.setDesc_Denuncia(dGeralEnd.getDesc_Denuncia());
		denuncia.setEnderecoFK(endereco);
		
		endereco.getListDenuncias().add(denuncia);
		
		EnderecoDao enderecoDao = new EnderecoDao();
	
		enderecoDao.mergeEnd(endereco);
		
		
		// pegar o valor, levar para o MainController  e depois para o label lblEnd no InterfController
		eGeral = endereco;
		main.pegarEnd(eGeral);
					
					
		listarEnderecos(strPesquisaEnd);
		
		modularBotoesInicial (); 
		
		}	
	}
	
	// -- botão excluir -- //
	public void btnEndExcHab (ActionEvent event) {
		
		EnderecoTabela endereco = tvListaEnd.getSelectionModel().getSelectedItem();
		
		int id = endereco.getCod_Endereco();
		
		//enderecoList = enderecoDao.listEndereco();
		
		// obsList.
		
		EnderecoDao enderecoDao = new EnderecoDao();
		
		if (!endereco.getListTabelaEnderecoDenuncias().isEmpty()) { // eu vejo que é  se !endereco  diferente de vazio, isso quer dizer que há denúncia associada
			
			//-- Alerta de denúncia salva --//
			Alert aSalvo = new Alert (Alert.AlertType.ERROR);
			aSalvo.setTitle("Alerta!!!");
			aSalvo.setContentText("Há denúncia associada à este endereço! Delete antes a denúncia!");
			aSalvo.setHeaderText(null);
			aSalvo.show();
			
		} else {
			
			enderecoDao.removeEndereco(id);
			
			listarEnderecos(strPesquisaEnd);
			
			modularBotoesInicial();
		}
		
	}
	
	//-- botão cancelar --//
	public void btnEndCanHab (ActionEvent event) {

		modularBotoesInicial (); 
		
	}
	
	//-- botão pesquisar endereço --//
	public void btnEndPesqHab (ActionEvent event) {
		
		strPesquisaEnd = tfEndPesq.getText();
		
		//enderecoList = enderecoDao.listEndereco();
			// não entendi este endereço list aqui
		
		listarEnderecos (strPesquisaEnd);
		
		modularBotoesInicial (); 
	
	}
	
	//-- botão obter coordenadas do link --//
	public void  btnEndLatLonHab (ActionEvent event) {
		
		if (tfLinkEnd.getText().isEmpty()) {
			
			Alert aLink = new Alert (Alert.AlertType.ERROR);
			aLink.setTitle("Alerta!!!");
			aLink.setContentText("Não há link associado à solicitação!");
			aLink.setHeaderText(null);
			aLink.show();
			
		} else {
			
			String linkEndCoord = (tfLinkEnd.getText());
			
			int latIni= linkEndCoord.indexOf("@");
			String lat = linkEndCoord.substring(latIni);
			
			int latF = lat.indexOf(",");
			String latitude = lat.substring(1, latF);
			
			String longitude = lat.substring(latF + 1, latF + latitude.length());
			
			 /*
			 tirei o +1 para resolver o aparecimento da vírgula neste link
			 https://www.google.com.br/maps/place/Brazl%C3%A2ndia,+Bras%C3%ADlia+-+DF/@-15.8422254,-48.097489,10364m/data=!3m1!1e3!4m5!3m4!1s0x935bb399f0e712b7:0xe5dd05c541a49871!8m2!3d-15.6701849!4d-48.200585
			 String longitude = lat.substring(latF + 1, latF + 1 + latitude.length());
			 */
			
			tfEndLat.setText(latitude);
			tfEndLon.setText(longitude);
			
		}
	}
	
	//-- Botão atualizar mapa de acordo com as coordenadas --//
	public void btnEndAtualizarHab (ActionEvent event) throws IOException, ScriptException {
		
		
		//-- jsoup e html maps --//
		String latMap = tfEndLat.getText();
		String lonMap = tfEndLon.getText();
		
		File file = new File("../FiscalizacaoSRH/src/main/resources/html/enderecoMap.html");
			
		Document docHtml = Jsoup.parse(file, "UTF-8").clone();
	
		docHtml.select("script").prepend("var uluru = {lat: " + latMap + ", lng: " + lonMap + "};");
	
		linkEndMap = docHtml.toString();
		
		getWvEndMap ();
		
		
	}
	
	//-- Botão pesquisar documento na TabEndereco --//
	public void btnBuscarDocHab (ActionEvent event) {
		
	}
	
	
	//-- buscador de endereços e coordenadas --//
		public void btnEndMapsHab (ActionEvent event) throws IOException {

			
			
			GoogleMap google = new GoogleMap();
			
			Group group = new Group();
			group.getChildren().addAll(google, btnCoord);
			
			Scene scene = new Scene(group);
			
			btnCoord.setLayoutY(8);
			btnCoord.setLayoutX(502);
			btnCoord.setGraphic(new ImageView(imgGetCoord));
			
			btnCoord.setOnAction(new EventHandler<ActionEvent>() {
	            @Override public void handle(ActionEvent e) {
	            	tfEndLat.setText(latDec);
	        		tfEndLon.setText(lngDec);
	        		
	            }
	        });
		    
			Stage stage = new Stage(StageStyle.UTILITY);
			stage.setWidth(1250);
			stage.setHeight(750);
	        stage.setScene(scene);
	        
	        stage.show();
		}
	
	//-- INITIALIZE --//
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		
		//-- Modular a forma de abrir dos botões --//
		modularBotoesInicial ();
		//-- Selecionar endereço --//
		selecionarEndereco ();
		
		/*
		//-- Chamar o Google Maps da TabEndereco --//
		Platform.runLater(() ->{
			getWvEndMap ();
			});
		*/
	}

	//-- Modular os botões na iniciação do programa --//
	private void modularBotoesInicial () {
		
		tfEnd.setDisable(true);
		tfEndRA.setDisable(true);
		tfEndCep.setDisable(true);
		tfEndCid.setDisable(true);
		tfEndUF.setDisable(true);
		tfEndLat.setDisable(true);
		tfEndLon.setDisable(true);
		tfLinkEnd.setDisable(true);
		tfLinkEnd.setText("");
		
		btnEndSalvar.setDisable(true);
		btnEndEditar.setDisable(true);
		btnEndExc.setDisable(true);
		btnEndNovo.setDisable(false);
		
	}
	
	//-- Método getWebView para o mapa do endereço --//
	public void getWvEndMap () {
		
		WebView wv1 = new WebView();
		WebEngine engine = wv1.getEngine();
		engine.loadContent(linkEndMap);
		
		wv1.setPrefSize(700,500);
		wv1.getEngine();
	
		BorderPane root = new BorderPane();
		root.setCenter(wv1);
		root.setPrefSize(700, 420);
		root.setLayoutY(365);
		root.setLayoutX(220);
		
		aPaneEnd.getChildren().add(root);
	
		engine.getLoadWorker().stateProperty().addListener((observable, oldValue, newValue) -> {
            if (Worker.State.SUCCEEDED.equals(newValue)) {
            	
            }
        });
		
	}
	
	//-- método atualizar latitude e longitude --//
	
	public void setLatLng (double lat, double lng) {
		
		latDec = Double.toString(lat);
		lngDec = Double.toString(lng);
			
			
	}
	
	
	//-- Main Controller --//
	public void init(MainController mainController) {
		main = mainController;
	}

	
}

/*
 WebView wv1 = new WebView();
		wv1.setPrefSize(700,500);
		wv1.getEngine().loadContent(linkEndMap);
	
		BorderPane root = new BorderPane();
		root.setCenter(wv1);
		root.setPrefSize(700, 420);
		root.setLayoutY(349);
		root.setLayoutX(122);
		
		aPaneEnd.getChildren().add(root);
	*/


/*
 //-- Buscador de endereços e coordenadas --//
	public void btnEndMapsHab (ActionEvent event) throws IOException {
		
		File file = new File("../FiscalizacaoSRH/src/main/resources/html/mapSearch.html");
		
		Document docHtml = Jsoup.parse(file, "UTF-8").clone();
	
		linkEndMap = docHtml.toString();
		
		WebView wvEndMapsView = new WebView();
		WebEngine weEndMapsView = wvEndMapsView.getEngine();
		weEndMapsView.loadContent(linkEndMap);
		wvEndMapsView.setMaxSize(500, 300);
		
		
		
		Stage stage = new Stage(StageStyle.UTILITY);
        stage.setScene(new Scene(wvEndMapsView, 911, 600));
        stage.show();
	}
	*/


/*
 
 File file = new File("../FiscalizacaoSRH/src/main/resources/html/mapSearch.html");
		
		Document docHtml = Jsoup.parse(file, "UTF-8").clone();
	
		linkEndMap = docHtml.toString();
		
		WebView wvEndMapsView = new WebView();
		WebEngine weEndMapsView = wvEndMapsView.getEngine();
		
		
		//weEndMapsView.loadContent("../FiscalizacaoSRH/src/main/resources/html/mapSearch.html");
		weEndMapsView.loadContent(linkEndMap);
		wvEndMapsView.setMaxSize(500, 300);
		
		JSObject window = (JSObject) weEndMapsView.executeScript("window");
		window.setMember("app", new TabEnderecoController());
  */


/*
 
 
 //-- Buscador de endereços e coordenadas --//
	public void btnEndMapsHab (ActionEvent event) throws IOException {
		
		//File file = new File("../FiscalizacaoSRH/src/main/resources/html/mapSearch.html");
		
		//Document docHtml = Jsoup.parse(file, "UTF-8").clone();
	
		//linkEndMap = docHtml.toString();
		
		WebView wvEndMapsView = new WebView();
		WebEngine weEndMapsView = wvEndMapsView.getEngine();
		
		
		weEndMapsView.getLoadWorker().stateProperty().addListener(
				
				new ChangeListener<Worker.State>() {
					@Override
					public void changed (ObservableValue<? extends Worker.State> observable, Worker.State
							oldValue, Worker.State newValue) {
						if (newValue == Worker.State.SUCCEEDED) {
							System.out.println("succeded");
							JSObject windowObject = (JSObject) weEndMapsView.executeScript("window");
							windowObject.setMember("app", new TabEnderecoController());
							windowObject.call("ready");
							
						}
					}
				}
			);
				
		URL url = getClass().getResource("/html/mapSearch.html");	
		weEndMapsView.load(url.toString());
		
  */


/*
 
 public static class MeuMapa () {
		
		//-- obter as coordenadas do javascript --//
		public void getEndCoord (String lat, String lon) {
			
			try {
				//tfLatMap.setText(lat):
				tfEndLat.setText(lat);
				tfEndLon.setText(lon);
			
				System.out.println("primeiro método: " + lat + " e " + lon);
				
				getEndCoordJavaFX (lat, lon);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
 
 */


//File file = new File("../FiscalizacaoSRH/src/main/resources/html/mapSearch.html");

//Document docHtml = Jsoup.parse(file, "UTF-8").clone();

//linkEndMap = docHtml.toString();


//weEndMapsView.loadContent(linkEndMap);
/*
weEndMapsView.loadContent("../FiscalizacaoSRH/src/main/resources/html/mapSearch.html");
weEndMapsView.loadContent(linkEndMap);
wvEndMapsView.setMaxSize(500, 300);

JSObject window = (JSObject) weEndMapsView.executeScript("window");
window.setMember("app", new TabEnderecoController());


engine.getLoadWorker().stateProperty().addListener((observable, oldValue, newValue) -> {
    if (Worker.State.SUCCEEDED.equals(newValue)) {
    	
    }
});

*/


//TENTANDO INCLUIR O WEBVIEW UM TEXTFIELD JAVAFX E PREENCHER COM AS COORDENADAS

//TextField tfLatMap = new TextField();
//tfLatMap.setText("TextField");
//tfLatMap.setLayoutY(10);

/*
	DOIS WEBVIEW PARA DIVIDIR A PARTE DO JSOBJECT DO TEXTFIELD QUE RECEBE AS COORDENADAS
	
	NÃO DEU CERTO, TERMINA QUE O JSOBJECT PERDE A REFERÊNCIA
	
	
public void btnEndMapsHab (ActionEvent event) throws IOException {
	
	WebView wvEndMapsView = new WebView();
	WebView wvEndBuscador = new WebView();
	
	WebEngine weEndMapsView = wvEndMapsView.getEngine();
	WebEngine weEngineEndBuscador = wvEndBuscador.getEngine();
	
	URL url = getClass().getResource("/html/mapSearch.html");	
	weEndMapsView.load(url.toString());
	
	weEngineEndBuscador.getLoadWorker().stateProperty().addListener(
			
			new ChangeListener<Worker.State>() {
				@Override
				public void changed (ObservableValue<? extends Worker.State> observable, Worker.State
						oldValue, Worker.State newValue) {
					if (newValue == Worker.State.SUCCEEDED) {
						System.out.println("succeded");
						JSObject windowObject = (JSObject) weEngineEndBuscador.executeScript("window");
						windowObject.setMember("app", new MyMapSearch());
						
					} 
				}
			}
		);
			
	URL urlEndBuscador = getClass().getResource("/html/endBuscador.html");	
	
	weEngineEndBuscador.load(urlEndBuscador.toString());
	
	wvEndMapsView.setLayoutY(50);
	wvEndBuscador.setLayoutY(5);
	
	Pane paneMap = new Pane();
	paneMap.getChildren().setAll(wvEndBuscador, wvEndMapsView);
	
	Stage stage = new Stage(StageStyle.UTILITY);
    stage.setScene(new Scene(paneMap));  //911600   
    //stage.setScene(new Scene(wvEndMapsView, 911, 600));
    stage.show();
}

*/

