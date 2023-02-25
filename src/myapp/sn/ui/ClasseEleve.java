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

public class ClasseEleve extends JFrame {
	public ClasseEleve() {
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
		df.addColumn("id");
		df.addColumn("Nom");
		df.addColumn("Prenom");
		df.addColumn("Classe");
		df.addColumn("Sexe");
		df.addColumn("Date de naissance");
	
		tb.setModel(df);
		panel.add(scrl);
		
		JLabel lblNewLabel = new JLabel("Classe");
		lblNewLabel.setBounds(56, 362, 45, 13);
		panel.add(lblNewLabel);
		
		JComboBox list_classe = new JComboBox();

		list_classe.addItem("CRECHE");
		list_classe.addItem("TPS");
		list_classe.addItem("PS/MS/GS");
		list_classe.addItem("CI");
		list_classe.addItem("CP");
		list_classe.addItem("CE1");
		list_classe.addItem("CE2");
		list_classe.addItem("CM1");
		list_classe.addItem("CM2");
		list_classe.addItem("6EME");
		list_classe.addItem("5EME");
		list_classe.addItem("4EME");
		list_classe.addItem("3EME");
		list_classe.setBounds(111, 358, 91, 21);
		panel.add(list_classe);
		
		JButton btnAfficher = new JButton("Afficher");
		btnAfficher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					df.setRowCount(0);
					Connection connection = DbManager.getConnection();
					String query = "SELECT * FROM eleve WHERE Classe = ?";

					PreparedStatement ps = connection.prepareStatement(query);
					ps.setString(1, list_classe.getSelectedItem().toString());
					ResultSet rs = ps.executeQuery();
					while(rs.next()) {
						df.addRow(new Object[] {rs.getString("id_eleve"), rs.getString("nom"), rs.getString("prenom"), rs.getString("classe"), rs.getString("sexe"), rs.getString("naissance")});
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnAfficher.setBounds(249, 358, 85, 21);
		panel.add(btnAfficher);
		
		JButton btnListClass = new JButton("Liste des classes");
		btnListClass.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ListeClasse listeClasse;
				try {
					listeClasse = new ListeClasse();
					listeClasse.afficher();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				dispose();
				
			}
		});
		
		/* 
		btnEffacer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				df.setRowCount(0);
			}
		});
		*/
		btnListClass.setBounds(380, 358, 114, 21);
		panel.add(btnListClass);
		
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
