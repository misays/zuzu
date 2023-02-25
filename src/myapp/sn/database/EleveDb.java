package myapp.sn.database;
import myapp.sn.ui.dashbord1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.mysql.cj.xdevapi.Table;
import javax.swing.*;

public class EleveDb {

    public void insert(String nom, String prenom, String date_naissance,String classe, String sexe) throws Exception {
        try {
            Connection connection = DbManager.getConnection();
            String query = "INSERT INTO eleve (nom, prenom, classe, naissance, sexe) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, nom);
            ps.setString(2, prenom);
            ps.setString(3, classe);
            ps.setString(4, date_naissance);
            ps.setString(5, sexe);
            ps.executeUpdate();
            ps.close();
            connection.close();
        } catch (Exception e) {
            throw new Exception("Error : impossible d'inserer l'eleve dans la base de donnees !");
        }
    }
	public void update(String id,String nom, String prenom, String date_naissance,String classe, String sexe) throws Exception {
		try {
			Connection connection = DbManager.getConnection();
			String query = "UPDATE eleve SET nom = ?, prenom = ?, classe = ?, naissance = ?, sexe = ? WHERE id_eleve = ?";
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, nom);
			ps.setString(2, prenom);
			ps.setString(3, classe);
			ps.setString(4, date_naissance);
			ps.setString(5, sexe);
			ps.setString(6, id);

			ps.executeUpdate();
			//ps.close();
			//connection.close();
		} catch (Exception e) {
			throw new Exception("Error : impossible d'inserer l'eleve dans la base de donnees !");
		}
	}
	public void delete(String id) throws Exception {
		try {
			Connection connection = DbManager.getConnection();
			String query = "DELETE FROM eleve WHERE id_eleve = ?";
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, id);
			ps.executeUpdate();
			//ps.close();
			//connection.close();
		} catch (Exception e) {
			throw new Exception("Error : impossible d'inserer l'eleve dans la base de donnees !");
		}
	}
	/* 
    try {
        Connection connection = DbManager.getConnection();
        String query = "INSERT INTO eleve (nom, prenom, classe, date_naissance, sexe) VALUES (?, ?, ?, ?)";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, nom);
        ps.setString(2, prenom);
        ps.setString(3, date_naissance);
        ps.setString(4, sexe);
        ps.executeUpdate();
       // ps.close();
        //connection.close();
    } catch (Exception e) {
        throw new Exception("Error : impossible d'inserer l'eleve dans la base de donnees !");
        
        
 }
 */
    
    

// recupere les donnees de la table eleve
    public TableModel getEleve() {
    	try {
    		Connection connection = DbManager.getConnection();
    		String query = "SELECT * FROM eleve";
    		PreparedStatement ps = connection.prepareStatement(query);
    		ps.executeQuery();
    		//ps.close();
    		//connection.close();
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	return null;
    }
    
    
	

   


    
}



