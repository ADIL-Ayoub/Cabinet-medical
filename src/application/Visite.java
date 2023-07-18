package application;

public class Visite {
	public int idVisiteC;
	private int idPatientC;
	private String dateVisiteC;
	private String examenC;
	private String conclusionC;
	
	public Visite(int idVisiteC, int idPatientC, String dateVisiteC, String examenC, String conclusionC) {
		super();
		this.idVisiteC = idVisiteC;
		this.idPatientC = idPatientC;
		this.dateVisiteC = dateVisiteC;
		this.examenC = examenC;
		this.conclusionC = conclusionC;
	}
	
	public int getIdVisiteC() {
		return idVisiteC;
	}
	public int getIdPatientC() {
		return idPatientC;
	}
	public String getDateVisiteC() {
		return dateVisiteC;
	}
	public String getExamenC() {
		return examenC;
	}
	public String getConclusionC() {
		return conclusionC;
	}

	
	
}
