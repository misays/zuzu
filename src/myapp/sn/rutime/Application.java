package myapp.sn.rutime;

import java.sql.Connection;

import javax.swing.JOptionPane;

import myapp.sn.database.DbManager;
import myapp.sn.ui.dashbord1;

public class Application {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try (Connection connection = DbManager.getConnection()) {
			JOptionPane.showMessageDialog (null, "Connexion à la base OK." );
			} catch (Exception e) {
			JOptionPane.showMessageDialog (null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE );
			}
		
		
			dashbord1 dashbord = new dashbord1();
			dashbord.afficher();
		
			

	}

}
