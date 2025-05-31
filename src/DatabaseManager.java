import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManager {

    private static final String DB_URL = "jdbc:sqlite:bank.db"; // creates file in project directory

    // NOTE: createTables() requires an active Connection passed in from connect().
    // It does NOT open or manage the connection itself.
    // Be sure to provide a valid, open Connection when calling createTables().

    // Created this method to keep things simple for now,
    // but will refactor later to handle queries and better connection management.
    public static void connect() {
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            if (conn != null) {
                System.out.println("Connected to SQLite database");
                createTables(conn);
            }
        } catch (SQLException e) {
            System.out.println("Connection failed: " + e.getMessage());
        }
    }

    private static void createTables(Connection conn) {
        String createUsersTable = """
            CREATE TABLE IF NOT EXISTS users (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                username TEXT NOT NULL UNIQUE,
                password TEXT NOT NULL,
                balance REAL DEFAULT 0
            );
        """;
        // Automatically creates ID's by incrementing each user
        String createTransactionsTable = """
            CREATE TABLE IF NOT EXISTS transactions (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                user_id INTEGER,
                amount REAL,
                type TEXT,
                timestamp DATETIME DEFAULT CURRENT_TIMESTAMP,
                FOREIGN KEY(user_id) REFERENCES users(id)
            );
        """;

        try (Statement stmt = conn.createStatement()) {
            stmt.execute(createUsersTable);
            stmt.execute(createTransactionsTable);
            System.out.println("Tables created or already exist");
        } catch (SQLException e) {
            System.out.println("Table creation failed: " + e.getMessage());
        }
    }
}
