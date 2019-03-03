package model.util;

import javafx.scene.paint.Color;

/**
 * Colors that can be used for specific situation in game Duelovka.
 */
public enum Colors {
    /**
     * Water colors.
     */
    WATER(36, 166, 254),
    /**
     * Wateroutline colors.
     */
    WATEROUTLINE(5, 12, 221),
    /**
     * Colors for missing hit.
     */
    MISS(40, 40, 40),
    /**
     * Hit colors.
     */
    HIT(204, 110, 50),
    /**
     * Ship colors.
     */
    SHIP(178, 194, 207),
    /**
     * Destroyed colors for ships.
     */
    DESTROYED(185, 38, 54);

    private int r;
    private int g;
    private int b;

    /*
    Creates an opaque sRGB color with the specified red, green, and blue values in the range (0 - 255).
     */
    Colors(int r, int g, int b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }

    /**
     * The method which returns color.
     *
     * @return the color
     */
    public Color getColor() {
        return Color.rgb(r, g, b);
    }
}
