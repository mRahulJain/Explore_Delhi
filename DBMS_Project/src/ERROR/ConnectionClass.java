package ERROR;

import java.sql.*;

import javax.swing.JOptionPane;
public class ConnectionClass {
	public static Connection dbconnect() {
		try {
			Class.forName("Oracle.jdbc.OracleDriver");
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "SYSTEM#2429");
			return conn;
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null, e);
			return null;
		}
	}
}
