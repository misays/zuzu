package myapp.sn.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

public class ConnexionDb {

    public void connect(String login, String password, String role) throws Exception {
        try {
            Connection con = DbManager.getConnection();
            String sql = "select * from identifiant where login=? and password=? and role=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, login);
            ps.setString(2, password);
            ps.setString(3, role);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                if (role.equals("Admin")) {
                    // AdminUi admin=new AdminUi();
                    // admin.setVisible(true);
                    JOptionPane.showMessageDialog(null, "Bienvenue Admin");
                } else if (role.equals("secretaire")) {
                    // SecretaireUi secretaire=new SecretaireUi();
                    // secretaire.setVisible(true);
                    JOptionPane.showMessageDialog(null, "Bienvenue secretaire");
                } else if (role.equals("caissier")) {
                    // CaissierUi caissier=new CaissierUi();
                    // caissier.setVisible(true);
                    JOptionPane.showMessageDialog(null, "Bienvenue caissier");
                } else {
                    JOptionPane.showMessageDialog(null, "Error : role incorrect !", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Error : l'identifiant ou le mot de passe est incorrect !", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

}
