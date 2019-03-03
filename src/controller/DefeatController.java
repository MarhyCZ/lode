package controller;

import controller.util.StageLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Class Defeat Controller is for situation when is end of game.
 * This class is part of game Duelovka.
 */
public class DefeatController implements Initializable {
    Stage appStage;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    @FXML
    private Button MenuButton;
    @FXML
    private Button editorButton;

    /**
     * The method which will lead us after clicking on button to Menu.
     *
     * @param actionEvent the action event
     * @throws IOException the io exception
     */
    @FXML
    public void menuClick(ActionEvent actionEvent) throws IOException {
        AudioPlayer.defeatStop();
        Stage appStage = (Stage) MenuButton.getScene().getWindow();

        StageLoader.load(appStage, "MainMenu.fxml");
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


}
