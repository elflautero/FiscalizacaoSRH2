package controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import dao.DenunciaDao;
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
import tabela.DenunciaTabela;
import tabela.EnderecoTabela;

public class TabEnderecoController implements Initializable {
	
	@FXML Pane tabEndereco = new Pane();
	
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
	@FXML Button btnPesqDoc = new Button();

	@FXML TextField tfPesquisar = new TextField();
	
	// TABLE VIEW - BUSCAR, EDITAR ETC ENDERECO
	@FXML private TableView <EnderecoTabela> tvListaEnd;
	
	@FXML TableColumn<EnderecoTabela, String> tcDesEnd;
	@FXML TableColumn<EnderecoTabela, String> tcEndRA;
	@FXML TableColumn<EnderecoTabela, String> tcEndCid;
	
	//
	
	// TABLE BUSCAR DENÚNCIA
	@FXML private TableView <DenunciaTabela> tvListaDoc;
	
	@FXML private TableColumn<DenunciaTabela, String> tcDocumento;
	@FXML private TableColumn<DenunciaTabela, String> tcDocSEI;
	@FXML private TableColumn<DenunciaTabela, String> tcProcSEI;
	
	
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
		List<Denuncia> denuncias  = new ArrayList<Denuncia>(0);
		
		denuncias.get(TabDenunciaController.getCod_Denuncia_TabEnd());
		
		//denuncia.setCod_Denuncia(TabDenunciaController.getCod_Denuncia_TabEnd());
		
		
		endereco.setDesc_Endereco(tfEnd.getText());
		endereco.setRA_Endereco(tfEndBairro.getText());
		endereco.setCEP_Endereco(tfEndCep.getText());
		endereco.setUF_Endereco(tfEndUF.getText());
		endereco.setDenuncias(denuncias);
		
		EnderecoDao enderecoDao = new EnderecoDao();
		
		enderecoDao.salvaEndereco(endereco);

		
	}
	public void btnEndEditHab (ActionEvent event) {
		
	}
	public void btnEndExcHab (ActionEvent event) {
		
	}
	public void btnEndCancHab (ActionEvent event) {
		
	}
	public void btnEndPesqHab (ActionEvent event) {
		
	}
	
	public void btnPesqDocHab (ActionEvent event) {
		String strPesquisa = tfPesquisar.getText();
		listarDenuncias (strPesquisa); // chamar método listar denúncias //
		
		
	}
	
	// criar método para listar denúncias //
	public void listarDenuncias (String strPesquisa) {
		DenunciaDao denunciaDao = new DenunciaDao();
		List<Denuncia> denunciaList = denunciaDao.listDenuncia(strPesquisa);
		ObservableList<DenunciaTabela> obsListDenunciaTabela= FXCollections.observableArrayList();
		if (!obsListDenunciaTabela.isEmpty()) {
			obsListDenunciaTabela.clear();
		}
		for (Denuncia denuncia : denunciaList) {
			DenunciaTabela denTab = new DenunciaTabela(
					denuncia.getCod_Denuncia(), 
					denuncia.getDoc_Denuncia(),
					denuncia.getDoc_SEI_Denuncia(), 
					denuncia.getProc_SEI_Denuncia(),
					denuncia.getDesc_Denuncia()
					);
			
				obsListDenunciaTabela.add(denTab);
		}
		
		tcDocumento.setCellValueFactory(new PropertyValueFactory<DenunciaTabela, String>("Doc_Denuncia")); 

        tcDocSEI.setCellValueFactory(new PropertyValueFactory<DenunciaTabela, String>("Doc_SEI_Denuncia")); 

        tcProcSEI.setCellValueFactory(new PropertyValueFactory<DenunciaTabela, String>("Proc_SEI_Denuncia")); 
        
        tvListaDoc.setItems(obsListDenunciaTabela); 
	}
	

	@Override
	public void initialize(URL url, ResourceBundle rb) {
	
	}

	public void init(MainController mainController) {
		// TODO Auto-generated method stub
		
	}
	

}
