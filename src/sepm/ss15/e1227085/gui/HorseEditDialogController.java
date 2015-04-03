package sepm.ss15.e1227085.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sepm.ss15.e1227085.domain.Horse;
import sepm.ss15.e1227085.service.IHorseService;
import sepm.ss15.e1227085.service.Validator;
import sepm.ss15.e1227085.service.impl.JDBCHorseService;

import java.io.File;
import java.util.Arrays;

/**
 * Created by Patrick Grosslicht <e1227085@student.tuwien.ac.at>.
 */
public class HorseEditDialogController {
  private static final Logger LOGGER = LogManager.getLogger();
  @FXML
  private TextField nameField;
  @FXML
  private TextField minSpeedField;
  @FXML
  private TextField maxSpeedField;
  @FXML
  private ImageView imageView;
  private IHorseService horseService;
  private Horse horse;
  private Stage dialogStage;
  private boolean newHorseCreation;
  private boolean okClicked;
  private Image image;
  private String imagePath;
  private Validator validator = new Validator();

  /**
   * Initializes the controller class. This method is automatically called
   * after the fxml file has been loaded.
   */
  @FXML
  private void initialize() {
    horseService = new JDBCHorseService();
  }

  /**
   * Sets the stage of this dialog.
   *
   * @param dialogStage
   */
  public void setDialogStage(Stage dialogStage) {
    this.dialogStage = dialogStage;
  }

  /**
   * Sets the horse to be edited in the dialog.
   *
   * @param horse
   */
  public void setHorse(Horse horse) {
    this.horse = horse;

    nameField.setText(horse.getName());
    minSpeedField.setText(String.valueOf(horse.getMinSpeed()));
    maxSpeedField.setText(String.valueOf(horse.getMaxSpeed()));
    this.imagePath = horse.getImagePath();
    if (!newHorseCreation) {
      try {
        image = new Image(horse.getImagePath());
        imageView.setImage(image);
      } catch (IllegalArgumentException e) {
        LOGGER.error(e);
      }
    }
  }

  /**
   * Shows filechooser when button is pressed
   */
  @FXML
  private void showFileChooser() {
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Bild auswählen");
    fileChooser.getExtensionFilters().add(
        new FileChooser.ExtensionFilter("Bilder", Arrays.asList("*.png", "*.gif", "*.jpg", "*.jpeg", "*.bmp"))
    );
    File file = fileChooser.showOpenDialog(dialogStage);
    if (file != null) {
      imagePath = file.toURI().toString();
      image = new Image(file.toURI().toString());
      imageView.setImage(image);
    }
  }

  /**
   * Sets whether we create a new horse or just edit.
   *
   * @param bool the bool
   */
  public void setNewHorseCreation(boolean bool) {
    this.newHorseCreation = bool;
  }

  /**
   * Returns true if the user clicked OK, false otherwise.
   *
   * @return
   */
  public boolean isOkClicked() {
    return okClicked;
  }

  /**
   * Called when the user clicks ok.
   */
  @FXML
  private void handleOk() {
    if (isInputValid()) {
      horse.setName(nameField.getText());
      horse.setMinSpeed(Double.parseDouble(minSpeedField.getText()));
      horse.setMaxSpeed(Double.parseDouble(maxSpeedField.getText()));
      horse.setImagePath(imagePath);
      if (newHorseCreation) {
        horseService.create(horse);
      } else {
        horseService.update(horse);
      }
      okClicked = true;
      dialogStage.close();
    }
  }

  /**
   * Called when the user clicks cancel.
   */
  @FXML
  private void handleCancel() {
    dialogStage.close();
  }

  /**
   * Validates the user input in the text fields.
   *
   * @return true if the input is valid
   */
  private boolean isInputValid() {
    String errorMessage = "";

    if (!validator.isValidName(nameField.getText())) {
      errorMessage += "Name fehlt oder ist falsch!\n";
    }
    if (!validator.isValidSpeed(minSpeedField.getText())) {
      errorMessage += "Minimalgeschwindigkeit fehlt oder ist falsch!\n";
    }
    if (!validator.isValidSpeed(maxSpeedField.getText())) {
      errorMessage += "Maximalgeschwindigkeit fehlt oder ist falsch!\n";
    }

    if (errorMessage.length() == 0) {
      return true;
    } else {
      // Show the error message.
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.initOwner(dialogStage);
      alert.setTitle("Fehler in der Eingabe");
      alert.setHeaderText("Bitte falsche Felder ausbessern.");
      alert.setContentText(errorMessage);

      alert.showAndWait();

      return false;
    }
  }
}
