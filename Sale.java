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

    public Sale(){}

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
