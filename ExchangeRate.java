import java.util.Date;

public class ExchangeRate {

    private int exchangeRateID;
    private Date startDate;
    private Date endDate;
    private float amount;
    private boolean isExpired;

    public ExchangeRate (Date startDate, float amount){
        this.startDate = startDate;
        this.amount = amount;
    }

    public ExchangeRate(Date startDate, Date endDate, float amount){
        this.startDate = startDate;
        this.endDate = endDate;
        this.amount = amount;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public int getExchangeRateID() {
        return exchangeRateID;
    }

    public void setExchangeRateID(int exchangeRateID) {
        this.exchangeRateID = exchangeRateID;
    }

    public boolean isExpired() {
        return isExpired;
    }

    public void setExpired(boolean expired) {
        isExpired = expired;
    }
}
