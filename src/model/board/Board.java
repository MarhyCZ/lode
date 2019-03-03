package model.board;

import config.Config;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.util.Colors;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Class Board is main class for situations on game board with ships. For example when we are setting them into board.
 *
 * This class is part of game Duelovka.
 */
public class Board extends Parent {
    private int Y_SIZE;
    private int X_SIZE;
    private ArrayList<Cell> currentShipCells = new ArrayList<>();
    private Random random = new Random();

    private VBox column = new VBox();

    /**
     * Instantiates a new board.
     *
     * @param mouseClickHandler   the mouse click handler
     * @param mouseEnteredHandler the mouse entered handler
     * @param mouseExitedHandler  the mouse exited handler
     */
    public Board(
            EventHandler<? super MouseEvent> mouseClickHandler,
            EventHandler<? super MouseEvent> mouseEnteredHandler,
            EventHandler<? super MouseEvent> mouseExitedHandler) {

        Y_SIZE = Config.getYSize();
        X_SIZE = Config.getXSize();
        for (int y = 0; y < Y_SIZE; y++) {
            HBox row = new HBox();
            for (int x = 0; x < X_SIZE; x++) {
                Cell cell = new Cell(x, y, this);
                cell.setOnMouseClicked(mouseClickHandler);
                cell.setOnMouseEntered(mouseEnteredHandler);
                cell.setOnMouseExited(mouseExitedHandler);
                row.getChildren().add(cell);
            }
            column.getChildren().add(row);
        }

        getChildren().add(column);
    }

    /**
     * Instantiates a new board without any attached eventHandlers.
     * Suitable only for displaying ships without interaction
     *
     * @param X_SIZE width of the board
     * @param Y_SIZE height of the board
     */
    public Board(int X_SIZE, int Y_SIZE) {
        this.X_SIZE = X_SIZE;
        this.Y_SIZE = Y_SIZE;
        for (int y = 0; y < Y_SIZE; y++) {
            HBox row = new HBox();
            for (int x = 0; x < X_SIZE; x++) {
                Cell cell = new Cell(x, y, this);
                row.getChildren().add(cell);
            }
            column.getChildren().add(row);
        }

        getChildren().add(column);
    }
    /**
     * The method which is used for registration events.
     *
     * @param mouseClickHandler   the mouse click handler
     * @param mouseEnteredHandler the mouse entered handler
     * @param mouseExitedHandler  the mouse exited handler
     */
    public void registerEvents(
            EventHandler<? super MouseEvent> mouseClickHandler,
            EventHandler<? super MouseEvent> mouseEnteredHandler,
            EventHandler<? super MouseEvent> mouseExitedHandler) {

        for (Node child : column.getChildren()) {
            HBox row = (HBox) child;
            for (Node cell : row.getChildren()) {
                cell.setOnMouseClicked(mouseClickHandler);
                cell.setOnMouseEntered(mouseEnteredHandler);
                cell.setOnMouseExited(mouseExitedHandler);
            }
        }
    }

    /**
     * The method which is used for clearing cells.
     */
    public void clear() {
        for (Node child : column.getChildren()) {
            HBox row = (HBox) child;
            for (Node cell : row.getChildren()) {
                ((Cell) cell).formatCell();
            }
        }
    }

    /**
     * The method which is used for hiding ships.
     */
    public void hideShips() {
        for (Node child : column.getChildren()) {
            HBox row = (HBox) child;
            for (Node cell : row.getChildren()) {
                ((Cell) cell).setColors(Colors.WATER.getColor(), Colors.WATEROUTLINE.getColor());
            }
        }
    }

    /**
     * The method which shows ships if they are hit or not.
     */
    public void showShips() {
        for (Node child : column.getChildren()) {
            HBox row = (HBox) child;
            for (Node cellNode : row.getChildren()) {
                Cell cell = (Cell) cellNode;

                if (cell.getShip() != null) {
                    if (cell.wasShot()) {
                        cell.setColors(Colors.HIT.getColor(), Colors.HIT.getColor());
                    } else {
                        cell.setColors(Colors.SHIP.getColor(), Colors.SHIP.getColor());
                    }
                }
            }
        }
    }

    /**
     * The method which returns cell.
     *
     * @param x the x
     * @param y the y
     * @return the cell
     */
    public Cell getCell(int x, int y) {
        HBox row = (HBox) column.getChildren().get(y);
        return (Cell) row.getChildren().get(x);
    }

    /**
     * Place ship randomly.
     *
     * @param ship the ship
     */
    public void placeShipRandomly(Ship ship) {
        boolean succesfullPlacement;

        do {
            int xPosition = this.random.nextInt(X_SIZE);
            int yPosition = this.random.nextInt(Y_SIZE);
            Cell cell = getCell(xPosition, yPosition);

            succesfullPlacement = placeShip(ship, cell);
        } while (!succesfullPlacement);
    }

    private boolean isValidPoint(int x, int y) {
        return x >= 0 && x < X_SIZE && y >= 0 && y < Y_SIZE;
    }

    /**
     * Initializes neighboring ships.
     * @param x
     * @param y
     */
    private Cell[] getCellsNeighbors(int x, int y) {
        Position[] positions =
                new Position[]{
                        new Position(x, y - 1), // N
                        new Position(x + 1, y - 1), // NE
                        new Position(x + 1, y), // E
                        new Position(x + 1, y + 1), // SE
                        new Position(x, y + 1), // S
                        new Position(x - 1, y + 1), // SW
                        new Position(x - 1, y), // W
                        new Position(x - 1, y - 1) // NW
                };
        List<Cell> neighbors = new ArrayList<>();

        for (Position position : positions) {
            int xPosition = position.getX();
            int yPosition = position.getY();
            if (isValidPoint(xPosition, yPosition)) neighbors.add(getCell(xPosition, yPosition));
        }

        return neighbors.toArray(new Cell[0]);
    }

    /**
     * Calculates the location of all the boxes that the ship will occupy and stores them in the cells list.
     * At the same time throws an error if the ship is out of the box.
     * @param ship
     * @param position
     */
    private boolean verifyShipCells(Ship ship, Position position) {
        ArrayList<Position> offsets = ship.getOffsets();
        int xBasePosition = position.getX();
        int yBasePosition = position.getY();

        boolean result = true;
        currentShipCells.clear();

        for (Position offset : offsets) {
            int xPosition = xBasePosition + offset.getX();
            int yPosition = yBasePosition + offset.getY();
            Cell cell = null;

            if (isValidPoint(xPosition, yPosition)) {
                cell = getCell(xPosition, yPosition);

                if (!cell.isEmpty()) return false;

                if (cell != null) this.currentShipCells.add(cell);

                // Aby nelepil lode k sobe
                for (Cell neighbor : getCellsNeighbors(xPosition, yPosition))
                    if (!neighbor.isEmpty()) return false;

            } else return false;
        }

        return result;
    }

    /**
     * Place ship boolean.
     *
     * @param ship       the ship
     * @param originCell the origin cell
     * @return the boolean
     */
    public boolean placeShip(Ship ship, Cell originCell) {

        if (!verifyShipCells(ship, originCell.getPosition())) {
            return false;
        }

        for (Cell cell : this.currentShipCells) {
            cell.setShip(ship);
            ship.registerCell(cell);
        }
        return true;
    }

}
