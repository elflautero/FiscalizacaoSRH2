package controllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

//import javax.persistence.EntityManager;

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
import javafx.scene.control.Alert;
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
	
	Double latCoord = -15.7754084; // latitude inicial do mapa - ADASA
 	Double longCoord = -47.9411395; // longitude inicial do mapa - ADASA
 	
 	
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
 				
 					System.out.println("foreign key: " + endTab.getListTabelaEnderecoDenuncias());
 					System.out.println(endTab);
 					
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
	
	// --  botão salvar -- //
	public void btnEndSalvarHab (ActionEvent event) {
			
		if (tfEndLat.getText().isEmpty() || tfEndLon.getText().isEmpty()) {
			
			System.out.println("não é número");
			
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
		
			enderecoDao.mergeEnd(endereco);
			
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
		
		enderecoDao.removeEndereco(id);
		
		listarEnderecos(strPesquisaEnd);
		
		modularBotoesInicial();
		
		/*
		 
		DenunciaTabela denunciaExcluir = tvLista.getSelectionModel().getSelectedItem();
		
		int id = denunciaExcluir.getCod_Denuncia(); // buscar id para deletar
		
		denunciaList = denunciaDao.listDenuncia(strPesquisa);
		
		obsListDenunciaTabela.remove(denunciaExcluir);
		
		denunciaDao.removeDenuncia(id);
		
		denunciaList = denunciaDao.listDenuncia(strPesquisa);
		
		listarDenuncias();
		
		modularBotoesInicial (); 	
		 */
	}
	
	//-- botão cancelar --//
	public void btnEndCanHab (ActionEvent event) {

		modularBotoesInicial (); 
		
	}
	
	//-- botão pesquisar endereço --//
	public void btnEndPesqHab (ActionEvent event) {
		
		System.out.println("///////---////////-----/////////// endereços abaixo ----/////////////////-----//////////////////");
		
		strPesquisaEnd = tfEndPesq.getText();
		
		//enderecoList = enderecoDao.listEndereco();
			// não entendi este endereço list aqui
		
		listarEnderecos (strPesquisaEnd);
		
		modularBotoesInicial (); 
		
		System.out.println("///////---////////-----/////////// endereços acima ----/////////////////-----//////////////////");
		
	}
	
	public void  btnEndLatLonHab (ActionEvent event) {
		
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
	
	public void btnBuscarDocHab (ActionEvent event) {
		
	}
	
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		
		// modular a forma de abrir dos botões
		modularBotoesInicial ();
		
		selecionarEndereco ();
		
		//listarEnderecos (strPesquisaEnd);
		
	}
	
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
	
	
	public void init(MainController mainController) {
		
		
	}
	

}
