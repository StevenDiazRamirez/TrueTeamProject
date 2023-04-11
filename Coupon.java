import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Coupon {

    private int couponID;
    private String couponType;
    private String destTo1;
    private String destFrom1;
    private String destTo2;
    private String destFrom2;
    private int blankType;
    private int blankIDCoupons;

    private static Connection con = DBSConnection.getConnection();

    public Coupon(String couponType, int blankType, int blankIDCoupons ){
        this.couponID = couponID;
        this.couponType = couponType;
        this.blankType = blankType;
        this.blankIDCoupons = blankIDCoupons;
    }

    public static void addCoupons(Coupon coupon) {
        try {
            String query = "INSERT INTO coupons (`couponID`,`couponType`,`blankType`,`destFrom1`," +
                    "`destTo1`,`destFrom2`,`destTo2`,`blankIDCoupons`) VALUES ('" + coupon.getCouponID() + "', '"
                    + coupon.getCouponType() + "', '" + coupon.getBlankType() + "', '" + coupon.getDestFrom1()
                    + "', '" + coupon.getDestTo1() + "', '" + coupon.getDestFrom2() + "', '" + coupon.getDestTo2()
                    + "', '" + coupon.getBlankIDCoupons() + "')";

            PreparedStatement stm = con.prepareStatement(query);
            stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static int getLatestCustomerID() {
        try{
            String query = "SELECT MAX(couponID) from coupons";
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

    public String getDestTo1() {
        return destTo1;
    }

    public void setDestTo1(String destTo1) {
        this.destTo1 = destTo1;
    }

    public String getDestFrom1() {
        return destFrom1;
    }

    public void setDestFrom1(String destFrom1) {
        this.destFrom1 = destFrom1;
    }

    public String getDestTo2() {
        return destTo2;
    }

    public void setDestTo2(String destTo2) {
        this.destTo2 = destTo2;
    }

    public String getDestFrom2() {
        return destFrom2;
    }

    public void setDestFrom2(String destFrom2) {
        this.destFrom2 = destFrom2;
    }

    public int getBlankType() {
        return blankType;
    }

    public void setBlankType(int blankType) {
        this.blankType = blankType;
    }

    public int getBlankIDCoupons() {
        return blankIDCoupons;
    }

    public void setBlankIDCoupons(int blankIDCoupons) {
        this.blankIDCoupons = blankIDCoupons;
    }

    public int getCouponID() {
        return couponID;
    }

    public void setCouponID(int couponID) {
        this.couponID = couponID;
    }

    public String getCouponType() {
        return couponType;
    }

    public void setCouponType(String couponType) {
        this.couponType = couponType;
    }

}
