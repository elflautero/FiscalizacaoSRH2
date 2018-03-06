package controllers;

import entity.Denuncia;
import javafx.fxml.FXML;

public class MainController {
	
	 	@FXML private TabDenunciaController tabDenunciaController;
	    @FXML private TabInterfController tabInterfController;
	    @FXML private TabUsuarioController tabUsuarioController;
	    @FXML private TabEnderecoController tabEnderecoController;
	    @FXML private TabNavegadorController tabNavegadorController;
	    

	    @FXML 
	    private void initialize() {
	       System.out.println("Application Started!");
	       tabDenunciaController.init(this);
	       tabEnderecoController.init(this);
	    }

	    // mudei voi para retornar  Denuncia
		public void pegarDoc(Denuncia dGeral) {
			
			tabEnderecoController.lblDoc.setText(dGeral.getDoc_Denuncia() + "  |  SEI nº: " + dGeral.getDoc_SEI_Denuncia());
			tabEnderecoController.dGeralEnd = dGeral;
		}
}
