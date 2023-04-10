import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class Commission {

    private int commissionID;
    private float amount;
    private Date startDate;
    private Date endDate;
    private int blankType;
    private boolean isExpired;

    public Commission(float amount, Date startDate, int blankType){
        this.amount = amount;
        this.startDate = startDate;
        this.blankType = blankType;
    }

    public Commission(float amount, Date startDate, Date endDate, int blankType){
        this.amount = amount;
        this.startDate = startDate;
        this.endDate = endDate;
        this.blankType = blankType;
    }

    public static void addCommission(float amount, int blankType, Date endDate) {
        try {
            Connection con = DBSConnection.getConnection();

            String searchQuery = "SELECT MAX(CommissionID) FROM commission";
            PreparedStatement highestCommissionID = con.prepareStatement(searchQuery);


            ResultSet rs = highestCommissionID.executeQuery(searchQuery);
            int highestCommission = 1;
            while (rs.next()) {
                highestCommission = rs.getInt(1) + 1;
            }

            java.sql.Date startDate = new java.sql.Date(new Date().getTime());
            java.sql.Date endDate2 = new java.sql.Date(endDate.getTime());

            String addQuery = "INSERT INTO commission (`CommissionID`, `amount`, `commissionDate`, `commissionEndDate`, `blankType`) " +
                    "VALUES ('" + highestCommission + "', '" + amount + "', '" + startDate + "', '" + endDate2 + "', '" + blankType + "');";

            highestCommissionID.executeUpdate(addQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public boolean isExpired() {
        return isExpired;
    }

    public void setExpired(boolean expired) {
        isExpired = expired;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public int getCommissionID() {
        return commissionID;
    }

    public void setCommissionID(int commissionID) {
        this.commissionID = commissionID;
    }

    public int getBlankType() {
        return blankType;
    }

    public void setBlankType(int blankType) {
        this.blankType = blankType;
    }
}
