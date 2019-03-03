package model.board;

import model.util.Colors;

import java.util.ArrayList;

/**
 * Class Ship which is used for the characterization of the ship and all possible situations that connect with it.
 * This class is part of game Duelovka.
 */
public abstract class Ship {

    private ArrayList<Cell> cells;
    private ArrayList<Position> offsets;
    private int health;


    /**
     * Returns boolean if the boat isn't hit.
     *
     * @return the boolean
     */
    public boolean isAlive() {
        return this.health > 0;
    }

    /**
     * Returns boolean if the boat is hit.
     */
    public void hit() {
        this.health--;
    }

    /**
     * The method which returns cells in array list.
     *
     * @return the cells
     */
    public ArrayList<Cell> getCells() {
        return cells;
    }

    /**
     * The method which registers cell.
     *
     * @param cell the cell
     */
    public void registerCell(Cell cell) {
        if (this.cells == null) this.cells = new ArrayList<>();
        this.cells.add(cell);
    }

    /**
     * The method which builds ship.
     *
     * @param offsets the offsets
     */
    public void buildShip(ArrayList<Position> offsets) {

        this.offsets = offsets;
        this.health = offsets.size();
    }

    /**
     * The method which destroys ship and gives them color.
     */
    public void destroyShip() {

        // ArrayList<Cell> cells = ship.getCells();

        for (Cell cell : cells) {
            cell.setColors(Colors.DESTROYED.getColor(), Colors.DESTROYED.getColor());
        }
    }

    /**
     * The method which return offsets.
     *
     * @return the offsets
     */
    public ArrayList<Position> getOffsets() {
        return offsets;
    }

    /**
     * The method which returns that boat isn't hit.
     *
     * @return the health
     */
    public int getHealth() {
        return health;
    }
}
