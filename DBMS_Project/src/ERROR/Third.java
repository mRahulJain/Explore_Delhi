package ERROR;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import oracle.jdbc.OraclePreparedStatement;
import oracle.jdbc.OracleResultSet;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JInternalFrame;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.ImageIcon;

public class Third extends JFrame {

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
					Third frame = new Third();
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
	public Third() {
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
		ifr.setIconifiable(true);
		ifr.setBounds(0, 0, 390, 450);
		contentPane.add(ifr);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(590, 150, 406, 489);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 179, 356, 176);
		ifr.getContentPane().add(scrollPane);
		
		JList list_1 = new JList();
		scrollPane.setViewportView(list_1);
		
		textField = new JTextField();
		textField.setBounds(196, 50, 170, 30);
		ifr.getContentPane().add(textField);
		textField.setColumns(10);
		
		
		contentPane.add(ifr);

		//f.setVisible(true);
		ifr.getContentPane().setLayout(null);
		
				JLabel lblNewLabel = new JLabel("Enter the prime location : ");
				lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
				lblNewLabel.setBounds(10, 50, 200, 25);
				ifr.getContentPane().add(lblNewLabel);
				lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
				

				JButton btnSearch = new JButton("SEARCH");
				btnSearch.addActionListener(new ActionListener() {
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
							String query = "select location.MetroStation, location.Distance, m.MetroColor from location join station s on "
									+ "location.MetroStation = s.MetroStation join metro m on s.MetroID = m.MetroID where PrimeLocation=?";
							pst = (OraclePreparedStatement) con.prepareStatement(query);
							pst.setString(1, prime);
							ResultSet rs = (OracleResultSet) pst.executeQuery();
							DefaultListModel<String> model = new DefaultListModel<String>();
							while (rs.next()) {
								intFlag = 1;
								String value = rs.getString("MetroStation");
								String dist = rs.getString("Distance");
								String color = rs.getString("MetroColor");
								String addv = "";
								if (value.equals("Kashmere Gate")) {
									addv = "" + value + " metro station is " + dist + "km far and has two lines: "+ color +" and red";
								} else if (value.equals("Rajiv Chowk")) {
									addv = "" + value + " metro station is " + dist + "km far and has two lines: "+ color +" and blue";
								} else {
									addv = "" + value + " metro station is " + dist + "km far and has one line: "+ color;
								}
								list_1.setModel(model);
								model.addElement(addv);
							}

							if (intFlag == 0) {
								JOptionPane.showMessageDialog(null,
										"There is no such prime location in our database. You can add though!!");
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
				btnSearch.setBounds(88, 108, 176, 42);//10, 406, 13
				ifr.getContentPane().add(btnSearch);
		
						JButton btnNewButton = new JButton("GO BACK");
						btnNewButton.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								Second.rdbtnNewRadioButton.setSelected(false);
								dispose();
							}
						});
						btnNewButton.setBounds(251, 369, 117, 42);
						ifr.getContentPane().add(btnNewButton);

					
						
						

								ifr.setVisible(true);
	}
}
