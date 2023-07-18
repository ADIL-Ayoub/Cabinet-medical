package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MainController implements Initializable{

    @FXML
    private LineChart<?,?> lineChart;

    @FXML
    private CategoryAxis x;

    @FXML
    private NumberAxis y;
    
    @FXML
    private ComboBox<Integer> yearsBox;
    
    @FXML 
    private Pane statPane;
    
    @FXML
    private BarChart<?,?> barChart;
    
    public void changeData() {
    	lineChart.getData().clear();
    	try {
    		ArrayList<Integer> years=new ArrayList<>();
    		yearsBox.getItems().addAll(years);
    		XYChart.Series series = new XYChart.Series();
    		for(int i=1;i<13;i++) {
    			series.getData().add(new XYChart.Data<>(String.valueOf(i),DB.countVisite(i, (Integer)yearsBox.getValue())));
    		}
    		lineChart.getData().addAll(series);
    		}catch(Exception e) {
    			System.out.println("MainConroller Error!");
    		}

    }
    
    public void toHome() {
		Parent root;
		Stage stage;
		statPane.setVisible(false);
		stage=(Stage)statPane.getScene().getWindow();
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

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		try {
		ArrayList<Integer> years=new ArrayList<>();
		lineChart.getData().clear();
		for(int i=2022;i<2025;i++)
				years.add(i);
		yearsBox.getItems().addAll(years);
		yearsBox.setValue(2022);
		XYChart.Series series = new XYChart.Series();
		for(int i=1;i<13;i++) {
			series.getData().add(new XYChart.Data<>(String.valueOf(i),DB.countVisite(i, (Integer)yearsBox.getValue())));
		}	
		lineChart.getData().addAll(series);
		lineChart.setLegendVisible(false);
		
		//barCHart
		barChart.getData().clear();
		barChart.setTitle("Nombre total de Patients: "+DB.countPatient());
		XYChart.Series barseries = new XYChart.Series();
		barseries.getData().add(new XYChart.Data<>("M",(Integer)DB.countPatient("M")));
		barseries.getData().add(new XYChart.Data<>("F",(Integer)DB.countPatient("F")));
		barChart.getData().add(barseries);
		barChart.setLegendVisible(false);
		//barChart.
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("MainConroller Error!");
		}
		
	}

	
}
