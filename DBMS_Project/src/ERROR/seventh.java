package ERROR;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import oracle.jdbc.OraclePreparedStatement;
import oracle.jdbc.OracleResultSet;

import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class seventh extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_2;
	private JComboBox comboBox;
	private JButton btnSeasrchMetroStation;
	private JLabel lblNewLabel;
	private JButton btnNewButton;

	/**
	 * Launch the application.
	 */
	
	Connection conn = null;
	OraclePreparedStatement pst = null;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					seventh frame = new seventh();
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
	public seventh() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\aknit\\Desktop\\DBMS_Project\\USED_IMAGES\\dmrc.png"));
		
		LookAndFeelInfo lf[] = UIManager.getInstalledLookAndFeels();
		try {
			UIManager.setLookAndFeel(lf[1].getClassName());
		} catch (Exception e) {

		}
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(600, 220, 386, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "    UPDATE VALUES IN TABLE    ", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblPrimeLoaction = new JLabel("PRIME LOCATION :");
		lblPrimeLoaction.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPrimeLoaction.setBounds(10, 38, 162, 42);
		contentPane.add(lblPrimeLoaction);
		
		JLabel lblMetroStation = new JLabel("METRO STATION :");
		lblMetroStation.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblMetroStation.setBounds(10, 158, 162, 42);
		contentPane.add(lblMetroStation);
		
		JLabel lblDistance = new JLabel("NEW DISTANCE :");
		lblDistance.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblDistance.setBounds(10, 210, 162, 42);
		contentPane.add(lblDistance);
		
		textField = new JTextField();
		textField.setBounds(182, 44, 180, 35);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(182, 216, 180, 35);
		contentPane.add(textField_2);
		

		lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(33, 314, 311, 59);
		contentPane.add(lblNewLabel);
		
		comboBox = new JComboBox();
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboBox.setBounds(182, 158, 180, 43);
		contentPane.add(comboBox);
		
		JButton btnUpdate = new JButton("UPDATE");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String primeLocation = textField.getText();
				String metroStation = comboBox.getSelectedItem().toString();
				String distance = textField_2.getText();
				try {
					try {
						Class.forName("oracle.jdbc.driver.OracleDriver");
					} catch (ClassNotFoundException e1) {
						JOptionPane.showMessageDialog(null, "Error: unable to load driver class!");
						e1.printStackTrace();
					}
					DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
					Connection con = (Connection) DriverManager.getConnection("jdbc:oracle:thin:@localhost:1599:xe",
							"system", "rahul");
					String query = "UPDATE location SET Distance=? WHERE PrimeLocation=? AND MetroStation=?";
					pst = (OraclePreparedStatement) con.prepareStatement(query);
					pst.setString(1, distance);
					pst.setString(2, primeLocation);
					pst.setString(3, metroStation);
					pst.executeUpdate();
					lblNewLabel.setText("UPDATED");
					textField.setText("");
					textField_2.setText("");
					comboBox.setModel(model);
					pst.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnUpdate.setBounds(119, 262, 146, 42);
		contentPane.add(btnUpdate);
		
		
		
		btnSeasrchMetroStation = new JButton("SEASRCH NEARBY STATION");
		btnSeasrchMetroStation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String primeLocation = textField.getText();
				try {
					try {
						Class.forName("oracle.jdbc.driver.OracleDriver");
					} catch (ClassNotFoundException e1) {
						JOptionPane.showMessageDialog(null, "Error: unable to load driver class!");
						e1.printStackTrace();
					}
					Connection con = (Connection) DriverManager.getConnection("jdbc:oracle:thin:@localhost:1599:xe",
							"system", "rahul");
					String query = "SELECT MetroStation from location WHERE PrimeLocation=?";
					pst = (OraclePreparedStatement) con.prepareStatement(query);
					pst.setString(1, primeLocation);
					ResultSet rs = (OracleResultSet) pst.executeQuery();
					DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
					comboBox.setModel(model);
					while(rs.next()) {
						String value = rs.getString("MetroStation");
						model.addElement(value);
					}
					pst.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnSeasrchMetroStation.setBounds(94, 103, 179, 35);
		contentPane.add(btnSeasrchMetroStation);
		
		
		btnNewButton = new JButton("BACK");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnNewButton.setBounds(294, 382, 68, 21);
		contentPane.add(btnNewButton);
	}

}
