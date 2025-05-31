import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Random;

public class AccountCreationScreen {

    private final BankManager bankManager;

    public AccountCreationScreen(BankManager bankManager) {
        this.bankManager = bankManager;
    }

    public void start(Stage stage) {


        // Labels and input fields
        Label userLabel = new Label("Username: ");
        TextField usernameField = new TextField();

        Label passLabel = new Label("Password: ");
        PasswordField passwordField = new PasswordField();

        Label confirmLabel = new Label("Confirm Password: ");
        PasswordField confirmPasswordField = new PasswordField();

        // Buttons
        Button createBtn = new Button("Create Account");
        Button backBtn = new Button("Back to main screen");


        createBtn.setOnAction(e-> {
            String username = usernameField.getText();
            String password = passwordField.getText();
            String confirmPassword = confirmPasswordField.getText();

            if (!password.equals(confirmPassword)) {
                showAlert("Passwords do not match.");
                return;
            }

            if (username.isEmpty() || password.isEmpty()) {
                showAlert("Please fill in both fields.");
                return;
            }

            if (bankManager.usernameExists(username)) {
                showAlert("Username already taken.");
                return;
            }

            else {
                // Create and store user
                Account userAcc = new Account();
                User newUser = new User(username, password,userAcc);
                bankManager.addUser(newUser);

                new SuccessScreen(bankManager).start(stage, username); // displays success screen
            }
        });

        // Clearing Fields
        usernameField.clear();
        passwordField.clear();
        confirmPasswordField.clear();

        // Back button logic
        backBtn.setOnAction(e-> {
            MainScreen mainScreen = new MainScreen(bankManager);
            mainScreen.start(stage); // Switch back to main screen
        });

        // Layout
        VBox layout = new VBox(10);
        layout.setStyle("-fx-padding: 20; -fx-alignment: center;");
        layout.getChildren().addAll(
                userLabel, usernameField,
                passLabel, passwordField,
                confirmLabel, confirmPasswordField,
                createBtn, backBtn
        );

        // Scene
        Scene scene = new Scene(layout, 400, 400);
        stage.setScene(scene);
        stage.setTitle("Create New Account");
        stage.show();
    }

    // Alert message
    private void showAlert(String message){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(message);
        alert.setHeaderText(null);
        alert.showAndWait();
    }
}
