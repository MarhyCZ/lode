package model.util;

import model.board.Board;
import model.board.Ship;
import model.board.ShipInventory;

/**
 * Class Random Generator for initialization of the game plan.
 * This class is part of game Duelovka.
 */
public class RandomGenerator {

    /**
     * The method which fill board with boats.
     *
     * @param board the board
     */
    public static void fillBoard(Board board) {
        ShipInventory inventory = new ShipInventory();
        Ship currentShip = inventory.getNextShip();
        board.clear();

        do {
            board.placeShipRandomly(currentShip);
            currentShip = inventory.getNextShip();

        } while (currentShip != null);
    }

    /**
     * The method which returns random position of boats.
     */
    public static void getRandPosition() {

    }
}
