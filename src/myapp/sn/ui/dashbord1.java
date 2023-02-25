package myapp.sn.ui;
import myapp.sn.database.*;

import javax.swing.JFrame;
import javax.swing.*;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.mysql.cj.xdevapi.Table;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.Window.Type;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.SystemColor;

public class dashbord1 extends JFrame {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField txtHbhbb;
	private JTable Table;
	public dashbord1() throws Exception {

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
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(0, 0, 886, 463);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JComboBox sexe = new JComboBox();
		sexe.addItem("Masculin");
		sexe.addItem("Feminin");
		sexe.setBounds(105, 223, 96, 21);
		panel.add(sexe);
		
		JLabel lblNewLabel = new JLabel("Nom");
		lblNewLabel.setBounds(65, 71, 45, 13);
		panel.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(105, 68, 96, 19);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Pr\u00E9nom");
		lblNewLabel_1.setBounds(50, 128, 45, 13);
		panel.add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		textField_1.setBounds(105, 125, 96, 19);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		txtHbhbb = new JTextField();
		txtHbhbb.setToolTipText("");
		txtHbhbb.setBounds(105, 177, 96, 19);
		panel.add(txtHbhbb);
		txtHbhbb.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Date de naissance");
		lblNewLabel_2.setBounds(10, 180, 85, 13);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("sexe");
		lblNewLabel_3.setBounds(65, 227, 45, 13);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Classse");
		lblNewLabel_4.setBounds(50, 277, 45, 13);
		panel.add(lblNewLabel_4);
		
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

		//les classse de l'ecole creche, tps, ps/ms/gs, ci, cp, ce1, ce2, cm1, cm2, 6eme, 5eme, 4eme, 3eme
		

		list_classe.setBounds(105, 273, 96, 21);
		panel.add(list_classe);
		
		/* 
		JScrollPane etudiant_tab = new JScrollPane();
		//colonne et ligne
		String[] entete = {"Id","Nom", "Prenom", "Date de naissance", "Sexe", "Classe"};
		String[][] donnees = {{"", "", "", "", "",""}};
		JTable etudiant_tabs = new JTable(donnees, entete);
		etudiant_tab.setViewportView(etudiant_tabs);
		
		
		etudiant_tab.setBounds(246, 31, 483, 168);
		panel.add(etudiant_tab);
		*/

		DefaultTableModel df=new DefaultTableModel();
		JTable tb=new JTable();
		tb.setBackground(new Color(255, 255, 255));
		JScrollPane scrl=new JScrollPane();
		scrl.setBackground(SystemColor.window);
		scrl.setViewportView(tb);
		scrl.setBounds(246, 31, 630, 363);
		df.addColumn("id");
		df.addColumn("Nom");
		df.addColumn("Prenom");
		df.addColumn("Date de naissance");
		df.addColumn("Classe");
		df.addColumn("Sexe");
		
		
		tb.setModel(df);
		panel.add(scrl);
		
	
		try{
			Connection connection = DbManager.getConnection();
			String query = "SELECT * FROM eleve";
			PreparedStatement ps = connection.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				df.addRow(new Object[]{
						rs.getString("id_eleve"),rs.getString("nom"),rs.getString("prenom"),rs.getString("naissance")
						,rs.getString("classe"),rs.getString("sexe")
						
				});
				
			}
		}
		catch(SQLException ex){
			ex.printStackTrace();
		}
		//fonction pour actualiser la table
		
		JButton btnAddEtu = new JButton("Ajouter");
		btnAddEtu.setBackground(Color.ORANGE);
		//Quand on clique sur le bouton ajouter
		btnAddEtu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//On récupère les données saisies
				String nom = textField.getText();
				String prenom = textField_1.getText();
				String date_naissance = txtHbhbb.getText();
				//String sexe = (String) sexe.getSelectedItem();
				String sex = (String) sexe.getSelectedItem();
				String classe = (String) list_classe.getSelectedItem();
				//on envoie les données saisies dans la base de données
				EleveDb etu = new EleveDb();
				try {
					etu.insert(nom, prenom, date_naissance, classe, sex);
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				//On effface les données saisies
				textField.setText("");
				textField_1.setText("");
				txtHbhbb.setText("");
				//On recharge le tableau
				//etudiant_tabs.setModel(etu.actualiser());
			
				
			}
		});
	
		btnAddEtu.setBounds(246, 420, 85, 21);
		panel.add(btnAddEtu);
		
		JButton btnActualiser = new JButton("Actualiser");
		btnActualiser.setBackground(Color.GREEN);
		btnActualiser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				
				 try {
					dashbord1 d = new dashbord1();
					d.afficher();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnActualiser.setBounds(381, 420, 85, 21);
		
		panel.add(btnActualiser);

		//Quand ûne ligne du tableau est selectionnée on affiche les données dans les champs de saisie
		tb.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int ligne = tb.getSelectedRow();
				textField.setText(tb.getValueAt(ligne, 1).toString());
				textField_1.setText(tb.getValueAt(ligne, 2).toString());
				txtHbhbb.setText(tb.getValueAt(ligne, 3).toString());
				//sexe.setText(tb.getValueAt(ligne, 4).toString());
				list_classe.setSelectedItem(tb.getValueAt(ligne, 5).toString());
			}
		});
		
		JButton btnModifier = new JButton("Modifier");
		btnModifier.setBackground(Color.CYAN);
		btnModifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				 try {
					//quand on selectionne une ligne du tableau
				int ligne = tb.getSelectedRow();
				//on récupère les données de la ligne selectionnée	
				String id = tb.getValueAt(ligne, 0).toString();
				//String nom = tb.getValueAt(ligne, 1).toString();
				
				//String prenom = tb.getValueAt(ligne, 2).toString();
				//String date_naissance = tb.getValueAt(ligne, 3).toString();
				//String classe = tb.getValueAt(ligne, 4).toString();
				//String sex = tb.getValueAt(ligne, 5).toString();
				//On récupère les données saisies
				String nom = textField.getText();
				String prenom = textField_1.getText();
				String date_naissance = txtHbhbb.getText();
				//String sexe = (String) sexe.getSelectedItem();
				String sex = (String) sexe.getSelectedItem();
				String classe = (String) list_classe.getSelectedItem();
				//on envoie les données saisies dans la base de données

				//on envoie les données dans la base de données
				EleveDb etu = new EleveDb();
				etu.update(id, nom, prenom, date_naissance, classe, sex);
				//on recharge le tableau

			
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnModifier.setBounds(519, 420, 85, 21);
		panel.add(btnModifier);
		
		JButton btnSupprimer = new JButton("Supprimer");
		btnSupprimer.setBackground(Color.RED);
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				 try {
					int ligne = tb.getSelectedRow();
					String id = tb.getValueAt(ligne, 0).toString();
					EleveDb etu = new EleveDb();
					etu.delete(id);

				} catch (Exception e1) {
					
				}
			}
		});
		
		btnSupprimer.setBounds(649, 420, 85, 21);
		panel.add(btnSupprimer);

		JButton btnQuitter = new JButton("Quitter\r\n");
		btnQuitter.setBounds(768, 420, 85, 21);
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
		this.setVisible(true);
	}
	/* 
	public void actualiserss() {
		try {
			Connection connection = DbManager.getConnection();
			String query = "SELECT * FROM eleve";
			PreparedStatement ps = connection.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			DefaultTableModel tblModel = new DefaultTableModel(6,0);
			while(rs.next()) {
				String id = rs.getString("id_eleve");
				String nom = rs.getString("nom");
				String prenom = rs.getString("prenom");
				String date_naissance = rs.getString("naissance");
				String classe = rs.getString("classe");
				String sexe = rs.getString("sexe");
	
				String tbData[] = {id,nom, prenom, date_naissance, classe, sexe};
				tblModel.addRow(new Object[]{id,nom, prenom, date_naissance, classe, sexe
					
			});
	
				//tblModel.addRow(tbData);
			}
			
			Table.setModel(tblModel);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/
}
