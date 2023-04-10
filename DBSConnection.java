
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
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ats","root","");
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return con;
    }
}