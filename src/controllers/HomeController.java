package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.DB;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class HomeController implements Initializable{

	@FXML
	private Parent fxml;
	@FXML
    private AnchorPane root;
	@FXML
	private Pane pane;
	@FXML
	private Button acceuilButton;
	@FXML
	private Button cpButton;
	@FXML
	private Button rpButton;
	@FXML
	private Button apButton;
	
	
	
	@FXML
	public void toHome() {
		Parent root;
		Stage stage;
		pane.setVisible(false);
		stage=(Stage)pane.getScene().getWindow();
		stage.close();
		try {
			if (DB.isAdmin)
				root = FXMLLoader.load(getClass().getResource("/Vue/HomeAd.fxml"));
			else
				root = FXMLLoader.load(getClass().getResource("/Vue/HomeSec.fxml"));
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.setTitle("Home");
			stage.getIcons().add(new Image("C:\\Users\\asus\\eclipse-workspace\\CabinetMedical\\src\\Icons\\transparent-heart-icon-health-care-icon-medical-services-icon-5fcbd37a03aac1.974566991607193466015.png"));
			stage.setResizable(false);
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

    @FXML
    void afficher() {
    	try {
			fxml = FXMLLoader.load(getClass().getResource("/vue/AfficherPatient.fxml"));
			root.getChildren().removeAll();
			root.getChildren().setAll(fxml);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void creer() {
    	try {
			fxml = FXMLLoader.load(getClass().getResource("/vue/CreerPatient.fxml"));
			root.getChildren().removeAll();
			root.getChildren().setAll(fxml);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void rechercher() {
    	try {
			fxml = FXMLLoader.load(getClass().getResource("/vue/RechercherPatient.fxml"));
			root.getChildren().removeAll();
			root.getChildren().setAll(fxml);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
	public void entred(Event e){
		((Button)e.getSource()).setScaleX(1.1);
		((Button)e.getSource()).setScaleY(1.1);
		
	}
	
	public void exited(Event e){
		((Button)e.getSource()).setScaleX(1);
		((Button)e.getSource()).setScaleY(1);
		
	}

	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		try {
			fxml = FXMLLoader.load(getClass().getResource("/vue/CreerPatient.fxml"));
			root.getChildren().removeAll();
			root.getChildren().setAll(fxml);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
