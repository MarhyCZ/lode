package controller;

import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;

import java.net.URISyntaxException;

/**
 * Class Audio Player is used for better game experience, because adds sounds into game.
 * This class is part of game Duelovka.
 */
public class AudioPlayer {
    private static AudioClip BUTTONCLICK = getAudioClip("../resources/audio/Buttonclick.aiff");
    private static AudioClip DEFEAT = getAudioClip("../resources/audio/Defeat.aiff");
    private static AudioClip HIT = getAudioClip("../resources/audio/Hit.aiff");
    private static AudioClip MAINTHEME = getAudioClip("../resources/audio/Maintheme.aiff");
    private static AudioClip VICTORY = getAudioClip("../resources/audio/Victory.aiff");
    private static AudioClip WATERSHOT = getAudioClip("../resources/audio/Watershot.aiff");
    private static AudioClip SHIPCRAFT = getAudioClip("../resources/audio/Shipcraft.aiff");

    /**
     * Create an AudioClip loaded from the supplied source URL.
     * @param path
     */
    private static AudioClip getAudioClip(String path) {
        AudioClip clip = null;
        try {
            clip = new AudioClip(AudioPlayer.class.getResource(path).toURI().toString());
        } catch (URISyntaxException ignored) {

        }
        return clip;
    }

    /**
     * Constructs a Media instance.
     * @param path
     */
    private static Media getMedia(String path) {
        Media media = null;
        try {
            media = new Media(AudioPlayer.class.getResource(path).toURI().toString());
        } catch (URISyntaxException ignored) {

        }
        return media;
    }

    /**
     * The method which sets up button click sound.
     */
    public static void buttonClick() {
        BUTTONCLICK.play();
    }

    /**
     * The method which sets up main theme.
     */
    public static void mainTheme() {
        MAINTHEME.setVolume(0.5);
        MAINTHEME.play();
    }

    /**
     * The method which stops main theme.
     */
    public static void mainThemeStop() {
        MAINTHEME.stop();
    }

    /**
     * The method for sound of hit.
     */
    public static void hit() {
        HIT.play();
    }

    /**
     * The method for sound of missing cell.
     */
    public static void miss() {
        WATERSHOT.play();
    }

    /**
     * The method for sound of victory.
     */
    public static void victory() {
        VICTORY.play();
    }

    /**
     * The method for sound of defeat.
     */
    public static void defeat() {
        DEFEAT.play();
    }

    /**
     * The method for stopping defeat sound.
     */
    public static void defeatStop() {
        DEFEAT.stop();
    }

    /**
     * The method for sound of ship craft.
     */
    public static void shipCraft() {
        SHIPCRAFT.play();
    }

}
