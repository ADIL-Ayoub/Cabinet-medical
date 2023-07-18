package controllers;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.time.LocalDate;
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
import models.PatientModel;

public class ModifierPatientController implements Initializable{

	@FXML
    private TextField txtId;
	
	@FXML
	private TextField txtNom;

	@FXML
	private TextField txtPrenom;
	
	@FXML
    private TextField txtCin;

	@FXML
    private ComboBox<String> cbSexe;

	@FXML
    private TextField txtTel;

    @FXML
    private DatePicker dateP;

    @FXML
    private TextField txtCreation;

	private ObservableList<String> list = FXCollections.observableArrayList("M","F");
    
    @FXML
    void getPatient(ActionEvent e) throws IOException, ParseException {
    	String sid = txtId.getText();
    	int id = Integer.parseInt(sid);
    	PatientModel std = DbConnectionInfo.getPatientId(id);
    	txtNom.setText(std.getNom());
    	txtPrenom.setText(std.getPrenom());
    	txtCin.setText(std.getCin());
    	cbSexe.setValue(std.getSexe());
    	txtTel.setText(std.getTel());
    	//comments here are right methods but it doesnt works
    	//java.sql.Date sqlDate = java.sql.Date.valueOf(dateP.getValue() );
    	//sqlDate.setDate(std.getDateN());
    	/***Convert date thats already in BD to localDate to insert it in our datePicker which take a local date format****/
    	//LocalDate localDate = std.getDateN().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();    
    	LocalDate localDate = std.getDateN().toLocalDate();
    	dateP.setValue(localDate);
    }

    @FXML
    void ModifierPatient() throws IOException{
    	String sid = txtId.getText();
    	int id = Integer.parseInt(sid);
    	String nom = txtNom.getText();
    	String prenom = txtPrenom.getText();
    	String cin = txtCin.getText();
    	String tel = txtTel.getText();
    	String sexe = cbSexe.getValue();
    	LocalDate localDate = dateP.getValue();
    	PatientModel std = new PatientModel();
    	std.setId_patient(id);
    	std.setNom(nom);
    	std.setPrenom(prenom);
    	std.setCin(cin);
    	std.setTel(tel);
    	std.setSexe(sexe);
    	java.sql.Date sqlDate = java.sql.Date.valueOf(localDate);
    	std.setDateN(sqlDate);
    	/**meme methode que creer**/
    	if(!nom.equals("") && !prenom.equals("") && !cin.equals("") && !tel.equals("") && !sqlDate.equals("") && !cbSexe.equals("")) {
	    	int status = DbConnectionInfo.update(std);
	    	if(status>0) {
	    		txtCreation.setText("Patient bien modifier!");
	    		txtCreation.setStyle("-fx-text-fill: green");
	    		txtCreation.setEditable(false);
	    		txtCreation.setBackground(null);
	    	}else {
	    		txtCreation.setText("Patient non modifier!");
	    		txtCreation.setStyle("-fx-text-fill: red");
	    		txtCreation.setEditable(false);
	    		txtCreation.setBackground(null);
	    	}
    	}else {
    		txtCreation.setText("Veuiller remplir tout les champs!");
    		txtCreation.setStyle("-fx-text-fill: red");
    		txtCreation.setEditable(false);
    		txtCreation.setBackground(null);
    	}

    }
    
    @FXML
    void SupprimerPatient(ActionEvent e) throws IOException{
    	String sid = txtId.getText();
    	int id = Integer.parseInt(sid);
    	int status = DbConnectionInfo.delete(id);
    	if(status>0) {
    		txtCreation.setText("Patient bien supprimer!");
    		txtCreation.setStyle("-fx-text-fill: green");
    		txtCreation.setEditable(false);
    		txtCreation.setBackground(null);
    		
    	}else {
    		txtCreation.setText("Patient non supprimer!");
    		txtCreation.setStyle("-fx-text-fill: red");
    		txtCreation.setEditable(false);
    		txtCreation.setBackground(null);
    	}
    }
    
    @FXML
    void vider(ActionEvent e) {
    	txtId.clear();
    	txtNom.clear();
    	txtPrenom.clear();
    	txtCin.clear();
    	txtTel.clear();
    	txtCreation.clear();
    	dateP.setValue(null);
    	cbSexe.setValue(null);
    }
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		cbSexe.setItems(list);
		
	}

}
