package models;

import java.sql.Date;

public class PatientModel {
	private int id_patient;
	private String nom;
	private String prenom;
	private String cin;
	private String tel;
	private Date dateN;
	private String sexe;

	public PatientModel() {
		super();
	}
	
	public PatientModel(int id_patient, String nom, String prenom, String cin, String tel, Date dateN, String sexe) {
		super();
		this.id_patient=id_patient;
		this.nom=nom;
		this.prenom=prenom;
		this.cin=cin;
		this.tel=tel;
		this.dateN=dateN;
		this.sexe=sexe;
	}

	public int getId_patient() {
		return id_patient;
	}

	public void setId_patient(int id_patient) {
		this.id_patient = id_patient;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getCin() {
		return cin;
	}

	public void setCin(String cin) {
		this.cin = cin;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public Date getDateN() {
		return dateN;
	}

	public void setDateN(Date dateN) {
		this.dateN = dateN;
	}

	public String getSexe() {
		return sexe;
	}

	public void setSexe(String sexe) {
		this.sexe = sexe;
	}
}
