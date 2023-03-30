import java.util.Date;

public class Staff {
    class  employeeAccount {
        int EmployeeID;
        String Firstname;
        String lastname;
        String email;
        String role;

        public int getEmployeeID() {
            return EmployeeID;
        }

        public void setEmployeeID(int employeeID) {
            EmployeeID = employeeID;
        }

        public String getFirstname() {
            return Firstname;
        }

        public void setFirstname(String firstname) {
            Firstname = firstname;
        }

        public String getLastname() {
            return lastname;
        }

        public void setLastname(String lastname) {
            this.lastname = lastname;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }

    }
    class officeManager extends employeeAccount{


    }
    class commission{
        int commissionID;
        float amount;
        Date startDate;
        Date endDate;
        int blankType;
        boolean isExpired;

        public int getCommissionID() {
            return commissionID;
        }

        public void setCommissionID(int commissionID) {
            this.commissionID = commissionID;
        }

        public float getAmount() {
            return amount;
        }

        public void setAmount(float amount) {
            this.amount = amount;
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

        public int getBlankType() {
            return blankType;
        }

        public void setBlankType(int blankType) {
            this.blankType = blankType;
        }

        public boolean isExpired() {
            return isExpired;
        }

        public void setExpired(boolean expired) {
            isExpired = expired;
        }
    }

}
