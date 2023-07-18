package application;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;

public class Main extends Application {

	@Override
	public void start(Stage stage) {
			 
		try {		
			Parent root = FXMLLoader.load(getClass().getResource("/Vue/Connection.fxml"));
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.setTitle("Connectez-vous");
			stage.getIcons().add(new Image("C:\\Users\\asus\\eclipse-workspace\\CabinetMedical\\src\\Icons\\transparent-heart-icon-health-care-icon-medical-services-icon-5fcbd37a03aac1.974566991607193466015.png"));
			stage.setResizable(false);
			stage.show();
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Main problem");
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
