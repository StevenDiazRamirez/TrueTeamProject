import java.util.Date;

public class Payment {
    class Payments{
        int paymentID;
        String Type;
        int cardNo;
        Date payDate;
        boolean isComplete;

        public Payments(int paymentID, String type, int cardNo, Date payDate, boolean isComplete) {
            this.paymentID = paymentID;
            Type = type;
            this.cardNo = cardNo;
            this.payDate = payDate;
            this.isComplete = isComplete;
        }

        public int getPaymentID() {
            return paymentID;
        }

        public void setPaymentID(int paymentID) {
            this.paymentID = paymentID;
        }

        public String getType() {
            return Type;
        }

        public void setType(String type) {
            Type = type;
        }

        public int getCardNo() {
            return cardNo;
        }

        public void setCardNo(int cardNo) {
            this.cardNo = cardNo;
        }

        public Date getPayDate() {
            return payDate;
        }

        public void setPayDate(Date payDate) {
            this.payDate = payDate;
        }

        public boolean isComplete() {
            return isComplete;
        }

        public void setComplete(boolean complete) {
            isComplete = complete;
        }
    }
    class refund{
        float amount;
        int blankID;
        int saleID;

        public float getAmount() {
            return amount;
        }

        public void setAmount(float amount) {
            this.amount = amount;
        }

        public int getBlankID() {
            return blankID;
        }

        public void setBlankID(int blankID) {
            this.blankID = blankID;
        }

        public int getSaleID() {
            return saleID;
        }

        public void setSaleID(int saleID) {
            this.saleID = saleID;
        }
    }
}
