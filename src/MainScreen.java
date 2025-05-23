import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainScreen {

    private BankManager bankManager;

    public MainScreen (BankManager bankManager){
        this.bankManager = bankManager;
    }

    public void start(Stage stage) {

        // Title
        Text title = new Text("🏦 Welcome to Chan Bank");

        // Buttons
        Button createAccBtn = new Button("Create Account");
        Button loginBtn = new Button("Log In");

        // Placeholder actions
        createAccBtn.setOnAction(e -> {
            AccountCreationScreen accountScreen = new AccountCreationScreen(bankManager);
            accountScreen.start(stage); //Switch to account creation screen
        });

        loginBtn.setOnAction(e -> new LoginScreen(bankManager).start(stage));


        // Layout
        VBox layout = new VBox(20);
        layout.setStyle("-fx-padding: 30; -fx-alignment: center;");
        layout.getChildren().addAll(title, createAccBtn, loginBtn);

        // Scene
        Scene scene = new Scene(layout, 400, 300);

        // Set stage
        stage.setTitle("Chan Bank - Main Menu");
        stage.setScene(scene);
        stage.show();
    }
}
