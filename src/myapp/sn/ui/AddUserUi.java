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

public class AddUserUi extends JFrame {
	private JTextField chLogin;
	private JTextField chPassword;
	private JTextField chRepassword;
	public AddUserUi() throws Exception {
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
		df.addColumn("Identifiant");
		df.addColumn("Mots de passe");
		df.addColumn("Role");
		panel.setLayout(null);
		
		
		tb.setModel(df);
		panel.add(scrl);
		
		JLabel lblNewLabel = new JLabel("Identifiant\r\n");
		lblNewLabel.setBounds(40, 51, 56, 13);
		panel.add(lblNewLabel);
		
		chLogin = new JTextField();
		chLogin.setBounds(119, 48, 96, 19);
		panel.add(chLogin);
		chLogin.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Mot de passe");
		lblNewLabel_1.setBounds(26, 94, 70, 13);
		panel.add(lblNewLabel_1);
		
		chPassword = new JTextField();
		chPassword.setBounds(119, 91, 96, 19);
		panel.add(chPassword);
		chPassword.setColumns(10);
		
		JComboBox comboBox = new JComboBox();
        comboBox.addItem("secretaire");
        comboBox.addItem("caissier");
		comboBox.setBounds(91, 192, 85, 21);
		panel.add(comboBox);

        try{
			Connection connection = DbManager.getConnection();
			String query = "SELECT * FROM identifiant";
			PreparedStatement ps = connection.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				df.addRow(new Object[]{
						rs.getString("id"),rs.getString("login"),rs.getString("password"),rs.getString("role")					
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
                chLogin.setText(df.getValueAt(i, 1).toString());
                chPassword.setText(df.getValueAt(i, 2).toString());
                chRepassword.setText(df.getValueAt(i, 2).toString());
                comboBox.setSelectedItem(df.getValueAt(i, 3).toString());
            }
        });

		
		JButton btnAjouter = new JButton("Ajouter\r\n");
        btnAjouter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String login = chLogin.getText();
                String password = chPassword.getText();
                String repassword = chRepassword.getText();
                String role = comboBox.getSelectedItem().toString();
                try {
                    AddUserDb db = new AddUserDb();
                    db.insert(login, password, repassword, role);
                } catch (Exception e1) {
                    
                    e1.printStackTrace();
                }
				//On effface les données saisiee
				chLogin.setText("");
				chPassword.setText("");	
				chRepassword.setText("");
				comboBox.setSelectedItem("");

            
            }
                
        });
		btnAjouter.setBounds(26, 223, 85, 21);
		panel.add(btnAjouter);
		
		JButton btnSupprimer = new JButton("Supprimer\r\n");
        btnSupprimer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
					int ligne = tb.getSelectedRow();
					String id = tb.getValueAt(ligne, 0).toString();
					AddUserDb etu = new AddUserDb();
					etu.delete(id);

				} catch (Exception e1) {
					
				}
            
            }
                
        });
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnSupprimer.setBounds(159, 223, 85, 21);
		panel.add(btnSupprimer);
		
		JButton btnActualiser = new JButton("Actualiser");
        btnActualiser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				
				 try {
					AddUserUi d = new AddUserUi();
					d.afficher();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnActualiser.setBounds(26, 274, 85, 21);
		panel.add(btnActualiser);
		
		JButton btnModifier = new JButton("Modifier");
        btnModifier.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int ligne = tb.getSelectedRow();
                    String id = tb.getValueAt(ligne, 0).toString();
                    String login = chLogin.getText();
                    String password = chPassword.getText();
                    String repassword = chRepassword.getText();
                    String role = comboBox.getSelectedItem().toString();
                    AddUserDb etu = new AddUserDb();
                    etu.update(id, login, password, repassword, role);
                } catch (Exception e1) {
                    
                }
            
            }
                
        });
		btnModifier.setBounds(159, 274, 85, 21);
		panel.add(btnModifier);
		
		JLabel lblNewLabel_2 = new JLabel("Confirmation de mot de passse");
		lblNewLabel_2.setBounds(10, 128, 142, 13);
		panel.add(lblNewLabel_2);
		
		chRepassword = new JTextField();
		chRepassword.setBounds(159, 125, 96, 19);
		panel.add(chRepassword);
		chRepassword.setColumns(10);
        try{
			Connection connection = DbManager.getConnection();
			String query = "SELECT * FROM identifiant";
			PreparedStatement ps = connection.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				df.addRow(new Object[]{
						rs.getString("id"),
                        rs.getString("login"),
                        rs.getString("password"),
                        rs.getString("type")
                });
				
			}
		}catch(Exception e){
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
        this.setVisible(true);}
    
}
