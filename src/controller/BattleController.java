package controller;

import controller.util.StageLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Game;
import model.board.Board;
import model.board.Cell;
import model.util.RandomGenerator;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Class Battle Controller which is used for displaying our real game with computer.
 * This class is part of game Duelovka.
 */
public class BattleController implements Initializable {
    private Board playerBoard;
    private Board pcBoard;
    private Game game;

    /**
     * The Player board pane.
     */
    @FXML
    public VBox playerBoardPane;
    /**
     * The Pc board pane.
     */
    @FXML
    public VBox pcBoardPane;
    /**
     * The Move counter.
     */
    @FXML
    public Label moveCount;

    /**
     * The Defeat button.
     */
    @FXML
    public Button defeatButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    /**
     * Initialize slow game.
     *
     * @param board the board
     */
// Neco pro nahrani playerBoardu, kdyz uzivatel preskoci z editoru mapy
    public void initializeSlowGame(Board board) {
        playerBoard = board;
        playerBoardPane.getChildren().addAll(this.playerBoard);
        playerBoard.registerEvents(this::playerBoardClick, this::playerBoardEntered, this::playerBoardExited);

        startGame();
    }

    /**
     * The method which is used for initialization quick game.
     */
    public void initializeQuickGame() {
        playerBoard =
                new Board(this::playerBoardClick, this::playerBoardEntered, this::playerBoardExited);
        playerBoardPane.getChildren().addAll(playerBoard);
        RandomGenerator.fillBoard(playerBoard);

        startGame();
    }

    /**
     * The method which is used for PC's board of game.
     */
    private void startGame() {
        pcBoard = new Board(this::pcBoardClick, this::pcBoardEntered, this::pcBoardExited);
        pcBoardPane.getChildren().addAll(pcBoard);
        RandomGenerator.fillBoard(pcBoard);
        pcBoard.hideShips();

        game = new Game(playerBoard, pcBoard);
    }


    private void playerBoardClick(MouseEvent mouseEvent) {
        Cell cell = (Cell) mouseEvent.getSource();
    }

    private void playerBoardEntered(MouseEvent mouseEvent) {
    }

    private void playerBoardExited(MouseEvent mouseEvent) {
    }

    private void pcBoardExited(MouseEvent mouseEvent) {
    }

    private void pcBoardEntered(MouseEvent mouseEvent) {
    }

    /**
     * The method which is used for computer's click.
     * @param mouseEvent
     */
    private void pcBoardClick(MouseEvent mouseEvent) {
        Cell cell = (Cell) mouseEvent.getSource();
        game.shoot(cell);
        evaluateGame();
        moveCount.setText("  " + game.getGameCounter());
    }

    /**
     *
     * Ends the game and make you the winner.
     * @param actionEvent the action event
     */
    public void bratClick(ActionEvent actionEvent) {
        AudioPlayer.buttonClick();
        game.setVictory();
        game.setEnd();
        evaluateGame();
    }

    /**
     * The method which is used for defeat click.
     *
     * @param actionEvent the action event
     */
    public void defeatClick(ActionEvent actionEvent) {
        AudioPlayer.buttonClick();
        game.setEnd();
        evaluateGame();
    }

    /**
     * The method which is used for flashlight click.
     *
     * @param actionEvent the action event
     */
    public void flashlightClick(ActionEvent actionEvent) {
        AudioPlayer.buttonClick();
        pcBoard.showShips();
    }

    /**
     * The method which is used for evaluate game after and of game.
     */
    public void evaluateGame() {
        try {
            if (game.isEnd() && game.isVictorious()) {
                AudioPlayer.victory();
                Stage appStage = (Stage) moveCount.getScene().getWindow();
                FXMLLoader loader = StageLoader.loadAdvanced(appStage, "SaveScore.fxml");
                SaveScoreController controller = loader.getController();
                controller.importData(game.getGameCounter());
            } else if (game.isEnd()) {
                AudioPlayer.defeat();
                Stage appStage = (Stage) moveCount.getScene().getWindow();
                StageLoader.load(appStage, "Defeat.fxml");
            }
        } catch (Exception e) {
            System.out.println("Exception");
            System.out.println(e);
            System.out.println(game.isEnd());

        }
    }

}
