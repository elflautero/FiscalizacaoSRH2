package controllers;

import javafx.fxml.FXML;

public class MainController {
	
	 	@FXML 
	    private TabDenunciaController consoleTabController;
	    @FXML 
	    private TabInterfController tabInterfController;
	    @FXML
	    private TabUsuarioController usuarioTabController;
	    

	    @FXML 
	    private void initialize() {
	        //consoleTabController.injectMainController(this);
	    }
}
