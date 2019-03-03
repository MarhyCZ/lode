package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

/**
 * Claass Menu Bar Controller with information about game.
 */
public class MenuBarController {
    @FXML
    private Button aboutButton;

    /**
     * The method which returns information about game after click.
     */
    public void aboutClick() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Lodě");
        alert.setHeaderText("JavaFX Ultra Seminárka");
        alert.setContentText("verze 0.001 \nMichal Marhan");

        alert.showAndWait();
    }
}
