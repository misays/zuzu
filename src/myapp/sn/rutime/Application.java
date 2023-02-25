package myapp.sn.rutime;

import java.sql.Connection;

import javax.swing.JOptionPane;

import myapp.sn.database.DbManager;
import myapp.sn.ui.*;

public class Application {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		try (Connection connection = DbManager.getConnection()) {
			JOptionPane.showMessageDialog (null, "Connexion à la base OK." );
			} catch (Exception e) {
			JOptionPane.showMessageDialog (null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE );
			}
		
		
			//NoteUi dashbord = new NoteUi();
			//ashbord.afficher();
		
		//ClasseEleve c = new ClasseEleve();
	//c.afficher();
		
		//ListeClasse c = new ListeClasse();
		//c.afficher();
					
		
		
		dashbord1 c = new dashbord1();
		//c.afficher();
		
		//AddUserUi c = new AddUserUi();
		//c.afficher();

		//ConnexionUi c = new ConnexionUi();
		c.afficher();
		//PaymentUi c = new PaymentUi();
		//c.afficher();
		//ReductionUi c = new ReductionUi();
		//c.afficher();	
		//EcheanceUi c = new EcheanceUi();
	//c.afficher();

	}

}
