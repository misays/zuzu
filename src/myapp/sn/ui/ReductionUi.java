package myapp.sn.ui;

import javax.swing.JFrame;
import java.awt.Dimension;
import javax.swing.JPanel;
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


public class ReductionUi extends JFrame {
	private JTextField textField;
	public ReductionUi() {

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


	   
	   DefaultTableModel df=new DefaultTableModel();
	   JTable tb=new JTable();
	   JScrollPane scrl=new JScrollPane();
	   scrl.setViewportView(tb);
	   scrl.setBounds(422, 5, 464, 269);
	   df.addColumn("id");
	   df.addColumn("idEleve");
	   df.addColumn("nomEleve");
	   df.addColumn("montant1");
	   df.addColumn("montant2");
	   df.addColumn("montant3");
	   df.addColumn("pourcentage de reduction");
	   panel.setLayout(null);
	   
	   
	   tb.setModel(df);
	   panel.add(scrl);

	   tb.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				int ligne = tb.getSelectedRow();
				String id = tb.getValueAt(ligne, 0).toString();
				String reduction = tb.getValueAt(ligne, 6).toString();
				textField.setText(reduction);
			}
		});
	   
	   textField = new JTextField();
	   textField.setBounds(131, 56, 96, 19);
	   panel.add(textField);
	   textField.setColumns(10);
	   
	   JLabel lblNewLabel = new JLabel("Taux de reduction");
	   lblNewLabel.setBounds(27, 59, 96, 13);
	   panel.add(lblNewLabel);
	   
	   JButton btnModifier = new JButton("Modifier\r\n\r\n");

	   btnModifier.setBounds(27, 180, 85, 21);
	   btnModifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int ligne = tb.getSelectedRow();
                    String id = tb.getValueAt(ligne, 0).toString();
					String reduction = textField.getText();
					ReductionDb db = new ReductionDb();
					db.update(id, reduction);

					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				textField.setText("");

			}
		});

	   panel.add(btnModifier);
	   
	   JButton btnActualiser = new JButton("Actualiser");
	   btnActualiser.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			dispose();
			
			 try {
				ReductionUi d = new ReductionUi();
				d.afficher();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	});
	   btnActualiser.setBounds(142, 180, 85, 21);
	   panel.add(btnActualiser);
		setSize(new Dimension(900, 500));
		getContentPane().setLayout(null);

		try{
			Connection connection = DbManager.getConnection();
			String query = "SELECT * FROM payment";
			PreparedStatement ps = connection.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				df.addRow(new Object[]{
						rs.getString("id"),rs.getString("idEleve"),rs.getString("nomEleve"),rs.getString("montant1"),
						rs.getString("montant2"),rs.getString("montant3"),
						rs.getString("reduction")					
				});
				
			}
		}
		catch(SQLException ex){
			ex.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
