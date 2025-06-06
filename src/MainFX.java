import javafx.application.Application;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;

public class MainFX extends Application {

    HashMap<Integer, User> usersByID = new HashMap<>();
    HashMap<String, Integer> usernameToID = new HashMap<>();

    @Override
    public void start(Stage primaryStage) {


        try {
            // 1. Connect to SQLite database (creates file if it doesn't exist)
            Connection conn = DriverManager.getConnection("jdbc:sqlite:bank.db");

            // 2. Initialize BankManager with connection
            BankManager bankManager = new BankManager(conn);

            // 3. Launch GUI
            MainScreen mainScreen = new MainScreen(bankManager);
            mainScreen.start(primaryStage);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }





    public static void main(String[] args) {
        launch(args);
    }
}
