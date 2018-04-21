package mydatabase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class MySQLHelper {
	public static Connection getConnection(String dataBaseName) throws SQLException {
		String dataBaseConnection = String.format("jdbc:mysql://localhost:3306/%s?useSSL=false&useUnicode=true&characterEncoding=utf-8", dataBaseName);
		Connection con = DriverManager.getConnection(dataBaseConnection, "root", "coderslab");
		return con;
	}

	public static ResultSet executeQuery(Connection con, String sql, String... conditions) throws SQLException {
		PreparedStatement stm = con.prepareStatement(sql);
		for (int i = 1; i <= conditions.length; i++) {
			stm.setString(i, conditions[i - 1]);
		}
		ResultSet rs = stm.executeQuery();
		return rs;
	}

	public static void executeUpdate(Connection con, String sql, String... conditions) throws SQLException {
		PreparedStatement stm = con.prepareStatement(sql);
		for (int i = 1; i <= conditions.length; i++) {
			stm.setString(i, conditions[i - 1]);
		}
		stm.executeUpdate();
	}

	public static void printResultSet(ResultSet rs) throws SQLException {
		ResultSetMetaData metaData = rs.getMetaData();
		String message = "";
		int columnsCount = metaData.getColumnCount();
		for (int i = 1; i <= columnsCount; i++) {
			message += metaData.getColumnLabel(i) + " = %s,";
		}
		while (rs.next()) {

			String[] data = new String[columnsCount];
			for (int i = 1; i <= columnsCount; i++) {
				data[i - 1] = rs.getString(i);
			}
			System.out.println(String.format(message, (Object[]) data));
		}
	}
}
	