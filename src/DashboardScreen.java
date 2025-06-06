import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DashboardScreen {

    private final BankManager bankManager;
    private final User currentUser;

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
        Button transactionHistoryBtn = new Button("Transaction History");
        Button logoutBtn = new Button("Log Out");

        // Add button actions (these can open new screens or dialogs)
        depositBtn.setOnAction(e -> {
            new DepositScreen(bankManager, currentUser).start(stage);

        });

        withdrawBtn.setOnAction(e -> {
            new WithdrawScreen(bankManager, currentUser).start(stage);
        });

        logoutBtn.setOnAction(e -> {
            new MainScreen(bankManager).start(stage);
        });

        transactionHistoryBtn.setOnAction(e-> {
            new TransactionHistoryScreen(currentUser,bankManager).start(stage);
        });

        VBox layout = new VBox(15);
        layout.setStyle("-fx-padding: 20; -fx-alignment: center;");
        layout.getChildren().addAll(welcomeLabel, idLabel, balanceLabel, depositBtn, withdrawBtn, transactionHistoryBtn,
                logoutBtn);

        Scene scene = new Scene(layout, 400, 300);
        stage.setScene(scene);
        scene.getStylesheets().add(getClass().getResource("/styling.css").toExternalForm());
        stage.setTitle("Dashboard");
        stage.setFullScreen(true);
    }
}
