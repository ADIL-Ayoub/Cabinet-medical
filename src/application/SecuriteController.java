package application;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.Set;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class SecuriteController implements Initializable {
	@FXML
	Pane adminPane;
	@FXML
	Pane userPane;
	@FXML
	Button adminButton;
	@FXML
	Button userButton;
	@FXML
	Button acceuilButton;
	

	@FXML
	TextField adminUsername;
	@FXML
	TextField adminPassword;
	@FXML
	TextField userUsername;
	@FXML
	TextField newUserUsername;
	@FXML
	TextField newUserPassword;
	@FXML
	Button validateUserButton;
	@FXML
	Label errorUserLabel;
	/////////////AdminPage
	@FXML
	TextField adminUsernameA;
	@FXML
	TextField adminPasswordA;
	@FXML
	Button updateAdminButton;
	@FXML
	TextField newAdminUsername;
	@FXML
	TextField newAdminPassword;
	@FXML
	Label errorAdminLabel;
	@FXML
	ComboBox<String> listUsers;
	
	public void adminPage(ActionEvent e) {
		userPane.setVisible(false);
		adminPane.setVisible(true);
	}
	
	public void userPage(ActionEvent e) {
		userPane.setVisible(true);
		adminPane.setVisible(false);
	}
	
	public void entred(Event e){
		((Button)e.getSource()).setScaleX(1.1);
		((Button)e.getSource()).setScaleY(1.1);
		
	}
	
	public void exited(Event e){
		((Button)e.getSource()).setScaleX(1);
		((Button)e.getSource()).setScaleY(1);
		
	}
	
	@FXML
	public void toHome() {
		Parent root;
		Stage stage;
		adminPane.setVisible(false);
		userPane.setVisible(false);
		stage=(Stage)adminPane.getScene().getWindow();
		try {
			stage.close();
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
	
	
	public void updateUserButton(ActionEvent e){
		HashMap<String,String> adminData=DB.selectAdmin();
		HashMap<Integer,String>list=DB.selectUser();
		String adminusername=adminUsername.getText().trim();
		String Userusername=listUsers.getSelectionModel().getSelectedItem();
		if(adminData.get(adminusername)!=null && adminData.get(adminusername).equals(adminPassword.getText().toString())) {
			if(list.containsValue(Userusername)) {
				if(!newUserUsername.getText().trim().isEmpty() && !newUserPassword.getText().toString().isEmpty()) {
					for(int i:list.keySet()) {
						if(list.get(i).equals(Userusername)) {
							DB.updateUser(i, newUserUsername.getText().trim(), newUserPassword.getText().toString());
							errorUserLabel.setVisible(true);
							errorUserLabel.setTextFill(Color.GREEN);
							errorUserLabel.setText("Mise à jour!");
							ObservableList<String> list2=FXCollections.observableArrayList();
							list2=DB.selectUserUsername();
							listUsers.setItems(list2);
							break;
						}
					}
				}else {
					errorUserLabel.setVisible(true);
					errorUserLabel.setTextFill(Color.RED);
					errorUserLabel.setText("Veuillez remplir tous les champs!");
				}
			}
			else {
				errorUserLabel.setVisible(true);
				errorUserLabel.setTextFill(Color.RED);
				errorUserLabel.setText("Nom d'utilisateur inexistant!");
			}
		}else {
			errorUserLabel.setVisible(true);
			errorUserLabel.setTextFill(Color.RED);
			errorUserLabel.setText("Nom d'utilisateur ou mot de passe incorrecte!");
		}
	}
	
	public void updateAdminButton(ActionEvent e){
		try {
			HashMap<String,String> adminData=DB.selectAdmin();
			String username=adminUsernameA.getText().trim().toString();
			if(adminData.get(username)!=null && adminData.get(username).equals(adminPasswordA.getText().toString())) {
				if(!newAdminUsername.getText().trim().isEmpty() && !newAdminPassword.getText().isEmpty()) {
					DB.updateAdmin(newAdminUsername.getText().trim(),newAdminPassword.getText().toString());
					errorAdminLabel.setVisible(true);
					errorAdminLabel.setTextFill(Color.GREEN);
					errorAdminLabel.setText("Mise à jour!");
				}else {
					errorAdminLabel.setVisible(true);
					errorAdminLabel.setTextFill(Color.RED);
					errorAdminLabel.setText("Veuillez remplir tous les champs!");
				}
			}else {
				errorAdminLabel.setVisible(true);
				errorAdminLabel.setTextFill(Color.RED);
				errorAdminLabel.setText("Nom d'administrateur ou mot de passe incorrecte!");
			}
		}catch(Exception e1) {
			System.out.println("updateAdmin problem");
		}
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ObservableList<String> list=FXCollections.observableArrayList();
		list=DB.selectUserUsername();
		listUsers.setItems(list);
	
	}
}
