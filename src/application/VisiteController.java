package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class VisiteController implements Initializable {

	@FXML
	Button listVisiteButton;
	@FXML
	Button creerVisiteButton;
	@FXML 
	Button acceuilButton;
	@FXML
	Button addNewVisiete;
	@FXML 
	Pane listVisitePane;
	@FXML 
	Pane newVisitePane;
	
	@FXML
	Label examenLabel;
	@FXML
	Label conclusionLabel;
	@FXML 
	Label warningLabel;
	
	///////////////////////////////////////
	//creer visite
	ObservableList<String> list=FXCollections.observableArrayList();
	@FXML 
	ComboBox<String> patientListVisite;
	@FXML
	DatePicker dpVisite;
	@FXML
	TextArea examenArea;
	@FXML
	TextArea conclusionArea;
	
	/////////////////////////////////////////
	//tableau des visites
	@FXML
	private TableView<Visite> tableVisite;
	@FXML
	private TableColumn<Visite,Integer> idVisiteC;
	@FXML
	private TableColumn<Visite,Integer> idPatientC;
	@FXML
	private TableColumn<Visite,String> dateVisiteC;
	@FXML
	private TableColumn<Visite,String> examenC;
	@FXML
	private TableColumn<Visite,String> conclusionC;
	ObservableList<Visite> rdvList=DB.selectVisite();
	
	public void listVisitePage(ActionEvent e) {
		newVisitePane.setVisible(false);
		listVisitePane.setVisible(true);
	}
	
	@FXML
	public void toHome() {
		Parent root;
		Stage stage;
		listVisitePane.setVisible(false);
		newVisitePane.setVisible(false);
		stage=(Stage)listVisitePane.getScene().getWindow();
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
	
	public void newVisite(ActionEvent e) {
		try {
			String patient=patientListVisite.getSelectionModel().getSelectedItem();
			String dateVisite=dpVisite.getValue().toString();
			String examenVisite=examenArea.getText().toString();
			String conclusionVisite=conclusionArea.getText().toString();
			
			if(!dateVisite.equals("") && !patient.equals("") && !examenVisite.equals("") && !conclusionVisite.equals("")) {
				String temp="";
				int index=patient.trim().indexOf(':');
				for(int i=0; i<index;i++)
					temp+=patient.charAt(i);
				System.out.println(temp.trim());
				System.out.println("you can insert this visite!");
				DB.insertVisite(Integer.parseInt(temp),dateVisite, examenVisite, conclusionVisite);
				tableVisite.setItems(DB.selectVisite());
				warningLabel.setVisible(true);
				warningLabel.setText("Visite est creée!");
				warningLabel.setStyle("-fx-text-fill: green");
			}
			else {
				warningLabel.setVisible(true);
				warningLabel.setText("Veuillez remplir tous les champs!");
				warningLabel.setStyle("-fx-text-fill: red");
			}
		}catch(Exception e2) {
			warningLabel.setVisible(true);
			warningLabel.setText("Veuillez remplir tous les champs!");
			warningLabel.setStyle("-fx-text-fill: red");
		}
		
	}
	
	public void deleteVisiteButton(ActionEvent e) {
		int indexOfTableRow=tableVisite.getSelectionModel().getSelectedIndex();
		int idVisiteToDelete=-1;
		if(indexOfTableRow!=-1) {
			idVisiteToDelete=idVisiteC.getCellData(indexOfTableRow);
			DB.deleteVisite(idVisiteToDelete);
			tableVisite.setItems(DB.selectVisite());
		}
	}
	
	public void creerVisitePage(ActionEvent e) {
		newVisitePane.setVisible(true);
		listVisitePane.setVisible(false);
	}
	
	public void entred(Event e){
		((Button)e.getSource()).setScaleX(1.1);
		((Button)e.getSource()).setScaleY(1.1);
		
	}
	
	public void exited(Event e){
		((Button)e.getSource()).setScaleX(1);
		((Button)e.getSource()).setScaleY(1);
		
	}
	
	public void fetchData() {
		int indexOfTableRow=tableVisite.getSelectionModel().getSelectedIndex();
		int idVisiteToDelete=-1;
		if(indexOfTableRow!=-1) {
			examenLabel.setText("Examen : "+examenC.getCellData(indexOfTableRow));
			conclusionLabel.setText("Conclusion : "+conclusionC.getCellData(indexOfTableRow));
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		//initialisation du comboBox
		list=DB.selectPatient();
		patientListVisite.setItems(list);
		dpVisite.setEditable(false);
		
		idVisiteC.setCellValueFactory(new PropertyValueFactory<>("idVisiteC"));
		//System.out.println("ok-(-------------------------------------------");
		idPatientC.setCellValueFactory(new PropertyValueFactory<>("idPatientC"));
		dateVisiteC.setCellValueFactory(new PropertyValueFactory<>("dateVisiteC"));
		examenC.setCellValueFactory(new PropertyValueFactory<>("examenC"));
		conclusionC.setCellValueFactory(new PropertyValueFactory<>("conclusionC"));
		tableVisite.setItems(rdvList);
	}
	
}
