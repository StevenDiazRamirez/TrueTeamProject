import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FlexibleDiscount extends Discount{

    private int flexibleID;
    private float lowerRange;
    private float upperRange;
    private float amount;

    private static Connection con = DBSConnection.getConnection();

    public FlexibleDiscount(float lowerRange, float upperRange, float amount, int discountID){
        super("Flexible");
        super.setDiscountID(discountID);
        this.lowerRange = lowerRange;
        this.upperRange = upperRange;
        this.amount = amount;

    }

    public static void addFlexibleDiscount(FlexibleDiscount flexibleDiscount) {
        try {
            String query = "INSERT INTO flexiblediscount (flexibleID, `flexibleDiscountID`, `discountAmount`) VALUES " +
                    "('" +  flexibleDiscount.getFlexibleID() + "', '" + flexibleDiscount.getDiscountID() +
                    "', '" + flexibleDiscount.getAmount() +"')";

            PreparedStatement stm = con.prepareStatement(query);
            stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static int getLatestFlexibleDiscountID() {
        try{
            String query = "SELECT MAX(flexibleID) from flexiblediscount";
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

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public int getFlexibleID() {
        return flexibleID;
    }

    public void setFlexibleID(int flexibleID) {
        this.flexibleID = flexibleID;
    }

    public float getLowerRange() {
        return lowerRange;
    }

    public void setLowerRange(float lowerRange) {
        this.lowerRange = lowerRange;
    }

    public float getUpperRange() {
        return upperRange;
    }

    public void setUpperRange(float upperRange) {
        this.upperRange = upperRange;
    }
}
