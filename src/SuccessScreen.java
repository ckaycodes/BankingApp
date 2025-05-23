import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SuccessScreen {

    private BankManager bankManager;

    public SuccessScreen(BankManager bankManager) {
        this.bankManager = bankManager;
    }

    public void start(Stage stage, String username) {
        Label successLabel = new Label("🎉 Account created successfully for " + username + "!");
        Button goToLoginBtn = new Button("Go to Login");

        goToLoginBtn.setOnAction(e -> {
            new LoginScreen(bankManager).start(stage);  // This assumes you have LoginScreen ready
        });

        VBox layout = new VBox(20);
        layout.setStyle("-fx-padding: 30; -fx-alignment: center;");
        layout.getChildren().addAll(successLabel, goToLoginBtn);

        Scene scene = new Scene(layout, 400, 300);
        stage.setScene(scene);
        stage.show();
    }
}