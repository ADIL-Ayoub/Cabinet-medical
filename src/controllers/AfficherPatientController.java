package controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import connecte.DbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import models.PatientModel;

public class AfficherPatientController implements Initializable {

	@FXML
    private TableView<PatientModel> table;
	
	@FXML
    private TableColumn<PatientModel, Integer> id_patient;
	
	@FXML
    private TableColumn<PatientModel, String> nom;

    @FXML
    private TableColumn<PatientModel, String> prenom;

	@FXML
    private TableColumn<PatientModel, String> cin;

    @FXML
    private TableColumn<PatientModel, String> tel;
	
    @FXML
    private TableColumn<PatientModel, Date> dateN;

    @FXML
    private TableColumn<PatientModel, String> sexe;
	
    public ObservableList<PatientModel> data = FXCollections.observableArrayList();
    
    private Parent fxml;
    @FXML
    private AnchorPane root;
	
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
    void modifier() {
    	try {
			fxml = FXMLLoader.load(getClass().getResource("/vue/ModifierPatient.fxml"));
			root.getChildren().removeAll();
			root.getChildren().setAll(fxml);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			Connection con = DbConnection.connect();
			String sql = "SELECT * FROM patient";
			PreparedStatement stat = con.prepareStatement(sql);
			ResultSet rs = stat.executeQuery();
			while(rs.next()) {
				data.add(new PatientModel(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getDate(6),rs.getString(7)));
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}	
		id_patient.setCellValueFactory(new PropertyValueFactory<PatientModel,Integer>("id_patient"));
		nom.setCellValueFactory(new PropertyValueFactory<PatientModel,String>("nom"));
		prenom.setCellValueFactory(new PropertyValueFactory<PatientModel,String>("prenom"));
		cin.setCellValueFactory(new PropertyValueFactory<PatientModel,String>("cin"));
		tel.setCellValueFactory(new PropertyValueFactory<PatientModel,String>("tel"));
		dateN.setCellValueFactory(new PropertyValueFactory<PatientModel,Date>("dateN"));
		sexe.setCellValueFactory(new PropertyValueFactory<PatientModel,String>("sexe"));
		table.setItems(data);
}

}
