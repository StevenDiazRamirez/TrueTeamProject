public class FlexibleDiscount extends Discount{

    private int ID;
    private float lowerRange;
    private float upperRange;
    private float amount;

    public FlexibleDiscount(float lowerRange, float upperRange, float amount, int discountID){
        super("Flexible");
        super.setDiscountID(discountID);
        this.lowerRange = lowerRange;
        this.upperRange = upperRange;
        this.amount = amount;

    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
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
