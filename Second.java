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

import java.awt.Font;
import java.awt.Toolkit;

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
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JRadioButton;
import javax.swing.JScrollBar;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JScrollPane;
import javax.swing.border.BevelBorder;
import java.awt.Color;

public class Second extends JFrame {

	private JPanel contentPane;
	static int flag = 0;
	static JRadioButton rdbtnNewRadioButton = new JRadioButton("PRIME LOCATION TO METRO");
	static JRadioButton rdbtnMetroToPrime = new JRadioButton("METRO TO PRIME LOCATION");

	/**
	 * Launch the application.
	 */
	Connection conn = null;
	OraclePreparedStatement pst = null;
	private final JButton btnNewButton_1 = new JButton("CHANGE OUR DATABASE TABLE");
	private final JPanel panel = new JPanel();

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
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\aknit\\Desktop\\DBMS_Project\\USED_IMAGES\\dmrc.png"));

		LookAndFeelInfo lf[] = UIManager.getInstalledLookAndFeels();
		try {
			UIManager.setLookAndFeel(lf[1].getClassName());
		} catch (Exception e) {

		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Third third = new Third();
		
		
		Fourth fourth = new Fourth();
		
		JButton btnNewButton = new JButton("LOGOUT");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Initial_Page.txtSaasad.setText("");
				Initial_Page.passwordField.setText("");
				dispose();
			}
		});
		btnNewButton.setBounds(41, 446, 171, 45);
		contentPane.add(btnNewButton);
		Fifth fifth = new Fifth();
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fifth.isVisible()==true) {
					JOptionPane.showMessageDialog(null, "Already a page is open close it first.");
					return;
				}
				fifth.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(76, 312, 367, 65);
		
		contentPane.add(btnNewButton_1);
		

		panel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel.setBounds(41, 33, 435, 230);
		
		contentPane.add(panel);
				panel.setLayout(null);
		
				JLabel lblNewLabel = new JLabel("Please select any one of the following option............");
				lblNewLabel.setBounds(10, 5, 415, 45);
				panel.add(lblNewLabel);
				lblNewLabel.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 19));
				lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
				rdbtnNewRadioButton.setBounds(48, 79, 300, 51);
				panel.add(rdbtnNewRadioButton);
				rdbtnNewRadioButton.setFont(new Font("Segoe UI", Font.PLAIN, 18));
				rdbtnNewRadioButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(third.isVisible()==true) {
							JOptionPane.showMessageDialog(null, "Already a page is open. Close it first.");
							return;
						}
						third.setVisible(true);
						/*JInternalFrame ifr=new JInternalFrame();
						ifr.setSize(400,500);
						JFrame f=new JFrame();
						f.setSize(400, 450);
						f.setLocation(590, 150);
						f.add(ifr);
						ifr.setVisible(true);
						f.setVisible(true);*/
					}
					});
				
				
				rdbtnMetroToPrime.setBounds(48, 141, 300, 51);
				panel.add(rdbtnMetroToPrime);
				rdbtnMetroToPrime.setFont(new Font("Segoe UI", Font.PLAIN, 18));
				rdbtnMetroToPrime.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(fourth.isVisible()==true) {
							JOptionPane.showMessageDialog(null, "Already a page is open close it first.");
							return;
						}
						fourth.setVisible(true);
					}
				});
				ButtonGroup d=new ButtonGroup();
				d.add(rdbtnNewRadioButton);
				d.add(rdbtnMetroToPrime);

	}
}
