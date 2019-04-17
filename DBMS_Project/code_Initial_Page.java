import java.sql.*;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import oracle.jdbc.OraclePreparedStatement;
import oracle.jdbc.OracleResultSet;

import java.awt.Font;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Initial_Page {

	private JFrame frame;
	static public JTextField txtSaasad;
	static public JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	Connection conn = null;
	OraclePreparedStatement pst = null;	
	OracleResultSet rs = null;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Initial_Page window = new Initial_Page();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Initial_Page() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 900, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("DELHI METRO SHORTEST ROUTE");
		lblNewLabel.setForeground(Color.MAGENTA);
		lblNewLabel.setFont(new Font("Lucida Sans", Font.BOLD, 28));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 62, 550, 102);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\Rahul\\Desktop\\DBMS_Project\\240px-Delhi_Metro_logo.svg.png"));
		lblNewLabel_1.setBounds(570, 11, 304, 259);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("UserName :");
		lblNewLabel_2.setFont(new Font("Arial Narrow", Font.PLAIN, 25));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(60, 219, 166, 51);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblPassword = new JLabel("Password :");
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassword.setFont(new Font("Arial Narrow", Font.PLAIN, 25));
		lblPassword.setBounds(60, 292, 166, 51);
		frame.getContentPane().add(lblPassword);
		
		txtSaasad = new JTextField();
		txtSaasad.setFont(new Font("Arial Narrow", Font.PLAIN, 20));
		txtSaasad.setBounds(236, 219, 187, 51);
		frame.getContentPane().add(txtSaasad);
		txtSaasad.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setEchoChar('*');
		passwordField.setBounds(236, 292, 187, 51);
		frame.getContentPane().add(passwordField);
		
		JButton btnNewButton = new JButton("Log in");
		Second second = new Second();
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				conn = ConnectionClass.dbconnect();
				String username = txtSaasad.getText();
				String passkey = passwordField.getText();
				try {
					
					try {
						Class.forName("oracle.jdbc.driver.OracleDriver");
					} catch (ClassNotFoundException e1) {
						JOptionPane.showMessageDialog(null, "Error: unable to load driver class!");
						e1.printStackTrace();
					}
					Connection con = (Connection)DriverManager.getConnection("jdbc:oracle:thin:@localhost:1599:xe","system","rahul");
					String query = "SELECT * FROM users where Username=? AND Passkey=?";
					pst = (OraclePreparedStatement) con.prepareStatement(query);
					pst.setString(1, username);
					pst.setString(2, passkey);
					ResultSet rs = (OracleResultSet) pst.executeQuery();
					if(rs.next()) {
						if(second.isVisible()==true) {
							JOptionPane.showMessageDialog(frame, "Already an account is logged in. Log out first!");
							return;
						}
						second.setVisible(true);
					} else {
						JOptionPane.showMessageDialog(frame, "Either your password is wrong or you don't have account. Sign in first!");
					}
					pst.close();
					rs.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
				
				
			}
		});
		btnNewButton.setFont(new Font("Lucida Sans", Font.PLAIN, 18));
		btnNewButton.setBounds(164, 371, 166, 39);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel_3 = new JLabel("Don't have an account, create here..");
		lblNewLabel_3.setFont(new Font("Arial Narrow", Font.PLAIN, 20));
		lblNewLabel_3.setBounds(153, 496, 280, 37);
		frame.getContentPane().add(lblNewLabel_3);
		
		JButton btnSignIn = new JButton("Sign in");
		SignIn_Page sip = new SignIn_Page();
		btnSignIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					if(sip.isVisible()==true) {
						JOptionPane.showMessageDialog(frame, "Already an account is logged in.");
						return;
					}
					sip.setVisible(true);
			}
		});
		btnSignIn.setFont(new Font("Lucida Sans", Font.PLAIN, 18));
		btnSignIn.setBounds(433, 496, 280, 37);
		frame.getContentPane().add(btnSignIn);
		
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setIcon(new ImageIcon("C:\\Users\\Rahul\\Desktop\\DBMS_Project\\c20c5b1f-5aa5-428e-89d9-18c6f4595604.png"));
		lblNewLabel_4.setForeground(Color.WHITE);
		lblNewLabel_4.setBackground(Color.WHITE);
		lblNewLabel_4.setBounds(60, 183, 403, 267);
		frame.getContentPane().add(lblNewLabel_4);
	}
}
