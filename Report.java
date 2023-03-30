public class Report {
    class Reports{
        int employeeID;
        String reportName;
        String reportPeriod;
        int reportNumber;

        public Reports(int employeeID, String reportName, String reportPeriod, int reportNumber) {
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
    class turnOverReport{
        Blank[] listOfAgentStock;
        Blank[] listOfBlankAssigned;
        Blank[] listOfUsedBlanks;
        Blank[] listOfUnusedBlanks;
        int finalAmount;

        public turnOverReport(Blank[] listOfAgentStock, Blank[] listOfBlankAssigned, Blank[] listOfUsedBlanks, Blank[] listOfUnusedBlanks, int finalAmount) {
            this.listOfAgentStock = listOfAgentStock;
            this.listOfBlankAssigned = listOfBlankAssigned;
            this.listOfUsedBlanks = listOfUsedBlanks;
            this.listOfUnusedBlanks = listOfUnusedBlanks;
            this.finalAmount = finalAmount;
        }

        public Blank[] getListOfAgentStock() {
            return listOfAgentStock;
        }

        public void setListOfAgentStock(Blank[] listOfAgentStock) {
            this.listOfAgentStock = listOfAgentStock;
        }

        public Blank[] getListOfBlankAssigned() {
            return listOfBlankAssigned;
        }

        public void setListOfBlankAssigned(Blank[] listOfBlankAssigned) {
            this.listOfBlankAssigned = listOfBlankAssigned;
        }

        public Blank[] getListOfUsedBlanks() {
            return listOfUsedBlanks;
        }

        public void setListOfUsedBlanks(Blank[] listOfUsedBlanks) {
            this.listOfUsedBlanks = listOfUsedBlanks;
        }

        public Blank[] getListOfUnusedBlanks() {
            return listOfUnusedBlanks;
        }

        public void setListOfUnusedBlanks(Blank[] listOfUnsedBlanks) {
            this.listOfUnusedBlanks = listOfUnsedBlanks;
        }

        public int getFinalAmount() {
            return finalAmount;
        }

        public void setFinalAmount(int finalAmount) {
            this.finalAmount = finalAmount;
        }

    }
    class interline{
        float exchangeRate;
        float localTax;
        float fareAmountUSD;
        float fareAmountLocal;
        float otherTax;
        String currencyName;

        public float getExchangeRate() {
            return exchangeRate;
        }

        public void setExchangeRate(float exchangeRate) {
            this.exchangeRate = exchangeRate;
        }

        public float getLocalTax() {
            return localTax;
        }

        public void setLocalTax(float localTax) {
            this.localTax = localTax;
        }

        public float getFareAmountUSD() {
            return fareAmountUSD;
        }

        public void setFareAmountUSD(float fareAmountUSD) {
            this.fareAmountUSD = fareAmountUSD;
        }

        public float getFareAmountLocal() {
            return fareAmountLocal;
        }

        public void setFareAmountLocal(float fareAmountLocal) {
            this.fareAmountLocal = fareAmountLocal;
        }

        public float getOtherTax() {
            return otherTax;
        }

        public void setOtherTax(float otherTax) {
            this.otherTax = otherTax;
        }

        public String getCurrencyName() {
            return currencyName;
        }

        public void setCurrencyName(String currencyName) {
            this.currencyName = currencyName;
        }
    }
}
