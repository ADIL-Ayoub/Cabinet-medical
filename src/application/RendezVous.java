package application;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;

public class RendezVous {

	public int idRDVC;
	private int idPatientC;
	private String dateC;
	private String heureC;
	
	public RendezVous(int i, int p, String d, String h) {
		this.idRDVC=i;
		this.idPatientC=p;
		this.dateC=d;
		this.heureC=h;
	}

	public int getIdRDVC() {
		return idRDVC;
	}

	public int getIdPatientC() {
		return idPatientC;
	}

	public String getDateC() {
		return dateC;
	}

	public String getHeureC() {
		return heureC;
	}
	
}
