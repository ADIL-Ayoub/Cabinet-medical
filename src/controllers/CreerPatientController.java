package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import connecte.DbConnectionInfo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import models.PatientModel;
public class CreerPatientController implements Initializable{
	@FXML
    private TextField txtNom;

    @FXML
    private TextField txtPrenom;
	
    @FXML
    private TextField txtCin;

    @FXML
    private DatePicker dateP;

    @FXML
    private TextField txtTel;
    
	@FXML
    private ComboBox<String> cbSexe;
	
	@FXML
    private TextField txtCreation;
	
	private ObservableList<String> list = FXCollections.observableArrayList("M","F");
	
	/*private static Date convertLocalDateToDate(LocalDate localDate){
        ZoneId defaultZoneId = ZoneId.systemDefault();
        return (Date) Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());
    }*/

	@FXML
    void vider(ActionEvent event) {
    	txtNom.clear();
    	txtPrenom.clear();
    	txtCin.clear();
    	txtTel.clear();
    	dateP.setValue(null);
    	cbSexe.setValue(null);
    }
	
    @FXML
    void insertPatient(ActionEvent event) {
    	String nom = txtNom.getText();
    	String prenom = txtPrenom.getText();
    	String cin = txtCin.getText();
    	String tel = txtTel.getText();
    	//Date PatientDN = convertLocalDateToDate(dateP.getValue());
    	
    	//java.sql.Date sqlDate = new java.sql.Date(PatientDN.getTime());
    try {
    	java.sql.Date sqlDate = java.sql.Date.valueOf(dateP.getValue() );
    	
    	PatientModel std = new PatientModel();

        	std.setNom(nom);
        	std.setPrenom(prenom);
        	std.setCin(cin);
    		std.setDateN(sqlDate); 
        	std.setTel(tel);
        	std.setSexe(cbSexe.getValue());
    	
    	

    	//if(!nom.equals("") && !prenom.equals("") && !cin.equals("") && !tel.equals("") && !String.valueOf(sqlDate.getTime()).equals("") && !cbSexe.getValue().equals("")) {

    	if(!nom.equals("") && !prenom.equals("") && !cin.equals("") && !tel.equals("") && !sqlDate.equals("") && !cbSexe.equals("")) {
	    	int status = DbConnectionInfo.save(std);
	    	if(status>0) {
	    		txtCreation.setText("Patient bien ajouter!");
	    		txtCreation.setStyle("-fx-text-fill: green");
	    		txtCreation.setEditable(false);
	    		txtCreation.setBackground(null);

	    	}else {
	    		txtCreation.setText("Patient non ajouter!");
	    		txtCreation.setStyle("-fx-text-fill: red");
	    		txtCreation.setEditable(false);
	    		txtCreation.setBackground(null);

	    	}
	    	vider(event);
    	}else {
    		txtCreation.setText("Veuiller remplir tous les champs!");
    		txtCreation.setStyle("-fx-text-fill: red");
    		txtCreation.setEditable(false);
    		txtCreation.setBackground(null);
    		
    	}
    	}catch(Exception  e) {
    		
    	}
    }
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		dateP.setEditable(false);
		cbSexe.setItems(list);
		
	}

}
