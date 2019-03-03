package controller.util;

import config.Config;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * The type Stage loader.
 */
public class StageLoader {
    private static int WIDTH = Config.getStageWidth();
    private static int HEIGHT = Config.getStageHeight();


    /**
     * Load.
     *
     * @param appStage the app stage
     * @param fxmlName the fxml name
     * @throws IOException the io exception
     */
    public static void load(Stage appStage, String fxmlName) throws IOException {
        Parent root = FXMLLoader.load(StageLoader.class.getResource("../../view/" + fxmlName));
        Scene scene = new Scene(root, WIDTH, HEIGHT);
        // scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        appStage.setScene(scene);

        System.out.println("FXML scena otevrena");
    }

    /**
     * Load advanced fxml loader.
     *
     * @param appStage the app stage
     * @param fxmlName the fxml name
     * @return the fxml loader
     * @throws IOException the io exception
     */
    public static FXMLLoader loadAdvanced(Stage appStage, String fxmlName) throws IOException {
        FXMLLoader loader = new FXMLLoader(StageLoader.class.getResource("../../view/" + fxmlName));
        Parent root = loader.load();

        Scene scene = new Scene(root, WIDTH, HEIGHT);
        // scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        appStage.setScene(scene);

        return loader;
    }


}