package myapp.sn.ui;

import myapp.sn.database.*;

import javax.swing.JButton;
import javax.swing.JFrame;
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

public class NoteUi extends JFrame {
	private JTextField chIdEleve;
	private JTextField chMatiere;
	private JTextField chNomEleve;
	private JTextField chNoteDevoir;
	private JTextField chNoteExam;
	
	public NoteUi() throws Exception {
		setSize(new Dimension(900, 500));
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setSize(new Dimension(900, 500));
		panel.setBounds(0, 0, 886, 463);
		getContentPane().add(panel);
		panel.setLayout(null);

		DefaultTableModel df=new DefaultTableModel();
		JTable tb=new JTable();
		JScrollPane scrl=new JScrollPane();
		scrl.setViewportView(tb);
		scrl.setBounds(403, 10, 483, 168);
		df.addColumn("Noteid");
		df.addColumn("Etudiant");
		df.addColumn("Matiere");
		df.addColumn("Devoir");
		df.addColumn("Examen");
		//df.addColumn("Sexe");
		
		
		tb.setModel(df);
		panel.add(scrl);
		//affficher les donnees qui sont dans la base de donnees
		try{
			Connection connection = DbManager.getConnection();
			String query = "SELECT * FROM note";
			PreparedStatement ps = connection.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				df.addRow(new Object[]{
						rs.getString("idNote"),rs.getString("idEleve"),rs.getString("nomEleve"),rs.getString("idMatiere")
						,rs.getString("devoir"),rs.getString("examen")
						
				});
				
			}
		}
		catch(SQLException ex){
			ex.printStackTrace();
		}
		
		JLabel lblNewLabel = new JLabel("ld El\u00E8ve");
		lblNewLabel.setBounds(70, 43, 45, 13);
		panel.add(lblNewLabel);
		
		chIdEleve = new JTextField();
		chIdEleve.setBounds(136, 40, 96, 19);
		panel.add(chIdEleve);
		chIdEleve.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Mati\u00E8re");
		lblNewLabel_1.setBounds(70, 144, 45, 13);
		panel.add(lblNewLabel_1);
		
		chMatiere = new JTextField();
		chMatiere.setBounds(136, 141, 96, 19);
		panel.add(chMatiere);
		chMatiere.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Nom de l'\u00E9l\u00E8ve");
		lblNewLabel_2.setBounds(48, 89, 67, 13);
		panel.add(lblNewLabel_2);
		
		chNomEleve = new JTextField();
		chNomEleve.setBounds(136, 86, 96, 19);
		panel.add(chNomEleve);
		chNomEleve.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Note Devoir");
		lblNewLabel_3.setBounds(48, 186, 67, 13);
		panel.add(lblNewLabel_3);
		
		chNoteDevoir = new JTextField();
		chNoteDevoir.setBounds(136, 183, 96, 19);
		panel.add(chNoteDevoir);
		chNoteDevoir.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Note Examen");
		lblNewLabel_4.setBounds(48, 240, 67, 13);
		panel.add(lblNewLabel_4);
		
		chNoteExam = new JTextField();
		chNoteExam.setBounds(136, 237, 96, 19);
		panel.add(chNoteExam);
		chNoteExam.setColumns(10);
		
		JButton btnAddNote = new JButton("Ajouter");
		btnAddNote.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//On rÃ©cupÃ¨re les donnÃ©es saisies
				String idEtu = chIdEleve.getText();
				String nomEtu = chNomEleve.getText();
				String noteDevoir = chNoteDevoir.getText();
				String noteExam = chNoteExam.getText();
				String idMatiere = chMatiere.getText();
				//On crÃ©e un objet Etudiant
				NoteDb note = new NoteDb();
				try {
					note.insert(idEtu, nomEtu, idMatiere, noteDevoir, noteExam);
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}			
			}
		});
		btnAddNote.setBounds(30, 312, 85, 21);
		panel.add(btnAddNote);
		
		JButton btnSupprimer = new JButton("Suprimer");
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				 try {
					int ligne = tb.getSelectedRow();
					String id = tb.getValueAt(ligne, 0).toString();
					NoteDb etu = new NoteDb();
					etu.delete(id);

				} catch (Exception e1) {
					
				}
			}
		});
		btnSupprimer.setBounds(147, 312, 85, 21);
		panel.add(btnSupprimer);
		
		JButton btnModifier = new JButton("Modifier");

		//Quand ûne ligne du tableau est selectionnée on affiche les données dans les champs de saisie
		tb.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i = tb.getSelectedRow();
				chIdEleve.setText(df.getValueAt(i, 0).toString());
				chNomEleve.setText(df.getValueAt(i, 1).toString());
				chMatiere.setText(df.getValueAt(i, 2).toString());
				chNoteDevoir.setText(df.getValueAt(i, 3).toString());
				chNoteExam.setText(df.getValueAt(i, 4).toString());
			}
		});

		btnModifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				 try {

					//quand on selectionne une ligne du tableau
					int ligne = tb.getSelectedRow();
					String id = tb.getValueAt(ligne, 0).toString();
					
					NoteDb d = new NoteDb();
					d.update(id, chIdEleve.getText(), chNomEleve.getText(), chMatiere.getText(), chNoteDevoir.getText(), chNoteExam.getText());
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnModifier.setBounds(147, 365, 85, 21);
		panel.add(btnModifier);
		
		JButton btnActualiser = new JButton("Actualiser\r\n");
		btnActualiser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				
				 try {
					NoteUi d = new NoteUi();
					d.afficher();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnActualiser.setBounds(30, 365, 85, 21);
		panel.add(btnActualiser);
		
	
		try{
			Connection connection = DbManager.getConnection();
			String query = "SELECT * FROM note";
			PreparedStatement ps = connection.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				df.addRow(new Object[]{
						rs.getString("idNote"),rs.getString("idEleve"),rs.getString("nomEleve"),rs.getString("idMatiere ")
						,rs.getString("devoir"),rs.getString("examen")
						
				});
				
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
		this.setVisible(true);
	}
}

