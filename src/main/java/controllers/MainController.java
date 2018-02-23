package controllers;

import javafx.fxml.FXML;

public class MainController {
	
	 	@FXML private TabDenunciaController tabDenunciaController;
	    @FXML private TabInterfController tabInterfController;
	    @FXML private TabUsuarioController tabUsuarioController;
	    @FXML private TabEnderecoController tabEnderecoController;
	    

	    @FXML 
	    private void initialize() {
	       System.out.println("Appliction Started!");
	       tabDenunciaController.init(this);
	       tabEnderecoController.init(this);
	    }


		public void pegarDoc(String doc_Denuncia_TabEnd) {
			tabEnderecoController.lblDoc.setText(doc_Denuncia_TabEnd);
		}
}
