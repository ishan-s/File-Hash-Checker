package app.ishan.jash;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import app.ishan.jash.common.AppLog;
import app.ishan.jash.common.Const;
import app.ishan.jash.common.Const.StatusType;
import app.ishan.jash.hash.HashGenerator;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class JashMainController implements Initializable {
	private Stage primaryStage;
	private Main main;

	private AppLog log = new AppLog();

	@FXML
	TextField chosenFileTextField;
	@FXML
	GridPane rootGridPane;
	@FXML
	GridPane inputGridPane;
	@FXML
	ComboBox<String> hashComboBox;
	@FXML
	Label startLabel;
	@FXML
	Label genHashLabel;
	@FXML
	Label inpHashLabel;
	@FXML
	TextArea genHashTextArea;
	@FXML
	TextArea inpHashTextArea;
	@FXML
	Text statusText;

	private File selectedFile;

	public void initialize(Stage primaryStage, Main main) {
		this.primaryStage = primaryStage;
		this.main = main;

	}

	@FXML
	protected void handleBrowseButtonAction() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Select Input File");
		fileChooser.getExtensionFilters().add(new ExtensionFilter("All Files", "*.*"));
		
		selectedFile = fileChooser.showOpenDialog(primaryStage);
		if(selectedFile==null)
			return;
		
		chosenFileTextField.setText(selectedFile.getAbsolutePath());

		log.info("Selected File: " + selectedFile.getName());
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		hashComboBox.getItems().addAll(Const.SUPPORTED_HASH_TYPES);
		hashComboBox.setValue(Const.SUPPORTED_HASH_TYPES[0]);

		rootGridPane.getStyleClass().add("rootgridpane");
		startLabel.getStyleClass().add("headerlabel");
		genHashLabel.getStyleClass().add("headerlabel");
		inpHashLabel.getStyleClass().add("headerlabel");
		genHashTextArea.getStyleClass().add("textarea");
		
		updateStatus("Generate the hash and compare against an input to see the status here.", StatusType.INFO);
	}

	@FXML
	public void handleGenerateButtonAction() {
		if(selectedFile==null || !selectedFile.exists() || !selectedFile.isFile()){
			updateStatus("File not selected or invalid!", StatusType.FAILURE);
			return;
		}
			
		String hashType = hashComboBox.getValue();
		log.info("Selected Hash Type: " + hashType);

		HashGenerator hgen = new HashGenerator(hashType);
		String fileHashText;
		try {
			fileHashText = hgen.generateHash(selectedFile);
			genHashTextArea.setText(fileHashText);
		} catch (IOException e) {
			// TODO: show error on status bar
			e.printStackTrace();
			updateStatus("Error while generating hash!", StatusType.FAILURE);
		}

	}

	@FXML
	public void handleCopyButtonAction() {
		log.info("Copying generated has to clipboard...");
		String genHashText = genHashTextArea.getText();
		if (!(genHashText == null || "".equals(genHashText.trim()))) {
			final Clipboard clipboard = Clipboard.getSystemClipboard();
			final ClipboardContent content = new ClipboardContent();
			content.putString(genHashText);

			clipboard.setContent(content);
			log.info("Copied " + genHashText + " to clipboard.");
		} else {
			updateStatus("Nothing to copy.", StatusType.WARNING);
			log.warning("No content to be copied. Skipping...");
		}

	}

	@FXML
	public void handleCompareButtonAction() {
		String genHashText = genHashTextArea.getText();
		String inpHashText = inpHashTextArea.getText();

		if ((genHashText == null || "".equals(genHashText.trim()))
				|| (inpHashText == null || "".equals(inpHashText.trim()))) {
			updateStatus("Nothing to compare against.", StatusType.WARNING);
			return;
		} else {
			if (genHashText.equalsIgnoreCase(inpHashText))
				updateStatus("Hashes match.", StatusType.SUCCESS);
			else
				updateStatus("Hashes do not match.", StatusType.FAILURE);
		}
	}

	public void updateStatus(String statusMsg, StatusType statusType) {
		String styleClass = "statustext_default";
		switch (statusType) {
		case SUCCESS:
			styleClass = "statustext_success";
			break;
		case FAILURE:
			styleClass = "statustext_failure";
			break;
		case INFO:
			styleClass = "statustext_info";
			break;
		case WARNING:
			styleClass = "statustext_warning";
			break;
		default:
			styleClass = "statustext_default";
		}

		statusText.getStyleClass().clear();
		statusText.getStyleClass().add(styleClass);
		statusText.setText(statusMsg);
	}

}
