package controller;

import controller.util.StageLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Class Help is used for give advices about playing game Duelovka through video.
 * This class is part of game Duelovka.
 */
public class HelpController implements Initializable {
    private Stage appStage;
    private Boolean isPlaying;
    @FXML
    private MediaView mview;
    @FXML
    private Button MenuButton;

    private MediaPlayer mplayer;

    private Media media;

    @Override
    /**
     * The method which is used for finding video file, plays it and sets isPlaying to true
     */
    public void initialize(URL url, ResourceBundle resourceBundle) {
        AudioPlayer.mainThemeStop();
        media = new Media(getClass().getClassLoader().getResource("resources/napoveda.mp4").toString());
        mplayer = new MediaPlayer(media);
        mview.setMediaPlayer(mplayer);
        mplayer.setAutoPlay(true);
        isPlaying = true;
    }

    /**
     * The method which is used for play and pause action.
     */
    @FXML
    public void playPause() {
        if (isPlaying) {
            mplayer.pause();
            isPlaying = false;
        } else {
            mplayer.play();
            isPlaying = true;
        }
    }

    /**
     * The method which will lead us after clicking on button to Menu.
     *
     * @param actionEvent the action event
     * @throws IOException the io exception
     */
    @FXML
    public void menuClick(ActionEvent actionEvent) throws IOException {
       AudioPlayer.mainThemeStop();
        AudioPlayer.buttonClick();
        appStage = (Stage) MenuButton.getScene().getWindow();

        StageLoader.load(appStage, "MainMenu.fxml");
        mplayer.pause();
        isPlaying = false;
    }
}
