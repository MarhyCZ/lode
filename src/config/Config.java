package config;

/**
 * Class Config which sets board before game can starts.
 * This class is part of game Duelovka.
 */
public class Config {
    private static final int BOARD_Y_SIZE = 18;
    private static final int BOARD_X_SIZE = 22;

    private static final int STAGE_WIDTH = 1000;
    private static final int STAGE_HEIGHT = 562;

    private static String APPDATA = System.getProperty("user.dir") + "/";

    /**
     * The method which returns x size of board.
     *
     * @return the x size
     */
    public static int getXSize() {
        return BOARD_X_SIZE;
    }

    /**
     * The method which returns y size of board.
     *
     * @return the y size
     */
    public static int getYSize() {
        return BOARD_Y_SIZE;
    }

    /**
     * The method which returns stage width.
     *
     * @return the stage width
     */
    public static int getStageWidth() {
        return STAGE_WIDTH;
    }

    /**
     * The method which returns stage height.
     *
     * @return the stage height
     */
    public static int getStageHeight() {
        return STAGE_HEIGHT;
    }

    /**
     *
     * The method which returns game folder.
     *
     * @return the game folder
     */
    public static String getGameFolder() {
        return APPDATA;
    }


}
