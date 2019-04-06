package ERROR;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import oracle.jdbc.OraclePreparedStatement;
import oracle.jdbc.OracleResultSet;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.AbstractListModel;
import javax.swing.DefaultListModel;
import javax.swing.JRadioButton;
import javax.swing.JScrollBar;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JScrollPane;

public class Second extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	static int flag = 0;

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
					Second frame = new Second();
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
	public Second() {

		LookAndFeelInfo lf[] = UIManager.getInstalledLookAndFeels();
		try {
			UIManager.setLookAndFeel(lf[1].getClassName());
		} catch (Exception e) {

		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Enter prime locations and see your nearest metro");
		lblNewLabel.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 19));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 11, 573, 79);
		contentPane.add(lblNewLabel);

		JLabel lblPrimeLocationNear = new JLabel("Prime Location near you : ");
		lblPrimeLocationNear.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblPrimeLocationNear.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrimeLocationNear.setBounds(32, 113, 283, 48);
		contentPane.add(lblPrimeLocationNear);

		JLabel lblPrimeLocationYou = new JLabel("Prime Location you want to go : ");
		lblPrimeLocationYou.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrimeLocationYou.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblPrimeLocationYou.setBounds(32, 188, 283, 48);
		contentPane.add(lblPrimeLocationYou);

		textField = new JTextField();
		textField.setBounds(339, 113, 177, 48);
		contentPane.add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(339, 188, 177, 48);
		contentPane.add(textField_1);

		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(339, 309, 177, 48);
		contentPane.add(textField_2);

		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(339, 384, 177, 48);
		contentPane.add(textField_3);
		

		JLabel lblMetroStationFrom = new JLabel("Metro station FROM : ");
		lblMetroStationFrom.setHorizontalAlignment(SwingConstants.CENTER);
		lblMetroStationFrom.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblMetroStationFrom.setBounds(32, 307, 283, 48);
		contentPane.add(lblMetroStationFrom);

		JLabel lblMetroStationTo = new JLabel("Metro station TO : ");
		lblMetroStationTo.setHorizontalAlignment(SwingConstants.CENTER);
		lblMetroStationTo.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblMetroStationTo.setBounds(32, 384, 283, 48);
		contentPane.add(lblMetroStationTo);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(753, 113, 121, 123);
		contentPane.add(scrollPane);
		
				JList<String> list_1 = new JList<String>();
				scrollPane.setViewportView(list_1);
				list_1.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						String val = list_1.getSelectedValue();
						String toBePrinted = "";
						for (int i = 0; i < val.length(); i++) {
							if (val.charAt(i) == '(') {
								break;
							}
							toBePrinted = toBePrinted + val.charAt(i);
						}
						if (flag == 1) {
							textField_2.setText(toBePrinted);
						} else {
							textField_3.setText(toBePrinted);

						}
					}
				});
				list_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
				
				
		JButton btnSearchNearestMetro = new JButton("Search nearest metro");
		btnSearchNearestMetro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int intFlag = 0;
				flag = 1;
				String prime = textField.getText();
				int min = Integer.MAX_VALUE;
				String nearestM = "";
				try {

					try {
						Class.forName("oracle.jdbc.driver.OracleDriver");
					} catch (ClassNotFoundException e1) {
						JOptionPane.showMessageDialog(null, "Error: unable to load driver class!");
						e1.printStackTrace();
					}
					Connection con = (Connection) DriverManager.getConnection("jdbc:oracle:thin:@localhost:1599:xe",
							"system", "rahul");
					String query = "SELECT * FROM pl where PrimeLoc=?";
					pst = (OraclePreparedStatement) con.prepareStatement(query);
					pst.setString(1, prime);
					ResultSet rs = (OracleResultSet) pst.executeQuery();
					DefaultListModel<String> model = new DefaultListModel<String>();
					while (rs.next()) {
						intFlag = 1;
						int distt = rs.getInt("Distance");
						String value = rs.getString("NearestMetro");
						String dist = rs.getString("Distance");
						String addv = "" + value + " (" + dist + " km)";
						if (min > distt) {
							min = distt;
							nearestM = value;
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
					textField_2.setText(nearestM);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnSearchNearestMetro.setBounds(526, 111, 204, 48);
		contentPane.add(btnSearchNearestMetro);

		JButton button = new JButton("Search nearest metro");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				flag = 0;
				int intFlag = 0;
				String prime = textField_1.getText();
				int min = Integer.MAX_VALUE;
				String nearestM = "";
				try {

					try {
						Class.forName("oracle.jdbc.driver.OracleDriver");
					} catch (ClassNotFoundException e1) {
						JOptionPane.showMessageDialog(null, "Error: unable to load driver class!");
						e1.printStackTrace();
					}
					Connection con = (Connection) DriverManager.getConnection("jdbc:oracle:thin:@localhost:1599:xe",
							"system", "rahul");
					String query = "SELECT * FROM pl where PrimeLoc=?";
					pst = (OraclePreparedStatement) con.prepareStatement(query);
					pst.setString(1, prime);
					ResultSet rs = (OracleResultSet) pst.executeQuery();

					DefaultListModel<String> model = new DefaultListModel<String>();
					while (rs.next()) {
						intFlag = 1;
						int distt = rs.getInt("Distance");
						String value = rs.getString("NearestMetro");
						String dist = rs.getString("Distance");
						String addv = "" + value + " (" + dist + " km)";
						if (min > distt) {
							min = distt;
							nearestM = value;
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
					textField_3.setText(nearestM);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		button.setBounds(526, 188, 204, 48);
		contentPane.add(button);
		
				
		

	}
}
