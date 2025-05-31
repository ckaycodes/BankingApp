import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.converter.FloatStringConverter;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import java.util.function.UnaryOperator;

public class WithdrawScreen {

    private final BankManager bankManager;
    private final User user;

    public WithdrawScreen(BankManager bankManager, User user) {
        this.bankManager = bankManager;
        this.user = user;
    }

    public void start(Stage stage) {

        Label withdrawInputLabel = new Label("Amount: ");
        TextField withdrawInput = new TextField();

        // Set a TextFormatter to filter and format float input
        UnaryOperator<TextFormatter.Change> filter = change -> {
            String newText = change.getControlNewText();
            if (newText.isEmpty()) return change; // allow empty
            try {
                Float.parseFloat(newText);
                return change;
            } catch (NumberFormatException e) {
                return null; // block invalid input
            }
        };

        TextFormatter<Float> floatFormatter = new TextFormatter<>(new FloatStringConverter(), null, filter);
        withdrawInput.setTextFormatter(floatFormatter);

        Button confirmWithdraw = new Button("Confirm Withdraw");
        Button backBtn = new Button("Back to Dashboard");

        confirmWithdraw.setOnAction(e -> {
            try {
                withdrawInput.commitValue(); // ensure TextFormatter value is up to date (finalize selected value)
                Float withdrawAmount = floatFormatter.getValue();

                if (withdrawAmount == null || withdrawAmount <= 0) {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Please enter a valid withdrawal amount.", ButtonType.OK);
                    alert.showAndWait();
                    return;
                }
                else if (withdrawAmount > user.getAccount().getBalance()) {

                    Alert alert = new Alert(Alert.AlertType.ERROR, "Insufficient Funds.", ButtonType.OK);
                    alert.showAndWait();
                    return; // If number is very large, there is an instance where the user cannot delete the amount
                }

                user.getAccount().withDraw(withdrawAmount); //deposits amount to User's account
                bankManager.updateUserBalance(user.getID(), user.getAccount().getBalance()); // update db with new balance
                bankManager.addTransaction(user.getID(), withdrawAmount, "Withdraw"); // add transaction to db history

                new WithdrawSuccess(bankManager, user, withdrawAmount).start(stage);

            } catch (Exception ex) {
                ex.printStackTrace(); // logs error
                Alert alert = new Alert(Alert.AlertType.ERROR, "Something went wrong. Please try again.", ButtonType.OK);
                alert.showAndWait();
            }
        });

        backBtn.setOnAction(e -> {
            new DashboardScreen(bankManager, user).start(stage);
        });

        VBox layout = new VBox(15, withdrawInputLabel, withdrawInput, confirmWithdraw, backBtn);
        layout.setPadding(new Insets(20)); // spacing from edges
        layout.setAlignment(Pos.CENTER); // centers content

        Scene scene = new Scene(layout, 400, 300);
        stage.setScene(scene);
        stage.setTitle("Deposit");
        stage.show();
    }
}
