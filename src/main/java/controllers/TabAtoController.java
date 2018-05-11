package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import dao.AtoDao;
import entity.Ato;
import entity.Endereco;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import tabela.AtoTabela;

public class TabAtoController implements Initializable{
	
	String strPesquisaAto = "";
	
	@FXML	Pane tabAto = new Pane ();
	
	@FXML Button btnNovo;
	@FXML Button btnSalvar;
	@FXML Button btnEditar;
	@FXML Button btnExcluir;
	@FXML Button btnCancelar;
	@FXML Button btnPesquisar;
	
	
	
	//-- TableView Endereço --//
			@FXML private TableView <AtoTabela> tvAto;
			
			@FXML TableColumn<AtoTabela, String> tcTipo;
			@FXML TableColumn<AtoTabela, String> tcNumero;
			@FXML TableColumn<AtoTabela, String> tcSEI;
		
			
			
	
	@FXML TextField tfPesquisar;
	
	@FXML
	ChoiceBox<String> cbAtoTipo = new ChoiceBox<String>();
		ObservableList<String> olAtoTipo = FXCollections
			.observableArrayList(
					"Relatório de Vistoria" , 
					"Termo de Notificação", 
					"Auto de Infração de Advertência",
					"Auto de Infração de Multa");
	
	@FXML public Label lblUsEnd; // público para receber valor do MainController, método pegarEnd()
	
	// --- objeto para passar os valor pelo MainControoler para outro controller --- //
	public Endereco eGeralUs;
	
	// --- Controller Principal - MainController --- //
	@FXML private MainController main;
	
	//-- MAIN CONTROLLER --//
	public void init(MainController mainController) {
			main = mainController;
	}
	
	public void btnNovoHab (ActionEvent event) {
		
	}

	public void btnSalvarHab (ActionEvent event) {
		
	}

	public void btnEditarHab (ActionEvent event) {
		
	}

	public void btnExcluirHab (ActionEvent event) {
	
	}
	
	public void btnCancelarHab (ActionEvent event) {
		
	}

	public void btnPesquisarHab (ActionEvent event) {
		
		listarAtos (strPesquisaAto);
	
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		cbAtoTipo.setItems(olAtoTipo);
		
		
		//-- Selecionar a tabela de acordo com o tipo de captação --//
		cbAtoTipo.getSelectionModel().selectedItemProperty().addListener( 
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
		
	}
	
	TabVistoriaController tabVis;
	
	@FXML Pane paneTipoAto;
	
	public void abrirTabs (String newString) throws IOException {
		
		if (newString == "Relatório de Vistoria") {
			
			paneTipoAto.getChildren().clear();
			
			Pane paneVistoria = new Pane();
			
			tabVis = new TabVistoriaController();
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/TabVistoria.fxml"));
			loader.setRoot(paneTipoAto);
			loader.setController(tabVis);
			loader.load();
			
			paneTipoAto.getChildren().add(paneVistoria);
			
		}
		
	}
	
	// --- método para listar interferencias --- //
 	public void listarAtos (String strPesquisaAto) {
 	
	 	// --- conexão - listar endereços --- //
		AtoDao atoDao = new AtoDao();
		List<Ato> atoList = atoDao.listAto(strPesquisaAto);
		ObservableList<AtoTabela> olAto = FXCollections.observableArrayList();
		
		
		if (!olAto.isEmpty()) {
			olAto.clear();
		}
		
			for (Ato ato : atoList) {
				
				AtoTabela atoTab = new AtoTabela(
					
				ato.getAtoCodigo(),
				ato.getAtoEndCodigo(),
				ato.getAtoVistoriaFK(),  // tipo identificacao sei cara infr atenu agrav datafis datacri
				ato.getAtoTipo(),
				ato.getAtoIdentificacao(),
				ato.getAtoSEI(),
				ato.getAtoCaracterizacao(),
				ato.getAtoInfracao(),
				ato.getAtoAtenuante(),
				ato.getAtoAgravante(),
				ato.getAtoDataFiscalizacao(),
				ato.getAtoDataCriacao()
				
				);
			
			olAto.add(atoTab);
			
 					
		}
		
		tcTipo.setCellValueFactory(new PropertyValueFactory<AtoTabela, String>("atoTipo")); 
		tcNumero.setCellValueFactory(new PropertyValueFactory<AtoTabela, String>("atoIdentificacao")); 
		tcSEI.setCellValueFactory(new PropertyValueFactory<AtoTabela, String>("atoSEI")); 
		
		tvAto.setItems(olAto);
		
 	}
	 	
	 	/*
	 	// método selecionar interferência -- //
	 	public void selecionarInterferencia () {
		
			tvListaInt.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Object>() {
				public void changed(ObservableValue<?> observable , Object oldValue, Object newValue) {
				
				intTab = (InterferenciaTabela) newValue;
				
				if (intTab == null) {
					
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
					
					// mudar o endereço da interfência de acordo com a seleção do usuário
					eGeralInt = intTab.getEnderecoInterferenciaObjetoTabelaFK();
					
					lblEnd.setText(eGeralInt.getDesc_Endereco()  + " |  RA: "  + eGeralInt.getRA_Endereco());
					
					String tipoInt = intTab.getInter_Tipo();
					
					if (tipoInt.equals("Subterrânea")) {
						
						tabSubCon.imprimirSubterranea(intTab.getInterSub());
						
					}
					
					if (tipoInt.equals("Superficial") || tipoInt.equals("Canal")) {
						
						tabSupCon.imprimirSuperficial(intTab.getInterSup());
						
					}
					
					btnIntNovo.setDisable(true);
					btnIntSalvar.setDisable(true);
					btnIntEdit.setDisable(false);
					btnIntExc.setDisable(false);
					btnIntCan.setDisable(false);
					
					}
				}
			});
		}
		
		*/
	

}
