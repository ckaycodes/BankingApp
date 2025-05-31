import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.converter.FloatStringConverter;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

import java.util.function.UnaryOperator;

public class DepositScreen {

    private final BankManager bankManager;
    private final User user;

    public DepositScreen(BankManager bankManager, User user) {
        this.bankManager = bankManager;
        this.user = user;
    }

    public void start(Stage stage) {
        Label depositInputLabel = new Label("Amount: ");
        TextField depositInput = new TextField();

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
        depositInput.setTextFormatter(floatFormatter);

        Button confirmDeposit = new Button("Confirm Deposit");
        Button backBtn = new Button("Back to Dashboard");

        confirmDeposit.setOnAction(e -> {
            try {
                depositInput.commitValue(); // ensure TextFormatter value is up to date
                Float depositAmount = floatFormatter.getValue();

                if (depositAmount == null || depositAmount <= 0) {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Please enter a valid deposit amount.", ButtonType.OK);
                    alert.showAndWait();
                    return;
                }

                user.getAccount().deposit(depositAmount); //deposits amount to User's account
                bankManager.updateUserBalance(user.getID(), user.getAccount().getBalance()); // update db with new balance
                bankManager.addTransaction(user.getID(), depositAmount, "Deposit"); // add transaction to db history


                
                new DepositSuccess(bankManager, user, depositAmount).start(stage);

            } catch (Exception ex) {
                ex.printStackTrace(); // logs error
                Alert alert = new Alert(Alert.AlertType.ERROR, "Something went wrong. Please try again.", ButtonType.OK);
                alert.showAndWait();
            }
        });

        backBtn.setOnAction(e -> {
            new DashboardScreen(bankManager, user).start(stage);
        });

        VBox layout = new VBox(15, depositInputLabel, depositInput, confirmDeposit, backBtn);
        layout.setPadding(new Insets(20)); // spacing from edges
        layout.setAlignment(Pos.CENTER); // centers content

        Scene scene = new Scene(layout, 400, 300);
        stage.setScene(scene);
        stage.setTitle("Deposit");
        stage.show();
    }
}