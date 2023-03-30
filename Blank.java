import java.util.Date;

public class Blank {
    class blanks{
        int blankID;
        String status;
        int employeeID;
        int blankType;
        Date recieveDate;
        Date assignedDate;

        public blanks(int blankID, String status, int employeeID, int blankType, Date recieveDate, Date assignedDate) {
            this.blankID = blankID;
            this.status = status;
            this.employeeID = employeeID;
            this.blankType = blankType;
            this.recieveDate = recieveDate;
            this.assignedDate = assignedDate;
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

        public Date getRecieveDate() {
            return recieveDate;
        }

        public void setRecieveDate(Date recieveDate) {
            this.recieveDate = recieveDate;
        }

        public Date getAssignedDate() {
            return assignedDate;
        }

        public void setAssignedDate(Date assignedDate) {
            this.assignedDate = assignedDate;
        }

    }
    class coupon{
        int couponID;
        String couponType;
        String destTo;
        String destFrom;

        public coupon(int couponID, String couponType, String destTo, String destFrom) {
            this.couponID = couponID;
            this.couponType = couponType;
            this.destTo = destTo;
            this.destFrom = destFrom;
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

        public String getDestTo() {
            return destTo;
        }

        public void setDestTo(String destTo) {
            this.destTo = destTo;
        }

        public String getDestFrom() {
            return destFrom;
        }

        public void setDestFrom(String destFrom) {
            this.destFrom = destFrom;
        }
    }


}
