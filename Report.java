public class Report {
        int employeeID;
        String reportName;
        String reportPeriod;
        int reportNumber;

        public Report(int employeeID, String reportName, String reportPeriod, int reportNumber) {
            this.employeeID = employeeID;
            this.reportName = reportName;
            this.reportPeriod = reportPeriod;
            this.reportNumber = reportNumber;
        }


        public int getEmployeeID() {
            return employeeID;
        }

        public void setEmployeeID(int employeeID) {
            this.employeeID = employeeID;
        }

        public String getReportName() {
            return reportName;
        }

        public void setReportName(String reportName) {
            this.reportName = reportName;
        }

        public String getReportPeriod() {
            return reportPeriod;
        }

        public void setReportPeriod(String reportPeriod) {
            this.reportPeriod = reportPeriod;
        }

        public int getReportNumber() {
            return reportNumber;
        }

        public void setReportNumber(int reportNumber) {
            this.reportNumber = reportNumber;
        }
}
