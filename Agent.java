import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class Agent {

    private String name;
    private String address;

    public Agent(String name,String address){
        this.name = name;
        this.address = address;
    }

    public static void addAgent(String name, String address) {
        try {
            Connection con = DBSConnection.getConnection();

            String searchQuery = "SELECT MAX(ID) FROM travelagent";
            PreparedStatement highestID = con.prepareStatement(searchQuery);


            ResultSet rs = highestID.executeQuery(searchQuery);
            int highestAgentID = 1;
            while (rs.next()) {
                highestAgentID = rs.getInt(1) + 1;
            }

            String addQuery = "INSERT INTO travelagent (`ID`, `name`, `address`) VALUES ('" + highestAgentID +
                    "', '" + name + "', '" + address + "');";

            highestID.executeUpdate(addQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
