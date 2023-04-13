import javax.swing.*;
import java.sql.*;
import java.util.Date;

public class Employee {

        private int EmployeeID;
        private String firstName;
        private String lastName;
        private String username;
        private String role;
        private String password;

        private static Connection con = DBSConnection.getConnection();

        public Employee(String firstName, String lastName, String username, String password, String role) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.username = username;
            this.password = password;
            this.role = role;
        }

        public static boolean login(String username, String password) {
            try {
                Connection con = DBSConnection.getConnection();

                Statement stm = con.createStatement();
                ResultSet rs = stm.executeQuery("SELECT * FROM EmployeeAccount WHERE Password = " + '"' + password + '"');
                while (rs.next()) {
                    String name = rs.getString("Username");
                    if (username.equals(name)) {
                        return true;
                    }
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
            //AlertWindow.showInformationAlert("Wrong details","Please make sure that the ID and the password are both correct!");
            return false;
        }

        public static int getLatestEmployeeID() {
            try{
                String query = "SELECT MAX(EmployeeID) from employeeaccount";
                PreparedStatement stm = con.prepareStatement(query);
                ResultSet rs =stm.executeQuery();

                while(rs.next()){
                    return rs.getInt(1);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return  -1;
        }

    public static int getThisEmployeeID(String username) {
        try{
            String query = "SELECT EmployeeID from employeeaccount WHERE Username = " + "'" + username + "'";
            PreparedStatement stm = con.prepareStatement(query);
            ResultSet rs =stm.executeQuery();

            while(rs.next()){
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  -1;
    }

        public static void addEmployeeAccount(Employee employee) {
            try {
                String query = "INSERT INTO employeeaccount (`EmployeeID`, `FirstName`, `LastName`, `Username`, `Password`," +
                        "`AccountType`) VALUES ('" + employee.getEmployeeID() + "', '" + employee.getFirstname() + "', '" +
                        employee.getLastname() + "', '" + employee.getUsername() + "', '" + employee.getPassword() + "', '" + employee.getRole() + "');";

                PreparedStatement stm = con.prepareStatement(query);
                stm.executeUpdate();
                JOptionPane.showMessageDialog(null, "Added Account!");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public int getEmployeeID() {
            return EmployeeID;
        }

        public void setEmployeeID(int employeeID) {
            EmployeeID = employeeID;
        }

        public String getFirstname() {
            return firstName;
        }

        public void setFirstname(String firstname) {
            firstname = firstname;
        }

        public String getLastname() {
            return lastName;
        }

        public void setLastname(String lastname) {
            this.lastName = lastname;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getRole() {
            return role;
        }
        public void setRole(String role) {
            this.role = role;
        }
 }