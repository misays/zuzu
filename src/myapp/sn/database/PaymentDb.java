package myapp.sn.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import myapp.sn.ui.dashbord1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.mysql.cj.xdevapi.Table;
import javax.swing.*;

public class PaymentDb {

    public void insertPayment(String idEleve,String nomEleve,String montant1,String date1, String recu1, String montant2, String date2,String recu2,String montant3, String date3,String recu3) throws Exception {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DbManager.getConnection();
            String sql = "INSERT INTO payment(idEleve,nomEleve,montant1,date1,recu1,montant2,date2,recu2,montant3,date3,recu3) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
            ps = con.prepareStatement(sql);
            ps.setString(1, idEleve);
            ps.setString(2, nomEleve);
            ps.setString(3, montant1);
            ps.setString(4, date1);
            ps.setString(5, recu1);
            ps.setString(6, montant2);
            ps.setString(7, date2);
            ps.setString(8, recu2);
            ps.setString(9, montant3);
            ps.setString(10, date3);
            ps.setString(11, recu3);

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                ps.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void updatePayment(String id,String idEleve,String nomEleve,String montant1,String date1, String recu1, String montant2, String date2,String recu2,String montant3, String date3,String recu3) throws Exception {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DbManager.getConnection();
            String sql = "UPDATE payment SET idEleve = ?,nomEleve = ?,montant1 = ?,date1 = ?,recu1 = ?,montant2 = ?,date2 = ?,recu2 = ?,montant3 = ?,date3 = ?,recu3 = ? WHERE id = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, idEleve);
            ps.setString(2, nomEleve);
            ps.setString(3, montant1);
            ps.setString(4, date1);
            ps.setString(5, recu1);
            ps.setString(6, montant2);
            ps.setString(7, date2);
            ps.setString(8, recu2);
            ps.setString(9, montant3);
            ps.setString(10, date3);
            ps.setString(11, recu3);
            ps.setString(12, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                ps.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void deletePayment(String id) throws Exception {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DbManager.getConnection();
            String sql = "DELETE FROM payment WHERE id = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                ps.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
