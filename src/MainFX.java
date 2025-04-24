import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainFX extends Application {

    @Override
    public void start(Stage primaryStage) {

        MainScreen mainScreen = new MainScreen();
        mainScreen.start(primaryStage);

    }

    public static void main(String[] args) {
        launch(args);
    }
}
