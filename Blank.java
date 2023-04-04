import java.util.Date;

public class Blank {

        private int blankID;
        private String status;
        private int employeeID;
        private int blankType;
        private Date receiveDate;
        private Date assignedDate;

        public Blank(int blankType, String status, Date receiveDate) {
            this.status = status;
            this.blankType = blankType;
            this.receiveDate = receiveDate;
        }

        public Blank(int blankType, String status, Date receiveDate, Date assignedDate, int employeeID) {
        this.status = status;
        this.blankType = blankType;
        this.receiveDate = receiveDate;
        this.assignedDate = assignedDate;
        this.employeeID = employeeID;
        }

        public int getBlankID() {
            return blankID;
        }

        public void setBlankID(int blankID) {
            this.blankID = blankID;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public int getEmployeeID() {
            return employeeID;
        }

        public void setEmployeeID(int employeeID) {
            this.employeeID = employeeID;
        }

        public int getBlankType() {
            return blankType;
        }

        public void setBlankType(int blankType) {
            this.blankType = blankType;
        }

        public Date getReceiveDate() {
            return receiveDate;
        }

        public void setReceiveDate(Date receiveDate) {
            this.receiveDate = receiveDate;
        }

        public Date getAssignedDate() {
            return assignedDate;
        }

        public void setAssignedDate(Date assignedDate) {
            this.assignedDate = assignedDate;
        }

    }

