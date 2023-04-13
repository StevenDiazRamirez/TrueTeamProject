import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class Sale {

    private int saleID;
    private String type;
    private float price;
    private int blankID;
    private int customerID;
    private int employeeID;
    private int commissionID;
    private Date salesDate;
    private String cardNumber;
    private String MCOtype;
    private Date latePaymentDate;
    private String latePaymentStatus;
    private int blankType;
    private float localTax;
    private float otherTax;

    //cash payment
    public Sale(String type, float price, int blankID, int employeeID, int customerID, int commissionID, Date salesDate){
        this.type = type;
        this.price = price;
        this.blankID = blankID;
        this.customerID = customerID;
        this.employeeID = employeeID;
        this.commissionID = commissionID;
        this.salesDate = salesDate;
    }

    //card payment
    public Sale(String type, float price, int blankID, int employeeID, int customerID, int commissionID, Date salesDate, String cardNumber){
        this.type = type;
        this.price = price;
        this.blankID = blankID;
        this.customerID = customerID;
        this.employeeID = employeeID;
        this.commissionID = commissionID;
        this.salesDate = salesDate;
        this.cardNumber = cardNumber;
    }

    /**
     * Adds a Domestic Cash sale to database
     * @param blankType
     * @param amount
     * @param taxes
     * @param latePaymentDate
     * @param blankID
     * @param employeeID
     * @param customerID
     * @param commissionID
     */
    public static void addCashSaleDomestic(int blankType, float amount, float taxes, Date latePaymentDate, int blankID, int employeeID, int customerID, int commissionID) {
        try {
            Connection con = DBSConnection.getConnection();

            String searchQuery = "SELECT MAX(SaleID) FROM sale";
            PreparedStatement highestSaleID = con.prepareStatement(searchQuery);


            ResultSet rs = highestSaleID.executeQuery(searchQuery);
            int highestSale = 1;
            while (rs.next()) {
                highestSale = rs.getInt(1) + 1;
            }

            java.sql.Date todayDate = new java.sql.Date(new Date().getTime());

            //Checks if customer is new or regular/valued
            if (!(latePaymentDate == null)) {
                //For regular or valued customer
                java.sql.Date lateDate = new java.sql.Date(latePaymentDate.getTime());
                String addQuery = "INSERT INTO sale (`SaleID`, `blankTypeSale`, `PaymentType`, `flightType`, `PaymentAmount`, " +
                        "`saleDate`, `taxes`, `latePaymentDate`, `blankIDSale`, `employeeIDSale`, `customerIDSale`, `commissionIDSale`) VALUES ('" + highestSale +
                        "', '" + blankType + "', '" + "CASH" + "', '" + "Domestic" + "', '" + amount + "', '" + todayDate +
                        "', '" + taxes + "', '" + lateDate + "', '" + blankID + "', '" + employeeID + "', '" + customerID + "', '" + commissionID + "');";
                highestSaleID.executeUpdate(addQuery);
            } else {
                //For new/guest customers
                String addQuery2 = "INSERT INTO sale (`SaleID`, `blankTypeSale`, `PaymentType`, `flightType`, `PaymentAmount`, " +
                        "`saleDate`, `taxes`, `blankIDSale`, `employeeIDSale`, `customerIDSale`, `commissionIDSale`) VALUES ('" + highestSale +
                        "', '" + blankType + "', '" + "CASH" + "', '" + "Domestic" + "', '" + amount + "', '" + todayDate +
                        "', '" + taxes + "', '" + blankID + "', '" + employeeID + "', '" + customerID + "', '" + commissionID + "');";
                highestSaleID.executeUpdate(addQuery2);
            }
            JOptionPane.showMessageDialog(null, "Sale Created");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Adds a Domestic Card sale to database
     * @param blankType
     * @param amount
     * @param taxes
     * @param latePaymentDate
     * @param blankID
     * @param employeeID
     * @param customerID
     * @param commissionID
     * @param cardDetails
     */
    public static void addCardSaleDomestic(int blankType, float amount, float taxes, Date latePaymentDate, int blankID, int employeeID, int customerID, int commissionID, String cardDetails) {
        try {
            Connection con = DBSConnection.getConnection();

            String searchQuery = "SELECT MAX(SaleID) FROM sale";
            PreparedStatement highestSaleID = con.prepareStatement(searchQuery);


            ResultSet rs = highestSaleID.executeQuery(searchQuery);
            int highestSale = 1;
            while (rs.next()) {
                highestSale = rs.getInt(1) + 1;
            }

            java.sql.Date todayDate = new java.sql.Date(new Date().getTime());

            //Checks if customer is new or regular/valued
            if (!(latePaymentDate == null)) {
                //For regular or valued customer
                java.sql.Date lateDate = new java.sql.Date(latePaymentDate.getTime());
                String addQuery = "INSERT INTO sale (`SaleID`, `blankTypeSale`, `PaymentType`, `flightType`, `PaymentAmount`, " +
                        "`saleDate`, `taxes`, `latePaymentDate`, `blankIDSale`, `employeeIDSale`, `customerIDSale`, `commissionIDSale`, `cardDetails`) VALUES ('" + highestSale +
                        "', '" + blankType + "', '" + "CARD" + "', '" + "Domestic" + "', '" + amount + "', '" + todayDate +
                        "', '" + taxes + "', '" + lateDate + "', '" + blankID + "', '" + employeeID + "', '" + customerID + "', '" + commissionID + "', '" + cardDetails + "');";

                highestSaleID.executeUpdate(addQuery);
            } else {
                //For new/guest customers
                String addQuery2 = "INSERT INTO sale (`SaleID`, `blankTypeSale`, `PaymentType`, `flightType`, `PaymentAmount`, " +
                        "`saleDate`, `taxes`, `blankIDSale`, `employeeIDSale`, `customerIDSale`, `commissionIDSale`, `cardDetails`) VALUES ('" + highestSale +
                        "', '" + blankType + "', '" + "CARD" + "', '" + "Domestic" + "', '" + amount + "', '" + todayDate +
                        "', '" + taxes + "', '" + blankID + "', '" + employeeID + "', '" + customerID + "', '" + commissionID + "', '" + cardDetails + "');";

                highestSaleID.executeUpdate(addQuery2);
            }
            JOptionPane.showMessageDialog(null, "Sale Created");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Add Global Cash sale to database
     * @param blankType
     * @param amount
     * @param exchangeRate
     * @param taxes
     * @param latePaymentDate
     * @param blankID
     * @param employeeID
     * @param customerID
     * @param commissionID
     */
    public static void addCashSaleGlobal(int blankType, float amount, float exchangeRate, float taxes, Date latePaymentDate, int blankID, int employeeID, int customerID, int commissionID) {
        try {
            Connection con = DBSConnection.getConnection();

            String searchQuery = "SELECT MAX(SaleID) FROM sale";
            PreparedStatement highestSaleID = con.prepareStatement(searchQuery);


            ResultSet rs = highestSaleID.executeQuery(searchQuery);
            int highestSale = 1;
            while (rs.next()) {
                highestSale = rs.getInt(1) + 1;
            }

            java.sql.Date todayDate = new java.sql.Date(new Date().getTime());

            //checks if customer is new or regular/valued
            if (!(latePaymentDate == null)) {
                //For regular or valued customer
                java.sql.Date lateDate = new java.sql.Date(latePaymentDate.getTime());
                String addQuery = "INSERT INTO sale (`SaleID`, `blankTypeSale`, `PaymentType`, `flightType`, `PaymentAmount`, " +
                        "`exchangeRate`, `saleDate`, `taxes`, `latePaymentDate`, `blankIDSale`, `employeeIDSale`, `customerIDSale`, `commissionIDSale`) VALUES ('" + highestSale +
                        "', '" + blankType + "', '" + "CASH" + "', '" + "Global" + "', '" + amount + "', '" + exchangeRate + "', '" + todayDate +
                        "', '" + taxes + "', '" + lateDate + "', '" + blankID + "', '" + employeeID + "', '" + customerID + "', '" + commissionID + "');";

                highestSaleID.executeUpdate(addQuery);
            } else {
                //For new/guest customer
                String addQuery2 = "INSERT INTO sale (`SaleID`, `blankTypeSale`, `PaymentType`, `flightType`, `PaymentAmount`, " +
                        "`exchangeRate`, `saleDate`, `taxes`, `blankIDSale`, `employeeIDSale`, `customerIDSale`, `commissionIDSale`) VALUES ('" + highestSale +
                        "', '" + blankType + "', '" + "CASH" + "', '" + "Global" + "', '" + amount + "', '" + exchangeRate + "', '" + todayDate +
                        "', '" + taxes + "', '" + blankID + "', '" + employeeID + "', '" + customerID + "', '" + commissionID + "');";

                highestSaleID.executeUpdate(addQuery2);
            }
            JOptionPane.showMessageDialog(null, "Sale Created");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Adds Global Card sale to database
     * @param blankType
     * @param amount
     * @param exchangeRate
     * @param taxes
     * @param latePaymentDate
     * @param blankID
     * @param employeeID
     * @param customerID
     * @param commissionID
     * @param cardDetails
     */
    public static void addCardSaleGlobal(int blankType, float amount, float exchangeRate, float taxes, Date latePaymentDate, int blankID, int employeeID, int customerID, int commissionID, String cardDetails) {
        try {
            Connection con = DBSConnection.getConnection();

            String searchQuery = "SELECT MAX(SaleID) FROM sale";
            PreparedStatement highestSaleID = con.prepareStatement(searchQuery);


            ResultSet rs = highestSaleID.executeQuery(searchQuery);
            int highestSale = 1;
            while (rs.next()) {
                highestSale = rs.getInt(1) + 1;
            }

            java.sql.Date todayDate = new java.sql.Date(new Date().getTime());

            //checks if customer is new or regular/valued
            if (!(latePaymentDate == null)) {
                //For regular or valued customer
                java.sql.Date lateDate = new java.sql.Date(latePaymentDate.getTime());
                String addQuery = "INSERT INTO sale (`SaleID`, `blankTypeSale`, `PaymentType`, `flightType`, `PaymentAmount`, " +
                        "`exchangeRate`, `saleDate`, `taxes`, `latePaymentDate`, `blankIDSale`, `employeeIDSale`, `customerIDSale`, `commissionIDSale`, `cardDetails`) VALUES ('" + highestSale +
                        "', '" + blankType + "', '" + "CARD" + "', '" + "Global" + "', '" + amount + "', '" + exchangeRate + "', '" + todayDate +
                        "', '" + taxes + "', '" + lateDate + "', '" + blankID + "', '" + employeeID + "', '" + customerID + "', '" + commissionID + "', '" +  cardDetails + "');";

                highestSaleID.executeUpdate(addQuery);
            } else {
                //For new/guest customer
                String addQuery2 = "INSERT INTO sale (`SaleID`, `blankTypeSale`, `PaymentType`, `flightType`, `PaymentAmount`, " +
                        "`exchangeRate`, `saleDate`, `taxes`, `blankIDSale`, `employeeIDSale`, `customerIDSale`, `commissionIDSale`, `cardDetails`) VALUES ('" + highestSale +
                        "', '" + blankType + "', '" + "CARD" + "', '" + "Global" + "', '" + amount + "', '" + exchangeRate + "', '" + todayDate +
                        "', '" + taxes + "', '" + blankID + "', '" + employeeID + "', '" + customerID + "', '" + commissionID + "', '"  + cardDetails + "');";

                highestSaleID.executeUpdate(addQuery2);
            }
            JOptionPane.showMessageDialog(null, "Sale Created");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Adds a late pay status to an existing sale
     * @param latePaymentStatus
     * @param saleID
     */
    public static void addLatePayStatus(String latePaymentStatus, int saleID) {
        try {
            Connection con = DBSConnection.getConnection();

            String query = "UPDATE sale SET latePaymentStatus = " + '"' + latePaymentStatus + '"' + " WHERE saleID = "
                    + saleID;

            PreparedStatement addStatus = con.prepareStatement(query);
            addStatus.executeUpdate();
            JOptionPane.showMessageDialog(null, "Updated status");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets the type of customer
     * @param customerEmail
     * @return
     */
    public static String getCustomerType(String customerEmail) {
        try {
            Connection con = DBSConnection.getConnection();

            String searchQuery = "SELECT AccountType FROM customeraccount WHERE CustomerEmail = " + "'" + customerEmail + "'";
            PreparedStatement accountType = con.prepareStatement(searchQuery);


            ResultSet rs = accountType.executeQuery(searchQuery);
            String customerAccountType = "Customer";
            while (rs.next()) {
                customerAccountType = rs.getString(1);
            }

            return customerAccountType;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int getSaleID() {
        return saleID;
    }

    public void setSaleID(int saleID) {
        this.saleID = saleID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getBlankID() {
        return  blankID;
    }

    public void setBlankID(int blankID) {
        this.blankID = blankID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public int getCommissionID() {
        return commissionID;
    }

    public void setCommissionID(int commissionID) {
        this.commissionID = commissionID;
    }

    public Date getSalesDate() {
        return salesDate;
    }

    public void setSalesDate(Date salesDate) {
        this.salesDate = salesDate;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getMCOtype() {
        return MCOtype;
    }

    public void setMCOtype(String MCOtype) {
        this.MCOtype = MCOtype;
    }


    public Date getLatePaymentDate() {
        return latePaymentDate;
    }

    public void setLatePaymentDate(Date latePaymentDate) {
        this.latePaymentDate = latePaymentDate;
    }

    public String getLatePaymentStatus() {
        return latePaymentStatus;
    }

    public void setLatePaymentStatus(String latePaymentStatus) {
        this.latePaymentStatus = latePaymentStatus;
    }

    public int getBlankType() {
        return blankType;
    }

    public void setBlankType(int blankType) {
        this.blankType = blankType;
    }

    public float getLocalTax() {
        return localTax;
    }

    public void setLocalTax(float localTax) {
        this.localTax = localTax;
    }

    public float getOtherTax() {
        return otherTax;
    }

    public void setOtherTax(float otherTax) {
        this.otherTax = otherTax;
    }
}
