import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.converter.FloatStringConverter;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import java.text.NumberFormat;

public class WithdrawSuccess {

    private final BankManager bankManager;
    private final User user;
    private final Float depositAmount;


    public WithdrawSuccess(BankManager bankManager, User user, Float depositAmount){
        this.bankManager = bankManager;
        this.user = user;
        this.depositAmount = depositAmount;
    }

    public void start(Stage stage) {
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
        Label successLabel = new Label("🎉 Withdraw of: " + currencyFormat.format(depositAmount) + " successful");
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
    }
}

