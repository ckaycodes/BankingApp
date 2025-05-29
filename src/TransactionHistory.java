import java.util.ArrayList;

public class TransactionHistory {

    // ArrayList that holds transactions
    private ArrayList<Transaction> history = new ArrayList<>();

    // Function that adds transactions to history
    public void addTransaction(Transaction transaction){
        history.add(transaction);
    }

    public ArrayList<Transaction> getTransactions() {
        return history;
    }



}
