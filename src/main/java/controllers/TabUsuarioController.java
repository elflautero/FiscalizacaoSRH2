package controllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import dao.UsuarioDao;
import entity.Endereco;
import entity.Interferencia;
import entity.Usuario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import tabela.UsuarioTabela;

public class TabUsuarioController implements Initializable {
	
	//-- Strings --//
	
	String strPesquisaUsuario = "";
	
	@FXML Pane tabUsuario = new Pane();
	
	@FXML TextField tfUsNome;
	@FXML TextField tfUsCPFCNPJ;
	@FXML TextField tfUsEnd;
	@FXML TextField tfUsCEP;
	@FXML TextField tfUsCidade;
	@FXML TextField tfUsTel;
	@FXML TextField tfUsCel;
	@FXML TextField tfUsEmail;
	
	
	//-- Persistência --//
	
	@FXML Button btnUsNovo;
	@FXML Button btnUsSalvar;
	@FXML Button btnUsEditar;
	@FXML Button btnUsExcluir;
	@FXML Button btnUsCancelar;
	@FXML TextField tfUsPesquisar;
	@FXML Button btnUsPesq;
	
	// --- objeto para passar os valor pelo MainControoler para outro controller --- //
	public Endereco eGeralUs;
	
	
	public void btnUsNovoHab (ActionEvent event) {
		
	}
	
	public void btnUsSalvarHab (ActionEvent event) {
			
	}
	
	public void btnUsEditarHab (ActionEvent event) {
		
	}
	
	public void btnUsExcluirHab (ActionEvent event) {
		
	}
	
	public void btnUsCancelarHab (ActionEvent event) {
		
	}
	
	public void btnUsPesqHab (ActionEvent event) {
		
	}


	//-- combobox - tipo de pessoa --//
	@FXML
	ChoiceBox<String> cbTipoPessoa = new ChoiceBox<String>();
		ObservableList<String> olTipoPessoa = FXCollections
			.observableArrayList("Física" , "Jurídica"); // box - seleção pessoa físcia ou jurídica
		
	//-- combobox - Região Administrativa --//	
	@FXML
	ChoiceBox<String> cbUsRA = new ChoiceBox<String>();
		ObservableList<String> olUsRA = FXCollections
			.observableArrayList(
					
					"Brasília",
					"Gama",
					"Taguatinga",
					"Brazlândia",
					"Sobradinho",
					"Planaltina",
					"Paranoá",
					"Núcleo Bandeirante",
					"Ceilândia",
					"Guará",
					"Cruzeiro",
					"Samambaia",
					"Santa Maria",
					"São Sebastião",
					"Recanto das Emas",
					"Lago Sul",
					"Riacho Fundo",
					"Lago Norte",
					"Candangolândia",
					"Águas Claras",
					"Riacho Fundo II",
					"Sudoeste/Octogonal",
					"Varjão",
					"Park Way",
					"SCIA",
					"Sobradinho II",
					"Jardim Botânico",
					"Itapoã",
					"SIA",
					"Vicente Pires",
					"Fercal"); 	
		
		//-- combobox - unidade federal --//
		@FXML
		ChoiceBox<String> cbUsUF = new ChoiceBox<String>();
			ObservableList<String> olUsDF = FXCollections
				.observableArrayList("DF" , "GO", "Outro"); // box - seleção pessoa físcia ou jurídica

		//-- receber o endereço --//
		public Label lblEndUsuario;
		
		public void listarUsuarios (String strPesquisaUsuario) {
			
			UsuarioDao usDao = new UsuarioDao();
			
			List<Usuario> usuarioList = usDao.listUsuario(strPesquisaUsuario);
			
			ObservableList<UsuarioTabela> olUsuarioTabela = FXCollections.observableArrayList();
			
			
			if (!olUsuarioTabela.isEmpty()) {
				olUsuarioTabela.clear();
			}
			
				for (Usuario usuario :usuarioList) {
					
					UsuarioTabela usTab = new UsuarioTabela(
							
							usuario.getUs_Codigo(),
							usuario.getUs_Nome(),
							usuario.getUs_CPFCNPJ(),
							usuario.getUs_Descricao_End(),
							usuario.getUs_RA(),
							usuario.getUs_Bairro(),
							usuario.getUs_Cidade(),
							usuario.getUs_Postal(),
							usuario.getUs_Telefone(),
							usuario.getUs_Celular(),
							usuario.getUs_Email(),
							
							usuario.getUs_End_CodigoFK()
							
							);
					{
						
						
						
					}
					
					
				}
					
			
		}
			
		
		
		//-- INITIALIZE --//
		public void initialize(URL url, ResourceBundle rb) {
			

			cbTipoPessoa.setValue("Física");
			cbTipoPessoa.setItems(olTipoPessoa);
			
			cbUsRA.setValue("Brasília");
			cbUsRA.setItems(olUsRA);
			
			cbUsUF.setValue("DF");
			cbUsUF.setItems(olUsDF);
			
		}
}










