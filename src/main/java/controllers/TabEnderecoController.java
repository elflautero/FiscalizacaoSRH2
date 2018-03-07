package controllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import dao.EnderecoDao;
import entity.Denuncia;
import entity.Endereco;
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
import javafx.scene.layout.Pane;
import tabela.EnderecoTabela;

public class TabEnderecoController implements Initializable {
	
	@FXML Pane tabEndereco = new Pane();
	
	@FXML Button btnBuscarDoc = new Button();
	
	@FXML TextField tfEnd = new TextField();
	@FXML TextField tfEndBairro = new TextField();
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

	@FXML Button btnDenAtualizar = new Button();
	@FXML TextField tfEndPesq = new TextField();
	
	@FXML Button btnEndLatLon = new Button();
	@FXML Button btnEndAtualizar = new Button();
	
	// TABLE VIEW - BUSCAR, EDITAR ETC ENDERECO
	@FXML private TableView <EnderecoTabela> tvListaEnd;
	
	@FXML TableColumn<EnderecoTabela, String> tcDesEnd;
	@FXML TableColumn<EnderecoTabela, String> tcEndRA;
	@FXML TableColumn<EnderecoTabela, String> tcEndCid;
	
	
	// --- Objeto para passar os valor pelo MainControoler para outro controller --- //
	public Denuncia dGeralEnd;
	
	String strPesquisaEnd = "";
	

	// --- conexão - listar endereços --- //
	private EnderecoDao enderecoDao = new EnderecoDao();
	private List<Endereco> enderecoList = enderecoDao.listEndereco(strPesquisaEnd);
	private ObservableList<EnderecoTabela> obsListEnderecoTabela = FXCollections.observableArrayList();
	
	
	Double latCoord = -15.7754084; // latitude inicial do mapa - ADASA
 	Double longCoord = -47.9411395; // longitude inicial do mapa - ADASA
 	
 	
 	// --- método para listar endereços --- //
 	public void listarEnderecos () {
 		
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
				tfEndBairro.setText("");
				tfEndCep.setText("");
				tfEndCid.setText("");
				tfEndUF.setText("");
				
				btnEndNovo.setDisable(true);
				btnEndSalvar.setDisable(true);
				btnEndEditar.setDisable(false);
				btnEndExc.setDisable(false);
				btnEndCan.setDisable(false);
				
			} else {

				// -- preencher os campos -- //
				tfEnd.setText(endTab.getDesc_Endereco());
				tfEndBairro.setText(endTab.getRA_Endereco());
				tfEndCep.setText(endTab.getCEP_Endereco());
				tfEndCid.setText(endTab.getCid_Endereco());
				tfEndUF.setText(endTab.getUF_Endereco());
				
				// -- habilitar e desabilitar botões -- //
				btnEndNovo.setDisable(true);
				btnEndSalvar.setDisable(true);
				btnEndEditar.setDisable(false);
				btnEndExc.setDisable(false);
				btnEndCan.setDisable(false);
				
			}
			}
		});
	}
 	
 	// -- botão novo - - //
	public void btnEndNovoHab (ActionEvent event) {
		
		tfEnd.setText("");
		tfEndBairro.setText("");
		tfEndCep.setText("");
		tfEndCid.setText("");
		tfEndUF.setText("");
		
		tfEnd.setDisable(false);
		tfEndBairro.setDisable(false);
		tfEndCep.setDisable(false);
		tfEndCid.setDisable(false);
		tfEndUF.setDisable(false);
		
		btnEndSalvar.setDisable(false);
		btnEndEditar.setDisable(true);
		btnEndExc.setDisable(true);
		btnEndEditar.setDisable(true);
		
	}
	
	// --  botão salvar -- //
	public void btnEndSalvarHab (ActionEvent event) {
			
		Endereco endereco = new Endereco();
		
			endereco.setDesc_Endereco(tfEnd.getText());
			endereco.setRA_Endereco(tfEndBairro.getText());
			endereco.setCEP_Endereco(tfEndCep.getText());
			endereco.setCid_Endereco(tfEndCid.getText());
			endereco.setUF_Endereco(tfEndUF.getText());
		
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
		
	}
	
	// -- botão editar -- //
	public void btnEndEditarHab (ActionEvent event) {
		
		if (tfEnd.isDisable()) {
			tfEnd.setDisable(false);
			tfEndBairro.setDisable(false);
			tfEndBairro.setDisable(false);
			tfEndCid.setDisable(false);
			tfEndUF.setDisable(false);
			
		} else {
		
		EnderecoTabela enderecoTabelaEditar = tvListaEnd.getSelectionModel().getSelectedItem();
		Endereco endereco = new Endereco(enderecoTabelaEditar);
		
		endereco.setDesc_Endereco(tfEnd.getText());
		endereco.setRA_Endereco(tfEndBairro.getText());
		endereco.setCEP_Endereco(tfEndCep.getText());
		endereco.setCid_Endereco(tfEndCid.getText());
		endereco.setUF_Endereco(tfEndUF.getText());
	
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
		}
		
		listarEnderecos();
		
		modularBotoesInicial (); 
		
	}
	
	// -- botão excluir -- //
	public void btnEndExcHab (ActionEvent event) {
		
	}
	public void btnEndCanHab (ActionEvent event) {
		
	}
	public void btnEndPesqHab (ActionEvent event) {
		
		strPesquisaEnd = tfEndPesq.getText(); // para buscar
		
		enderecoList = enderecoDao.listEndereco(strPesquisaEnd);
		listarEnderecos (); //listar a busca
		
		/*
		strPesquisa = (String) tfPesquisar.getText();
		
		denunciaList = denunciaDao.listDenuncia(strPesquisa);
		
		listarDenuncias();
		
		modularBotoesInicial (); 
		*/
	}
	
	public void  btnEndLatLonHab (ActionEvent event) {
		
		String linkEndCoord = (tfLinkEnd.getText());
		
		int latIni= linkEndCoord.indexOf("@");
		String lat = linkEndCoord.substring(latIni);
		
		int latF = lat.indexOf(",");
		String latitude = lat.substring(1, latF);
		
		String longitude = lat.substring(latF + 1, latF + 1 + latitude.length());
		
		tfEndLat.setText(latitude);
		tfEndLon.setText(longitude);
	}
	
	public void btnBuscarDocHab (ActionEvent event) {
		
	}
	
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		
		// modular a forma de abrir dos botões
		modularBotoesInicial ();
		
		selecionarEndereco ();
		
		listarEnderecos ();
		
	}
	
	private void modularBotoesInicial () {
		
		tfEnd.setDisable(true);
		tfEndBairro.setDisable(true);
		tfEndCep.setDisable(true);
		tfEndCid.setDisable(true);
		tfEndUF.setDisable(true);
		btnEndSalvar.setDisable(true);
		btnEndEditar.setDisable(true);
		btnEndExc.setDisable(true);
		btnEndNovo.setDisable(false);
		
	}
	
	
	public void init(MainController mainController) {
		
		
	}
	

}
