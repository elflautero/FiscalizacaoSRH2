package controllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import dao.DenunciaDao;
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
	
	// 
	public Denuncia dGeralEnd;
	
	Double latCoord = -15.7754084; // latitude inicial do mapa - ADASA
 	Double longCoord = -47.9411395; // longitude inicial do mapa - ADASA
 	
 	String srtPesquisaEnd = "";
 	String strPesquisaDoc = "";
	
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
		
		(endereco.getDenuncias()).add(denuncia);
		
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
	
	public void btnPesqDocHab (ActionEvent event) {
		String srtPesquisaDoc = tfPesquisar.getText();
		listarDenuncias (srtPesquisaDoc); // chamar método listar denúncias //
		// Selecionar um documento pesquisado
		selecionarDenunciaDoc ();
		
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
	
	// criar método para listar denúncias //
	public void listarDenuncias (String srtPesquisaDoc) {
		DenunciaDao denunciaDao = new DenunciaDao();
		List<Denuncia> denunciaList = denunciaDao.listDenuncia(srtPesquisaDoc);
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
					denuncia.getDesc_Denuncia(),
					//adicionado o objeto  endereçoFK, 
					denuncia.getEnderecoFK()
					);
			
				obsListDenunciaTabela.add(denTab);
		}
		
		tcDocumento.setCellValueFactory(new PropertyValueFactory<DenunciaTabela, String>("Doc_Denuncia")); 
        tcDocSEI.setCellValueFactory(new PropertyValueFactory<DenunciaTabela, String>("Doc_SEI_Denuncia")); 
        tcProcSEI.setCellValueFactory(new PropertyValueFactory<DenunciaTabela, String>("Proc_SEI_Denuncia")); 
        
        tvListaDoc.setItems(obsListDenunciaTabela); 
	}
	
	// criar método para listar endereços //
	public void listarEnderecos (String srtPesquisaEnd) {
		EnderecoDao enderecoDao = new EnderecoDao();
		List<Endereco> enderecoList = enderecoDao.listEndereco(srtPesquisaEnd);
		ObservableList<EnderecoTabela> obsListEnderecoTabela = FXCollections.observableArrayList();
		//Set<Denuncia> denunciasSet = new HashSet<Denuncia>();
		
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
					endereco.getDenuncias()  
					);
					//System.out.println(endereco.getDenuncias());
					//System.out.println(endTab.getDenuncias());
					//System.out.println(endereco.getDenuncias());
					//System.out.println(endereco.getDenuncias().toString());
					
					//getEndDenuncias ()
			
					obsListEnderecoTabela.add(endTab);
					
					
					//ERRO denuncias=<uninitialized>
		}
		
		tcDesEnd.setCellValueFactory(new PropertyValueFactory<EnderecoTabela, String>("Desc_Endereco")); 
		tcEndRA.setCellValueFactory(new PropertyValueFactory<EnderecoTabela, String>("RA_Endereco")); 
		tcEndCid.setCellValueFactory(new PropertyValueFactory<EnderecoTabela, String>("CEP_Endereco")); 
		
        tvListaEnd.setItems(obsListEnderecoTabela); 
	}
	
	public void selecionarDenunciaDoc () {
		
		// TABLE VIEW SELECIONAR DOCUMENTO AO CLICAR NELE
		
		tvListaDoc.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Object>() {
			public void changed(ObservableValue<?> observable , Object oldValue, Object newValue) {
																																						DenunciaTabela denTab = (DenunciaTabela) newValue;
			if (denTab == null) {
				
				lblDoc.setText("Campo nulo!");
				
			} else {

				
				Denuncia dGeral = new Denuncia(denTab);
				
				dGeralEnd = dGeral;
				lblDoc.setText(dGeralEnd.getDoc_Denuncia() + "  |  SEI nº: " + dGeralEnd.getDoc_SEI_Denuncia());
				
			}
			}
		});
	}
	

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		
	}

	public void init(MainController mainController) {
		// TODO Auto-generated method stub
		
	}
	

}
