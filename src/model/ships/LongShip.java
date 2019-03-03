package model.ships;

import model.board.Position;
import model.board.Ship;

import java.util.ArrayList;

/**
 * Class Long Ship, which characterizes a particular type of ship with special characteristics (size).
 * This class is part of game Duelovka.
 */
public class LongShip extends Ship {
    /**
     * Instantiates of new boat - Long ship. Sets the dimensions using x and y points.
     */
    public LongShip() {
        ArrayList<Position> offsets = new ArrayList<>();
        offsets.add(new Position(0, 0));
        offsets.add(new Position(1, 0));
        offsets.add(new Position(2, 0));
        offsets.add(new Position(3, 0));
        this.buildShip(offsets);
    }

}
