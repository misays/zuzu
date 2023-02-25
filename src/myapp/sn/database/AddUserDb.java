package myapp.sn.database;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.swing.JOptionPane;

public class AddUserDb {

    public void insert( String login, String password, String repassword, String role) throws Exception {
        try {
            Connection connection = DbManager.getConnection();
            String query = "INSERT INTO identifiant (login, password, role) VALUES (?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(query);
            if(password.equals(repassword)){    
            ps.setString(1, login);
            ps.setString(2, password);
            ps.setString(3, role);
            ps.executeUpdate();
            ps.close();
            connection.close();}
            else{
                //throw new Exception("Error : les mots de passe ne sont pas identiques !");
                JOptionPane.showMessageDialog(null, "Error : les mots de passe ne sont pas identiques !", "Error", JOptionPane.ERROR_MESSAGE );
            }
        } catch (Exception e) {
            throw new Exception("Error : impossible d'inserer l'utilisateur dans la base de donnees !");
        }
    }

    public void delete(String id) throws Exception {
        try {
            Connection connection = DbManager.getConnection();
            String query = "DELETE FROM identifiant WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, id);
            ps.executeUpdate();
            ps.close();
            connection.close();
        } catch (Exception e) {
            throw new Exception("Error : impossible de supprimer l'utilisateur dans la base de donnees !");
        }
    }

    public void update(String id, String login, String password, String repassword, String role) throws Exception {
        try {
            Connection connection = DbManager.getConnection();
            String query = "UPDATE identifiant SET login = ?, password = ?, role = ? WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            if(password.equals(repassword)){    
            ps.setString(1, login);
            ps.setString(2, password);
            ps.setString(3, role);
            ps.setString(4, id);
            ps.executeUpdate();
            ps.close();
            connection.close();}
            else{
                
                JOptionPane.showMessageDialog(null, "Error : les mots de passe ne sont pas identiques !", "Error", JOptionPane.ERROR_MESSAGE );
            }
        } catch (Exception e) {
            throw new Exception("Error : impossible de modifier l'utilisateur dans la base de donnees !");
        }
    }

}
