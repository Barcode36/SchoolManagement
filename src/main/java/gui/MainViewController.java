package gui;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.Consumer;

import com.jfoenix.controls.JFXButton;

import gui.util.Alerts;
import gui.util.FXMLPath;
import gui.util.Roots;
import gui.util.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.entites.Collaborator;
import sharedData.Globe;

public class MainViewController implements Initializable {

	@FXML private ScrollPane content;
	@FXML private Label labelCurrentUser;
	@FXML private JFXButton btnFullScreen;
	@FXML private ImageView imageFullScreen;

	@Override
	public void initialize(URL url, ResourceBundle resource) {
		// Put this controller inside Globe to allow to share this controller
		Globe.getGlobe().putItem("mainViewController", this);;
		// Make the scrollPane content fit the whole space
		this.content.setFitToHeight(true);
		this.content.setFitToWidth(true);
		// Set the name of currentUser to label
		Collaborator currentUser =  Globe.getGlobe().getItem(Collaborator.class, "currentUser");
		updateLabelCurrentUser(currentUser.getName());
		// Defines main menu as content
		setContent(FXMLPath.MAIN_MENU, x -> {});
	}
	
	public void updateLabelCurrentUser(String text) {
		this.labelCurrentUser.setText(text);
	}
	
	public void handleBtnHome(ActionEvent event) {
		Roots.home();
	}
	
	public void handleBtnStudents(ActionEvent event) {
		Roots.listStudents();
	}
	
	public void handleBtnChangeUser(ActionEvent event) {
		// Close this stage and show login form
		Utils.currentStage(event).close();
		Roots.loginForm(this);
	}
	
	public void handleBtnChangeFullScreen(ActionEvent event) {
		Stage currentStage = Utils.currentStage(event);
		if(currentStage.isFullScreen()) {
			imageFullScreen.setImage(new Image("/png_icons/expand_white.png"));
			currentStage.setFullScreen(false);
		} else {
			imageFullScreen.setImage(new Image("/png_icons/compress_white.png"));
			currentStage.setFullScreen(true);
		}
	}
	
	public void handleBtnExit(ActionEvent event) {
		// Confirmation message to exit
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Sair");
		alert.setHeaderText("Clique em OK para encerrar o sistema");
		alert.initOwner(Utils.currentStage(event));
		Optional<ButtonType> result =alert.showAndWait();
		if (result.isPresent() && result.get() == ButtonType.OK) {
			Utils.currentStage(event).close();			
		}
	}
	
	// Set content to scrollPane
	public <T> void setContent(String path, Consumer<T> initializingAction) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
			ScrollPane newContent = loader.load();
			this.content.setContent(newContent.getContent());
			this.content.setStyle(newContent.getStyle());
			T controller = loader.getController();
			initializingAction.accept(controller);
		} catch (IOException e) {
			e.printStackTrace();
			Alerts.showAlert("IOException", "Erro ao exibir tela", e.getMessage(),AlertType.ERROR, null);
		} catch (IllegalStateException e) {
			e.printStackTrace();
			Alerts.showAlert("IllegalStateException", "Erro ao exibir tela", e.getMessage(), AlertType.ERROR, null);
		}
	}
	
}
