package controller;

import config.Config;
import controller.util.StageLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.*;

/**
 * Class Leaderboards Controller is used to displaying the leaderboards of players who save their
 * game result. This class is part of game Duelovka.
 */
public class LeaderboardsController implements Initializable {
    private Stage appStage;
    @FXML
    private ListView<String> list;
    @FXML
    private Button MenuButton;

    private List<String> lines;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lines = new ArrayList<>();
        loadFile(lines);
        sortScores(lines);

        for (String line : lines) {
            String[] parts = line.split(";");
            line = parts[0] + "  -  " + parts[1];
            list.getItems().add(line);
        }

    }

    /**
     * Comparator function which takes List of Strings. Ignores all characters in String
     * except numbers and sorts them accodingly
     *
     * @param lines List of lines to sort
     */
    private void sortScores(List<String> lines) {
        Collections.sort(lines, new Comparator<String>() {
            public int compare(String part1, String part2) {
                return extractInt(part1) - extractInt(part2);
            }

            int extractInt(String s) {
                String num = s.replaceAll("\\D", "");
                // 0 kdyz to nenajde cislo
                return num.isEmpty() ? 0 : Integer.parseInt(num);
            }
        });
    }

    /**
     * Function which loads text file with scores and saves each line into given list
     *
     * @param lines List where save lines to
     */
    private void loadFile(List<String> lines) {
        String line;
        FileReader inputreader = null;

        try {
            inputreader = new FileReader(Config.getGameFolder() + "leaderboards.txt");
            BufferedReader buffreader = new BufferedReader(inputreader);

            while ((line = buffreader.readLine()) != null) {
                lines.add(line);
            }
            buffreader.close();
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }

    /**
     * Button which displays MainMenu scene
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
    }
}