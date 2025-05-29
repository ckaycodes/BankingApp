
public class Transaction {
    private float amount;
    private String type; //type of transaction

    public Transaction(float amount, String type) {
        this.amount = amount;
        this.type = type;
    }

    public float getAmount() {
        return amount;
    }

    public String getType() {
        return type;
    }

}
