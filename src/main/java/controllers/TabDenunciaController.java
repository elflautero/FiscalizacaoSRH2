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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import tabela.DenunciaTabela;


public class TabDenunciaController implements Initializable {
	
	// String teste para o leitor de relatórios
	
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
	
	
	@FXML
	AnchorPane tabDenuncia = new AnchorPane();
	@FXML
	TextField tfDocumento = new TextField();
	@FXML
	TextField tfDocSei = new TextField();
	@FXML
	TextField tfProcSei =  new TextField();
	@FXML
	TextField tfResDen = new TextField();
	@FXML
	TextField tfPesquisar = new TextField();

	@FXML
	Button btnNovo = new Button();
	@FXML
	Button btnSalvar = new Button();
	@FXML
	Button btnEditar = new Button();
	@FXML
	Button btnExcluir = new Button();
	@FXML
	Button btnCancelar = new Button();
	@FXML
	Button btnPesquisar = new Button();
	@FXML
	Button btnSair = new Button();
	
	@FXML
	private TableView <DenunciaTabela> tvLista;
	// COLUMNS
	@FXML
	private TableColumn<DenunciaTabela, String> tcDocumento;
	@FXML
	private TableColumn<DenunciaTabela, String> tcDocSEI;
	@FXML
	private TableColumn<DenunciaTabela, String> tcProcSEI;
	
	
	// String para primeira pesquisa do banco ao chamar o programa
	String strPesquisa = "";
	
	private DenunciaDao denunciaDao = new DenunciaDao();	
	private List<Denuncia> denunciaList = denunciaDao.listDenuncia(strPesquisa);
	private ObservableList<DenunciaTabela> obsListDenunciaTabela= FXCollections.observableArrayList();
	
	public void listarDenuncias () {
		if (!obsListDenunciaTabela.isEmpty()) {
			obsListDenunciaTabela.clear();
		}
		for (Denuncia denuncia : denunciaList) {
			DenunciaTabela denTab = new DenunciaTabela(
					denuncia.getCod_Denuncia(), 
					denuncia.getDocumento_Denuncia(),
					denuncia.getDocumento_SEI_Denuncia(), 
					denuncia.getProcesso_SEI_Denuncia(),
					denuncia.getDescricao_Denuncia()
					);
			
				obsListDenunciaTabela.add(denTab);
		}
		
		tcDocumento.setCellValueFactory(new PropertyValueFactory<DenunciaTabela, String>("Documento_Denuncia")); 

        tcDocSEI.setCellValueFactory(new PropertyValueFactory<DenunciaTabela, String>("Documento_SEI_Denuncia")); 

        tcProcSEI.setCellValueFactory(new PropertyValueFactory<DenunciaTabela, String>("Processo_SEI_Denuncia")); 
        
        tvLista.setItems(obsListDenunciaTabela); 
	}
	
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
	
	public void btnSalvarSalvar (ActionEvent event) {
		
		Denuncia denuncia = new Denuncia();
		
		denuncia.setDocumento_Denuncia(tfDocumento.getText());
		denuncia.setProcesso_SEI_Denuncia(tfProcSei.getText());
		denuncia.setDocumento_SEI_Denuncia(tfDocSei.getText());
		denuncia.setDescricao_Denuncia(tfResDen.getText());
		
		DenunciaDao dao = new DenunciaDao();
		dao.salvaDenuncia(denuncia);
		
		tfDocumento.setText("");
		tfDocSei.setText("");
		tfProcSei.setText("");
		tfResDen.setText("");
		
		denunciaList = denunciaDao.listDenuncia(strPesquisa);
		listarDenuncias();
		
		modularBotoesInicial (); 
			
		}

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
			
			denunciaEditar.setDocumento_Denuncia(tfDocumento.getText());
			denunciaEditar.setDocumento_SEI_Denuncia(tfDocSei.getText());
			denunciaEditar.setProcesso_SEI_Denuncia(tfProcSei.getText());
			denunciaEditar.setDescricao_Denuncia(tfResDen.getText());
			
			denunciaDao.editarDenuncia(denunciaEditar);
			denunciaList = denunciaDao.listDenuncia(strPesquisa);
			listarDenuncias();
			
			modularBotoesInicial (); 
				
			}
	}
			
	public void btnExcluirHabilitar (ActionEvent event) {
	
		DenunciaTabela denunciaExcluir = tvLista.getSelectionModel().getSelectedItem();
		int id = denunciaExcluir.getCod_Denuncia();
		System.out.println("O id é: " + id);
		obsListDenunciaTabela.remove(denunciaExcluir);
		denunciaDao.removeDenuncia(id);
		denunciaList = denunciaDao.listDenuncia(strPesquisa);
		listarDenuncias();
		
		modularBotoesInicial (); 		
	}
		
			
	public void btnCancelarHabilitar (ActionEvent event) {
			
		modularBotoesInicial();
	}
		
	public void btnPesquisarHabilitar (ActionEvent event) {
		
		strPesquisa = (String) tfPesquisar.getText();
		
		DenunciaDao denunciaDao = new DenunciaDao();	
		denunciaList = denunciaDao.listDenuncia(strPesquisa);
		listarDenuncias (); 
		
		modularBotoesInicial (); 
		
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		
		// DENUNCIA - BOTÕES
				modularBotoesInicial();
				
				// Selecionar um documento pesquisado
				SelecionarDenuncia ();
				
				// listar denuncias
				listarDenuncias();
		
	}
		
	//MÉTODO INICIAL DE HABILITAR E DESABILITAR BOTÕES
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
		
		public void SelecionarDenuncia () {
			
			// TABLE VIEW SELECIONAR DOCUMENTO AO CLICAR NELE
			
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

					tfDocumento.setText(denTab.getDocumento_Denuncia());
					tfDocSei.setText(denTab.getDocumento_SEI_Denuncia());
					tfProcSei.setText(denTab.getProcesso_SEI_Denuncia());
					tfResDen.setText(denTab.getDescricao_Denuncia());
					
					btnNovo.setDisable(true);
					btnSalvar.setDisable(true);
					btnEditar.setDisable(false);
					btnExcluir.setDisable(false);
					btnCancelar.setDisable(false);
				}
				}
			});
		}
}

