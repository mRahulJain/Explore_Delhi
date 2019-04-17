package ERROR;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.border.TitledBorder;

import oracle.jdbc.OraclePreparedStatement;
import oracle.jdbc.OracleResultSet;

import javax.swing.border.EtchedBorder;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;

import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class sixth extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	Connection conn = null;
	OraclePreparedStatement pst = null;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					sixth frame = new sixth();
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
	public sixth() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\aknit\\Desktop\\DBMS_Project\\USED_IMAGES\\dmrc.png"));
		
		LookAndFeelInfo lf[] = UIManager.getInstalledLookAndFeels();
		try {
			UIManager.setLookAndFeel(lf[1].getClassName());
		} catch (Exception e) {

		}
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(600, 220, 386, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "   INSERT VALUES TO TABLE      ", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("PRIME LOCATION :");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(10, 42, 146, 43);
		contentPane.add(lblNewLabel);
		
		JLabel lblMetroStaion = new JLabel("METRO STAION :");
		lblMetroStaion.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblMetroStaion.setBounds(10, 115, 146, 43);
		contentPane.add(lblMetroStaion);
		
		JLabel lblDistance = new JLabel("DISTANCE :");
		lblDistance.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblDistance.setBounds(10, 178, 146, 43);
		contentPane.add(lblDistance);
		
		JLabel label_2 = new JLabel("");
		label_2.setBounds(38, 289, 290, 62);
		contentPane.add(label_2);
		
		textField = new JTextField();
		textField.setBounds(185, 42, 165, 43);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(185, 178, 165, 43);
		contentPane.add(textField_2);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"<--Select Metro Station-->", "Central Secretariat", "Chandini Chowk", "Civil Lines", "GTB Nagar", "Hauz Khas", "JLN Stadium", "Karol Bagh\t", "Kashmere Gate", "Kohat Enclave\t", "Model Town", "Netaji Subhash Place", "New Delhi", "Qutub Minar", "Rajiv Chowk", "Rajouri Garden", "Rohini West", "Saket", "Shastri Park", "Subhash Nagar", "Tis Hazari", "Vidhan Sabha"}));
		comboBox.setBounds(185, 113, 165, 43);
		contentPane.add(comboBox);
		
		JButton btnInsert = new JButton("INSERT");
		btnInsert.addActionListener(new ActionListener() {
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
					Connection con = (Connection) DriverManager.getConnection("jdbc:oracle:thin:@localhost:1599:xe",
							"system", "rahul");
					String query = "INSERT INTO location VALUES('"+primeLocation+"','"+metroStation+"','"+distance+"')";
					pst = (OraclePreparedStatement) con.prepareStatement(query);
					pst.executeUpdate();
					label_2.setText("INSERTED.");
					textField.setText("");
					textField_2.setText("");
					comboBox.setSelectedIndex(0);
					pst.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnInsert.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnInsert.setBounds(106, 231, 136, 48);
		contentPane.add(btnInsert);
		
		
		
		JButton btnNewButton = new JButton("BACK");
		btnNewButton.addActionListener(new ActionListener() {

	public void actionPerformed(ActionEvent arg0) {
		dispose();
	}
});btnNewButton.setBounds(295,382,67,21);contentPane.add(btnNewButton);}}
