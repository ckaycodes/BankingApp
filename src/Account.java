
public class Account {

    private float balance;

    // Constructor for Account Class
    Account() {
        balance = 0;
    }

    // Constructor for BankManager
    public Account(float initialBalance) {
        this.balance = initialBalance;
    }

    //Methods
    void deposit(float cashIn) {
        balance += cashIn;
    }

    void withDraw(float cashOut) {
        balance -= cashOut;
    }

    float getBalance() {
        return balance;
    }


}
