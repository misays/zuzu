package myapp.sn.ui;

import javax.swing.JFrame;
import java.awt.Dimension;
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


public class EcheanceUi extends JFrame {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	public EcheanceUi() throws Exception {
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
		scrl.setBounds(275, 10, 601, 321);
		df.addColumn("id");
		df.addColumn("Nom de la classe");
		df.addColumn("echeance 1ere tranche");
		df.addColumn("echeance 2eme tranche");
		df.addColumn("echeance 3eme tranche");
		

		try {
			Connection con = null;
			try {
				con = DbManager.getConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			PreparedStatement ps = con.prepareStatement("select * from classe");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				df.addRow(new Object[] {rs.getString("id"),rs.getString("nomClasse"),rs.getString("echeance1"),
						rs.getString("echeance2"),rs.getString("echeance3")});
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		panel.setLayout(null);
		
		
		tb.setModel(df);
		panel.add(scrl);

		tb.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				textField.setText(tb.getValueAt(tb.getSelectedRow(), 2).toString());
				textField_1.setText(tb.getValueAt(tb.getSelectedRow(), 3).toString());
				textField_2.setText(tb.getValueAt(tb.getSelectedRow(), 4).toString());
			}
		});
		
		JLabel lblNewLabel = new JLabel("echeance 1ere tranche");
		lblNewLabel.setBounds(22, 64, 113, 13);
		panel.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(158, 61, 96, 19);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("echeance 2eme tranche");
		lblNewLabel_1.setBounds(22, 120, 113, 13);
		panel.add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		textField_1.setBounds(158, 117, 96, 19);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("echeance 3eme tranche");
		lblNewLabel_2.setBounds(22, 172, 113, 13);
		panel.add(lblNewLabel_2);
		
		textField_2 = new JTextField();
		textField_2.setBounds(158, 169, 96, 19);
		panel.add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnModifier = new JButton("Modifier\r\n");
		btnModifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Connection con = null;
					try {
						con = DbManager.getConnection();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					PreparedStatement ps = con.prepareStatement("update classe set echeance1=?,echeance2=?,echeance3=? where id=?");
					ps.setString(1, textField.getText());
					ps.setString(2, textField_1.getText());
					ps.setString(3, textField_2.getText());
					ps.setString(4, tb.getValueAt(tb.getSelectedRow(), 0).toString());
					ps.executeUpdate();
					
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnModifier.setBounds(30, 253, 85, 21);
		panel.add(btnModifier);
		
		JButton btnActualiser = new JButton("Actualiser\r\n");
		btnActualiser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				
				 try {
					EcheanceUi d = new EcheanceUi();
					d.afficher();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnActualiser.setBounds(158, 253, 85, 21);
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
