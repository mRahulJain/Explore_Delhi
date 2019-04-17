package ERROR;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import oracle.jdbc.OraclePreparedStatement;
import oracle.jdbc.OracleResultSet;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.Toolkit;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class eigth extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	
	Connection conn = null;
	OraclePreparedStatement pst = null;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					eigth frame = new eigth();
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
	public eigth() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\aknit\\Desktop\\DBMS_Project\\USED_IMAGES\\dmrc.png"));
		
		LookAndFeelInfo lf[] = UIManager.getInstalledLookAndFeels();
		try {
			UIManager.setLookAndFeel(lf[1].getClassName());
		} catch (Exception e) {

		}
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(600, 220, 386, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "    DELETE VALUES FROM TABLE      ", TitledBorder.TRAILING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblPrimeLocation = new JLabel("PRIME LOCATION :");
		lblPrimeLocation.setBounds(24, 38, 162, 42);
		lblPrimeLocation.setFont(new Font("Tahoma", Font.PLAIN, 16));
		contentPane.add(lblPrimeLocation);
		
		textField = new JTextField();
		textField.setBounds(183, 44, 162, 35);
		textField.setColumns(10);
		contentPane.add(textField);
		
		JLabel label_1 = new JLabel("METRO STATION :");
		label_1.setBounds(24, 189, 162, 42);
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		contentPane.add(label_1);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(183, 189, 162, 43);
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(comboBox);
		
		JLabel label_2 = new JLabel("");
		label_2.setBounds(50, 294, 268, 57);
		contentPane.add(label_2);
		
		JButton button = new JButton("SEASRCH NEARBY STATION");
		button.setBounds(78, 105, 206, 48);
		button.addActionListener(new ActionListener() {
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
		contentPane.add(button);
		
		
		
		
		
		JButton btnDelete = new JButton("DELETE");
		btnDelete.setBounds(92, 241, 173, 42);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String primeLocation = textField.getText();
				String metroStation = comboBox.getSelectedItem().toString();
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
					String query = "DELETE FROM location WHERE PrimeLocation=? AND MetroStation=?";
					pst = (OraclePreparedStatement) con.prepareStatement(query);
					pst.setString(1, primeLocation);
					pst.setString(2, metroStation);
					pst.executeUpdate();
					label_2.setText("DELETED");
					textField.setText("");
					comboBox.setModel(model);
					pst.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		contentPane.add(btnDelete);
		
		
		
		JButton btnBack = new JButton("BACK");
		btnBack.setBounds(277, 382, 85, 21);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		contentPane.add(btnBack);
	}

}
