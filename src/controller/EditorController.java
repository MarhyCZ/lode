package controller;

import controller.util.StageLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.board.Board;
import model.board.Cell;
import model.board.Ship;
import model.board.ShipInventory;
import model.util.RandomGenerator;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Class Editor Controller which shows us page, where we can manage our board with ships and after setting ships to board
 * we can play a game.
 * This class is part of game Duelovka.
 */
public class EditorController implements Initializable {
    private Board playerBoard;
    private Board previewBoard;
    private Ship currentShip;
    private ShipInventory inventory;

    private Stage appStage;
    @FXML
    private VBox playerBoardPane;
    @FXML
    private VBox previewBoardPane;

    @FXML
    private Button toBattlefield;
    @FXML
    private Button randomButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        inventory = new ShipInventory();
        currentShip = inventory.getNextShip();
        initializeEditor();
    }

    private void initializeEditor() {
        playerBoard =
                new Board(this::playerBoardClick, this::playerBoardEntered, this::playerBoardExited);
        playerBoardPane.getChildren().addAll(playerBoard);

        previewBoard = new Board(10, 10);
        previewBoardPane.getChildren().addAll(previewBoard);
        previewBoard.placeShip(currentShip, previewBoard.getCell(3, 3));

    }

    private void playerBoardExited(MouseEvent mouseEvent) {
    }

    private void playerBoardEntered(MouseEvent mouseEvent) {
    }

    /**
     * Methods which indicates mouse events on board when you are setting your board with ships.
     * @param mouseEvent
     */
    private void playerBoardClick(MouseEvent mouseEvent) {
        Cell cell = (Cell) mouseEvent.getSource();

        if (currentShip == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Varování");
            alert.setHeaderText("Už máš prázdný inventář.");
            alert.showAndWait();
            return;
        }
        if (playerBoard.placeShip(currentShip, cell)) {
            AudioPlayer.shipCraft();
            this.currentShip = inventory.getNextShip();
            previewBoard.clear();
            if (currentShip != null) {
                previewBoard.placeShip(currentShip, previewBoard.getCell(2, 3));
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Varování");
            alert.setHeaderText("Neklikej takhle všude!");

            alert.showAndWait();
        }
    }

    /**
     * The method which is used for random click on board.
     *
     * @param actionEvent the action event
     */
    @FXML
    public void randomClick(ActionEvent actionEvent) {
        AudioPlayer.buttonClick();
        RandomGenerator.fillBoard(playerBoard);
    }

    /**
     * The method which will lead us after clicking on button to battlefield (game).
     *
     * @param actionEvent the action event
     * @throws IOException the io exception
     */
    @FXML
    public void toBattlefieldClick(ActionEvent actionEvent) throws IOException {
        AudioPlayer.mainThemeStop();
        AudioPlayer.buttonClick();
        appStage = (Stage) toBattlefield.getScene().getWindow();

        FXMLLoader loader = StageLoader.loadAdvanced(appStage, "Battle.fxml");
        BattleController controller = loader.getController();
        controller.initializeSlowGame(playerBoard);
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
        appStage = (Stage) toBattlefield.getScene().getWindow();

        StageLoader.load(appStage, "MainMenu.fxml");
    }
}
