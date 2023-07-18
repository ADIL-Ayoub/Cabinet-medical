package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.DB;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class ConnectionController implements Initializable {

		@FXML private ImageView doct_image;
		@FXML private TextField utilisateur;
		@FXML private PasswordField password;
		@FXML private Label seConnecter_label;
		@FXML private Button connexion_btn;
		@FXML
		private Text textAlert;
		
		@FXML
		private AnchorPane pane;
	    private Parent fxml;

		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			// TODO Auto-generated method stub
			
		}

		public void login() { 
			String username=utilisateur.getText().toString().trim();
			if(DB.selectAdmin().containsKey(username)){
				if(DB.selectAdmin().get(username).equals(password.getText())){
					pane.getScene().getWindow().hide();
			    	Stage home = new Stage();
			    	DB.isAdmin=true;
			    	try {
						fxml = FXMLLoader.load(getClass().getResource("/Vue/HomeAd.fxml"));
						Scene scene = new Scene(fxml);
						home.setScene(scene);
						home.setTitle("Acceuil");
						home.getIcons().add(new Image("C:\\Users\\asus\\eclipse-workspace\\CabinetMedical\\src\\Icons\\transparent-heart-icon-health-care-icon-medical-services-icon-5fcbd37a03aac1.974566991607193466015.png"));
						home.setResizable(false);
						home.show();
			    	} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}			
				}
				else textAlert.setVisible(true);
			}
			else if(DB.selectSecretaire().containsKey(username)){
				if(DB.selectSecretaire().get(username).equals(password.getText())){
					pane.getScene().getWindow().hide();
			    	Stage home = new Stage();
			    	DB.isAdmin=false;
			    	try {
						fxml = FXMLLoader.load(getClass().getResource("/Vue/HomeSec.fxml"));
						Scene scene = new Scene(fxml);
						home.setScene(scene);
						home.setTitle("Acceuil");
						home.getIcons().add(new Image("C:\\Users\\asus\\eclipse-workspace\\CabinetMedical\\src\\Icons\\transparent-heart-icon-health-care-icon-medical-services-icon-5fcbd37a03aac1.974566991607193466015.png"));
						home.setResizable(false);
						home.show();
			    	} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}			
				}
				else textAlert.setVisible(true);
			}
				else {
					textAlert.setVisible(true);
				}
			}
		
		

		

}