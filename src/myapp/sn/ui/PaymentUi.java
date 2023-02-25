package myapp.sn.ui;

import javax.swing.JFrame;
import java.awt.Dimension;
import javax.swing.JPanel;
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



public class PaymentUi extends JFrame{
	private JTextField chIdEleve;
	private JTextField chNomEleve;
	private JTextField chDate2;
	private JTextField chDate3;
	private JTextField chMontant3;
	private JTextField chMontant1;
	private JTextField chDate1;
	private JTextField chMontant2;
	private JTextField chRecu1;
	private JTextField chRecu2;
	private JTextField chRecu3;
	public PaymentUi() throws Exception {
		// Dimension de la fenêtre
		setSize(new Dimension(900, 500));

		// Empêcher le redimensionnement de la fenêtre
		setResizable(false);

		// Position de la fenêtre au centre de l'écran
		setLocationRelativeTo(null);

		// Fermer l'application lors de la fermeture de la fenêtre
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		getContentPane().setSize(new Dimension(900, 500));
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 886, 463);
		getContentPane().add(panel);
		panel.setLayout(null);
		setSize(new Dimension(900, 500));

		DefaultTableModel df=new DefaultTableModel();
		JTable tb=new JTable();
		JScrollPane scrl=new JScrollPane();
		scrl.setViewportView(tb);
		scrl.setBounds(17, 10, 846, 221);
		df.addColumn("id");
		df.addColumn("id de l'eleve");
		df.addColumn("Nom de l'eleve");
		df.addColumn("Montant 1ere tranche");
		df.addColumn("Date");
		df.addColumn("N° reçu");

		df.addColumn("Montant 2eme tranche");
		df.addColumn("Date");
		df.addColumn("N° reçu");
		
		df.addColumn("Montant 3eme tranche");
		df.addColumn("Date");
		df.addColumn("N° reçu");
		
		panel.setLayout(null);
		
		
		tb.setModel(df);
		panel.add(scrl);
		
		JLabel lblNewLabel = new JLabel("id de l'eleve");
		lblNewLabel.setBounds(17, 264, 61, 13);
		panel.add(lblNewLabel);
		
		chIdEleve = new JTextField();
		chIdEleve.setBounds(88, 261, 96, 19);
		panel.add(chIdEleve);
		chIdEleve.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Nom de l'eleve");
		lblNewLabel_1.setBounds(10, 322, 77, 13);
		panel.add(lblNewLabel_1);
		
		chNomEleve = new JTextField();
		chNomEleve.setBounds(88, 319, 96, 19);
		panel.add(chNomEleve);
		chNomEleve.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Montant 2eme tranche");
		lblNewLabel_2.setBounds(422, 264, 108, 13);
		panel.add(lblNewLabel_2);
		
		chDate2 = new JTextField();
		chDate2.setBounds(540, 319, 96, 19);
		panel.add(chDate2);
		chDate2.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Montant 3eme tranche");
		lblNewLabel_3.setBounds(646, 264, 108, 13);
		panel.add(lblNewLabel_3);
		
		chDate3 = new JTextField();
		chDate3.setBounds(764, 319, 96, 19);
		panel.add(chDate3);
		chDate3.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("N\u00B0 re\u00E7u");
		lblNewLabel_4.setBounds(261, 375, 45, 13);
		panel.add(lblNewLabel_4);
		
		chMontant3 = new JTextField();
		chMontant3.setBounds(764, 261, 96, 19);
		panel.add(chMontant3);
		chMontant3.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("Date");
		lblNewLabel_5.setBounds(718, 322, 45, 13);
		panel.add(lblNewLabel_5);
		
		chMontant1 = new JTextField();
		chMontant1.setBounds(316, 261, 96, 19);
		panel.add(chMontant1);
		chMontant1.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("N\u00B0 re\u00E7u");
		lblNewLabel_6.setBounds(709, 375, 45, 13);
		panel.add(lblNewLabel_6);
		
		chDate1 = new JTextField();
		chDate1.setBounds(316, 319, 96, 19);
		panel.add(chDate1);
		chDate1.setColumns(10);
		
		JLabel lblNewLabel_7 = new JLabel("Montant 1ere tranche");
		lblNewLabel_7.setBounds(209, 264, 108, 13);
		panel.add(lblNewLabel_7);
		
		chMontant2 = new JTextField();
		chMontant2.setBounds(540, 264, 96, 19);
		panel.add(chMontant2);
		chMontant2.setColumns(10);
		
		JLabel lblNewLabel_8 = new JLabel("Date");
		lblNewLabel_8.setBounds(261, 322, 45, 13);
		panel.add(lblNewLabel_8);
		
		JLabel lblNewLabel_9 = new JLabel("Date");
		lblNewLabel_9.setBounds(495, 322, 45, 13);
		panel.add(lblNewLabel_9);
		
		chRecu1 = new JTextField();
		chRecu1.setBounds(316, 369, 96, 19);
		panel.add(chRecu1);
		chRecu1.setColumns(10);
		
		chRecu2 = new JTextField();
		chRecu2.setBounds(540, 369, 96, 19);
		panel.add(chRecu2);
		chRecu2.setColumns(10);

		try{
			Connection connection = DbManager.getConnection();
			String query = "SELECT * FROM payment";
			PreparedStatement ps = connection.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				df.addRow(new Object[]{
						rs.getString("id"),rs.getString("idEleve"),rs.getString("nomEleve"),rs.getString("montant1"),
						rs.getString("date1"),
						rs.getString("recu1"),rs.getString("montant2"),
						rs.getString("date2"),rs.getString("recu2"),rs.getString("montant3"),
						rs.getString("date3"),rs.getString("recu3")					
				});
				
			}
		}
		catch(SQLException ex){
			ex.printStackTrace();
		}

		//Quand ûne ligne du tableau est selectionnée on affiche les données dans les champs de saisie
		tb.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i = tb.getSelectedRow();
				
				chIdEleve.setText(df.getValueAt(i, 1).toString());
				chNomEleve.setText(df.getValueAt(i, 2).toString());
				chMontant1.setText(df.getValueAt(i, 3).toString());
				chDate1.setText(df.getValueAt(i, 4).toString());
				chRecu1.setText(df.getValueAt(i, 5).toString());
				chMontant2.setText(df.getValueAt(i, 6).toString());
				chDate2.setText(df.getValueAt(i, 7).toString());
				chRecu2.setText(df.getValueAt(i, 8).toString());
				chMontant3.setText(df.getValueAt(i, 9).toString());
				chDate3.setText(df.getValueAt(i, 10).toString());
				chRecu3.setText(df.getValueAt(i, 11).toString());
			}
		});
		
		JButton btnAjouter = new JButton("Ajouter");
		btnAjouter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
				
				String idEleve = chIdEleve.getText();
				String nomEleve = chNomEleve.getText();
				String montant1 = chMontant1.getText();
				String date1 = chDate1.getText();
				String recu1 = chRecu1.getText();
				String montant2 = chMontant2.getText();
				String date2 = chDate2.getText();
				String recu2 = chRecu2.getText();
				String montant3 = chMontant3.getText();
				String date3 = chDate3.getText();
				String recu3 = chRecu3.getText();

				//si la valeur des champs est vide on met une valeur par défaut
				
				if(montant1.equals("")) montant1 = "Impaye";
				if(date1.equals("")) date1 = "Pas de date";	
				if(recu1.equals("")) recu1 = "Pas de recu";
				if(montant2.equals("")) montant2 = "Impaye";
				if(date2.equals("")) date2 = "Pas de date";
				if(recu2.equals("")) recu2 = "Pas de recu";
				if(montant3.equals("")) montant3 = "Impaye";
				if(date3.equals("")) date3 = "Pas de date";
				if(recu3.equals("")) recu3 = "Pas de recu";

				PaymentDb db = new PaymentDb();
				try {
					db.insertPayment(idEleve, nomEleve, montant1, date1, recu1, montant2, date2, recu2, montant3, date3, recu3);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}});


		btnAjouter.setBounds(10, 368, 85, 21);
		panel.add(btnAjouter);
		
		JLabel lblNewLabel_11 = new JLabel("N\u00B0 re\u00E7u");
		lblNewLabel_11.setBounds(485, 375, 45, 13);
		panel.add(lblNewLabel_11);
		
		chRecu3 = new JTextField();
		chRecu3.setBounds(764, 372, 96, 19);
		panel.add(chRecu3);
		chRecu3.setColumns(10);

		
        
		
		JButton btnModifier = new JButton("Modifier");
		btnModifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int ligne = tb.getSelectedRow();
				String id = tb.getValueAt(ligne, 0).toString();

				String idEleve = chIdEleve.getText();
				String nomEleve = chNomEleve.getText();
				String montant1 = chMontant1.getText();
				String date1 = chDate1.getText();
				String recu1 = chRecu1.getText();
				String montant2 = chMontant2.getText();
				String date2 = chDate2.getText();
				String recu2 = chRecu2.getText();
				String montant3 = chMontant3.getText();
				String date3 = chDate3.getText();
				String recu3 = chRecu3.getText();
				PaymentDb db = new PaymentDb();
				


				try {
					db.updatePayment(id, idEleve, nomEleve, montant1, date1, recu1, montant2, date2, recu2, montant3, date3, recu3);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				//On effface les données saisies
				chIdEleve.setText("");
				chNomEleve.setText("");
				chMontant1.setText("");
				chDate1.setText("");
				chRecu1.setText("");
				chMontant2.setText("");
				chDate2.setText("");
				chRecu2.setText("");
				chMontant3.setText("");
				chDate3.setText("");
				chRecu3.setText("");

			}});
		btnModifier.setBounds(105, 368, 85, 21);
		panel.add(btnModifier);
		
		JButton bbnSupprimer = new JButton("Supprimer");
		bbnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int ligne = tb.getSelectedRow();
				String id = tb.getValueAt(ligne, 0).toString();
				PaymentDb db = new PaymentDb();
				try {
					db.deletePayment(id);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}});



		bbnSupprimer.setBounds(10, 413, 85, 21);
		panel.add(bbnSupprimer);
		
		JButton btnActualiser = new JButton("Actualiser\r\n");
		btnActualiser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				
				 try {
					PaymentUi d = new PaymentUi();
					d.afficher();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnActualiser.setBounds(105, 413, 85, 21);
		panel.add(btnActualiser);
		
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
