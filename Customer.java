import javax.swing.*;
import java.sql.*;
import java.util.Date;

public class Customer {

    private static Connection con = DBSConnection.getConnection();

    private int customerID;
    private String firstName;
    private String lastName;
    private String type;
    private String email;
    private int discountID;

    // For a new or regular Customer
    public Customer(String firstName, String lastName, String type, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.type = type;
        this.email = email;
    }

    // For a Valued Customer who has a discount
    public Customer(String firstName, String lastName, String type, String email, int discountID) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.type = type;
        this.email = email;
        this.discountID = discountID;
    }

    /**
     *  Adds a new or regular customer account to database
     * @param customer
     */
    public static void addCustomerAccount(Customer customer) {
        try {
            String query = "INSERT INTO customeraccount (`CustomerID`,`FirstName`, `LastName`, `AccountType`, `CustomerEmail`) VALUES " +
                    "('" + customer.getCustomerID() + "', '" + customer.getFirstName() + "', '" + customer.getLastName()
                    + "', '" + customer.getType() + "', '" + customer.getEmail() + "')";

            PreparedStatement stm = con.prepareStatement(query);
            stm.executeUpdate();
            JOptionPane.showMessageDialog(null, "Added!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Adds a valued customer account to database
     * @param customer
     */
    public static void addCustomerAccountValued(Customer customer) {
        try {
            String query = "INSERT INTO customeraccount (`CustomerID`,`FirstName`, `LastName`, `AccountType`, `CustomerEmail`, `DiscountIDCustomer`) " +
                    "VALUES " + "('" + customer.getCustomerID() + "', '" + customer.getFirstName() + "', '" + customer.getLastName()
                    + "', '" + customer.getType() + "', '" + customer.getEmail() + "', '" + customer.getDiscountID() + "')";

            PreparedStatement stm = con.prepareStatement(query);
            stm.executeUpdate();
            JOptionPane.showMessageDialog(null, "Added!");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Email already in use");
        }
    }

    /**
     * changes the account type of the customers account
     * @param customer
     */
    public static void updateCustomerAccount(Customer customer) {
        try {
            String query = "UPDATE customeraccount SET AccountType = " + "'" + customer.getType() + "'" + " WHERE CustomerID = "
                    + customer.getCustomerID();

            PreparedStatement stm = con.prepareStatement(query);
            stm.executeUpdate();
            JOptionPane.showMessageDialog(null,"Updated!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * deletes the customer account from the database
     * @param customer
     */
    public static void deleteCustomerAccount(Customer customer) {
        try{
            String statement = "DELETE FROM customeraccount WHERE CustomerID = "+ customer.getCustomerID();
            PreparedStatement stm = con.prepareStatement(statement);
            stm.executeUpdate();
            JOptionPane.showMessageDialog(null,"Deleted!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets the highest customer ID
     * @return
     */
    public static int getLatestCustomerID() {
        try{
            String query = "SELECT MAX(CustomerID) from customeraccount";
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

    /**
     * Gets the customer ID of the selected customer
     * @param customer
     * @return
     */
    public static int getThisCustomerID(Customer customer) {
        try {
            String query = "SELECT CustomerID from customeraccount WHERE CustomerEmail = " + "'" + customer.getEmail() + "'";
            PreparedStatement stm = con.prepareStatement(query);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  -1;
    }


    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getDiscountID() {
        return discountID;
    }

    public void setDiscountID(int discountID) {
        this.discountID = discountID;
    }
}