package model.ships;

import model.board.Position;
import model.board.Ship;

import java.util.ArrayList;

/**
 * Class Boat Ship, which characterizes a particular type of ship with special characteristics (size).
 * This class is part of game Duelovka.
 */
public class BoatShip extends Ship {
    /**
     * Instantiates of new boat - Boat ship. Sets the dimensions using x and y points.
     */
    public BoatShip() {
        ArrayList<Position> offsets = new ArrayList<>();
        offsets.add(new Position(0, 0));
        offsets.add(new Position(1, 0));
        this.buildShip(offsets);
    }

}
