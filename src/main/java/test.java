import java.sql.Connection;
import java.sql.DriverManager;

public class test {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/studentmanagement", "root", "omar");
            System.out.println("Connection successful!");
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
