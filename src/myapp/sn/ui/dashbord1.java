package myapp.sn.ui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class dashbord1 extends JFrame {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField txtHbhbb;
	public dashbord1() {
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 764, 411);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JComboBox sexe = new JComboBox();
		sexe.addItem("M");
		sexe.addItem("F");
		sexe.setBounds(49, 139, 36, 21);
		panel.add(sexe);
		
		JLabel lblNewLabel = new JLabel("Nom");
		lblNewLabel.setBounds(36, 38, 45, 13);
		panel.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(105, 35, 96, 19);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Pr\u00E9nom");
		lblNewLabel_1.setBounds(25, 71, 45, 13);
		panel.add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		textField_1.setBounds(105, 68, 96, 19);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		txtHbhbb = new JTextField();
		txtHbhbb.setBounds(105, 97, 96, 19);
		panel.add(txtHbhbb);
		txtHbhbb.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Date de naissance");
		lblNewLabel_2.setBounds(10, 100, 85, 13);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("sexe");
		lblNewLabel_3.setBounds(25, 143, 45, 13);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Classse");
		lblNewLabel_4.setBounds(95, 143, 45, 13);
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
		

		list_classe.setBounds(134, 139, 67, 21);
		panel.add(list_classe);
	}
}