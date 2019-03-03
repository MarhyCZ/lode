package controller;

import config.Config;
import controller.util.StageLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Class Save Score Controller for saving score from our game to leaderboards.
 * This class is part of game Duelovka.
 */
public class SaveScoreController implements Initializable {
    Stage appStage;
    private int playerScore;

    @FXML
    private Button leaderboardsButton;

    @FXML
    private Button saveButton;
    @FXML
    private Label scoreLabel;
    @FXML
    private TextField playerNick;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    /**
     * The method which imports data.
     *
     * @param playerScore the player score
     */
    public void importData(int playerScore) {
        this.playerScore = playerScore;
        scoreLabel.setText(Integer.toString(playerScore));
    }


    /**
     * The method which saves score.
     *
     * @param event the event
     * @throws IOException the io exception
     */
    @FXML
    public void saveScore(ActionEvent event) throws IOException {
        String dataFolder = Config.getGameFolder();
        System.out.println(dataFolder + "leaderboards.txt");
        StringBuilder output = new StringBuilder();
        output.append(playerNick.getText() + ";");
        output.append(playerScore + "\n");

        File file = new File(dataFolder + "leaderboards.txt");
        if (!file.exists()) {
            file.createNewFile();
        }
        FileWriter writer = new FileWriter(file, true);
        writer.write(output.toString());
        writer.close();

        leaderboardsClick();
    }

    /**
     * The method which will lead us after clicking on button to Leaderboards page.
     *
     * @throws IOException the io exception
     */
    @FXML
    public void leaderboardsClick() throws IOException {
        AudioPlayer.buttonClick();
        appStage = (Stage) leaderboardsButton.getScene().getWindow();
        StageLoader.load(appStage, "Leaderboards.fxml");

    }


}
