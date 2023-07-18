package application;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DatePicker;

/*
 * rdv:
 * insert :ok
 * delete :ok
 * 
 * visite :
 * insert: ok
 * update: ok
 * delete: ok
 * 
 * 
 * for selectadmin() we will check the admin username given in the textfield("admin1") and check if it exists with
 * DB.selectAdmin().get("admin1")
 */

public class DB {
	
	public static  boolean isAdmin=false;
	
	public static Connection connect() {
		String url="jdbc:mysql://localhost:3307/cabinetmedical";
		String username="root";
		String pw="root";
		Connection con=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con= DriverManager.getConnection(url,username,pw);			
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(" sql non Reussite");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("class not found non Reussite");
		}
		return con;
	}
	///////////////////////////////////////////////////////
	//ADMIN

	public static HashMap<String,String> selectAdmin(){
		HashMap<String, String> list= new HashMap<String,String>();
		Connection con = connect();
		Statement statement=null;
		ResultSet rs=null;
		String query="Select Username,Password_ from admin;";
		try {
			statement= con.createStatement();
			rs= statement.executeQuery(query);
			while(rs.next()) {
				String key=rs.getString("Username");
				String value=rs.getString("Password_");
				list.put(key,value);
				return list;
			}
			
		}catch(Exception e) {
			System.out.println("selectAdmin problem!");
		}
		finally {
			try {
				rs.close();
				statement.close();
				con.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("selectAdmin problem!");
			}	
		}
		return list;
	}
	
	public static boolean updateAdmin(String newUsername,String newPassword) {
		Connection con=null;
		Statement statement=null;
		try {
			con=DB.connect();
			statement= con.createStatement();
			return !statement.execute("update admin set Username='"+newUsername+"',Password_='"+newPassword+"';");
		}catch(Exception e) {
			System.out.println("updateAdmin Exception!");
		}
		finally {
			try {
				statement.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("closing problem!");
			}
		}
		return false;
	}
	////////////////////////////////////////////////////////////////////////////////
	//User
	
	public static HashMap<String,String> selectSecretaire(){
		HashMap<String,String> list=new HashMap<String,String>();
		Connection con = connect();
		Statement statement=null;
		ResultSet rs=null;
		String query="Select Username,Password_ from utilisateur order by Username";
		try {
			statement= con.createStatement();
			rs= statement.executeQuery(query);
			while(rs.next()) {
				list.put(rs.getString("Username"),rs.getString("Password_"));				
			}
			
		}catch(Exception e) {
			System.out.println("selectUser Problem");
		}
		finally {
			try {
				rs.close();
				statement.close();
				con.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("selectUser Problem");
			}	
		}
		return list;
	}	
	
	public static HashMap<Integer,String> selectUser(){
		HashMap<Integer,String> list=new HashMap<Integer,String>();
		Connection con = connect();
		Statement statement=null;
		ResultSet rs=null;
		String query="Select ID_User,Username from utilisateur order by Username";
		try {
			statement= con.createStatement();
			rs= statement.executeQuery(query);
			while(rs.next()) {
				list.put(rs.getInt("ID_User"),rs.getString("Username"));				
			}
			
		}catch(Exception e) {
			System.out.println("selectUser Problem");
		}
		finally {
			try {
				rs.close();
				statement.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("selectUser Problem");
			}	
		}
		return list;
	}	
	
	public static ObservableList<String> selectUserUsername(){
		ObservableList<String> list=FXCollections.observableArrayList();
		Connection con = connect();
		Statement statement=null;
		ResultSet rs=null;
		String query="Select Username from utilisateur order by Username";
		try {
			statement= con.createStatement();
			rs= statement.executeQuery(query);
			while(rs.next()) {
				list.add(rs.getString("Username"));				
			}
			
		}catch(Exception e) {
			System.out.println("selectUser username Problem");
		}
		finally {
			try {
				rs.close();
				statement.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("selectUser username Problem");
			}	
		}
		return list;
	}	
	
	public static boolean updateUser(int id_user,String newUsername,String newPassword) {
		Connection con=null;
		Statement statement=null;
		try {
			con=DB.connect();
			statement= con.createStatement();
			return !statement.execute("update utilisateur set Username='"+newUsername+"',Password_='"+newPassword+"' where ID_User="+id_user+";");
		}catch(Exception e) {
			System.out.println("updateUSer Exception!");
		}
		finally {
			try {
				statement.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("udpateUser problem!");
			}
		}
		return false;
	}
	
	//////////////////////////////////////////////////////////////////////////////
	////Patient
	public static boolean insertPatient( String Nom,String Prenom, String CIN,String Tel) {
		Connection con=null;
		Statement statement=null;
		try {
			con=DB.connect();
			statement= con.createStatement();
			return !statement.execute("insert into patient (Nom,Prenom,CIN,Tel) values ('"+Nom+"','"+Prenom+"','"+CIN+"','"+Tel+"');");
		}catch(Exception e) {
			System.out.println("Exception!");
		}
		finally {
			try {
				statement.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("closing problem!");
			}
		}
		return false;
	}
	
	public static ObservableList<String> selectPatient(){
		ObservableList<String> list=FXCollections.observableArrayList();
		Connection con = connect();
		Statement statement=null;
		ResultSet rs=null;
		String query="Select ID_Patient, Nom,Prenom from patient order by Nom,Prenom;";
		try {
			statement= con.createStatement();
			rs= statement.executeQuery(query);
			while(rs.next()) {
				String item=rs.getInt("ID_Patient")+": "+rs.getString("Nom")+" "+rs.getString("Prenom");
				list.add(item);				
			}
			
		}catch(Exception e) {
			
		}
		finally {
			try {
				rs.close();
				statement.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
		return list;
	}	
//////////////////////////////////////////////////////////////////////////////
	//RDV
	public static ObservableList<RendezVous> selectRDV(){
		ObservableList<RendezVous> list=FXCollections.observableArrayList();
		Connection con = connect();
		Statement statement=null;
		ResultSet rs=null;
		String query="Select * from rendezvous;";
		try {
			statement= con.createStatement();
			rs= statement.executeQuery(query);
			while(rs.next()) {
				RendezVous temp=new RendezVous(rs.getInt("ID_RDV"),rs.getInt("ID_Patient"),rs.getString("Date_RDV"),rs.getString("heure_RDV"));
				list.add(temp);
			}	
		}catch(Exception e) {
			System.out.println("selectRDV problem!");
		}
		finally {
			try {
				rs.close();
				statement.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
		return list;
	}	
	public static boolean insertRDV(int idPatient,String dateRDV, String heureRDV) {
		Connection con=null;
		Statement statement=null;
		try {
			con=DB.connect();
			statement= con.createStatement();
			if(!DB.isReserved(dateRDV, heureRDV)) {
				return !statement.execute("insert into rendezvous (ID_Patient,Date_RDV,heure_RDV) values ("+idPatient+",'"+dateRDV+"','"+heureRDV+"');");
			}
			else {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Attention");
				alert.setHeaderText("");
				alert.setGraphic(null);
				alert.setContentText("Erreur lors de l'insertion ou RDV deja reservé");
				alert.showAndWait();
			}
		}catch(Exception e) {
			System.out.println("Exception!");
		}
		finally {
			try {
				statement.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("closing problem!");
			}
		}
		return false;
	}
	public static boolean deleteRDV(int idRDV) {
		Connection con=null;
		Statement statement=null;
		try {
			con=DB.connect();
			statement=con.createStatement();
			return !statement.execute("delete from rendezvous where ID_RDV="+idRDV+" limit 1;");
		}catch(Exception e) {
			
		}
		finally {
			try {
				statement.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("closing problem!");
			}
		}
		return false;
	}
	public static ArrayList<String> selectHourRDV(LocalDate hourRDV) {
		ArrayList<String> list= new ArrayList();
		Connection con = connect();
		Statement statement=null;
		ResultSet rs=null;
		try {
			String query="Select heure_RDV from rendezvous where Date_RDV='"+hourRDV+"';";
			statement= con.createStatement();
			rs= statement.executeQuery(query);
			while(rs.next()) {
				list.add(rs.getString("heure_RDV"));
			}	
		}catch(Exception e) {
			System.out.println("SelectRDV problem!");
		}
		finally {
			try {
				rs.close();
				statement.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("selectHourRDV problem");
			}	
		}
		return list;
	}
	public static boolean isReserved(String dateRDV, String heureRDV) {
		ArrayList<String> list= new ArrayList();
		Connection con = connect();
		Statement statement=null;
		ResultSet rs=null;
		String query="Select Date_RDV ,heure_RDV from rendezvous where Date_RDV='"+dateRDV+"'and heure_RDV='"+heureRDV+"';";
		try {
			statement= con.createStatement();
			rs= statement.executeQuery(query);
			if(rs.next()) {
				return true;
			}
			else return false;
			
		}catch(Exception e) {
			System.out.println("isReserved problem!");
		}
		finally {
			try {
				rs.close();
				statement.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
		return true;
	}
//////////////////////////////////////////////////////////////////////////////
	//Visite
	
	public static boolean insertVisite(int idPatient, String dateVisite, String examen, String conclusion) {
		
		Connection con=null;
		Statement statement=null;
		try {
		String query="insert into visite(ID_Patient,Date_Visite,Examen,Conclusion) values("
		+idPatient+",'"+dateVisite+"','"+examen+"','"+conclusion+"');";	
			con= DB.connect();
			statement=con.createStatement();
			return !statement.execute(query);
		}catch(Exception e) {
			System.out.println("insertVisite Exception!");
		}finally {
			try {
				statement.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}
	public static boolean deleteVisite(int idVisite) {
		Connection con=null;
		Statement statement=null;
		String query="delete from visite where ID_Visite="+idVisite+";";
		try {
			con= DB.connect();
			statement=con.createStatement();
			return !statement.execute(query);
		}catch(Exception e) {
			System.out.println("Exception!");
		}finally {
			try {
				statement.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}
	public static boolean modifyVisite(int idVisite,int idPatient,String dateVisite, String examan, String conclusion ) {
		Connection con=null;
		Statement statement=null;
		String query="update visite set ID_Patient="+idPatient+",Date_Visite='"+dateVisite+"',Examen='"+examan+"',Conclusion='"+conclusion+"'where ID_Visite="+idVisite+";";
		try {
			con= DB.connect();
			statement=con.createStatement();
			return !statement.execute(query);
		}catch(Exception e) {
			System.out.println("Exception!");
		}finally {
			try {
				statement.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}
	/////////////////////////////////////////////////////////////////
	//Visite
	
	public static ObservableList<Visite> selectVisite(){
		ObservableList<Visite> list=FXCollections.observableArrayList();
		Connection con = connect();
		Statement statement=null;
		ResultSet rs=null;
		String query="Select * from visite;";
		try {
			statement= con.createStatement();
			rs= statement.executeQuery(query);
			while(rs.next()) {
				Visite temp=new Visite(rs.getInt("ID_Visite"),rs.getInt("ID_Patient"),rs.getString("Date_Visite"),rs.getString("Examen"),rs.getString("Conclusion"));
				list.add(temp);
			}	
		}catch(Exception e) {
			System.out.println("selectVisite problem!");
		}
		finally {
			try {
				rs.close();
				statement.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
		return list;
	}	
	
	public static int countVisite(int month, int year) {
		int count=0;
		Connection con = connect();
		Statement statement=null;
		ResultSet rs=null;
		try {
			String query;
			if(month>9)
				query="SELECT count(*)  FROM `visite` WHERE Date_Visite LIKE '"+year+"-"+month+"-%' limit 1;";
			else
				query="SELECT count(*)  FROM `visite` WHERE Date_Visite LIKE '"+year+"-0"+month+"-%' limit 1;";
			statement= con.createStatement();
			rs= statement.executeQuery(query);
			rs.next();
				count=rs.getInt(1);	
		}catch(Exception e) {
			System.out.println("Count visite problem!");
		}
		finally {
			try {
				rs.close();
				statement.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("CountVisite problem");
			}	
		}
		return count;
	}
	
	public static int countPatient() {
		int count=0;
		Connection con = connect();
		Statement statement=null;
		ResultSet rs=null;
		try {
			String query;
			query="SELECT count(*)  FROM `patient` limit 1;";
			statement= con.createStatement();
			rs= statement.executeQuery(query);
			rs.next();
			count=rs.getInt(1);
		}catch(Exception e) {
			System.out.println("Count patient problem!");
		}
		finally {
			try {
				rs.close();
				statement.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("CountPatient problem");
			}	
		}
		return count;
	}
	
	public static int countPatient(String genre) {
		int count=0;
		Connection con = connect();
		Statement statement=null;
		ResultSet rs=null;
		try {
			String query;
			query="SELECT count(*)  FROM patient where sexe='"+genre+"' limit 1;";
			statement= con.createStatement();
			rs= statement.executeQuery(query);
			rs.next();
			count=rs.getInt(1);
		}catch(Exception e) {
			System.out.println("Count patient problem!");
		}
		finally {
			try {
				rs.close();
				statement.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("CountPatient problem");
			}	
		}
		return count;
	}
	
	
	
	public static void  main (String[] args) throws ParseException {
		//Date date=new Date();
		//System.out.println("this is:" +DB.insertPatient( "hamza","samih","BA123","0654321"));
		//System.out.println("this is "+ DB.insertRDV( 2,"2022-11-08","09H30"));
		//System.out.println("this is "+ DB.insertRDV( 2,"2022-11-08","10H30"));
		//System.out.println("this is: "+DB.deleteRDV(4));
		//System.out.println("this is :"+ DB.insertVisite(1, "2022-11-08", "headache", "traitement"));
		//System.out.println("this is :"+ DB.deleteVisite(1));
		//System.out.println("this is: "+DB.modifyVisite(2, 1, "2022-11-15", "other thing", "serious"));
		//DB.selectHourRDV("2022-11-08");
		//DB.selectAdmin();
		//System.out.println(DB.selectAdmin().get("zfzfzf"));
		//System.out.println("this is: "+DB.updateAdmin("admin","admin"));
		//for (String s :DB.selectUser())
		//	System.out.println(DB.selectUser());
		//	System.out.println(countVisite(11,2022));
		//	System.out.println(DB.selectAdmin());
		//System.out.println(DB.countPatient("femme"));
		System.out.println(DB.selectUserUsername());
	}
}
