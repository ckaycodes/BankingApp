import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DashboardScreen {

    private BankManager bankManager;
    private User currentUser;

    public DashboardScreen(BankManager bankManager, User currentUser) {
        this.bankManager = bankManager;
        this.currentUser = currentUser;
    }

    public void start(Stage stage) {
        Label welcomeLabel = new Label("Welcome, " + currentUser.getUsername());
        Label idLabel = new Label("Account ID: " + currentUser.getID());
        Label balanceLabel = new Label("Balance: $" + currentUser.getAccount().getBalance());

        Button depositBtn = new Button("Deposit");
        Button withdrawBtn = new Button("Withdraw");
        Button logoutBtn = new Button("Log Out");

        // Add button actions (these can open new screens or dialogs)
        depositBtn.setOnAction(e -> {
            // future: show deposit dialog or screen
        });

        withdrawBtn.setOnAction(e -> {
            // future: show withdraw dialog or screen
        });

        logoutBtn.setOnAction(e -> {
            new MainScreen(bankManager).start(stage);
        });

        VBox layout = new VBox(15);
        layout.setStyle("-fx-padding: 20; -fx-alignment: center;");
        layout.getChildren().addAll(welcomeLabel, idLabel, balanceLabel, depositBtn, withdrawBtn, logoutBtn);

        Scene scene = new Scene(layout, 400, 300);
        stage.setScene(scene);
        stage.setTitle("Dashboard");
        stage.show();
    }
}
