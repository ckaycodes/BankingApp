
import javafx.scene.control.Alert;
import java.sql.*;
import java.util.HashMap;
import java.util.Scanner;

public class BankManager {


     private HashMap<Integer, User> usersByID;
     private HashMap<String, Integer> usernameToID;

    //constructor
    public BankManager(HashMap<Integer, User> usersByID, HashMap<String, Integer> usernameToID) {

        //initialize hashmap
        this.usersByID = usersByID;
        this.usernameToID = usernameToID;
    }

    public BankManager() {

    }


    public void addUser(User user) {
        usersByID.put(user.getID(), user);
        usernameToID.put(user.getUsername().toLowerCase(), user.getID());
    }

    public boolean usernameExists(String username) {
        return usernameToID.containsKey(username);
    }

    public boolean userExists(int id) {
        return usersByID.containsKey(id);
    }

    public User getUserByUsername(String username) {

        String key = username.toLowerCase(); // consistent casing

        if (usernameToID.containsKey(key)) {
            int userId = usernameToID.get(key);
            return usersByID.get(userId);
        } else {
            throw new IllegalArgumentException("Username not found: " + username);
        }
    }
}

