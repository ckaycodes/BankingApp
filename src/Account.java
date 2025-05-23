public class Account extends User {

    //attributes
    private double balance;


    //constructor for Account Class
    Account() {
        balance = 100.0; //start with 100
    }


    //functions
    void deposit(double cashIn) {
        balance += cashIn;
    }

    void withDraw(double cashOut) {
        balance -= cashOut;
    }

    double getBalance() {
        return balance;
    }


}
