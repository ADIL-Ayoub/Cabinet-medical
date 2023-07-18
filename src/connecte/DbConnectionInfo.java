package connecte;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import models.PatientModel;

public class DbConnectionInfo {
	private static final String user = "root";
	private static final String password = "root";
	private static final String url = "jdbc:mysql://localhost:3307/cabinetmedical";
	
	public static Connection connect() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url,user,password);
			return conn;
		} catch(Exception e) {
			System.out.println("Probleme de connexion");
		}
		return null;
	}
	
	public static int save(PatientModel std) {
		int st =0;
		try {
			String sql = "INSERT INTO patient(nom,prenom,cin,tel,dateN,sexe) VALUES(?,?,?,?,?,?)";
			Connection con = DbConnectionInfo.connect();
			PreparedStatement stat;
			stat = con.prepareStatement(sql);
			stat.setString(1,std.getNom());
			stat.setString(2,std.getPrenom());
			stat.setString(3,std.getCin());
			stat.setString(4,std.getTel());
			stat.setDate(5,std.getDateN());
			stat.setString(6, std.getSexe());

			st = stat.executeUpdate();
			con.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return st;
	}
	
	public static int update(PatientModel std) {
		int st = 0;
		try {
			String sql = "UPDATE patient SET nom=?, prenom=?, cin=?, tel=?, dateN=?, sexe=? WHERE id_patient=?";
			Connection con = DbConnectionInfo.connect();
			PreparedStatement stat;
			stat = con.prepareStatement(sql);
			stat.setString(1,std.getNom());
			stat.setString(2,std.getPrenom());
			stat.setString(3,std.getCin());
			stat.setString(4,std.getTel());
			stat.setDate(5,std.getDateN());
			stat.setString(6, std.getSexe());
			
			stat.setInt(7, std.getId_patient());
			
			st =stat.executeUpdate();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return st;
	}
	
	public static int delete(int id_patient) {
		int st = 0;
		try {
			String sql = "DELETE FROM patient WHERE id_patient=?";
			Connection con = DbConnectionInfo.connect();
			PreparedStatement stat;
			stat = con.prepareStatement(sql);
			stat.setInt(1, id_patient);
			st = stat.executeUpdate();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return st;
	}
	
	public static PatientModel getPatientId(int id_patient) {
		PatientModel std = new PatientModel();
		try {
			String sql = "SELECT * FROM patient WHERE id_patient=?";
			Connection con = DbConnectionInfo.connect();
			PreparedStatement stat;
			stat = con.prepareStatement(sql);
			stat.setInt(1, id_patient);
			ResultSet rst = stat.executeQuery();
			while(rst.next()) {
				std.setId_patient(rst.getInt(1));
				std.setNom(rst.getString(2));
				std.setPrenom(rst.getString(3));
				std.setCin(rst.getString(4));
				std.setTel(rst.getString(5));
				std.setDateN(rst.getDate(6));
				std.setSexe(rst.getString(7));
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return std;
	}
	
	/*Rechercher patient*/
	/*public static PatientModel getPatientNom(String nom) {
		PatientModel std = new PatientModel();
		try {
			String sql = "SELECT * FROM patient WHERE nom=?";
			Connection con = DbConnectionInfo.connect();
			PreparedStatement stat;
			stat = con.prepareStatement(sql);
			stat.setString(1, nom);
			ResultSet rst = stat.executeQuery();
			if(rst.next()) {
				std.setId_patient(rst.getInt(1));
				std.setNom(rst.getString(2));
				std.setPrenom(rst.getString(3));
				std.setCin(rst.getString(4));
				std.setTel(rst.getString(5));
				std.setDateN(rst.getDate(6));
				std.setSexe(rst.getString(7));
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return std;
	}
	*/
}
