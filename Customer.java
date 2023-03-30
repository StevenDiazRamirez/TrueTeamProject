import java.util.Date;

public class Customer {
    class sale{
        int saleID;
        String type;
        float price;
        int blankID;
        int employeeID;
        Date saleDate;
        Date laterPaymentDate;
        int commissionID;
        int blankType;
        float localTax;
        float otherTax;

        public sale(int saleID, String type, float price, int blankID, int employeeID, Date saleDate, Date laterPaymentDate, int commissionID, int blankType, float localTax, float otherTax) {
            this.saleID = saleID;
            this.type = type;
            this.price = price;
            this.blankID = blankID;
            this.employeeID = employeeID;
            this.saleDate = saleDate;
            this.laterPaymentDate = laterPaymentDate;
            this.commissionID = commissionID;
            this.blankType = blankType;
            this.localTax = localTax;
            this.otherTax = otherTax;
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
            return blankID;
        }

        public void setBlankID(int blankID) {
            this.blankID = blankID;
        }

        public int getEmployeeID() {
            return employeeID;
        }

        public void setEmployeeID(int employeeID) {
            this.employeeID = employeeID;
        }

        public Date getSaleDate() {
            return saleDate;
        }

        public void setSaleDate(Date saleDate) {
            this.saleDate = saleDate;
        }

        public Date getLaterPaymentDate() {
            return laterPaymentDate;
        }

        public void setLaterPaymentDate(Date laterPaymentDate) {
            this.laterPaymentDate = laterPaymentDate;
        }

        public int getCommissionID() {
            return commissionID;
        }

        public void setCommissionID(int commissionID) {
            this.commissionID = commissionID;
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
    class customerAccount{
        int customerID;
        String firstName;
        String lastName;
        String type;
        String email;

        public customerAccount(int customerID, String firstName, String lastName, String type, String email) {
            this.customerID = customerID;
            this.firstName = firstName;
            this.lastName = lastName;
            this.type = type;
            this.email = email;
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

    }
    class exchangeRate{
        int exchangeRateID;
        Date startDate;
        Date endDate;
        float amount;
        boolean isExpired;
        String currencyCode;

        public exchangeRate(int exchangeRateID, Date startDate, Date endDate, float amount, boolean isExpired, String currencyCode) {
            this.exchangeRateID = exchangeRateID;
            this.startDate = startDate;
            this.endDate = endDate;
            this.amount = amount;
            this.isExpired = isExpired;
            this.currencyCode = currencyCode;
        }

        public int getExchangeRateID() {
            return exchangeRateID;
        }

        public void setExchangeRateID(int exchangeRateID) {
            this.exchangeRateID = exchangeRateID;
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

        public float getAmount() {
            return amount;
        }

        public void setAmount(float amount) {
            this.amount = amount;
        }

        public boolean isExpired() {
            return isExpired;
        }

        public void setExpired(boolean expired) {
            isExpired = expired;
        }

        public String getCurrencyCode() {
            return currencyCode;
        }

        public void setCurrencyCode(String currencyCode) {
            this.currencyCode = currencyCode;
        }
    }
    class discount{
        int discountID;
        String type;

        public discount(int discountID, String type) {
            this.discountID = discountID;
            this.type = type;
        }

        public int getDiscountID() {
            return discountID;
        }

        public void setDiscountID(int discountID) {
            this.discountID = discountID;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
    class fixedDiscount extends discount{
        int fixedID;
        float amount;

        public fixedDiscount(int discountID, String type, int fixedID, float amount) {
            super(discountID, type);
            this.fixedID = fixedID;
            this.amount = amount;
        }

        public int getFixedID() {
            return fixedID;
        }

        public void setFixedID(int fixedID) {
            this.fixedID = fixedID;
        }

        public float getAmount() {
            return amount;
        }

        public void setAmount(float amount) {
            this.amount = amount;
        }
    }
    class flexibleDiscount extends discount{
        int flexibleID;
        float amount;
        float lowerRange;
        float upperRange;

        public flexibleDiscount(int discountID, String type, int flexibleID, float amount, float lowerRange, float upperRange) {
            super(discountID, type);
            this.flexibleID = flexibleID;
            this.amount = amount;
            this.lowerRange = lowerRange;
            this.upperRange = upperRange;
        }

        public int getFlexibleID() {
            return flexibleID;
        }

        public void setFlexibleID(int flexibleID) {
            this.flexibleID = flexibleID;
        }

        public float getAmount() {
            return amount;
        }

        public void setAmount(float amount) {
            this.amount = amount;
        }

        public float getLowerRange() {
            return lowerRange;
        }

        public void setLowerRange(float lowerRange) {
            this.lowerRange = lowerRange;
        }

        public float getUpperRange() {
            return upperRange;
        }

        public void setUpperRange(float upperRange) {
            this.upperRange = upperRange;
        }
    }



}
