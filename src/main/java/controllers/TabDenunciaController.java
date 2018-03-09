package controllers;


import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import dao.DenunciaDao;
import entity.Denuncia;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import tabela.DenunciaTabela;


public class TabDenunciaController implements Initializable {
	
	// --- String teste para o leitor de relatórios  --- //
	
	String INITIAL_TEXT = "<table style='border:4px solid;border-bottom-width:0px; margin-left:auto;margin-right:auto;width:800px;'>"
			+ 				"<tbody>" 
			+					"<tr>"
			+						"<td align='left'><strong>RELAT&Oacute;RIO DE VISTORIA E FISCALIZA&Ccedil;&Atilde;O N&deg;</strong></td>" 
			+						"<td align='left'><strong>SEI N&deg;</strong></td>"
			+					"</tr>"
			+				"</tbody>"
			+			"</table>";
	
	// Endereço google para buscas se for usar o webvew com o google 
	String google = "https://www.google.com.br/search?source=hp&ei=BqVsWruNOMuXwgSko764DA&q=";
	
	// --- Controller Principal - MainController --- //
	@FXML private MainController main;
	
	@FXML AnchorPane tabDenuncia = new AnchorPane();
	
	@FXML TextField tfDocumento = new TextField();
	@FXML TextField tfDocSei = new TextField();
	@FXML TextField tfProcSei =  new TextField();
	@FXML TextField tfResDen = new TextField();
	@FXML TextField tfPesquisar = new TextField();
	
	// ----- Label - Endereço da denúncia ------ //
	@FXML Label lblDenEnd = new Label ();

	@FXML Button btnNovo = new Button();
	@FXML Button btnSalvar = new Button();
	@FXML Button btnEditar = new Button();
	@FXML Button btnExcluir = new Button();
	@FXML Button btnCancelar = new Button();
	@FXML Button btnPesquisar = new Button();
	@FXML Button btnSair = new Button();
	
	// -- Tabela --  //
	@FXML private TableView <DenunciaTabela> tvLista;
	
	// -- Colunas -- //
	@FXML private TableColumn<DenunciaTabela, String> tcDocumento;
	@FXML private TableColumn<DenunciaTabela, String> tcDocSEI;
	@FXML private TableColumn<DenunciaTabela, String> tcProcSEI;
	
	// capturar denuncia para a TabEnderecoController
	private static Denuncia dGeral;
	
	// --- String para primeira pesquisa --- //
	String strPesquisa = "";
	
	// -- Conexão e pesquisa de denúncias -- //
	private DenunciaDao denunciaDao = new DenunciaDao();	//passar classe
	private List<Denuncia> denunciaList = denunciaDao.listDenuncia(strPesquisa); //passar string de pesquisar
	private ObservableList<DenunciaTabela> obsListDenunciaTabela= FXCollections.observableArrayList(); //chamar observable list e outra classe
	
	// --- método listarDenuncias --- //
	public void listarDenuncias () {
		
		if (!obsListDenunciaTabela.isEmpty()) {
			obsListDenunciaTabela.clear();
		}
		for (Denuncia denuncia : denunciaList) {
			
			DenunciaTabela denTab = new DenunciaTabela(
					denuncia.getCod_Denuncia(), 
					denuncia.getDoc_Denuncia(),
					denuncia.getDoc_SEI_Denuncia(), 
					denuncia.getProc_SEI_Denuncia(),
					denuncia.getDesc_Denuncia(),
					// adicionao  o ojeto enderecoFK na DenunciaTabela
					denuncia.getEnderecoFK()
					);
			
				obsListDenunciaTabela.add(denTab);
		}
		
		tcDocumento.setCellValueFactory(new PropertyValueFactory<DenunciaTabela, String>("Doc_Denuncia")); 
        tcDocSEI.setCellValueFactory(new PropertyValueFactory<DenunciaTabela, String>("Doc_SEI_Denuncia")); 
        tcProcSEI.setCellValueFactory(new PropertyValueFactory<DenunciaTabela, String>("Proc_SEI_Denuncia")); 
        
        tvLista.setItems(obsListDenunciaTabela); 
	}
	
	// -- selecionar denúncia -- //
	public void selecionarDenuncia () {
		
		// TableView - selecionar denúncia ao clicar -- //
		
		tvLista.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Object>() {
			public void changed(ObservableValue<?> observable , Object oldValue, Object newValue) {
			
			DenunciaTabela denTab = (DenunciaTabela) newValue;
				
			if (denTab == null) {
				
				tfDocumento.setText("");
				tfDocSei.setText("");
				tfProcSei.setText("");
				tfResDen.setText("");
				
				btnNovo.setDisable(true);
				btnSalvar.setDisable(true);
				btnEditar.setDisable(false);
				btnExcluir.setDisable(false);
				btnCancelar.setDisable(false);
				
			} else {

				// preencher os campos
				tfDocumento.setText(denTab.getDoc_Denuncia());
				tfDocSei.setText(denTab.getDoc_SEI_Denuncia());
				tfProcSei.setText(denTab.getProc_SEI_Denuncia());
				tfResDen.setText(denTab.getDesc_Denuncia());
				
				
				//PRECISA MELHORAR, ESTÁ DANDO NULLPOINTEXCEPTION...
				
				if (denTab.getenderecoObjetoTabelaFK() != null) {
					
					lblDenEnd.setText(denTab.getenderecoObjetoTabelaFK().getDesc_Endereco() + ", " + denTab.getenderecoObjetoTabelaFK().getRA_Endereco());
				} else {
					lblDenEnd.setText("Sem endereço cadastrado!");
					
				}
				
				Denuncia dGeral = new Denuncia(denTab);

				main.pegarDoc(dGeral);
				
				// habilitar e desabilitar botões
				btnNovo.setDisable(true);
				btnSalvar.setDisable(true);
				btnEditar.setDisable(false);
				btnExcluir.setDisable(false);
				btnCancelar.setDisable(false);
			}
			}
		});
	}
	
	// --  botão novo -- //
	public void btnNovoHabilitar (ActionEvent event) {
		
		tfDocumento.setText("");
		tfDocSei.setText("");
		tfProcSei.setText("");
		tfResDen.setText("");
		
		tfDocumento.setDisable(false);
		tfDocSei.setDisable(false);
		tfProcSei.setDisable(false);
		tfResDen.setDisable(false);
		
		btnSalvar.setDisable(false);
		btnEditar.setDisable(true);
		btnExcluir.setDisable(true);
		btnNovo.setDisable(true);
	}
	
	// -- botão salvar -- //
	public void btnSalvarSalvar (ActionEvent event) {
		
		Denuncia denuncia = new Denuncia();
		
		denuncia.setDoc_Denuncia(tfDocumento.getText()); 
		denuncia.setProc_SEI_Denuncia(tfProcSei.getText());
		denuncia.setDoc_SEI_Denuncia(tfDocSei.getText()); 
		denuncia.setDesc_Denuncia(tfResDen.getText());
		
		DenunciaDao dao = new DenunciaDao();
		
		dao.salvaDenuncia(denuncia);
		
		denunciaList = denunciaDao.listDenuncia(strPesquisa);
		
		// pegar o valor, levar para o MainController  e depois para o label lblDoc no EnderecoController
		dGeral = denuncia;
		main.pegarDoc(dGeral);
		
		listarDenuncias();
		
		modularBotoesInicial (); 
			
		}

	// -- botão editar -- //
	public void btnEditarHabilitar (ActionEvent event) {
			
		if (tfDocumento.isDisable()) {
			
			tfDocumento.setDisable(false);
			tfDocumento.setDisable(false);
			tfDocSei.setDisable(false);
			tfProcSei.setDisable(false);
			tfResDen.setDisable(false);
			
		} else {
			
			DenunciaTabela denunciaTabelaEditar = tvLista.getSelectionModel().getSelectedItem();
			Denuncia denunciaEditar = new Denuncia(denunciaTabelaEditar);
			
			denunciaEditar.setDoc_Denuncia(tfDocumento.getText());
			denunciaEditar.setDoc_SEI_Denuncia(tfDocSei.getText());
			denunciaEditar.setProc_SEI_Denuncia(tfProcSei.getText());
			denunciaEditar.setDesc_Denuncia(tfResDen.getText());
			
			// para não editar o foreign key, continuar sendo a mesma
			//denunciaEditar.setEnderecoFK(denunciaEditar.getEnderecoFK());
			
			denunciaDao.mergeDenuncia(denunciaEditar);
				
			denunciaList = denunciaDao.listDenuncia(strPesquisa);
			
			// pegar o valor, levar para o MainController  e depois para o label lblDoc no EnderecoController
			dGeral = denunciaEditar;
			main.pegarDoc(dGeral);
			
			listarDenuncias();
			
			modularBotoesInicial (); 
				
			}
	}
	
	// -- botão excluir -- //
	public void btnExcluirHabilitar (ActionEvent event) {
	
		DenunciaTabela denunciaExcluir = tvLista.getSelectionModel().getSelectedItem();
		
		int id = denunciaExcluir.getCod_Denuncia(); // buscar id para deletar
		
		denunciaList = denunciaDao.listDenuncia(strPesquisa);
		
		obsListDenunciaTabela.remove(denunciaExcluir);
		
		denunciaDao.removeDenuncia(id);
		
		denunciaList = denunciaDao.listDenuncia(strPesquisa);
		
		listarDenuncias();
		
		modularBotoesInicial (); 		
	}
			
	// -- botão cancelar -- //
	public void btnCancelarHabilitar (ActionEvent event) {
			
		modularBotoesInicial();
	}
		
	// -- botão pesquisar denúncia -- //
	public void btnPesquisarHabilitar (ActionEvent event) {
		
		System.out.println("///////---////////-----/////////// denúncias abaixo ----/////////////////-----//////////////////");
		
		strPesquisa = (String) tfPesquisar.getText();
		
		denunciaList = denunciaDao.listDenuncia(strPesquisa);
		
		listarDenuncias();
		
		modularBotoesInicial (); 
		
		System.out.println("///////---////////-----/////////// denúncias acima ----/////////////////-----//////////////////");
		
	}
	
	// -- INITIALIZE -- //
	public void initialize(URL url, ResourceBundle rb) {
		
        // --- habilitar e desabilitar botões ---- //
		modularBotoesInicial();
		
		// --- Selecionar um documento pesquisado  --- //
		selecionarDenuncia ();
		
		// --- listar denuncias --- //
		listarDenuncias();
		
	}
		
	// -- método habilitar e desabilitar botões -- //
	private void modularBotoesInicial () {
			
			tfDocumento.setDisable(true);
			tfDocumento.setDisable(true);
			tfDocSei.setDisable(true);
			tfProcSei.setDisable(true);
			tfResDen.setDisable(true);
			btnSalvar.setDisable(true);
			btnEditar.setDisable(true);
			btnExcluir.setDisable(true);
			btnNovo.setDisable(false);
			
	}
	
	// -- Main initialize para transmitir variáveis para outros controllers -- //
	public void init(MainController mainController) {
			main = mainController;
	}

}

