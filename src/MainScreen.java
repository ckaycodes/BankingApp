import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class MainScreen {

    private BankManager bankManager;

    public MainScreen (BankManager bankManager){
        this.bankManager = bankManager;
    }

    public void start(Stage stage) {

        // Buttons
        Button createAccBtn = styleButton("Create Account");
        Button loginBtn = styleButton("Login");

        // Placeholder actions
        createAccBtn.setOnAction(e -> {
            AccountCreationScreen accountScreen = new AccountCreationScreen(bankManager);
            accountScreen.start(stage); //Switch to account creation screen
        });

        loginBtn.setOnAction(e -> new LoginScreen(bankManager).start(stage));

        // Including Logo in scene
        Image logoImage = new Image(getClass().getResourceAsStream("/20250603_1547_Clean Logo Design_remix_01jwvzjs5sfxgr3cycdex3ms8f.png"));
        ImageView logoView = new ImageView(logoImage);
        logoView.getStyleClass().add("image"); //added to try to make buttons closer

        logoView.setFitWidth(400);
        logoView.setFitHeight(600);
        logoView.setPreserveRatio(true);

        // Layout
        VBox mainLayout = new VBox(10, logoView, createAccBtn, loginBtn);
        mainLayout.setAlignment(Pos.CENTER);
        mainLayout.setPadding(new Insets(40, 20, 20, 20));

        // Moved buttons closer to logo
        createAccBtn.setTranslateY(-130);
        loginBtn.setTranslateY(-130);


        // Scene
        Scene scene = new Scene(mainLayout, 600, 600);
        scene.getStylesheets().add(getClass().getResource("/styling.css").toExternalForm());


        // Set stage
        stage.setTitle("Chan Bank - Main Menu");
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
        stage.setFullScreenExitHint(""); //removes annoying Escape Key reminder
        stage.setFullScreen(true);

    }

    // Following builder pattern, function to remove repeated pattern when styling buttons
    private Button styleButton(String contents) {
        Button styledResult = new Button(contents);
        styledResult.getStyleClass().add("button");
        return styledResult;
    }

}
