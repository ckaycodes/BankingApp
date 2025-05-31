import java.util.Random;
import java.util.Scanner;

public class User  {

    private int userId;
    private String userName;
    private String password;
    private Account account;
    private TransactionHistory history;


    // For creating new users (no ID yet)
    public User(String userName, String password, Account account) {
        this.userId = -1;
        this.userName = userName;
        this.password = password;
        this.account = account;
        this.history = new TransactionHistory();
        // userID will be set later after insertion
    }

    // For loading users from DB (ID is known)
    public User(int id, String userName, String password, Account account) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID must be a positive number.");
        }

        this.userId = id;
        this.userName = userName;
        this.password = password;
        this.account = account;
        this.history = new TransactionHistory();
    }




    // updates ID to value retrieved from database
    public void setID(int userID) {
        if (userID <= 0) {
            throw new IllegalArgumentException("ID must be a positive number.");
        }

        this.userId = userID;
    }

    String getUsername () {
        return userName;
    }

    String getPassword () {
        return password;
    }

    int getID () {
        return userId;
    }

    Account getAccount () {
        return account;
    }

    TransactionHistory getHistory() {
        return history;
    }

}
