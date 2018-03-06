package controllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import dao.EnderecoDao;
import entity.Denuncia;
import entity.Endereco;
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
	@FXML Button btnEndEdit = new Button();
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
	
	//
	
	
	// 
	public Denuncia dGeralEnd;
	
	Double latCoord = -15.7754084; // latitude inicial do mapa - ADASA
 	Double longCoord = -47.9411395; // longitude inicial do mapa - ADASA
 	
 	String srtPesquisaEnd = "";
 	String strPesquisaDoc = "";
 	
 	public void btnBuscarDocHab (ActionEvent event) {
 		
 	}
	
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
		btnEndEdit.setDisable(true);
		
	}
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
	public void btnEndEditHab (ActionEvent event) {
		
	}
	public void btnEndExcHab (ActionEvent event) {
		
	}
	public void btnEndCancHab (ActionEvent event) {
		
	}
	public void btnEndPesqHab (ActionEvent event) {
		
		String srtPesquisaEnd = tfEndPesq.getText(); // para buscar
		System.out.println(" O Valor digitado é: " + srtPesquisaEnd);
		listarEnderecos (srtPesquisaEnd); //listar a busca
		
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
	
	
	
	// criar método para listar endereços //
	public void listarEnderecos (String srtPesquisaEnd) {
		EnderecoDao enderecoDao = new EnderecoDao();
		List<Endereco> enderecoList = enderecoDao.listEndereco(srtPesquisaEnd);
		ObservableList<EnderecoTabela> obsListEnderecoTabela = FXCollections.observableArrayList();
		
		System.out.println("começo do listarEnderecos");
		
		if (!obsListEnderecoTabela.isEmpty()) {
			obsListEnderecoTabela.clear();
		}
		
		for (Endereco endereco : enderecoList) {
			
			System.out.println("Veja os valores de endereco: " +  endereco);
			
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
	
	
	
	private void modularBotoesInicial () {
		
		tfEnd.setDisable(true);
		tfEndBairro.setDisable(true);
		tfEndCep.setDisable(true);
		tfEndCid.setDisable(true);
		tfEndUF.setDisable(true);
		btnEndSalvar.setDisable(true);
		btnEndEdit.setDisable(true);
		btnEndExc.setDisable(true);
		btnEndNovo.setDisable(false);
		
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		
		// modular a forma de abrir dos botões
		modularBotoesInicial ();
		
	}
	public void init(MainController mainController) {
		
		
	}
	

}
