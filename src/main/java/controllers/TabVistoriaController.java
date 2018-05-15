package controllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import dao.VistoriaDao;
import entity.Endereco;
import entity.Vistoria;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
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
		
	}
	
	public void btnSalvarHab (ActionEvent event) {
		
		Vistoria vis = new Vistoria();
		
			vis.setVisIdentificacao(tfAto.getText());
			vis.setVisSEI(tfAtoSEI.getText());
			vis.setVisDataFiscalizacao(dpDataFiscalizacao.getValue().toString());
			vis.setVisDataCriacao(dpDataCriacaoAto.getValue().toString());
			
			vis.setVisInfracoes(strInfracoes);
			vis.setAtoPenalidades(strPenalidades);
			vis.setVisAgravantes(strAgravantes);
			vis.setVisAtenuantes(strAtenuantes);
			
			vis.setVisObjeto(taObjeto.getText());
			vis.setVisApresentacao(taApresentacao.getText());
			vis.setVisRelato(taRelato.getText());
			vis.setVisRecomendacoes(taRecomendacoes.getText());
			
			
			vis.setVisEndCodigoFK(eGeralVis);
			
			VistoriaDao visDao = new VistoriaDao();
			
			visDao.salvarVistoria(vis);
			visDao.mergeVistoria(vis);
			
			listarVistorias(strPesquisa);
			selecionarVistoria();
			
			//-- número da vistoria para a tabela atos --//
			System.out.println("código da vistoria salva " + vis.getVisCodigo());
			visGeral = vis;
			main.pegarVistoria(vis);
			
		
	}
	
	public void btnEditarHab (ActionEvent event) {
		
	}

	public void btnExcluirHab (ActionEvent event) {
	
	}
	
	public void btnCancelarHab (ActionEvent event) {
		
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


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
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
					vis.getAtoPenalidades(),
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
					
					/*
					tfEnd.setText("");
					
					tfEndCep.setText("");
					tfEndCid.setText("");
					//tfEndUF.setText("");
					tfEndLat.setText("");
					tfEndLon.setText("");
					
					btnEndNovo.setDisable(true);
					btnEndSalvar.setDisable(true);
					btnEndEditar.setDisable(false);
					btnEndExc.setDisable(false);
					btnEndCan.setDisable(false);
					*/
					System.out.println("valor null - método selecionar vistoria");
					
				} else {

					tfAto.setText(visTab.getVisIdentificacao());
					tfAtoSEI.setText(visTab.getVisSei());
					
					/*
					// -- preencher os campos -- //
					tfEnd.setText(endTab.getDesc_Endereco());
					cbEndRA.setValue(endTab.getRA_Endereco());  //tfEndRA.setText(endTab.getRA_Endereco());
					tfEndCep.setText(endTab.getCEP_Endereco());
					tfEndCid.setText(endTab.getCid_Endereco());
					cbEndUF.setValue(endTab.getUF_Endereco());  //tfEndUF.setText(endTab.getUF_Endereco());
					tfEndLat.setText(endTab.getLat_Endereco().toString());
					tfEndLon.setText(endTab.getLon_Endereco().toString());
					
					// -- habilitar e desabilitar botões -- //
					btnEndNovo.setDisable(true);
					btnEndSalvar.setDisable(true);
					btnEndEditar.setDisable(false);
					btnEndExc.setDisable(false);
					btnEndCan.setDisable(false);
					
					Endereco eGeral = new Endereco(endTab);
				
					main.pegarEnd(eGeral);
					*/
					
				}
				}
			});
		}

}
