package controllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import dao.DenunciaDao;
import entity.Denuncia;
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
	@FXML Label lblDoc = new Label();
	@FXML TextField tfLinkEnd = new TextField();
	@FXML TextField tfEndLat = new TextField();
	@FXML TextField tfEndLon = new TextField();
	
	@FXML Button btnEndNovo = new Button();
	@FXML Button btnEndSalvar = new Button();
	@FXML Button btnEndEditar = new Button();
	@FXML Button btnEndExc = new Button();
	@FXML Button btnEndEdit = new Button();
	@FXML Button btnEndPesq = new Button();

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
	
	
	// TABLE BUSCAR DENÚNCIA
	@FXML private TableView <DenunciaTabela> tvListaDoc;
	
	@FXML private TableColumn<DenunciaTabela, String> tcDocumento;
	@FXML private TableColumn<DenunciaTabela, String> tcDocSEI;
	@FXML private TableColumn<DenunciaTabela, String> tcProcSEI;
	
	
	public void btnEndNovoHab (ActionEvent event) {
		
	}
	public void btnEndSalvarHab (ActionEvent event) {
			
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

}
