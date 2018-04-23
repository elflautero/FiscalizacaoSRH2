package controllers;

import entity.Denuncia;
import entity.Endereco;
import javafx.fxml.FXML;

public class MainController {
	
	 	@FXML private TabDenunciaController tabDenunciaController;
	    @FXML private TabInterfController tabInterferenciaController;
	    @FXML private TabUsuarioController tabUsuarioController;
	    @FXML private TabEnderecoController tabEnderecoController;
	    @FXML private TabNavegadorController tabNavegadorController;
	   
	    

	    @FXML 
	    private void initialize() {
	    	
	       System.out.println("Application Started!");
	       
	       tabDenunciaController.init(this);
	       tabEnderecoController.init(this);
	       tabInterferenciaController.init(this);
	       
	       
	    }

	    // mudei voi para retornar  Denuncia
		public void pegarDoc(Denuncia dGeral) {
			
			tabEnderecoController.lblDoc.setText(dGeral.getDoc_Denuncia() + "  |  SEI nº: " + dGeral.getDoc_SEI_Denuncia());
			tabEnderecoController.dGeralEnd = dGeral;
		}
		
		public void pegarEnd (Endereco eGeral) {
			//tabInterferenciaController.lblEnd.setText("OK!!!");
			tabInterferenciaController.lblEnd.setText(eGeral.getDesc_Endereco() + " |  RA: "  + eGeral.getRA_Endereco() );
			tabInterferenciaController.eGeralInt = eGeral;
			
			tabUsuarioController.lblEndUsuario.setText(eGeral.getDesc_Endereco() + " |  RA: "  + eGeral.getRA_Endereco() );
			tabUsuarioController.eGeralUs  = eGeral;
		}
		
}
