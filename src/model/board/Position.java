package model.board;

/**
 * Class Position is used for positions of boats.
 * This class is part of game Duelovka.
 */
public class Position {
    private final int X;
    private final int Y;

    /**
     * Instantiates for new positions.
     *
     * @param x the x
     * @param y the y
     */
    public Position(int x, int y) {
        this.X = x;
        this.Y = y;
    }

    /**
     * The method which returns x.
     *
     * @return the x
     */
    int getX() {
        return X;
    }

    /**
     * The method which returns x.
     *
     * @return the y
     */
    int getY() {
        return Y;
    }
}
