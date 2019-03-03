package model.ships;


import model.board.Position;
import model.board.Ship;

import java.util.ArrayList;

/**
 * Class Tower Ship, which characterizes a particular type of ship with special characteristics (size).
 * This class is part of game Duelovka.
 */
public class TowerShip extends Ship {

    /**
     * Instantiates of new boat - Tower ship. Sets the dimensions using x and y points.
     */
    public TowerShip() {
        ArrayList<Position> offsets = new ArrayList<>();
        offsets.add(new Position(0, 0));
        offsets.add(new Position(1, 0));
        offsets.add(new Position(2, 0));
        offsets.add(new Position(3, 0));
        offsets.add(new Position(1, -1));
        offsets.add(new Position(2, -1));
        this.buildShip(offsets);
    }

}