package ERROR;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.table.DefaultTableModel;

import oracle.jdbc.OraclePreparedStatement;
import oracle.jdbc.OracleResultSet;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.border.BevelBorder;
import javax.swing.ImageIcon;
import javax.swing.JRadioButton;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Toolkit;

public class Fifth extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	
	Connection conn = null;
	OraclePreparedStatement pst = null;
	private JScrollPane scrollPane;
	private JPanel panel;
	private JRadioButton rdbtnInsert;
	private JRadioButton rdbtnUpdate;
	private JRadioButton rdbtnDelete;
	private JLabel lblToTableLocation;
	private JButton btnBack;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Fifth frame = new Fifth();
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
	public Fifth() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\aknit\\Desktop\\DBMS_Project\\USED_IMAGES\\dmrc.png"));
		
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
		
		panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel.setBounds(20, 56, 467, 497);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnViewTable = new JButton("VIEW TABLE");
		btnViewTable.setBounds(151, 13, 157, 45);
		panel.add(btnViewTable);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 102, 450, 385);
		panel.add(scrollPane);
		table = new JTable();
		table.setEnabled(false);
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Prime Location", "Metro Station", "Distance"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, Object.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		
		rdbtnInsert = new JRadioButton("INSERT");
		sixth sixth = new sixth();
		rdbtnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(sixth.isVisible()==true) {
					JOptionPane.showMessageDialog(null, "Already a page is open. Close it first.");
					return;
				}
				sixth.setVisible(true);
			}
		});
		rdbtnInsert.setFont(new Font("Tahoma", Font.PLAIN, 16));
		rdbtnInsert.setBounds(520, 56, 106, 41);
		contentPane.add(rdbtnInsert);
		
		rdbtnUpdate = new JRadioButton("UPDATE");
		seventh seventh = new seventh();
		rdbtnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(seventh.isVisible()==true) {
					JOptionPane.showMessageDialog(null, "Already a page is open. Close it first.");
					return;
				}
				seventh.setVisible(true);
			}
		});
		rdbtnUpdate.setFont(new Font("Tahoma", Font.PLAIN, 16));
		rdbtnUpdate.setBounds(628, 56, 114, 41);
		contentPane.add(rdbtnUpdate);
		
		rdbtnDelete = new JRadioButton("DELETE");
		eigth eigth = new eigth();
		rdbtnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(eigth.isVisible()==true) {
					JOptionPane.showMessageDialog(null, "Already a page is open. Close it first.");
					return;
				}
				eigth.setVisible(true);
			}
		});
		rdbtnDelete.setFont(new Font("Tahoma", Font.PLAIN, 16));
		rdbtnDelete.setBounds(744, 56, 109, 41);
		contentPane.add(rdbtnDelete);
		
		lblToTableLocation = new JLabel("To Table Location");
		lblToTableLocation.setHorizontalAlignment(SwingConstants.CENTER);
		lblToTableLocation.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblToTableLocation.setBounds(507, 10, 335, 52);
		contentPane.add(lblToTableLocation);
		table.getColumnModel().getColumn(0).setPreferredWidth(153);
		table.getColumnModel().getColumn(1).setPreferredWidth(151);
		table.getColumnModel().getColumn(2).setPreferredWidth(137);
		btnViewTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				table.enable();
				int intFlag = 0;
				try {

					try {
						Class.forName("oracle.jdbc.driver.OracleDriver");
					} catch (ClassNotFoundException e1) {
						JOptionPane.showMessageDialog(null, "Error: unable to load driver class!");
						e1.printStackTrace();
					}
					Connection con = (Connection) DriverManager.getConnection("jdbc:oracle:thin:@localhost:1599:xe",
							"system", "rahul");
					String query = "select * from location";
					pst = (OraclePreparedStatement) con.prepareStatement(query);
					ResultSet rs = (OracleResultSet) pst.executeQuery();
					DefaultTableModel model = new DefaultTableModel(new String[] { "Prime Location", "Metro Station", "Distance"},0);
					table.setModel(model);
					while (rs.next()) {
						intFlag = 1;
						String PrimeLocation = rs.getString("PrimeLocation");
						String MetroStation = rs.getString("MetroStation");
						String Distance = rs.getString("Distance");
						model.addRow(new Object[] {PrimeLocation, MetroStation, Distance});
					}

					if (intFlag == 0) {
						JOptionPane.showMessageDialog(null,
								"There is no such Metro Station in our database. You can add though!!");
					}
					pst.close();
					rs.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		ButtonGroup d=new ButtonGroup();
		d.add(rdbtnInsert);
		d.add(rdbtnUpdate);
		d.add(rdbtnDelete);
		
		btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnBack.setBounds(0, 0, 100, 29);
		contentPane.add(btnBack);
		
	}
	
	public static ImageIcon resize(ImageIcon im,int w,int h)
	{
	BufferedImage bi=new BufferedImage(w, h, BufferedImage.TRANSLUCENT);
	Graphics2D gd=(Graphics2D)bi.createGraphics();
	gd.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY));
	gd.drawImage(im.getImage(), 0, 0, w, h, null);
	gd.dispose();
	return new ImageIcon(bi);
	}
}
