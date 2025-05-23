import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.HashMap;

public class MainFX extends Application {

    HashMap<Integer, User> usersByID = new HashMap<>();
    HashMap<String, Integer> usernameToID = new HashMap<>();

    private BankManager bankManager = new BankManager(usersByID, usernameToID);

    @Override
    public void start(Stage primaryStage) {

        MainScreen mainScreen = new MainScreen(bankManager);
        mainScreen.start(primaryStage);

    }

    public static void main(String[] args) {
        launch(args);
    }
}
