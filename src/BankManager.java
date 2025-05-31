import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BankManager {

    //Connection to database
    private final Connection conn;

    // Constructor: Parameter including connection to DB, and creates DB table view
    public BankManager(Connection conn) {
        this.conn = conn;
        createTables();
    }

    public void addUser(User user) {
        try {
            String sql = "INSERT INTO users (username, password, balance) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.setFloat(3, user.getAccount().getBalance());

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                user.setID(rs.getInt(1)); // Set the generated ID back on the User object
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Replace with Logger in future
        }
    }

    public boolean usernameExists(String username) {
        try {
            String sql = "SELECT 1 FROM users WHERE LOWER(username) = LOWER(?) LIMIT 1";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);

            ResultSet rs = stmt.executeQuery();
            return rs.next();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public User getUserByUsername(String username) {
        String sql = "SELECT * FROM users WHERE LOWER(username) = LOWER(?)";
        try  (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("id");
                String uname = rs.getString("username");
                String password = rs.getString("password");
                float balance = rs.getFloat("balance");

                Account account = new Account(balance); // Pass balance to Account constructor
                return new User(id, uname, password, account);
            } else {
                return null;
            }

        } catch (SQLException e) {

            if (e.getMessage().contains("UNIQUE")) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Username is taken.", ButtonType.OK);
                alert.showAndWait();
            } else {
                e.printStackTrace();
            }
            return null;
        }
    }


    // Add transaction
    public void addTransaction(int userId, float amount, String type) {
        String sql = "INSERT INTO transactions (user_id, amount, type) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            stmt.setFloat(2, amount);
            stmt.setString(3, type);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Transaction> getTransactionsForUser(int userId) {
        List<Transaction> transactions = new ArrayList<>();
        String sql = "SELECT amount, type FROM transactions WHERE user_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                transactions.add(new Transaction(rs.getFloat("amount"), rs.getString("type")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transactions;
    }


    public void updateUserBalance(int userId, float newBalance) {
        String sql = "UPDATE users SET balance = ? WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setFloat(1, newBalance);
            stmt.setInt(2, userId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private void createTables() {
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS users (" +
                            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                            "username TEXT UNIQUE NOT NULL, " +
                            "password TEXT NOT NULL, " +
                            "balance REAL NOT NULL)"
            );

            stmt.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS transactions (" +
                            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                            "user_id INTEGER NOT NULL, " +
                            "amount REAL NOT NULL, " +
                            "type TEXT NOT NULL, " +
                            "timestamp DATETIME DEFAULT CURRENT_TIMESTAMP, " +
                            "FOREIGN KEY (user_id) REFERENCES users(id))"
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



}

