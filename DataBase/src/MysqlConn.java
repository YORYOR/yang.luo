import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MysqlConn {

	public Connection connMysql() {
		try {
			Class.forName("com.mysql.jdbc.Driver"); // 加载MYSQL JDBC驱动程序
			System.out.println("Success loading Mysql Driver!");
		} catch (Exception e) {
			System.out.print("Error loading Mysql Driver!");
			e.printStackTrace();
		}

		Connection connect;
		try {
			connect = setMysqlParam(Constants.mysqlUrl, Constants.user,
					Constants.password);
			return connect;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("mysql connect failed!");
		}
		return null;

	}

	private Connection setMysqlParam(String url, String user, String password)
			throws SQLException {

		Connection connect = DriverManager.getConnection(url, user, password);

		return connect;

	}
}
