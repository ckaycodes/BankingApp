import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DepositScreen {

    private BankManager bankManager;

    public DepositScreen(BankManager bankManager) {
        this.bankManager = bankManager;
    }

    public void start(Stage stage){

        Label depositInputLabel = new Label("Amount: ");
        TextField depositInput = new TextField();

        Button confirmDeposit = new Button("Confirm Deposit");
        Button backBtn = new Button("Back to Main Menu");

    }

}
