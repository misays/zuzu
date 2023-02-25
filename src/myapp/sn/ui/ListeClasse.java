package myapp.sn.ui;

import javax.swing.JFrame;
import myapp.sn.database.*;
import myapp.sn.ui.*;

import javax.swing.JButton;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;

public class ListeClasse extends JFrame {
	public ListeClasse () throws Exception {

        // Dimension de la fenêtre
		 setSize(new Dimension(900, 500));

		 // Empêcher le redimensionnement de la fenêtre
		 setResizable(false);
 
		 // Position de la fenêtre au centre de l'écran
		 setLocationRelativeTo(null);
 
		 // Fermer l'application lors de la fermeture de la fenêtre
		 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		 
		
        setSize(new Dimension(900, 500));
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 886, 463);
		getContentPane().add(panel);
		panel.setLayout(null);


		DefaultTableModel df=new DefaultTableModel();
		JTable tb=new JTable();
		JScrollPane scrl=new JScrollPane();
		scrl.setViewportView(tb);
		scrl.setBounds(28, 25, 848, 276);
		df.addColumn("Classe");
		df.addColumn("Effectif");
		df.addColumn("Fille");
        df.addColumn("Garcon");

        
		tb.setModel(df);
		panel.add(scrl);
		
		JButton btnRetour = new JButton("Retour");
        btnRetour.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ClasseEleve classeEleve = new ClasseEleve();
                classeEleve.afficher();
                dispose();
            }
        });
		btnRetour.setBounds(44, 350, 85, 21);
		panel.add(btnRetour);

        try{
			Connection connection = DbManager.getConnection();
			PreparedStatement ps = connection.prepareStatement("SELECT classe, COUNT(*) AS effectif, SUM(sexe='Feminin') AS fille, SUM(sexe='Masculin') AS garcon FROM eleve GROUP BY classe");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                df.addRow(new Object[]{rs.getString("classe"), rs.getString("effectif"), rs.getString("fille"), rs.getString("garcon")});
            }

		}
		catch(SQLException ex){
			ex.printStackTrace();
		}

		JButton btnQuitter = new JButton("Quitter\r\n");
		btnQuitter.setBounds(778, 432, 85, 21);
		panel.add(btnQuitter);
		//quand on clique sur le bouton quitter on a une fenetre de confirmation
		btnQuitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int reponse = JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment quitter?", "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if(reponse == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
			}
		});


	}

   

    

    public void afficher() {
        setVisible(true);
    }


}
