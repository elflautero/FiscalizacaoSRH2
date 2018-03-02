package principal;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{

    public Parent rootNode;
    
    // para commit
	
    public static void main(final String[] args) {
        Application.launch(args);
    }
    
    // 23 de Janeiro de 2017 - Primeiro Commit
    
    @Override
    public void init() throws Exception {
    
    
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/Main.fxml"));
    rootNode = fxmlLoader.load();
    
    }

    @Override
    public void start(Stage stage) throws Exception {
    	
        stage.setScene(new Scene(rootNode));
        
        stage.setMaximized(false);
        stage.setResizable(false);
        
        stage.show();
    }

}