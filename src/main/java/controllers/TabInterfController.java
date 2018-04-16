package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import dao.InterferenciaDao;
import dao.SubterraneaDao;
import entity.Endereco;
import entity.Interferencia;
import entity.Subterranea;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
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
	
	//-- SUPERFICIAL --//
	
	@FXML Pane tabSuperficial = new Pane ();
	
	@FXML TextField tfMarcaBomba = new TextField();
	@FXML TextField tfPotenciaBomba = new TextField();
	@FXML TextField tfTempoBomba = new TextField();
	@FXML TextField tfArea = new TextField();
	
	@FXML DatePicker dtDataOperacao = new DatePicker();
	
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
			
	@FXML Image imSup = new Image(getClass().getResourceAsStream("../images/superficial.png"));
	@FXML ImageView	iVewSup;
	
	//-- ^^^^^^ SUPERFICIAL ^^^^^^ --//
	
	//-- SUBTERRÂNEA --//
	
	/*
	
	@FXML Pane tabSubterranea = new Pane();
	
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
				
	@FXML Image imSubt = new Image(getClass().getResourceAsStream("../images/subterranea.png"));
	@FXML ImageView	iVewSubt;
	
	@FXML TextField tfVazao;
	@FXML TextField tfEstatico;
	@FXML TextField tfDinamico;
	@FXML TextField tfProfundidade;
	@FXML DatePicker dpDataSubterranea;
		
				
				*/
		
	//-- ^^^^^ SUBTERRÂNEA ^^^^^^ --//
			
	
	
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
	public static String latDec;
	public static String lngDec;
		
	// --- Controller Principal - MainController --- //
	@FXML private MainController main;
	
	@FXML Pane paneTipoInterferencia = new Pane();

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
				tfCorpoHid.setText(intTab.getInter_Corpo_Hidrico());
				cbSituacao.setValue(intTab.getInter_Situacao());
					// latitude e longitude
				tfIntLat.setText(intTab.getInter_Lat().toString());
				tfIntLon.setText(intTab.getInter_Lng().toString());
				
				
				
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

		
	}
 		
	public void btnLatLongHab (ActionEvent event) {
		
	}
	
	public void btnIntAtualizarHab (ActionEvent event) {
		
	}
	public void btnIntNovoHab (ActionEvent event) {
		
	}
	
	
	
	
	public void btnIntSalvarHab (ActionEvent event) {
		
		Interferencia interferencia = new Interferencia();
		
			interferencia.setInter_Tipo(cbTipoInt.getValue().toString());
			interferencia.setInter_Bacia(cbBacia.getValue().toString());
			interferencia.setInter_UH(tfUH.getText());
			interferencia.setInter_Corpo_Hidrico(tfCorpoHid.getText());
			interferencia.setInter_Situacao(cbSituacao.getValue().toString());
			interferencia.setInter_Desc_Endereco(eGeralInt.getDesc_Endereco());
			interferencia.setInter_Lat(Double.parseDouble(tfIntLat.getText()));
			interferencia.setInter_Lng(Double.parseDouble(tfIntLon.getText()));
			
		Endereco endereco = new Endereco();
			
			endereco = eGeralInt;
			
			endereco.getListInterferencias().add(interferencia);
		
			
			interferencia.setInter_End_CodigoFK(endereco);
		
			InterferenciaDao interferenciaDao = new InterferenciaDao ();
			
			interferenciaDao.mergeInterferencia(interferencia);
			
		Subterranea sub = new Subterranea ();
		
		
			
		
		/*
					Pane tabSubterranea = new Pane();
		
					try {
						tabSubterranea = FXMLLoader.load(getClass().getResource("/fxml/TabSubterranea.fxml"));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					*/
		
			//TabSubterraneaController tSubCon = new TabSubterraneaController();
		


			FXMLLoader fxmlLoader = new FXMLLoader(
	            getClass().getResource("/fxml/TabSubterranea.fxml"));
			TabSubterraneaController tabSubCon = (TabSubterraneaController) fxmlLoader.getController();
			
			System.out.println("Tipo de poço: " + tabSubCon.cbTipoCaptacao.getValue() );
			
			System.out.println("textfield " + tabSubCon.tfVazao.getText() );
		
				
					
					/*
					sub.setSub_Poco(cbTipoInt.getValue().toString());
					
					System.out.println("Subsistema: " + cbSubSis.getValue());
					
					sub.setSub_Sistema(cbSubSis.getValue().toString());
					
					sub.setSub_Vazao(tfVazao.getText());
					sub.setSub_Estatico(tfEstatico.getText());
					sub.setSub_Dinamico(tfDinamico.getText());
					sub.setSub_Profundidade(tfProfundidade.getText());
					
					sub.setSub_Caesb(cbCaesb.getValue().toString());
					//-- colocar a data de operação
			
					sub.setInterf_Sub(interferencia);
					
			
			*/
		
			SubterraneaDao sDao = new SubterraneaDao();
			
					
			sDao.mergeSubterranea(sub);
			
			
			
					
			
			//-- Alerta de endereço salvo --//
			Alert aSalvo = new Alert (Alert.AlertType.CONFIRMATION);
			aSalvo.setTitle("Parabéns!");
			aSalvo.setContentText("Interferência salva com sucesso!");
			aSalvo.setHeaderText(null);
			aSalvo.show();
		
	}
	
	public void btnIntEditHab (ActionEvent event) {
		
	}
	public void btnIntExcHab (ActionEvent event) {
		
	}
	public void btnIntCanHab (ActionEvent event) {
		
	}
	public void btnIntPesqHab (ActionEvent event) {
		
		strPesquisaInterferencia = tfIntPesq.getText();
		
		listarInterferencias(strPesquisaInterferencia);
		
		//modularBotoesInicial (); 
		
		selecionarInterferencia ();
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
        stage.setMaximized(false);
        stage.setResizable(false);
        
        stage.show();
        
        /* para ao fechar o mapa capturar as coordenadas, mas resolvi tirar 
         
        stage.setOnCloseRequest(new EventHandler<javafx.stage.WindowEvent>() {
            public void handle(javafx.stage.WindowEvent event) {
            	//lblEndereco.setText(endMap);
            	tfIntLat.setText(latDec);
            	tfIntLon.setText(lngDec);
                System.out.println("latitude e longitude da interferência captados");
            }
        });
        */
	}
	
	
	
	
	
	//-- INITIALIZE --//
	public void initialize(URL url, ResourceBundle rb) {
		
		
		cbTipoInt.setItems(olTipoInt);
		
		cbBacia.setItems(olBacia);
		
		cbSituacao.setValue("Inativa");
		cbSituacao.setItems(olSituacao);
		
		//selecionarInterferencia ();
		
		//-- SUPERFICIAL --//
		cbTipoInt.getSelectionModel().selectedItemProperty().addListener( 
				(ObservableValue<? extends String> observable, String oldString, String newString) -> 
				
				{
					try {
						abrirTabs(newString);
					} catch (IOException e) {
						System.out.println("erro na chamada do método abrirTabSuperficial: " + e);
						e.printStackTrace();
					}
				} 
				
				);
		
		cbCaesb.setItems(olCaesb);
		cbCaptacao.setItems(olCaptacao);
		cbFormaCaptacao.setItems(olFormaCaptacao);
		
		//-- SUBTERRÂNEA --//
		
		/*
		cbTipoCaptacao.setItems(olTipoCaptacao);
		cbSubCaesb.setItems(olSubCaesb);
	
		cbSubSis.setItems(olSubSis);
		*/
	}

	//-- MAIN CONTROLLER --//
	public void init(MainController mainController) {
		
		main = mainController;
		
	}
	
	//-- controlar as telas de subterraneo, superficial etc --//
	public Node getNode(String node){
        Node no = null;
        try {
            no = FXMLLoader.load(getClass().getResource(node));
        } catch (Exception e) {
        }
        return no;
        
    }
	
	public void abrirTabs (String newString) throws IOException {
		
		if (newString == "Superficial") {
			
			paneTipoInterferencia.getChildren().clear(); // limpar o pane
			
			Pane tabSuperficial = new Pane();
			
			tabSuperficial = FXMLLoader.load(getClass().getResource("/fxml/TabSuperficial.fxml"));
			
			iVewSup = new ImageView();
			iVewSup.setImage(imSup);
			
			Group groupSup = new Group(tabSuperficial, iVewSup);
			
			//paneTipoInterferencia = FXMLLoader.load(getClass().getResource("/fxml/TabInterferencia.fxml"));
			paneTipoInterferencia.getChildren().add(groupSup);
			
			
			
		}
		

		if (newString == "Canal") {
			
			paneTipoInterferencia.getChildren().clear(); // limpar o pane
			
			Pane tabSuperficial = new Pane();
			
			tabSuperficial = FXMLLoader.load(getClass().getResource("/fxml/TabSuperficial.fxml"));
			
			iVewSup = new ImageView();
			iVewSup.setImage(imSup);
			
			Group groupSup = new Group(tabSuperficial, iVewSup);
			
			//paneTipoInterferencia = FXMLLoader.load(getClass().getResource("/fxml/TabInterferencia.fxml"));
			paneTipoInterferencia.getChildren().add(groupSup);
			
			
			
		}
		
		if (newString == "Subterrânea") {
			
			paneTipoInterferencia.getChildren().clear();
			paneTipoInterferencia.getChildren().add(getNode("/fxml/TabSubterranea.fxml"));
			
			
			/*
			
			//paneTipoInterferencia.getChildren().clear(); // limpar o pane
			
			FXMLLoader fxmlLoader = new FXMLLoader(
		            getClass().getResource("/fxml/TabSubterranea.fxml"));
			
			
			
			paneTipoInterferencia = fxmlLoader.load();   //  FXMLLoader.load(getClass().getResource("/fxml/TabSubterranea.fxml"));
			
			//TabSubterraneaController tabSubCon = (TabSubterraneaController) fxmlLoader.getController();
			
			Scene tabSubCen = new Scene(paneTipoInterferencia);
			
			//paneTipoInterferencia.getChildren(tabSubCen);
			
			Stage stage = new Stage();
			stage.setX(70);
			stage.setY(401); // layoutX="70.0" layoutY="401.0"
			
			//tabSubCon.setStage(stage);
			
			//tabSubCen.
			stage.setScene(tabSubCen);
			
			stage.show();
			
			*/
			
			
			//paneTipoInterferencia.getChildren().add(tabSubCena);
			
			//paneTipoInterferencia.getChildren().addAll(tabSub);
			
			/*
			Pane tabSubterranea = new Pane();
			
			tabSubterranea = FXMLLoader.load(getClass().getResource("/fxml/TabSubterranea.fxml"));
			
			ChoiceBox<String> cbTipoCaptacao = new ChoiceBox<String>();
			
			*/
			
			/*
			tfVazao = new TextField();
			
			iVewSubt = new ImageView();
			iVewSubt.setImage(imSubt);
			
			Group groupSubt = new Group(tabSubterranea, iVewSubt, cbTipoCaptacao, tfVazao);
			   */                                  
			//paneTipoInterferencia.getChildren().add(groupSubt);
			
		
		}
		
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



/*
tabSuperficial = new Pane ();
tabSuperficial.setLayoutX(176.0);
tabSuperficial.setLayoutY(419.0);

paneTipoInterferencia.getChildren().add(tabSuperficial);


Label label = new Label("Área atendida pela Caesb: ");
tabSuperficial.getChildren().add(label);

*/



/*
Pane tabSuperficial = new Pane();

Label lblCaesb = new Label("Área atendida pela Caesb: ");
Label lblLocal = new Label ("Local de Captação: ");
Label lblForma = new Label ("Forma de captação: ");
Label lblMarca = new Label ("Marca da Bomba: ");
Label lblPotencia = new Label ("Potência da Bomba (cv): ");
Label lblTempo = new Label ("Tempo (h/dia): ");
Label lblAreaPro = new Label ("Tamanho da Porpriedade (ha): ");
Label lblOperacao = new Label ("Em operação desde: ");

ChoiceBox<String> cbCaesb = new ChoiceBox<String>();
	ObservableList<String> olCaesb = FXCollections
		.observableArrayList(
				"Sim" , 
				"Não"
				); 

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

		ChoiceBox<String> cbFormaCaptacao = new ChoiceBox<String>();
			ObservableList<String> olFormaCaptacao = FXCollections
				.observableArrayList(
						"Gravidade" , 
						"Bombeamento",
						"Outro"
						); 
			
		TextField tfMarcaBomba = new TextField();
		TextField tfPotenciaBomba = new TextField();
		TextField tfTempoBomba = new TextField();
			
tabSuperficial.setPrefHeight(306);
tabSuperficial.setPrefWidth(768);

Group group = new Group();

group.getChildren().addAll(
		
		tabSuperficial,
		lblCaesb,
		cbCaesb,
		cbCaptacao,
		cbFormaCaptacao,
		tfMarcaBomba,
		tfPotenciaBomba,
		tfTempoBomba
		);

paneTipoInterferencia.getChildren().add(group);

lblCaesb.setLayoutY(17);
lblCaesb.setLayoutX(57);

cbCaesb.setLayoutX(166);
cbCaesb.setLayoutY(53);
cbCaesb.setItems(olCaesb);


*/



/*
Parent root = FXMLLoader.load(getClass().getResource("/fxml/TabSuperficial.fxml"));
Stage stage = (Stage) paneTipoInterferencia.getScene().getWindow();
Scene scene = new Scene(root);
stage.setScene(scene);
stage.show();
*/

//paneTipoInterferencia.getScene().setRoot(FXMLLoader.load(getClass().getResource("/fxml/TabSuperficial.fxml")));

//paneTipoInterferencia.getChildren().add(tabSuperficial);




/*
 Scene scene = new Scene(group);
 
 Stage stage = new Stage(StageStyle.UTILITY);
	stage.setWidth(1250);
	stage.setHeight(750);
    stage.setScene(scene);
    
    stage.show();
 
 //--  <Pane fx:id="paneTipoInterferencia" layoutX="176.0" layoutY="419.0" prefHeight="310.0" prefWidth="807.0" /> --//
 */


	