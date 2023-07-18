package application;


import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
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
import javafx.stage.Stage;

public class RDVController implements Initializable{

	@FXML
	DatePicker dp;
	@FXML
	ComboBox<String> patientList;
	@FXML
	ListView<String> listView= new ListView();
	@FXML
	Label rdvHour;
	@FXML
	Pane newRDV;
	@FXML
	Pane listRDVPane;
	@FXML
	Button creerRDVButton;
	@FXML
	Label warningLabel;
	//////////////////////////////////Tableau
	@FXML
	private TableView<RendezVous> table;
	@FXML
	private TableColumn<RendezVous,Integer> idRDVC;
	@FXML
	private TableColumn<RendezVous,Integer> idPatientC;
	@FXML
	private TableColumn<RendezVous,String> dateC;
	@FXML
	private TableColumn<RendezVous,String> heureC;
	ObservableList<RendezVous> rdvList=DB.selectRDV();
	@FXML
	Button deleteButton;
	/////////////////////////////////////////////////////////
	int indexOfTableRow=-1;
	
	ObservableList<String> list=FXCollections.observableArrayList();
	ObservableList<String> listOfHours=FXCollections.observableArrayList();
	String[] hours= {"09H","09H30","10H","10H30","11H","11H30","12H","12H30","14H","14H30","15H","15H30","16H","16H30","17H","17H30","18H","18H30"};
	ObservableList<String>buffList=listOfHours;
	public void dp(Event e) {
		LocalDate temp=null;
		try {
			temp=dp.getValue();
		}catch(Exception e1) {
			System.out.println("error here");
		}
		try {
			listOfHours.clear();
			listOfHours.addAll(hours);
			buffList=listOfHours;
			if(!temp.toString().equals(null)) {
				try {
					ArrayList<String> hoursReserved= DB.selectHourRDV(temp);
					if(!hoursReserved.isEmpty()) {
						for(String i:hoursReserved)
							buffList.remove(i);
					}
					listView.setDisable(false);
				}
				catch(Exception e1) {
					System.out.println("invalide date");
				}
			}	
		}catch(Exception e1) {
			System.out.println("date invalide");
		}
		
		//dp.getValue();
	}

	public void entred(Event e){
		((Button)e.getSource()).setScaleX(1.1);
		((Button)e.getSource()).setScaleY(1.1);
		
	}
	
	public void exited(Event e){
		((Button)e.getSource()).setScaleX(1);
		((Button)e.getSource()).setScaleY(1);
		
	}
	public void newRDVButton(ActionEvent e) {
		newRDV.setVisible(true);
		listRDVPane.setVisible(false);
		//deleteRDV.setVisible(false);
		
	}
	public void listRDVButton(ActionEvent e) {
		newRDV.setVisible(false);
		listRDVPane.setVisible(true);
		//deleteRDV.setVisible(false);
		
	}
	@FXML
	public void toHome() {
		Parent root;
		Stage stage;
		newRDV.setVisible(false);
		listRDVPane.setVisible(false);
		stage=(Stage)newRDV.getScene().getWindow();
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
	
	public void deleteRDVButton(ActionEvent e) {
		indexOfTableRow=table.getSelectionModel().getSelectedIndex();
		int idRDVToDelete=-1;
		if(indexOfTableRow!=-1) {
			idRDVToDelete=idRDVC.getCellData(indexOfTableRow);
			DB.deleteRDV(idRDVToDelete);
			table.setItems(DB.selectRDV());
		}
	}
	
	public void newRDV(ActionEvent e) {
		try {
			String patient=patientList.getSelectionModel().getSelectedItem();
			LocalDate dateRDV=dp.getValue();
			String heureRDV=listView.getSelectionModel().getSelectedItem();
			
			if(!heureRDV.equals("") && !patient.equals("") && !dateRDV.toString().equals("")) {
				String temp="";
				int index=patient.trim().indexOf(':');
				for(int i=0; i<index;i++)
					temp+=patient.charAt(i);
				DB.insertRDV(Integer.parseInt(temp), dateRDV.toString(), heureRDV);
				table.setItems(DB.selectRDV());
				warningLabel.setVisible(true);
				warningLabel.setText("Rendez vous est réservé");
				warningLabel.setStyle("-fx-text-fill: green");
				listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>(){
					
					@Override
					public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
						ArrayList<String> hoursReserved= DB.selectHourRDV(dateRDV);
						if(!hoursReserved.isEmpty()) {
							for(String i:hoursReserved)
								buffList.remove(i);
						}
					}
				});
			}
			else System.out.println("you can't");

		}catch(Exception e1) {
			warningLabel.setVisible(true);
			System.out.println("Champs vide");
		}
			}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		dp.setEditable(false);
		
		list=DB.selectPatient();
		listOfHours.addAll(hours);
		patientList.setItems(list);
		listView.setItems(buffList);
		listView.setDisable(true);
		listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>(){
			
			@Override
			public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
				rdvHour.setText(listView.getSelectionModel().getSelectedItem());
			{
		}	
			}
		});
		///////Remplissage du tableau des rendez vous
		idRDVC.setCellValueFactory(new PropertyValueFactory<>("idRDVC"));
		idPatientC.setCellValueFactory(new PropertyValueFactory<>("idPatientC"));
		dateC.setCellValueFactory(new PropertyValueFactory<>("dateC"));
		heureC.setCellValueFactory(new PropertyValueFactory<>("heureC"));
		table.setItems(rdvList);
	}
}
