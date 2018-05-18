package controllers;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.List;
import java.util.ResourceBundle;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import dao.VistoriaDao;
import entity.Endereco;
import entity.Vistoria;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.web.HTMLEditor;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.StringConverter;
import tabela.VistoriaTabela;

public class TabVistoriaController implements Initializable{
	
	@FXML Pane tabVistoria = new Pane();
	
	
	@FXML Button btnNovo;
	@FXML Button btnSalvar;
	@FXML Button btnEditar;
	@FXML Button btnExcluir;
	@FXML Button btnCancelar;
	@FXML Button btnPesquisar;
	
	@FXML TextField tfAto;
	@FXML TextField tfAtoSEI;
	
	@FXML TextField tfPesquisar;
	
	@FXML DatePicker dpDataFiscalizacao;
	@FXML DatePicker dpDataCriacaoAto;
	
	//Locale.setDefault(new Locale("pt", "BR"));
	
	//private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	DateTimeFormatter formatter = new DateTimeFormatterBuilder()
			.parseCaseInsensitive()
			.append(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
			.toFormatter();
			
	
	
	// TableView Endereço //
			@FXML private TableView <VistoriaTabela> tvVistoria;
			
			@FXML TableColumn<VistoriaTabela, String> tcNumero;
			@FXML TableColumn<VistoriaTabela, String> tcSEI;
			@FXML TableColumn<VistoriaTabela, String> tcData;
	
	@FXML TextArea taObjeto;
	@FXML TextArea taApresentacao;
	@FXML TextArea taRelato;
	@FXML TextArea taRecomendacoes;
	
	@FXML Button btnPesquisarObjeto;
	@FXML Button  btnPesquisarApresentacao;
	@FXML Button btnPesquisarRelato;
	@FXML Button btnRelatorio;
	
	
	@FXML CheckBox checkInfra1;
	@FXML CheckBox checkInfra2;
	@FXML CheckBox checkInfra3;
	@FXML CheckBox checkInfra4;
	@FXML CheckBox checkInfra5;
	@FXML CheckBox checkInfra6;
	@FXML CheckBox checkInfra7;
	
	@FXML CheckBox checkPena1;
	@FXML CheckBox checkPena2;
	@FXML CheckBox checkPena3;
	@FXML CheckBox checkPena4;
	@FXML CheckBox checkPena5;
	@FXML CheckBox checkPena6;
	@FXML CheckBox checkPena7;
	
	@FXML CheckBox checkAgra1;
	@FXML CheckBox checkAgra2;
	@FXML CheckBox checkAgra3;
	@FXML CheckBox checkAgra4;
	@FXML CheckBox checkAgra5;
	@FXML CheckBox checkAgra6;
	@FXML CheckBox checkAgra7;
	@FXML CheckBox checkAgra8;
	@FXML CheckBox checkAgra9;
	
	@FXML CheckBox checkAten1;
	@FXML CheckBox checkAten2;
	@FXML CheckBox checkAten3;
	@FXML CheckBox checkAten4;
	@FXML CheckBox checkAten5;
	@FXML CheckBox checkAten6;
	@FXML CheckBox checkAten7;
	
	String strInfracoes;
	String strPenalidades;
	String strAgravantes;
	String strAtenuantes;
	
	String strPesquisa = "";

	HTMLEditor htmlObjeto;
	
	
	HTMLEditor htmlApresentacao; // = new HTMLEditor();
	HTMLEditor htmlRelato; //  = new HTMLEditor();
	HTMLEditor htmlRecomendacao; //   = new HTMLEditor();

	//-- pane para os editores html --//
	@FXML Pane paneObjeto; // = new Pane();
	@FXML Pane paneApresentacao; // = new Pane();
	@FXML Pane paneRelato; // = new Pane();
	@FXML Pane paneRecomendacao; // = new Pane();
	
	
	
	//-- passar vistoria para o maincontroller --//
	public Vistoria visGeral;
	
	public String obterIfracoes () {
		
		return strInfracoes;
	}
	
	int tipoAto = 2;
	
	public void checkInfraHab (ActionEvent event) {
		int count = 0;
		String strCheckInfra = "";
		
		if (checkInfra1.isSelected()) {
			count ++;
			strCheckInfra += "1";
		}
		if (checkInfra2.isSelected()) {
			count ++;
			strCheckInfra += "2";
		}
		if (checkInfra3.isSelected()) {
			count ++;
			strCheckInfra += "3";
		}
		if (checkInfra4.isSelected()) {
			count ++;
			strCheckInfra += "4";
		}
		if (checkInfra5.isSelected()) {
			count ++;
			strCheckInfra += "5";
		
		}
		if (checkInfra6.isSelected()) {
			strCheckInfra += "6";
			count ++;
		}
		if (checkInfra7.isSelected()) {
			strCheckInfra += "7";
			count ++;
		}
		
		System.out.println(count);
		strInfracoes = strCheckInfra;
		//System.out.println("infrações: " + strCheckInfra);
		
	}
	
	public void checkPenaHab (ActionEvent event) {
		int count = 0;
		String strCheckAten = "";
		
		if (checkPena1.isSelected()) {
			count ++;
			strCheckAten += "1";
		}
		if (checkPena2.isSelected()) {
			count ++;
			strCheckAten += "2";
		}
		if (checkPena3.isSelected()) {
			count ++;
			strCheckAten += "3";
		}
		if (checkPena4.isSelected()) {
			count ++;
			strCheckAten += "4";
		}
		if (checkPena5.isSelected()) {
			count ++;
			strCheckAten += "5";
		
		}
		if (checkPena6.isSelected()) {
			strCheckAten += "6";
			count ++;
		}
		if (checkPena7.isSelected()) {
			strCheckAten += "7";
			count ++;
		}
		
		strPenalidades = strCheckAten;
		System.out.println(count);
		//System.out.println("atenuações: " + strCheckAten);
		
	}
	
	public void checkAgraHab (ActionEvent event) {
		int count = 0;
		String strCheckAgra = "";
		
		if (checkAgra1.isSelected()) {
			count ++;
			strCheckAgra += "1";
		}
		if (checkAgra2.isSelected()) {
			count ++;
			strCheckAgra += "2";
		}
		if (checkAgra3.isSelected()) {
			count ++;
			strCheckAgra += "3";
		}
		if (checkAgra4.isSelected()) {
			count ++;
			strCheckAgra += "4";
		}
		if (checkAgra5.isSelected()) {
			count ++;
			strCheckAgra += "5";
		
		}
		if (checkAgra6.isSelected()) {
			strCheckAgra += "6";
			count ++;
		}
		if (checkAgra7.isSelected()) {
			strCheckAgra += "7";
			count ++;
		}
		
		if (checkAgra8.isSelected()) {
			strCheckAgra += "8";
			count ++;
		}
		
		if (checkAgra9.isSelected()) {
			strCheckAgra += "9";
			count ++;
		}
		
		strAgravantes = strCheckAgra;
		System.out.println(count);
		//System.out.println("agravantes: " + strCheckAgra);
		
	}
	
	public void checkAtenHab (ActionEvent event) {
		int count = 0;
		String strCheckAten = "";
		
		if (checkAten1.isSelected()) {
			count ++;
			strCheckAten += "1";
		}
		if (checkAten2.isSelected()) {
			count ++;
			strCheckAten += "2";
		}
		if (checkAten3.isSelected()) {
			count ++;
			strCheckAten += "3";
		}
		if (checkAten4.isSelected()) {
			count ++;
			strCheckAten += "4";
		}
		if (checkAten5.isSelected()) {
			count ++;
			strCheckAten += "5";
		
		}
		if (checkAten6.isSelected()) {
			strCheckAten += "6";
			count ++;
		}
		if (checkAten7.isSelected()) {
			strCheckAten += "7";
			count ++;
		}
		
		strAtenuantes = strCheckAten;
		System.out.println(count);
		//System.out.println("atenunações: " + strCheckAten);
		
	}
	
	public void btnNovoHab (ActionEvent event) {
		
		tfAto.setText(null);
		tfAtoSEI.setText(null);
		dpDataFiscalizacao.getEditor().clear(); // limpar datepicker
		dpDataCriacaoAto.getEditor().clear();
		
		htmlObjeto.setHtmlText("");
		htmlApresentacao.setHtmlText("");
		htmlRelato.setHtmlText("");
		htmlRecomendacao.setHtmlText("");
		
		tfAto.setDisable(false);
		tfAtoSEI.setDisable(false);
		dpDataFiscalizacao.setDisable(false);
		dpDataCriacaoAto.setDisable(false);
		
		htmlObjeto.setDisable(false);
		htmlApresentacao.setDisable(false);
		htmlRelato.setDisable(false);
		htmlRecomendacao.setDisable(false);
		
		btnNovo.setDisable(true);
		btnSalvar.setDisable(false);
		
		btnEditar.setDisable(true);
		btnExcluir.setDisable(true);
		
	}
	
	public void btnSalvarHab (ActionEvent event) {
		
		Vistoria vis = new Vistoria();
		
			vis.setVisIdentificacao(tfAto.getText());
			vis.setVisSEI(tfAtoSEI.getText());
			
			vis.setVisDataFiscalizacao(formatter.format(dpDataFiscalizacao.getValue()));
			vis.setVisDataCriacao(formatter.format(dpDataCriacaoAto.getValue()));
			
			vis.setVisInfracoes(strInfracoes);
			vis.setVisPenalidades(strPenalidades);
			vis.setVisAgravantes(strAgravantes);
			vis.setVisAtenuantes(strAtenuantes);
			
			vis.setVisObjeto(htmlObjeto.getHtmlText());
			vis.setVisApresentacao(htmlApresentacao.getHtmlText());
			vis.setVisRelato(htmlRelato.getHtmlText());
			vis.setVisRecomendacoes(htmlRecomendacao.getHtmlText());
			
			
			vis.setVisEndCodigoFK(eGeralVis);
			
			VistoriaDao visDao = new VistoriaDao();
			
			visDao.salvarVistoria(vis);
			visDao.mergeVistoria(vis);
			
			listarVistorias(strPesquisa);
			selecionarVistoria();
			
			//-- Alerta de interferência editada --//
			Alert vSalva = new Alert (Alert.AlertType.CONFIRMATION);
			vSalva.setTitle("Parabéns!");
			vSalva.setContentText("vistoria salva com sucesso!");
			vSalva.setHeaderText(null);
			vSalva.show();
			
			
			//-- número da vistoria para a tabela atos --//
			System.out.println("código da vistoria salva " + vis.getVisCodigo());
			visGeral = vis;
			main.pegarVistoria(vis);
			
		
	}
	
	public void btnEditarHab (ActionEvent event) {
		
		if (tfAto.isDisable()) {
			
			tfAto.setDisable(false);
			tfAtoSEI.setDisable(false);
			dpDataFiscalizacao.setDisable(false);
			dpDataCriacaoAto.setDisable(false);
			
			htmlObjeto.setDisable(false);
			htmlApresentacao.setDisable(false);
			htmlRelato.setDisable(false);
			htmlRecomendacao.setDisable(false);
			
		} else {
		
			try {
			
			VistoriaTabela visTab = tvVistoria.getSelectionModel().getSelectedItem();
			
			Vistoria vis = new Vistoria(visTab);
			
				//-- capturar endereço relacionado --//
				vis.setVisEndCodigoFK(eGeralVis);
			
				//-- capturar tela --//
				vis.setVisIdentificacao(tfAto.getText());
				vis.setVisSEI(tfAtoSEI.getText());
				
				vis.setVisDataFiscalizacao(formatter.format(dpDataFiscalizacao.getValue()));  // está dando erro temporal nullpoint
				vis.setVisDataCriacao(formatter.format(dpDataCriacaoAto.getValue()));
				
				vis.setVisInfracoes(strInfracoes);
				vis.setVisPenalidades(strPenalidades);
				vis.setVisAgravantes(strAgravantes);
				vis.setVisAtenuantes(strAtenuantes);
				
				vis.setVisObjeto(htmlObjeto.getHtmlText());
				vis.setVisApresentacao(htmlApresentacao.getHtmlText());
				vis.setVisRelato(htmlRelato.getHtmlText());
				vis.setVisRecomendacoes(htmlRecomendacao.getHtmlText());
				
						VistoriaDao visDao = new VistoriaDao();
						
						visDao.mergeVistoria(vis);
						

						listarVistorias(strPesquisa);
						selecionarVistoria();
						modularBotoes();
						
						//-- Alerta de interferência editada --//
						Alert vMerge = new Alert (Alert.AlertType.CONFIRMATION);
						vMerge.setTitle("Parabéns!");
						vMerge.setContentText("vistoria editada!");
						vMerge.setHeaderText(null);
						vMerge.show();
						
						visGeral = vis;
						main.pegarVistoria(vis);
			
			
					} catch (Exception e) {
						
						System.out.println("Erro ao editar: " + e);
						
						//-- Alerta de interferência editada --//
						Alert vMerge = new Alert (Alert.AlertType.ERROR);
						vMerge.setTitle("Atenção!");
						vMerge.setContentText("erro ao editar vistoria!");
						vMerge.setHeaderText(null);
						vMerge.show();
						
					}
			
			
			
		}
		
	}

	public void btnExcluirHab (ActionEvent event) {
		
		try {
		
			VistoriaTabela visTab = tvVistoria.getSelectionModel().getSelectedItem();
			
			VistoriaDao visDao = new VistoriaDao();
			
			visDao.removerVistoria(visTab.getVisCodigo());
			
			listarVistorias(strPesquisa);
			selecionarVistoria();
			modularBotoes();
			
			//-- Alerta de interferência editada --//
			Alert vRemover = new Alert (Alert.AlertType.CONFIRMATION);
			vRemover.setTitle("Parabéns!");
			vRemover.setContentText("vistoria excluída!");
			vRemover.setHeaderText(null);
			vRemover.show();
		
				} catch (Exception e) {
					
					System.out.println("Erro ao editar: " + e);
					
					//-- Alerta de interferência editada --//
					Alert vRemover = new Alert (Alert.AlertType.ERROR);
					vRemover.setTitle("Atenção!");
					vRemover.setContentText("erro ao excluir vistoria!");
					vRemover.setHeaderText(null);
					vRemover.show();
				}
	
		
	
	}
	
	public void btnCancelarHab (ActionEvent event) {
		
		modularBotoes();
	}
	
	public void btnPesquisarHab (ActionEvent event) {
		
		strPesquisa = tfPesquisar.getText();
		listarVistorias(strPesquisa);
		selecionarVistoria();
		
	}
	
	
	public void btnPesqObjHab (ActionEvent event) {
		
	}
	
	public void btnPesApHab (ActionEvent event) {
		
	}

	public void btnPesqRelHab (ActionEvent event) {
	
	}
	
	
	public void btnRelatorioHab (ActionEvent event) {
	
		
		File file = new File("../FiscalizacaoSRH/src/main/resources/html/relatoriovistoria.html");
		
		Document docHtml = null;
		
		try {
			docHtml = Jsoup.parse(file, "UTF-8");  // retirei o  .clone()
		} catch (IOException e1) {
			System.out.println("Erro na leitura do documento e Jsoup!!!");
			e1.printStackTrace();
		}
	
		
		//-- vistoria --//
		docHtml.select("nRela").prepend(visGeral.getVisIdentificacao());
		docHtml.select("nRelSEI").prepend(visGeral.getVisSEI());
		
		if (eGeralVis.getListUsuarios().get(0).getUsNome() != null) { // se usuário nulo 0 da lista nulo, não  há usuário cadastrado...
			
			//-- endereço --//
			docHtml.select("nomeUs").prepend(eGeralVis.getListUsuarios().get(0).getUsNome());
			docHtml.select("cpfUs").prepend(eGeralVis.getListUsuarios().get(0).getUsCPFCNPJ());
			docHtml.select("endUs").prepend(eGeralVis.getListUsuarios().get(0).getUsDescricaoEnd()); 
			
			if (eGeralVis.getListUsuarios().get(0).getUsRA() != null) { //se o combox diferente de null, não acontece o mesmo no textfield
				docHtml.select("raUs").prepend(eGeralVis.getListUsuarios().get(0).getUsRA());
			}
			
			docHtml.select("cepUs").prepend(eGeralVis.getListUsuarios().get(0).getUsCEP());
			docHtml.select("cidUs").prepend(eGeralVis.getListUsuarios().get(0).getUsCidade());
			docHtml.select("ufUs").prepend(eGeralVis.getListUsuarios().get(0).getUsEstado());
			docHtml.select("telUs").prepend(eGeralVis.getListUsuarios().get(0).getUsTelefone());
			docHtml.select("celUs").prepend(eGeralVis.getListUsuarios().get(0).getUsCelular());
			docHtml.select("emailUs").prepend(eGeralVis.getListUsuarios().get(0).getUsEmail());
			
		}
		
		//-- latitude e longitude do endereço --//
		docHtml.select("latEnd").prepend(eGeralVis.getLat_Endereco().toString());
		docHtml.select("lngEnd").prepend(eGeralVis.getLon_Endereco().toString());
		 
		//-- objecto, apresentação, relato e recomendações --//
		docHtml.select("objVis").prepend(visGeral.getVisObjeto());
		docHtml.select("apreVis").prepend(visGeral.getVisApresentacao());
		docHtml.select("relVis").prepend(visGeral.getVisRelato());
		docHtml.select("recVis").prepend(visGeral.getVisRecomendacoes());
		
		
		String html = new String ();
		
		html = docHtml.toString();
		
		html = html.replace("\"", "'");
		html = html.replace("\n", "");
		
		html =  "\"" + html + "\"";
		
		//-- webview do relatório --//
		
		WebView browser = new WebView();
		WebEngine webEngine = browser.getEngine();
		webEngine.loadContent(html);
		
		Scene scene = new Scene(browser);
		
		Stage stage = new Stage(StageStyle.UTILITY);
		stage.setWidth(1250);
		stage.setHeight(750);
        stage.setScene(scene);
        stage.setMaximized(false);
        stage.setResizable(false);
        
        stage.show();
        
        System.out.println("objeto e html texto");
        //System.out.println(htmlObjeto.getHtmlText());
        
        TabNavegadorController.html = html;
		
	}
	
	//-- INITIALIZE --//
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		
		modularBotoes();
		
		dpDataFiscalizacao.setConverter(new StringConverter<LocalDate>() {
			
			@Override
			public String toString(LocalDate t) {
				if (t != null) {
					return formatter.format(t);
				}
				return null;
			}
			
			@Override
			public LocalDate fromString(String string) {
				if (string != null && !string.trim().isEmpty()) {
					return LocalDate.parse(string, formatter);
				}
				return null;
			}

		});
		
		dpDataCriacaoAto.setConverter(new StringConverter<LocalDate>() {
			
			@Override
			public String toString(LocalDate t) {
				if (t != null) {
					return formatter.format(t);
				}
				return null;
			}
			
			@Override
			public LocalDate fromString(String string) {
				if (string != null && !string.trim().isEmpty()) {
					return LocalDate.parse(string, formatter);
				}
				return null;
			}

		});
		
		dpDataFiscalizacao.setOnAction((ActionEvent event) -> {
			

			System.out.println("valor sem formatar " + dpDataFiscalizacao.getValue());
			System.out.println("valor formatado " + formatter.format(dpDataFiscalizacao.getValue()));
		}
				
				
		);
		
		/*
		Platform.runLater(new Runnable() {
		    @Override
		    public void run() {
		        htmlObjeto = new HTMLEditor();
		    }
		});
		
		*/
		
		// -- inicitalizar o mapa -- //
				Platform.runLater(() ->{
				relatarHTML();  
				
				});
		
	}
	
 
	
	public void relatarHTML () {
	
		htmlObjeto = new HTMLEditor();
		
			htmlObjeto.setPrefSize(800, 200);
			
			htmlObjeto.setOnKeyPressed(event -> {
			    if (event.getCode() == KeyCode.SPACE  
			            || event.getCode() == KeyCode.TAB ) {
			        // Consume Event before Bubbling Phase, -> otherwise Scrollpane scrolls
			        event.consume();
			    }
			});
			
			StackPane root = new StackPane();
			root.getChildren().add(htmlObjeto);
			paneObjeto.getChildren().add(htmlObjeto);
	    
	   
		htmlApresentacao  = new HTMLEditor();
		
			htmlApresentacao.setPrefSize(800, 200);
			
			htmlApresentacao.setOnKeyPressed(event -> {
			    if (event.getCode() == KeyCode.SPACE  
			            || event.getCode() == KeyCode.TAB ) {
			        // Consume Event before Bubbling Phase, -> otherwise Scrollpane scrolls
			        event.consume();
			    }
			});
			
			StackPane rootAp = new StackPane();
			rootAp.getChildren().add(htmlApresentacao);
		    paneApresentacao.getChildren().add(htmlApresentacao);
	    
	    
		htmlRelato  = new HTMLEditor();
		
			htmlRelato.setPrefSize(800, 773);
			
			htmlRelato.setOnKeyPressed(event -> {
			    if (event.getCode() == KeyCode.SPACE  
			            || event.getCode() == KeyCode.TAB ) {
			        // Consume Event before Bubbling Phase, -> otherwise Scrollpane scrolls
			        event.consume();
			    }
			});
			
			
			StackPane rootRel = new StackPane();
			rootRel.getChildren().add(htmlRelato);
			paneRelato.getChildren().add(htmlRelato);
			
		    
		htmlRecomendacao  = new HTMLEditor();
		
			htmlRecomendacao.setPrefSize(800, 200);
			
			htmlRecomendacao.setOnKeyPressed(event -> {
			    if (event.getCode() == KeyCode.SPACE  
			            || event.getCode() == KeyCode.TAB ) {
			        // Consume Event before Bubbling Phase, -> otherwise Scrollpane scrolls
			        event.consume();
			    }
			});
			
			StackPane rootReco = new StackPane();
			rootReco.getChildren().add(htmlRecomendacao);
			paneRecomendacao.getChildren().add(htmlRecomendacao);

	}
	
	
	@FXML public Label lblVisEnd = new Label(); // público para receber valor do MainController, método pegarEnd()
	
	//  objeto para passar os valor pelo MainControoler para outro controller //
	public Endereco eGeralVis;
	
	//  Controller Principal - MainController  //
	@FXML private MainController main;
	
	// MAIN CONTROLLER //
	public void init(MainController mainController) {
			main = mainController;
	}
	
	public void listarVistorias (String strPesquisa) {
 		
	 	// --- conexão - listar endereços --- //
		VistoriaDao visDao = new VistoriaDao();
		List<Vistoria> visList = visDao.listarVistoria(strPesquisa);
		ObservableList<VistoriaTabela> oListVis = FXCollections.observableArrayList();
		
		
		if (!oListVis.isEmpty()) {
			oListVis.clear();
		}
		
			for (Vistoria vis : visList) {
				
			VistoriaTabela visTab = new VistoriaTabela(
					
				vis.getVisCodigo(),
				vis.getVisEndCodigoFK(),
				vis.getVisObjeto(),
				vis.getVisApresentacao(),
				vis.getVisRelato(),
				vis.getVisRecomendacoes(),
				vis.getVisInfracoes(),
				vis.getVisPenalidades(),
				vis.getVisAtenuantes(),
				vis.getVisAgravantes(),
				vis.getVisIdentificacao(),
				vis.getVisSEI(),
				vis.getVisDataFiscalizacao(),
				vis.getVisDataCriacao(),
				vis.getVisListAtos()
				
				);
				
				
				oListVis.add(visTab);
				System.out.println("Lista de denúncias vinculadas ao endereço: " + visTab.getListAtos());
	 					
		}
			
			
			tcNumero.setCellValueFactory(new PropertyValueFactory<VistoriaTabela, String>("visIdentificacao")); 
			//tcEndRA.setCellValueFactory(new PropertyValueFactory<EnderecoTabela, String>("RA_Endereco")); 
			//tcEndCid.setCellValueFactory(new PropertyValueFactory<EnderecoTabela, String>("CEP_Endereco")); 
			
			tvVistoria.setItems(oListVis);
			
	 	}
	
	
	// método selecionar vistoria -- //
	 	public void selecionarVistoria () {
		
			tvVistoria.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Object>() {
				public void changed(ObservableValue<?> observable , Object oldValue, Object newValue) {
				
				VistoriaTabela visTab = (VistoriaTabela) newValue;
				
				if (visTab == null) {
					
					btnNovo.setDisable(true);
					btnSalvar.setDisable(true);
					
					btnEditar.setDisable(false);
					btnExcluir.setDisable(false);
					btnCancelar.setDisable(false);
					
				} else {

					tfAto.setText(visTab.getVisIdentificacao());
					tfAtoSEI.setText(visTab.getVisSei());
					
					System.out.println("Veja a data da fiscalização " + LocalDate.parse(visTab.getVisDataFiscalizacao(), formatter));
					
					
					dpDataFiscalizacao.setValue(LocalDate.parse(visTab.getVisDataFiscalizacao(), formatter));
					dpDataCriacaoAto.setValue(LocalDate.parse(visTab.getVisDataCriacao(), formatter));
					
					//-- está dando erro na  hora de editar deste  jeito ---//
					//dpDataFiscalizacao.getEditor().setText(visTab.getVisDataFiscalizacao());
					//dpDataCriacaoAto.getEditor().setText(visTab.getVisDataCriacao());
					
					modularCheckBox ();
					
					String infr =  visTab.getVisInfracoes();
					String pena = visTab.getVisPenalidades();
					String agra = visTab.getVisAgravantes();
					String aten = visTab.getVisAtenuantes();
					
					
					
					//-- infrações --//
					if (infr != null) {
						
						String infrArray [] = infr.split("");
						
						System.out.println(infr);
						
						
						for (int i = 0; i<infrArray.length; i++) {
							if (infrArray[i].equals("1") ) {
								checkInfra1.setSelected(true);
							}
							if (infrArray[i].equals("2") ) {
								checkInfra2.setSelected(true);
							}
							if (infrArray[i].equals("3")  ) {
								checkInfra3.setSelected(true);
							}
							if (infrArray[i].equals("4") ) {
								checkInfra4.setSelected(true);
							}
							if (infrArray[i].equals("5")  ) {
								checkInfra5.setSelected(true);
							}
							if (infrArray[i].equals("6") ) {
								checkInfra6.setSelected(true);
							}
							if (infrArray[i].equals("7")  ) {
								checkInfra7.setSelected(true);
							}
							
							System.out.println(i + " veja as infrações selecionadas " + infrArray[i]);
							
						}}
					

									//-- penalidades --//
									if (pena != null) {
										
										String penaArray [] = infr.split("");
										
										System.out.println(infr);
										
										
										for (int i = 0; i<penaArray.length; i++) {
											if (penaArray[i].equals("1") ) {
												checkPena1.setSelected(true);
											}
											if (penaArray[i].equals("2") ) {
												checkPena2.setSelected(true);
											}
											if (penaArray[i].equals("3")  ) {
												checkPena3.setSelected(true);
											}
											if (penaArray[i].equals("4") ) {
												checkPena4.setSelected(true);
											}
											if (penaArray[i].equals("5")  ) {
												checkPena5.setSelected(true);
											}
											if (penaArray[i].equals("6") ) {
												checkPena6.setSelected(true);
											}
											if (penaArray[i].equals("7")  ) {
												checkPena7.setSelected(true);
											}
											
											System.out.println(i + " veja as penalidades selecionadas" + penaArray[i]);
											
										}}
									
					//-- agravantes --//
					if (agra != null) {
						
						String agraArray [] = infr.split("");
						
						System.out.println(agra);
						
						
						for (int i = 0; i<agraArray.length; i++) {
						
							if (agraArray[i].equals("1") ) {
								checkAgra1.setSelected(true);
							}
							if (agraArray[i].equals("2") ) {
								checkAgra2.setSelected(true);
							}
							if (agraArray[i].equals("3")  ) {
								checkAgra3.setSelected(true);
							}
							if (agraArray[i].equals("4") ) {
								checkAgra4.setSelected(true);
							}
							if (agraArray[i].equals("5")  ) {
								checkAgra5.setSelected(true);
							}
							if (agraArray[i].equals("6") ) {
								checkAgra6.setSelected(true);
							}
							if (agraArray[i].equals("7")  ) {
								checkAgra7.setSelected(true);
							}
							
							if (agraArray[i].equals("8")  ) {
								checkAgra8.setSelected(true);
							}
							
							if (agraArray[i].equals("9")  ) {
								checkAgra9.setSelected(true);
							}
							
							System.out.println(i + " veja os agravantes selecionadas" + agraArray[i]);
							
						}}
							
									//-- atenuantes --//
									if (aten != null) {
										
										String atenArray [] = infr.split("");
										
										System.out.println(aten);
										
										
										for (int i = 0; i<atenArray.length; i++) {
										
											if (atenArray[i].equals("1") ) {
												checkAten1.setSelected(true);
											}
											if (atenArray[i].equals("2") ) {
												checkAten2.setSelected(true);
											}
											if (atenArray[i].equals("3")  ) {
												checkAten3.setSelected(true);
											}
											if (atenArray[i].equals("4") ) {
												checkAten4.setSelected(true);
											}
											if (atenArray[i].equals("5")  ) {
												checkAten5.setSelected(true);
											}
											if (atenArray[i].equals("6") ) {
												checkAten6.setSelected(true);
											}
											if (atenArray[i].equals("7")  ) {
												checkAten7.setSelected(true);
											}
											
										
											System.out.println(i + " veja os atenuantes selecionadas" + atenArray[i]);
											
										}}
					
					
					htmlObjeto.setHtmlText(visTab.getVisObjeto());
					htmlApresentacao.setHtmlText(visTab.getVisApresentacao());
					htmlRelato.setHtmlText(visTab.getVisRelato());
					htmlRecomendacao.setHtmlText(visTab.getVisRecomendacoes());
					
					
					//-- pegar a vistoria selecionada --//
					Vistoria visG = new Vistoria(visTab);
					visGeral = visG;
					main.pegarVistoria(visGeral);
					
					//-- mudar o endereço de acordo com a seleção --//
					eGeralVis = visTab.getVisEndCodigoFK();
					lblVisEnd.setText(eGeralVis.getDesc_Endereco() + " |  RA: "  + eGeralVis.getRA_Endereco() );
					
					btnNovo.setDisable(true);
					btnSalvar.setDisable(true);
					btnEditar.setDisable(false);
					btnExcluir.setDisable(false);
					btnCancelar.setDisable(false);
					
					
				}
				}
			});
		}
	 	
	 public void modularBotoes () {
		 
		 tfAto.setDisable(true);
		 tfAtoSEI.setDisable(true);
		 dpDataFiscalizacao.setDisable(true);
		 dpDataCriacaoAto.setDisable(true);
		 
		 btnSalvar.setDisable(true);
		 btnEditar.setDisable(true);
		 btnExcluir.setDisable(true);
		 
		 btnNovo.setDisable(false);
	 }
	 
	 public void modularCheckBox () {
		 
		 checkInfra1.setSelected(false);
		 checkInfra2.setSelected(false);
		 checkInfra3.setSelected(false);
		 checkInfra4.setSelected(false);
		 checkInfra5.setSelected(false);
		 checkInfra6.setSelected(false);
		 checkInfra7.setSelected(false);
		 
		 checkPena1.setSelected(false);
		 checkPena2.setSelected(false);
		 checkPena3.setSelected(false);
		 checkPena4.setSelected(false);
		 checkPena5.setSelected(false);
		 checkPena6.setSelected(false);
		 checkPena7.setSelected(false);
		 
		 checkAten1.setSelected(false);
		 checkAten2.setSelected(false);
		 checkAten3.setSelected(false);
		 checkAten4.setSelected(false);
		 checkAten5.setSelected(false);
		 checkAten6.setSelected(false);
		 checkAten7.setSelected(false);
		 
		 
		 checkAgra1.setSelected(false);
		 checkAgra2.setSelected(false);
		 checkAgra3.setSelected(false);
		 checkAgra4.setSelected(false);
		 checkAgra5.setSelected(false);
		 checkAgra6.setSelected(false);
		 checkAgra7.setSelected(false);
		 checkAgra8.setSelected(false);
		 checkAgra9.setSelected(false);
	 }
	 
	
}
