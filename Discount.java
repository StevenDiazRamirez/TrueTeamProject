import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Discount {

    private int discountID;
    private String type;

    private static Connection con = DBSConnection.getConnection();

    public Discount(String type){
        this.type = type;
    }

    public static int getLatestDiscountID() {
        try{
            String query = "SELECT MAX(DiscountID) from discount";
            PreparedStatement stm = con.prepareStatement(query);
            ResultSet rs =stm.executeQuery();

            while(rs.next()){
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static void addDiscount(Discount discount) {
        try {
            String query = "INSERT INTO discount (`DiscountID`, `DiscountType`) VALUES " +
                    "('" +  discount.getDiscountID() + "', '" + discount.getType() + "')";

            PreparedStatement stm = con.prepareStatement(query);
            stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getDiscountID() {
        return discountID;
    }

    public void setDiscountID(int discountID) {
        this.discountID = discountID;
    }
}
