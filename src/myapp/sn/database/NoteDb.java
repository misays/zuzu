package myapp.sn.database;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class NoteDb {
    public void insert (String idEleve,String nomEleve, String idMatiere, String noteDevoir, String noteExam) throws Exception {
        try {
            Connection connection = DbManager.getConnection();
            String query = "INSERT INTO note (idEleve, nomEleve, idMatiere, devoir, examen) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, idEleve);
            ps.setString(2, nomEleve);
            ps.setString(3, idMatiere);
            ps.setString(4, noteDevoir);
            ps.setString(5, noteExam);
            ps.executeUpdate();
            ps.close();
            connection.close();
        } catch (Exception e) {
            throw new Exception("Error : impossible d'inserer la note dans la base de donnees !");
        }
    }

    


    public void update (String idNote, String idEleve,String nomEleve, String idMatiere, String noteDevoir, String noteExam) throws Exception {
        try {
            Connection connection = DbManager.getConnection();
            String query = "UPDATE note SET nomEleve = ?, idMatiere = ?, devoir = ?, examen = ?, idEleve = ? WHERE idNote = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, nomEleve);
            ps.setString(2, idMatiere);
            ps.setString(3, noteDevoir);
            ps.setString(4, noteExam);
            ps.setString(5, idEleve);
            ps.setString(6, idNote);
           
            ps.executeUpdate();
            ps.close();
            connection.close();
        } catch (Exception e) {
            throw new Exception("Error : impossible de modifier la note dans la base de donnees !");
        }
    }
    public void delete(String idNote) throws Exception {
        try {
            Connection connection = DbManager.getConnection();
            String query = "DELETE FROM note WHERE idNote = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, idNote);
            ps.executeUpdate();
            ps.close();
            connection.close();
        } catch (Exception e) {
            throw new Exception("Error : impossible de supprimer la note dans la base de donnees !");
        }
    }
    
}
