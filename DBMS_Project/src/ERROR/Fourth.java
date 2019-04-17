package ERROR;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import oracle.jdbc.OraclePreparedStatement;
import oracle.jdbc.OracleResultSet;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.JTextField;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import java.awt.Toolkit;
import javax.swing.ImageIcon;

public class Fourth extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	JInternalFrame ifr;
	/**
	 * Launch the application.
	 */

	Connection conn = null;
	OraclePreparedStatement pst = null;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Fourth frame = new Fourth();
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
	public Fourth() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\aknit\\Desktop\\DBMS_Project\\USED_IMAGES\\dmrc.png"));

		LookAndFeelInfo lf[] = UIManager.getInstalledLookAndFeels();
		try {
			UIManager.setLookAndFeel(lf[1].getClassName());
		} catch (Exception e) {

		}
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setSize(400,450);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		ifr = new JInternalFrame("PRIME LOCATION TO METRO");
		ifr.setFrameIcon(new ImageIcon("C:\\Users\\aknit\\Desktop\\DBMS_Project\\USED_IMAGES\\dmrc.png"));
		ifr.setBounds(0, 0, 390, 450);
		contentPane.add(ifr);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(590, 150, 406, 489);
		
		
		contentPane.add(ifr);
		ifr.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Enter Metro Station :");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 50, 200, 25);
		ifr.getContentPane().add(lblNewLabel);

		textField = new JTextField();
		textField.setBounds(196, 50, 170, 30);
		ifr.getContentPane().add(textField);
		textField.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 179, 356, 172);
		ifr.getContentPane().add(scrollPane);
		
		JList list = new JList();
		scrollPane.setViewportView(list);

		JButton btnNewButton = new JButton("SEARCH PLACES TO SEE");
		btnNewButton.setBounds(96, 109, 190, 49);
		ifr.getContentPane().add(btnNewButton);
		
				
		
				JButton btnNewButton_1 = new JButton("Go Back");
				btnNewButton_1.setBounds(253, 373, 113, 38);
				ifr.getContentPane().add(btnNewButton_1);
				btnNewButton_1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Second.rdbtnMetroToPrime.setSelected(false);
						dispose();
					}
				});
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int intFlag = 0;
				String prime = textField.getText();
				try {

					try {
						Class.forName("oracle.jdbc.driver.OracleDriver");
					} catch (ClassNotFoundException e1) {
						JOptionPane.showMessageDialog(null, "Error: unable to load driver class!");
						e1.printStackTrace();
					}
					Connection con = (Connection) DriverManager.getConnection("jdbc:oracle:thin:@localhost:1599:xe",
							"system", "rahul");
					String query = "select PrimeLocation, Distance from location where MetroStation=?";
					pst = (OraclePreparedStatement) con.prepareStatement(query);
					pst.setString(1, prime);
					ResultSet rs = (OracleResultSet) pst.executeQuery();
					DefaultListModel<String> model = new DefaultListModel<String>();
					while (rs.next()) {
						intFlag = 1;
						String value = rs.getString("PrimeLocation");
						String dist = rs.getString("Distance");
						String addv = "" + value + " is " + dist + "km far";
						list.setModel(model);
						model.addElement(addv);
					}

					if (intFlag == 0) {
						JOptionPane.showMessageDialog(null,
								"There is no such Metro Station in our database. You can add though!!");
					}
					JList<String> list = new JList<String>(model);
					contentPane.add(list);
					pst.close();
					rs.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		ifr.setVisible(true);
	}

}
