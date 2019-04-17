package ERROR;
import java.sql.*;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import oracle.jdbc.OraclePreparedStatement;
import oracle.jdbc.OracleResultSet;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
import javax.swing.JPasswordField;

public class SignIn_Page extends JFrame {

	private JPanel contentPane;
	private JTextField txt_mail;
	private JTextField txt_username;

	Connection conn = null;
	OraclePreparedStatement pst = null;	
	OraclePreparedStatement pst1 = null;	
	OracleResultSet rs = null;
	private JTextField txt_fname;
	private JTextField txt_lname;
	private JPasswordField passwordField;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignIn_Page frame = new SignIn_Page();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SignIn_Page() {
		
		LookAndFeelInfo lf[]=UIManager.getInstalledLookAndFeels();
		try{
			UIManager.setLookAndFeel(lf[1].getClassName());
		}catch(Exception e) {
			
		}
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "SIGN   IN", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panel.setBackground(new Color(240, 240, 240));
		panel.setBounds(31, 10, 814, 469);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel mail = new JLabel("Mail :");
		mail.setBounds(31, 99, 154, 44);
		panel.add(mail);
		mail.setFont(new Font("Arial Narrow", Font.PLAIN, 21));
		mail.setHorizontalAlignment(SwingConstants.LEFT);
		
		txt_mail = new JTextField();
		txt_mail.setBounds(187, 104, 295, 44);
		panel.add(txt_mail);
		txt_mail.setColumns(10);
		
		JLabel username_label = new JLabel("UserName*  :");
		username_label.setBounds(31, 235, 154, 44);
		panel.add(username_label);
		username_label.setHorizontalAlignment(SwingConstants.LEFT);
		username_label.setFont(new Font("Arial Narrow", Font.PLAIN, 21));
		
		txt_username = new JTextField();
		txt_username.setBounds(184, 240, 197, 44);
		panel.add(txt_username);
		txt_username.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password*  : ");
		lblPassword.setBounds(441, 235, 154, 44);
		panel.add(lblPassword);
		lblPassword.setHorizontalAlignment(SwingConstants.LEFT);
		lblPassword.setFont(new Font("Arial Narrow", Font.PLAIN, 21));
		
		passwordField = new JPasswordField();
		passwordField.setBounds(595, 240, 169, 44);
		panel.add(passwordField);
		
		JLabel lblFirstName = new JLabel("First Name*  :");
		lblFirstName.setBounds(31, 17, 164, 54);
		panel.add(lblFirstName);
		lblFirstName.setFont(new Font("Dialog", Font.PLAIN, 21));
		
		txt_fname = new JTextField();
		txt_fname.setToolTipText("");
		txt_fname.setText("");
		txt_fname.setForeground(Color.BLACK);
		txt_fname.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txt_fname.setColumns(10);
		txt_fname.setBounds(184, 21, 162, 51);
		panel.add(txt_fname);
		
		JLabel lblLastName = new JLabel("Last Name*  :");
		lblLastName.setFont(new Font("Dialog", Font.PLAIN, 21));
		lblLastName.setBounds(441, 21, 154, 54);
		panel.add(lblLastName);
		
		txt_lname = new JTextField();
		txt_lname.setText("");
		txt_lname.setForeground(Color.BLACK);
		txt_lname.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txt_lname.setColumns(10);
		txt_lname.setBounds(595, 21, 162, 48);
		panel.add(txt_lname);
		
		JLabel gender = new JLabel("Gender*  :");
		gender.setFont(new Font("Dialog", Font.PLAIN, 21));
		gender.setBounds(31, 168, 154, 44);
		panel.add(gender);
		
		JRadioButton male = new JRadioButton("Male");
		male.setFont(new Font("Dialog", Font.PLAIN, 21));
		male.setBounds(184, 173, 118, 35);
		panel.add(male);
		
		
		JRadioButton female = new JRadioButton("Female");
		female.setFont(new Font("Dialog", Font.PLAIN, 21));
		female.setBounds(442, 173, 114, 35);
		panel.add(female);
		
		
		JLabel label_3 = new JLabel("(all fields marked * are mandatory)");
		label_3.setForeground(Color.DARK_GRAY);
		label_3.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		label_3.setBounds(124, 289, 470, 36);
		panel.add(label_3);
		
		JLabel display = new JLabel("");
		display.setBounds(120, 479, 448, 74);
		contentPane.add(display);
		display.setFont(new Font("Lucida Sans", Font.PLAIN, 16));
		
		JCheckBox checkBox = new JCheckBox("     I accept all the terms and conditions.(T& C apply)");
		checkBox.setFont(new Font("Arial", Font.PLAIN, 18));
		checkBox.setBounds(56, 317, 459, 44);
		panel.add(checkBox);
		
		ButtonGroup d=new ButtonGroup();
		d.add(male);
		d.add(female);
		
		JButton signin = new JButton("Sign IN");
		signin.setBounds(277, 375, 219, 59);
		panel.add(signin);
		signin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String mail = "";
				mail = txt_mail.getText();
				String username = txt_username.getText();
				String passkey = new String(passwordField.getPassword());
				String fname = lblFirstName.getText();
				String lname = lblLastName.getText();
				String gender = "";
				if(male.isSelected()) {
					gender = "M";
				} else if(female.isSelected()) {
					gender = "F";
				}
				if(username.equals("")||passkey.equals("")||fname.equals("")||lname.equals(""))
				{
					JOptionPane.showMessageDialog(contentPane, "Please fill all the Fields marked (*)", "Warning Message", JOptionPane.WARNING_MESSAGE);
					contentPane.requestFocus();
				}
				else {
					if(!checkBox.isSelected())
					{
						JOptionPane.showMessageDialog(contentPane, "Please accept all the terms and conditions!", "Warning Message", JOptionPane.WARNING_MESSAGE);
						contentPane.requestFocus();
					}
					else {
					
						try {	
							try {
								Class.forName("oracle.jdbc.driver.OracleDriver");
							} catch (ClassNotFoundException e1) {
								JOptionPane.showMessageDialog(null, "Error: unable to load driver class!");
								e1.printStackTrace();
							}
							Connection con = (Connection)DriverManager.getConnection("jdbc:oracle:thin:@localhost:1599:xe","system","rahul");
							
					String query1 = "SELECT Username FROM users where Username=?";
					pst = (OraclePreparedStatement) con.prepareStatement(query1);
					pst.setString(1, username);
					ResultSet rs = (OracleResultSet) pst.executeQuery();
					if(rs.next()) {
						display.setText("Username already in use. Choose another.");
						txt_fname.setText("");
						txt_lname.setText("");
						txt_mail.setText("");
						txt_username.setText("");
						passwordField.setText("");
					} else {
						String query = "INSERT INTO users VALUES('"+fname+"','"+lname+"','"+username+"','"+passkey+"','"+mail+"','"+gender+"')";
						pst1 = (OraclePreparedStatement) con.prepareStatement(query);
						pst1.executeUpdate(query);
						display.setText("Account successfully signed in. Go back and log in...");						
					}
					pst.close();
					rs.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				}
				}
			}
		});
		signin.setFont(new Font("Lucida Sans", Font.PLAIN, 22));
		
		
		
		
		JButton back = new JButton("BACK");
		back.setBounds(678, 489, 185, 64);
		contentPane.add(back);
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Initial_Page.txtSaasad.setText("");
				Initial_Page.passwordField.setText("");
				dispose();
			}
		});
		back.setFont(new Font("Lucida Sans", Font.PLAIN, 19));
		
		
		
		
		
		
	}
}
