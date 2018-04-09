package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import dao.InterferenciaDao;
import entity.Endereco;
import entity.Interferencia;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import principal.GoogleMap;
import tabela.InterferenciaTabela;

public class TabInterfController implements Initializable {
	
	//-- String de pesquisa de endereços --//
		String strPesquisaInterferencia = "";
	
	/*
	  vazão litros dia tem que mudar, no superficial é litros por segundo/dia.
	  
	   		Talvez colocar:
	   		 	horas de captação (converter para segundos):
	   		
	  			vazão diária:
	  			
	  			e converter caso o técnico  coloque  no formato de água superficial ou subterrâneo
	 */
	
	//-- coordenadas do mapa javascript --//
	static String latDec;
	static String lngDec;
		
	// --- Controller Principal - MainController --- //
	@FXML private MainController main;

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
	
	//@FXML Label lblEnd = new  Label();
	@FXML public Label lblEnd; // público para receber valor do MainController, método pegarEnd()
	
	//-- Tab interferência latitude e longitude --//
	@FXML TextField tfIntLat = new TextField();
	@FXML TextField tfIntLon = new TextField();
	
	@FXML TextField tfLinkInt = new TextField();
	@FXML TextField tfIntPesq = new TextField();
	
	@FXML TextField tfUH;
	@FXML TextField tfCorpoHid;
	
	
	//-- chamar mapa --//
	@FXML Button btnIntMaps;
	
	//-- trazer a coordenada do mapa --//
	@FXML Image imgGetCoord = new Image(getClass().getResourceAsStream("../images/getCoord.png"));
	@FXML Button btnCoord = new Button();
	
	// --- objeto para passar os valor pelo MainControoler para outro controller --- //
	public Endereco eGeralInt;
	
	
	//-- TableView Endereço --//
		@FXML private TableView <InterferenciaTabela> tvListaInt;
		
		@FXML TableColumn<InterferenciaTabela, String> tcDescEndInt;
		@FXML TableColumn<InterferenciaTabela, String> tcIntCorpoHidrico;
		@FXML TableColumn<InterferenciaTabela, String> tcIntUH;
	
		//-- combobox - tipo interferência --//
		@FXML
		ChoiceBox<String> cbTipoInt = new ChoiceBox<String>();
			ObservableList<String> olTipoInt = FXCollections
				.observableArrayList(
						"Subterrânea" , 
						"Superficial",
						"Canal",
						"Caminhão Pipa",
						"Lançamento de Águas Pluviais",
						"Lançamento de Efluentes",
						"Barragem",
						"Outros"
						
						
						); 
			
								@FXML
								ChoiceBox<String> cbBacia = new ChoiceBox<String>();
									ObservableList<String> olBacia = FXCollections
										.observableArrayList(
												"Corumbá",
												"Descoberto",
												"Maranhão",
												"Paranoá",
												"Preto",
												"São Bartolomeu",
												"São Marcos"
												); 
									
									
											@FXML
											ChoiceBox<String> cbSituacao = new ChoiceBox<String>();
												ObservableList<String> olSituacao = FXCollections
													.observableArrayList(
															"Ativa",
															"Inativa"
															
															); 
									
								
								
								
	// --- método para listar interferencias --- //
 	public void listarInterferencias (String strPesquisaInterferencia) {
 		
 	// --- conexão - listar endereços --- //
	InterferenciaDao interferenciaDao = new InterferenciaDao();
	List<Interferencia> interferenciaList = interferenciaDao.listInterferencia(strPesquisaInterferencia);
	ObservableList<InterferenciaTabela> obsListInterferenciaTabela = FXCollections.observableArrayList();
	
	
	if (!obsListInterferenciaTabela.isEmpty()) {
		obsListInterferenciaTabela.clear();
	}
	
		for (Interferencia interferencia : interferenciaList) {
			
			InterferenciaTabela intTab = new InterferenciaTabela(
				
				interferencia.getInter_Codigo(),
				interferencia.getInter_Tipo(),
				interferencia.getInter_Bacia(),
				interferencia.getInter_UH(),
				interferencia.getInter_Corpo_Hidrico(),
				interferencia.getInter_Lat(),
				interferencia.getInter_Lng(),
				interferencia.getInter_Situacao(),
				interferencia.getInter_Desc_Endereco(),
				
				//-- foreign key --//
				interferencia.getInter_End_CodigoFK()
				
				
				);
			
			obsListInterferenciaTabela.add(intTab);
			
 					
		}
		
		tcDescEndInt.setCellValueFactory(new PropertyValueFactory<InterferenciaTabela, String>("inter_Desc_Endereco")); 
		tcIntCorpoHidrico.setCellValueFactory(new PropertyValueFactory<InterferenciaTabela, String>("inter_Corpo_Hidrico")); 
		tcIntUH.setCellValueFactory(new PropertyValueFactory<InterferenciaTabela, String>("inter_UH")); 
		
		tvListaInt.setItems(obsListInterferenciaTabela); 
		
 	}
 	
 	// método selecionar interferência -- //
 	public void selecionarInterferencia () {
	
		tvListaInt.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Object>() {
			public void changed(ObservableValue<?> observable , Object oldValue, Object newValue) {
			
			InterferenciaTabela intTab = (InterferenciaTabela) newValue;
			
			if (intTab == null) {
				/*
				tfEnd.setText("");
				tfEndRA.setText("");
				tfEndCep.setText("");
				tfEndCid.setText("");
				tfEndUF.setText("");
				tfEndLat.setText("");
				tfEndLon.setText("");
				*/
				btnIntNovo.setDisable(true);
				btnIntSalvar.setDisable(true);
				btnIntEdit.setDisable(false);
				btnIntExc.setDisable(false);
				btnIntCan.setDisable(false);
				
			} else {

				
				// -- preencher os campos -- //
				cbTipoInt.setValue(intTab.getInter_Tipo());
				cbBacia.setValue(intTab.getInter_Bacia());
				tfUH.setText(intTab.getInter_UH());
				cbSituacao.setValue(intTab.getInter_Situacao());
				
				
				btnIntNovo.setDisable(true);
				btnIntSalvar.setDisable(true);
				btnIntEdit.setDisable(false);
				btnIntExc.setDisable(false);
				btnIntCan.setDisable(false);
				
			}
			}
		});
	}
	
	public void btnBuscarIntHab (ActionEvent event) {

		strPesquisaInterferencia = tfIntPesq.getText();
		
		listarInterferencias(strPesquisaInterferencia);
		
		//modularBotoesInicial (); 
	}
 		
	public void btnLatLongHab (ActionEvent event) {
		
	}
	
	public void btnIntAtualizarHab (ActionEvent event) {
		
	}
	public void btnIntNovoHab (ActionEvent event) {
		
	}
	public void btnIntSalvarHab (ActionEvent event) {
		
		System.out.println("clicou btn Interf Salvar ");
		System.out.println("O código do endereço é : " + eGeralInt.getCod_Endereco());
		/*
		Endereco endereco = new Endereco();
		
			endereco.setCod_Endereco(eGeralInt.getCod_Endereco());
			endereco.setDesc_Endereco(eGeralInt.getDesc_Endereco());
			endereco.setRA_Endereco(eGeralInt.getRA_Endereco());
			endereco.setCEP_Endereco(eGeralInt.getCEP_Endereco());
			endereco.setCid_Endereco(eGeralInt.getCid_Endereco());
			endereco.setUF_Endereco(eGeralInt.getUF_Endereco());
			endereco.setLat_Endereco(eGeralInt.getLat_Endereco());
			endereco.setLon_Endereco(eGeralInt.getLon_Endereco());
		*/
		
		Interferencia interferencia = new Interferencia();
		
			interferencia.setInter_Tipo(cbTipoInt.getValue().toString());
			interferencia.setInter_Bacia(cbBacia.getValue().toString());
			interferencia.setInter_UH(tfUH.getText());
			interferencia.setInter_Corpo_Hidrico(tfCorpoHid.getText());
			interferencia.setInter_Lat(Double.parseDouble(tfIntLat.getText()));
			interferencia.setInter_Lng(Double.parseDouble(tfIntLon.getText()));
			
			Endereco endereco = new Endereco();
			
			endereco = eGeralInt;
			
			endereco.getListInterferencias().add(interferencia);
		
			interferencia.setInter_End_CodigoFK(endereco);
		
			InterferenciaDao interferenciaDao = new InterferenciaDao ();
			
			interferenciaDao.mergeInterferencia(interferencia);
			
			
			//EnderecoDao enderecoDao = new EnderecoDao();
			
			//enderecoDao.mergeEnd(endereco);
			
			//-- Alerta de endereço salvo --//
			Alert aSalvo = new Alert (Alert.AlertType.CONFIRMATION);
			aSalvo.setTitle("Parabéns!");
			aSalvo.setContentText("Interferência salva com sucesso!");
			aSalvo.setHeaderText(null);
			aSalvo.show();
		
		/*
		interferencia.getl
	
	EnderecoDao enderecoDao = new EnderecoDao();
	
		enderecoDao.mergeEnd(endereco);
		
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
		*/
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
	
	//-- método atualizar latitude e longitude --//
	
	public void setLatLng (double lat, double lng) {
		
		latDec = Double.toString(lat);
		lngDec = Double.toString(lng);
			
			
	}

	//-- buscador de endereços e coordenadas --//
	public void btnIntMapsHab (ActionEvent event) throws IOException {

		GoogleMap google = new GoogleMap();
		
		Group group = new Group();
		group.getChildren().addAll(google, btnCoord);
		
		Scene scene = new Scene(group);
		
		btnCoord.setLayoutY(8);
		btnCoord.setLayoutX(502);
		btnCoord.setGraphic(new ImageView(imgGetCoord));
		
		btnCoord.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
            	tfIntLat.setText(latDec);
        		tfIntLon.setText(lngDec);
        		
            }
        });
	    
		Stage stage = new Stage(StageStyle.UTILITY);
		stage.setWidth(1250);
		stage.setHeight(750);
        stage.setScene(scene);
        
        stage.show();
	}
	
	
	
	//-- INITIALIZE --//
	public void initialize(URL url, ResourceBundle rb) {
		
		cbTipoInt.setItems(olTipoInt);
		
		cbBacia.setItems(olBacia);
		
		cbSituacao.setValue("Ativa");
		cbSituacao.setItems(olSituacao);
		
		selecionarInterferencia ();
		
	}

	public void init(MainController mainController) {
		
		main = mainController;
		
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
	