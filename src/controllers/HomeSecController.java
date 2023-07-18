package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class HomeSecController implements Initializable{
	@FXML
    private AnchorPane pane;
	@FXML
    private Parent fxml;
	@FXML
    void openHome() {
    	pane.getScene().getWindow().hide();
    	Stage home = new Stage();
    	try {
			fxml = FXMLLoader.load(getClass().getResource("/vue/Home.fxml"));
			Scene scene = new Scene(fxml);
			home.setScene(scene);
			home.setTitle("Patient");
			home.getIcons().add(new Image("C:\\Users\\asus\\eclipse-workspace\\CabinetMedical\\src\\Icons\\transparent-heart-icon-health-care-icon-medical-services-icon-5fcbd37a03aac1.974566991607193466015.png"));
			home.setResizable(false);
			home.show();
			} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	
	public void toExit() {
		 Alert alert = new Alert(AlertType.CONFIRMATION);
	      alert.setTitle("Quitter");
	      alert.setHeaderText("");
	      alert.setGraphic(null);
	      alert.setContentText("Voulez vous vraiment quitter?");	      
	      //alert.show();
	      if (alert.showAndWait().get() == ButtonType.OK) {
	    	  Parent root;
		  		Stage stage;
		  		pane.setVisible(false);
		  		stage=(Stage)pane.getScene().getWindow();
		  		stage.close();
		  		try {
		  			root = FXMLLoader.load(getClass().getResource("/Vue/Connection.fxml"));
		  			Scene scene = new Scene(root);
		  			stage.setScene(scene);
		  			stage.setTitle("Connection");
		  			stage.getIcons().add(new Image("C:\\Users\\asus\\eclipse-workspace\\CabinetMedical\\src\\Icons\\transparent-heart-icon-health-care-icon-medical-services-icon-5fcbd37a03aac1.974566991607193466015.png"));
					stage.setResizable(false);
		  			stage.show();
		  		} catch (Exception e) {
		  			// TODO Auto-generated catch block
		  			e.printStackTrace();
		  		}
	      } 
	}

	
	
	public void toPatient() {
		Parent root;
		Stage stage;
		pane.setVisible(false);
		stage=(Stage)pane.getScene().getWindow();
		stage.close();
		try {
				root = FXMLLoader.load(getClass().getResource("/Vue/Home.fxml"));
				Scene scene = new Scene(root);
				stage.setScene(scene);
				stage.setTitle("Patient");
				stage.getIcons().add(new Image("C:\\Users\\asus\\eclipse-workspace\\CabinetMedical\\src\\Icons\\transparent-heart-icon-health-care-icon-medical-services-icon-5fcbd37a03aac1.974566991607193466015.png"));
				stage.setResizable(false);
				stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void toRDV() {
		
		Parent root;
		Stage stage;
		pane.setVisible(false);
		stage=(Stage)pane.getScene().getWindow();
		try {
			//stage.close();
			root = FXMLLoader.load(getClass().getResource("/Vue/RendezVous.fxml"));
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.setTitle("Rendez-Vous");
			stage.getIcons().add(new Image("C:\\Users\\asus\\eclipse-workspace\\CabinetMedical\\src\\Icons\\transparent-heart-icon-health-care-icon-medical-services-icon-5fcbd37a03aac1.974566991607193466015.png"));
			stage.setResizable(false);
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void toStatistics() {
		
		Parent root;
		Stage stage;
		pane.setVisible(false);
		stage=(Stage)pane.getScene().getWindow();
		try {
			//stage.close();
			root = FXMLLoader.load(getClass().getResource("/Vue/Main.fxml"));
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.setTitle("Statistics");
			stage.getIcons().add(new Image("C:\\Users\\asus\\eclipse-workspace\\CabinetMedical\\src\\Icons\\transparent-heart-icon-health-care-icon-medical-services-icon-5fcbd37a03aac1.974566991607193466015.png"));
			stage.setResizable(false);
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
}
