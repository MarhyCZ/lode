package model;

import config.Config;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import model.board.Board;
import model.board.Cell;
import model.board.Position;
import model.board.ShipInventory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * The class Game is the main application logic class.
 * It captures the end of the game and also evaluates individual strikes..
 * This class is part of game Duelovka.
 */
public class Game {
    private Random random = new Random();
    private static final int Y_SIZE = Config.getYSize();
    private static final int X_SIZE = Config.getXSize();
    private boolean isEnd = false;
    private boolean isVictory = false;
    private boolean isShooting = false;
    private int gameCounter;
    private Board playerBoard;
    private Board pcBoard;
    private List<Position> pcShots = new ArrayList<>();

    private int shipCellsNumber;
    private int playerhits;
    private int pchits;


    /**
     * Instantiates of new game.
     *
     * @param playerBoard the player board
     * @param pcBoard     the pc board
     */
    public Game(Board playerBoard, Board pcBoard) {
        this.playerBoard = playerBoard;
        this.pcBoard = pcBoard;
        ShipInventory inventory = new ShipInventory();
        this.shipCellsNumber = inventory.getCellsNumber();

    }

    /**
     * The method which initializes cell situations.
     *
     * @param cell the cell
     */
    public void shoot(Cell cell) {
        if (cell.wasShot() || isShooting) {
            return;
        }

        isShooting = true;

        if (cell.shootCell()) {
            playerhits++;
        }
        checkGameStatus();
        gameCounter++;

        if (isEnd) {
            return;
        }
        Timeline timeline = new Timeline(new KeyFrame(
                Duration.millis(2000),
                ae -> counterMove()));
        timeline.play();
    }

    /**
     * The method which is used for checking game status.
     */
    public void checkGameStatus() {
        if (pchits == shipCellsNumber) {
            isEnd = true;
        }

        if (playerhits == shipCellsNumber) {
            isEnd = true;
            isVictory = true;
        }
    }

    /**
     * Returns value if it's end of the game.
     *
     * @return the boolean
     */
    public boolean isEnd() {
        return isEnd;
    }

    /**
     * Returns value if is someone victorious.
     *
     * @return the boolean
     */
    public boolean isVictorious() {
        return isVictory;
    }

    /**
     * The method which returns game counter.
     *
     * @return the game counter
     */
    public int getGameCounter() {
        return gameCounter;
    }

    /**
     * The method which counts moves of player.
     */
    public void counterMove() {

        boolean succesfullThinking = false;

        do {
            int xPosition = this.random.nextInt(X_SIZE);
            int yPosition = this.random.nextInt(Y_SIZE);
            Cell cell = playerBoard.getCell(xPosition, yPosition);

            if (pcShots.contains(new Position(xPosition, yPosition))) {
                succesfullThinking = false;
            } else {
                if (cell.shootCell()) {
                    pchits++;
                }
                checkGameStatus();
                isShooting = false;
                succesfullThinking = true;
            }

        } while (!succesfullThinking);
    }

    /**
     * The method which sets end of game.
     */
    public void setEnd() {
        this.isEnd = true;
    }

    /**
     * The method which sets win a victory.
     */
    public void setVictory() {
        this.isVictory = true;
    }
}
