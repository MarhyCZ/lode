import config.Config;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Class Main - class represents the overall application - game Duelovka.
 * @version winter session 18/19
 */
public class Main extends Application {
    private Stage primaryStage;

    @Override
    /**
     * Start method initializes game - creates menu and overall scene that has defined dimensions and title.
     */
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("view/MainMenu.fxml"));
        primaryStage.setTitle("Duelovka");
        primaryStage.setScene(new Scene(root, Config.getStageWidth(), Config.getStageHeight()));
        primaryStage.show();
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
