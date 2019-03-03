package model.board;

import controller.AudioPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import model.util.Colors;

/**
 * Class Cell which characterizes the box (cell) in the game plan.
 * This class is part of game Duelovka.
 */
public class Cell extends Rectangle {
    private final Position position;

    private final Board board;
    private Ship ship = null;

    private boolean wasShot;

    /**
     * Instantiates for new cell.
     *
     * @param x     the x
     * @param y     the y
     * @param board the board
     */
    Cell(int x, int y, Board board) {
        super(20, 20);

        this.position = new Position(x, y);
        this.board = board;

        setColors(Colors.WATER.getColor(), Colors.WATEROUTLINE.getColor());
    }

    /**
     * The method which set normal state for cell.
     */
    public void formatCell() {
        ship = null;
        setColors(Colors.WATER.getColor(), Colors.WATEROUTLINE.getColor());
    }

    /**
     * The method which returns ship.
     *
     * @return the ship
     */
    public Ship getShip() {
        return ship;
    }

    /**
     * The method which sets ship.
     *
     * @param ship the ship
     */
    public void setShip(Ship ship) {
        setColors(Colors.SHIP.getColor(), Colors.SHIP.getColor());
        this.ship = ship;
    }


    /**
     * The method which sets colors.
     *
     * @param fillColor   the fill color
     * @param strokeColor the stroke color
     */
    public void setColors(Color fillColor, Color strokeColor) {
        this.setFill(fillColor);
        this.setStroke(strokeColor);
    }

    /**
     * The method which returns position.
     *
     * @return the position
     */
    public Position getPosition() {
        return position;
    }

    /**
     * During shooters ("přestřelka") retuns situations, that happen and return color for them.
     *
     * @return the boolean
     */
    public boolean shootCell() {

        if (wasShot == true) return false;
        wasShot = true;

        if (ship != null) {
            AudioPlayer.hit();
            ship.hit();
            setColors(Colors.HIT.getColor(), Colors.HIT.getColor());

            if (!ship.isAlive()) {
                // Ship ship = this.getShip();
                ship.destroyShip();
            }
            return true;
        } else
            AudioPlayer.miss();
        setColors(Colors.MISS.getColor(), Colors.MISS.getColor());
        return false;
    }


    /**
     * Returns value if ship wasn't shot.
     *
     * @return the boolean
     */
    public boolean isEmpty() {
        return this.ship == null;
    }

    /**
     * Returns value if ship was shot.
     *
     * @return the boolean
     */
    public boolean wasShot() {
        return this.wasShot;
    }

    @Override
    public String toString() {
        return "x = " + this.position.getX() + ", y = " + this.position.getY();
    }
}
