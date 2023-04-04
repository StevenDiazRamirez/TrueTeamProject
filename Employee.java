import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class Employee {

        private int EmployeeID;
        private String firstName;
        private String lastName;
        private String username;
        private String role;
        private String password;

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
                ResultSet rs = stm.executeQuery("SELECT * FROM EmployeeAccount WHERE employeePassword = " + '"' + password + '"');
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

        public static Employee loggedInProfile(String username, String password) {
            try {
                Connection con = DBSConnection.getConnection();

                Statement stm = con.createStatement();
                ResultSet rs = stm.executeQuery("SELECT * FROM EmployeeAccount WHERE employeePassword = " + '"' + password + '"' + "AND Username = " + '"' + username + '"');
                while (rs.next()) {
                    Employee employee = new Employee(rs.getString("First Name"),
                            rs.getString("Last Name"),
                            rs.getString("Username"),
                            rs.getString("employeePassword"),
                            rs.getString("AccountType"));
                    employee.setEmployeeID(rs.getInt("EmployeeID"));
                    return employee;
                }


            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
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