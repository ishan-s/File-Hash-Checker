package app.ishan.jash;
	
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import app.ishan.jash.common.AppLog;
import app.ishan.jash.common.Const;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;


public class Main extends Application {

	private AppLog log = new AppLog();
	
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/app/ishan/jash/JashMain.fxml"));
			
			VBox root = (VBox) fxmlLoader.load();
			addMenuBar(root);
			
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			primaryStage.getIcons().add(new Image(Main.class.getResourceAsStream("/images/jash-icon.png")));
			primaryStage.setTitle(Const.APP_TITLE);
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.show();
			
			JashMainController jashMainController = (JashMainController) fxmlLoader.getController();
			jashMainController.initialize(primaryStage, this);
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public void addMenuBar(VBox rootPane){
		MenuBar menuBar = new MenuBar();
		Menu fileMenu = new Menu("File");
		Menu aboutMenu = new Menu("About");
		menuBar.getMenus().addAll(fileMenu, aboutMenu);
		
		rootPane.getChildren().add(0, menuBar);
	}
}
