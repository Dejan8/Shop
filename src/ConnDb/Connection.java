package ConnDb;

import java.sql.DriverManager;
import java.sql.SQLException;

public class Connection {

    private static java.sql.Connection con = null;

    public static java.sql.Connection getConnection() throws SQLException {
        if (con != null) {
            return con;
        } else {
            try {
                String driver = "com.mysql.jdbc.Driver";
                String url = "jdbc:mysql://www.offthegrid.in.rs:3306/admin_deki";
                String user = "admin_deki";
                String password = "Deki!98!";
                Class.forName(driver);
                con = DriverManager.getConnection("jdbc:mysql://www.offthegrid.in.rs/admin_deki", "admin_deki", "Deki!98!");
            } catch (ClassNotFoundException | SQLException cnfe) {
                System.out.println(cnfe);
            }
            return con;
        }
    }
}
