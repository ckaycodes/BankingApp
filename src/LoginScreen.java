import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoginScreen {

    private BankManager bankManager;

    public LoginScreen(BankManager bankManager) {
        this.bankManager = bankManager;
    }

    public void start(Stage stage) {
        Label userLabel = new Label("Username:");
        TextField usernameField = new TextField();

        Label passLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();

        Button loginBtn = new Button("Log In");
        Button backBtn = new Button("Back to Main Menu");

        loginBtn.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();
            try {
                if (username.isEmpty() || password.isEmpty()) {
                    showAlert("Please fill in both fields.");
                    return;
                }

                User user = bankManager.getUserByUsername(username);

                if (user != null && user.getPassword().equals(password)) {
                    showAlert("Welcome back, " + username + "!");
                    DashboardScreen dashboard = new DashboardScreen(bankManager, user);
                    dashboard.start(stage);
                } else
                    showAlert("Invalid username or password.");

            } catch (Exception ex) {
                ex.printStackTrace(); // logs error
                Alert alert = new Alert(Alert.AlertType.ERROR, "Something went wrong. Please try again.", ButtonType.OK);
                alert.showAndWait();
            }
        });

        backBtn.setOnAction(e -> {
            new MainScreen(bankManager).start(stage);
        });

        VBox layout = new VBox(10);
        layout.setStyle("-fx-padding: 20; -fx-alignment: center;");
        layout.getChildren().addAll(userLabel, usernameField, passLabel, passwordField, loginBtn, backBtn);

        Scene scene = new Scene(layout, 400, 300);
        stage.setScene(scene);
        stage.setTitle("Login");
        stage.show();

    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
