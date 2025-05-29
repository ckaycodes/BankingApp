import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.ArrayList;


public class TransactionHistoryScreen extends TransactionHistory {

    private final User user;
    private final BankManager bankManager;

    // Takes in User and BankManager to tailor history to User/Account
    public TransactionHistoryScreen(User user, BankManager bankManager) {
        this.user = user;
        this.bankManager = bankManager;
    }

    public void start(Stage stage) {

        // ---- Table JavaFX set up ----
        TableView<Transaction> tableView = new TableView<>();

        // Amount Column
        TableColumn<Transaction, Float> amountColumn = new TableColumn<>("Amount");
        amountColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));
        tableView.getColumns().add(amountColumn);

        // Transaction Type Column
        TableColumn<Transaction, String> typeColumn = new TableColumn<>("Type");
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        tableView.getColumns().add(typeColumn);

        // Add the transactions
        tableView.getItems().addAll(user.getHistory().getTransactions());
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY); //Fit columns to table width

        //back button logic
        Button backBtn = new Button("Back To Dashboard");
        backBtn.setOnAction(e -> {
            new DashboardScreen(bankManager, user).start(stage);
        });


        VBox layout = new VBox(15);
        layout.setPadding(new Insets(20));
        VBox.setVgrow(tableView, Priority.ALWAYS); // Let table grow with the window
        layout.getChildren().addAll(backBtn, tableView);

        Scene scene = new Scene(layout, 400, 300);
        stage.setScene(scene);
        stage.setTitle("Transaction History");
        stage.show();

    }

}
