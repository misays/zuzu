package myapp.sn.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ReductionDb {

    public void update(String id, String reduction) throws Exception {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DbManager.getConnection();

            String sql = "UPDATE payment SET reduction = ? WHERE id = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, reduction);
            ps.setString(2, id);
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
