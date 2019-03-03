package controller;

import controller.util.StageLoader;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Class Main Menu Controller initializes the welcome screen of the game
 * and represents the main menu of the game .
 * This class is part of game Duelovka.
 */
public class MainMenuController implements Initializable {

    Stage appStage;

    @FXML
    private Button quickGameButton;

    @FXML
    private Button editorButton;

    @FXML
    private Button helpButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        AudioPlayer.mainTheme();

    }

    /**
     * The method which will lead us after clicking on button to quick game.
     *
     * @throws IOException the io exception
     */
    @FXML
    public void quickGameClick() throws IOException {
        AudioPlayer.buttonClick();
        appStage = (Stage) quickGameButton.getScene().getWindow();
        FXMLLoader loader = StageLoader.loadAdvanced(appStage, "Battle.fxml");
        BattleController controller = loader.getController();
        controller.initializeQuickGame();
        AudioPlayer.mainThemeStop();
    }

    /**
     * The method which will lead us after clicking on button to Editor page.
     *
     * @throws IOException the io exception
     */
    @FXML
    public void editorClick() throws IOException {
        AudioPlayer.buttonClick();
        appStage = (Stage) editorButton.getScene().getWindow();
        StageLoader.load(appStage, "Editor.fxml");

    }

    /**
     * The method which will lead us after clicking on button to Leaderboards page.
     *
     * @throws IOException the io exception
     */
    @FXML
    public void leaderboardsClick() throws IOException {
        AudioPlayer.buttonClick();
        appStage = (Stage) helpButton.getScene().getWindow();
        StageLoader.load(appStage, "Leaderboards.fxml");

    }

    /**
     * The method which will lead us after clicking on button to Help page.
     *
     * @throws IOException the io exception
     */
    @FXML
    public void helpClick() throws IOException {
        AudioPlayer.buttonClick();
        appStage = (Stage) helpButton.getScene().getWindow();
        StageLoader.load(appStage, "Help.fxml");

    }
}
