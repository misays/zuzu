package myapp.sn.database;

import java.sql.Connection;
import java.sql.PreparedStatement;

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
    
    //recharger la page pour voir le resultat
    public void refresh() {
    	try {
    		Connection connection = DbManager.getConnection();
    		String query = "SELECT * FROM eleve";
    		PreparedStatement ps = connection.prepareStatement(query);
    		ps.executeQuery();
    		ps.close();
    		connection.close();
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }

    
}



