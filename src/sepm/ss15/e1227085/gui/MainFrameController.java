package sepm.ss15.e1227085.gui;

/**
 * Created by patrickgrosslicht on 11.03.15.
 */

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.net.URL;
import java.util.ResourceBundle;

public class MainFrameController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML
    void onHelpClick(ActionEvent event) {
    }

    @FXML
        // This method is called by the FXMLLoader when initialization is complete
    void initialize() {

    }
}
