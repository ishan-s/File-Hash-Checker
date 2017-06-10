package app.ishan.jash;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import app.ishan.jash.common.AppLog;
import app.ishan.jash.common.Const;
import app.ishan.jash.hash.HashGenerator;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class JashMainController implements Initializable{
	private Stage primaryStage;
	private Main main;
	
	private AppLog log = new AppLog();
	
	@FXML TextField chosenFileTextField;
	@FXML GridPane inputGridPane;
	@FXML ComboBox<String> hashComboBox;
	@FXML Label startLabel;
	@FXML Label genHashLabel;
	@FXML TextArea genHashTextArea;
	
	private File selectedFile;
	
	public void initialize(Stage primaryStage, Main main){
		this.primaryStage = primaryStage; 
		this.main = main;
		
	}
	
	@FXML
	protected void handleBrowseButtonAction(){
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Select Input File");
		fileChooser.getExtensionFilters().add(
				new ExtensionFilter("All Files", "*.*"));
		
		selectedFile = fileChooser.showOpenDialog(primaryStage);
		chosenFileTextField.setText(selectedFile.getAbsolutePath());
		
		log.info("Selected File: "+selectedFile.getName());
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		hashComboBox.getItems().addAll(Const.SUPPORTED_HASH_TYPES);
		hashComboBox.setValue(Const.SUPPORTED_HASH_TYPES[0]);
		
		startLabel.getStyleClass().add("headerlabel");
		genHashLabel.getStyleClass().add("headerlabel");
		genHashTextArea.getStyleClass().add("textarea");
	}

	@FXML public void handleGenerateButtonAction() {	
		String hashType = hashComboBox.getValue();
		
		HashGenerator hgen = new HashGenerator(hashType);
		String fileHashText;
		try {
			fileHashText = hgen.generateHash(selectedFile);
			genHashTextArea.setText(fileHashText);
		} catch (IOException e) {
			//TODO: show error on status bar
			e.printStackTrace();
		}
		
		log.info("Selected Hash Type: "+hashType);
	}
	
	
	
}
