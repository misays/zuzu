package myapp.sn.ui;

import javax.swing.*;
import myapp.sn.database.*;
import myapp.sn.ui.*;

import java.awt.*;
import java.awt.event.*;
import java.net.URL;

public class ConnexionUi extends JFrame {

    private JTextField textField;
    private JPasswordField passwordField;
    private JComboBox<String> roleComboBox;

    public ConnexionUi() {

        setTitle("Connexion");

        // Dimension de la fen�tre
        setSize(new Dimension(600, 400));

        // Emp�cher le redimensionnement de la fen�tre
        // setResizable(false);

        // Position de la fen�tre au centre de l'�cran
        setLocationRelativeTo(null);

        // Fermer l'application lors de la fermeture de la fen�tre
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(255, 255, 255));
        panel.setLayout(null);

        JLabel labelTitle = new JLabel("Connexion");
        labelTitle.setFont(new Font("Arial", Font.BOLD, 20));
        labelTitle.setBounds(204, 10, 193, 43);
        panel.add(labelTitle);

        JLabel labelIdentifiant = new JLabel("Identifiant");
        labelIdentifiant.setFont(new Font("Arial", Font.PLAIN, 14));
        labelIdentifiant.setBounds(55, 78, 80, 20);
        panel.add(labelIdentifiant);

        textField = new JTextField();
        textField.setBounds(145, 80, 120, 20);
        panel.add(textField);

        JLabel labelPassword = new JLabel("Mot de passe");
        labelPassword.setFont(new Font("Arial", Font.PLAIN, 14));
        labelPassword.setBounds(29, 132, 106, 20);
        panel.add(labelPassword);

        passwordField = new JPasswordField();
        passwordField.setBounds(145, 134, 120, 20);
        panel.add(passwordField);

        JLabel labelRole = new JLabel("Role");
        labelRole.setFont(new Font("Arial", Font.PLAIN, 14));
        labelRole.setBounds(81, 181, 80, 20);
        panel.add(labelRole);

        roleComboBox = new JComboBox<>();
        roleComboBox.addItem("Admin");
        roleComboBox.addItem("Secretaire");
        roleComboBox.addItem("Caissier");
        roleComboBox.setBounds(145, 182, 120, 20);
        panel.add(roleComboBox);

        JButton buttonConnexion = new JButton("Connexion");
        buttonConnexion.setBackground(SystemColor.info);
        buttonConnexion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String login = textField.getText();
                String password = new String(passwordField.getPassword());
                String role = roleComboBox.getSelectedItem().toString();
                ConnexionDb db = new ConnexionDb();
                try {
                    db.connect(login, password, role);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });
        buttonConnexion.setBounds(171, 244, 100, 30);
        panel.add(buttonConnexion);

        JButton buttonQuitter = new JButton("Quitter");
        buttonQuitter.setBackground(SystemColor.activeCaption);
        buttonQuitter.setBounds(27, 244, 100, 30);
        panel.add(buttonQuitter);

        //quand on clique sur le bouton quitter on a une fen�tre de confirmation
        buttonQuitter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int reponse = JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment quitter?", "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if(reponse == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });

        getContentPane().add(panel);
        
        JLabel lblNewLabel = new JLabel("");
        ImageIcon imageIcon = new ImageIcon("D:\\jpp\\school\\java\\workspace java poo\\student0\\img\\5.png");
        Image image = imageIcon.getImage().getScaledInstance(176, 202, Image.SCALE_SMOOTH);
        ImageIcon newImageIcon = new ImageIcon(image);
        lblNewLabel.setIcon(newImageIcon);
        lblNewLabel.setBounds(375, 26, 201, 248);
        panel.add(lblNewLabel);
        //modifier la taille de l'image
        ImageIcon imageIcon1 = new ImageIcon("D:\\jpp\\school\\java\\workspace java poo\\student0\\img\\6.png");
        Image image1 = imageIcon1.getImage().getScaledInstance(400, 100, Image.SCALE_SMOOTH);

        
        
        


    }

    public void afficher() {
        setVisible(true);
    }
}

