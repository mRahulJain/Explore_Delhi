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
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
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

public class SignIn_Page extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	Connection conn = null;
	OraclePreparedStatement pst = null;	
	OraclePreparedStatement pst1 = null;	
	OracleResultSet rs = null;
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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\Rahul\\Desktop\\DBMS_Project\\c20c5b1f-5aa5-428e-89d9-18c6f4595604.png"));
		lblNewLabel.setBounds(59, 77, 401, 418);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Enter your data here...");
		lblNewLabel_1.setForeground(Color.MAGENTA);
		lblNewLabel_1.setFont(new Font("Arial Narrow", Font.PLAIN, 24));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(87, 113, 340, 44);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Mail :");
		lblNewLabel_2.setFont(new Font("Arial Narrow", Font.PLAIN, 21));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(98, 214, 154, 44);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblMail = new JLabel("UserName :");
		lblMail.setHorizontalAlignment(SwingConstants.CENTER);
		lblMail.setFont(new Font("Arial Narrow", Font.PLAIN, 21));
		lblMail.setBounds(98, 279, 154, 44);
		contentPane.add(lblMail);
		
		JLabel lblPassword = new JLabel("Password : ");
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassword.setFont(new Font("Arial Narrow", Font.PLAIN, 21));
		lblPassword.setBounds(98, 344, 154, 44);
		contentPane.add(lblPassword);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon("C:\\Users\\Rahul\\Desktop\\DBMS_Project\\240px-Delhi_Metro_logo.svg.png"));
		lblNewLabel_3.setBounds(565, 36, 267, 247);
		contentPane.add(lblNewLabel_3);
		
		textField = new JTextField();
		textField.setBounds(262, 214, 162, 44);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(262, 276, 162, 44);
		contentPane.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(262, 344, 162, 44);
		contentPane.add(textField_2);
		
		JTextArea textArea = new JTextArea();
		textArea.setFont(new Font("Arial Narrow", Font.PLAIN, 26));
		textArea.setToolTipText("RESULT : ");
		textArea.setLineWrap(true);
		textArea.setBounds(481, 384, 393, 69);
		contentPane.add(textArea);
		
		JButton btnNewButton = new JButton("Sign IN");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//String mail = textField.getText();
				String username = textField_1.getText();
				String passkey = textField_2.getText();
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
						textArea.setText("Username already in use. Choose another.");
						textField.setText("");
						textField_1.setText("");
						textField_2.setText("");
					} else {
						String query = "INSERT INTO users VALUES('"+username+"','"+passkey+"')";
						pst1 = (OraclePreparedStatement) con.prepareStatement(query);
						pst1.executeUpdate(query);
						textArea.setText("Account successfully signed in. Go back and log in...");
					}
					pst.close();
					rs.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setFont(new Font("Lucida Sans", Font.PLAIN, 22));
		btnNewButton.setBounds(163, 409, 205, 44);
		contentPane.add(btnNewButton);
		
		
		
		JButton btnNewButton_1 = new JButton("BACK");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Initial_Page.txtSaasad.setText("");
				Initial_Page.passwordField.setText("");
				dispose();
			}
		});
		btnNewButton_1.setFont(new Font("Lucida Sans", Font.PLAIN, 19));
		btnNewButton_1.setBounds(756, 516, 118, 34);
		contentPane.add(btnNewButton_1);
		
		
	}
}
