import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.text.NumberFormat;

public class DepositSuccess {

    private BankManager bankManager;
    private User user;
    private Float depositAmount;

    public DepositSuccess(BankManager bankManager, User user, Float depositAmount) {
        this.bankManager = bankManager;
        this.user = user;
        this.depositAmount = depositAmount;
    }

    public void start(Stage stage) {
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
        Label successLabel = new Label("🎉 Deposit of: " + currencyFormat.format(depositAmount) + " successful");
        successLabel.setStyle("-fx-font-size: 18; -fx-text-fill: green;");
        Button goToDashboardBtn = new Button("Go Back to Dashboard");

        goToDashboardBtn.setOnAction(e -> {
            new DashboardScreen(bankManager,user).start(stage);
        });

        VBox layout = new VBox(20);
        layout.setStyle("-fx-padding: 30; -fx-alignment: center;");
        layout.getChildren().addAll(successLabel, goToDashboardBtn);

        Scene scene = new Scene(layout, 400, 300);
        stage.setScene(scene);
        stage.show();
    }
}