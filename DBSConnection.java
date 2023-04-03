
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/** Creates connections between the driver and the java database.
 *
 *
 */
public class DBSConnection {

    private static Connection con;

    public static Connection getConnection(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ats","root","8W3hMJoZ");
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return con;
    }

  /*  public static void main(String[] args) {
        Connection con = DBSConnection.getConnection();

        try {
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM java_system_db.people");
            while (rs.next()) {
                System.out.println(rs.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    } */
}