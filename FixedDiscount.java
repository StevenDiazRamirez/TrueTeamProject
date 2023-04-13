import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FixedDiscount extends Discount{

    private int fixedID;
    private float amount;

    private static Connection con = DBSConnection.getConnection();

    public FixedDiscount(int discountID, float amount){
        super("Fixed");
        super.setDiscountID(discountID);
        this.amount = amount;
    }

    /**
     * Adds a fixed discount to the database
     * @param fixedDiscount
     */
    public static void addFixedDiscount(FixedDiscount fixedDiscount) {
        try {
            String query = "INSERT INTO fixeddiscount (fixedID, `fixedDiscountID`, `amount`) VALUES " +
                    "('" +  fixedDiscount.getFixedID() + "', '" + fixedDiscount.getDiscountID() +
                    "', '" + fixedDiscount.getAmount() +"')";

            PreparedStatement stm = con.prepareStatement(query);
            stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets the highest fixed discount ID from the database
     * @return
     */
    public static int getLatestFixedDiscountID() {
        try{
            String query = "SELECT MAX(fixedID) from fixeddiscount";
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

    public int getFixedID() {
        return fixedID;
    }

    public void setFixedID(int fixedID) {
        this.fixedID = fixedID;
    }
}
