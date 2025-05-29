import java.util.Random;
import java.util.Scanner;

public class User  {

    private int userID;
    private String userName;
    private String password;
    private Account account;
    private TransactionHistory history;


    User(int ID, String userName, String password) {
        if (ID < 10000 || ID > 99999) {
            throw new IllegalArgumentException("ID Generation Error");
        }

        this.userID = ID;
        this.userName = userName;
        this.password = password;
        this.account = new Account(); // creates account automatically w/each user
        this.history = new TransactionHistory(); // creates history automatically w/each user
    }

    User() {

    }

    String getUsername () {
        return userName;
    }

    String getPassword () {
        return password;
    }

    int getID () {
        return userID;
    }

    Account getAccount () {
        return account;
    }

    TransactionHistory getHistory() {
        return history;
    }

}
