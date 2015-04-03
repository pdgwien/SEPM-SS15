package sepm.ss15.e1227085.gui.jockey;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sepm.ss15.e1227085.domain.Jockey;
import sepm.ss15.e1227085.service.IJockeyService;
import sepm.ss15.e1227085.service.Validator;
import sepm.ss15.e1227085.service.impl.JDBCJockeyService;

/**
 * Created by Patrick Grosslicht <e1227085@student.tuwien.ac.at>.
 */
public class JockeyEditDialogController {
  private static final Logger LOGGER = LogManager.getLogger();
  @FXML
  private TextField nameField;
  @FXML
  private TextField ageField;
  @FXML
  private TextField talentField;
  private IJockeyService jockeyService;
  private Jockey jockey;
  private Stage dialogStage;
  private boolean newJockeyCreation;
  private boolean okClicked;
  private Validator validator = new Validator();

  /**
   * Initializes the controller class. This method is automatically called
   * after the fxml file has been loaded.
   */
  @FXML
  private void initialize() {
    jockeyService = new JDBCJockeyService();
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
   * Sets the jockey to be edited in the dialog.
   *
   * @param jockey
   */
  public void setJockey(Jockey jockey) {
    this.jockey = jockey;

    nameField.setText(jockey.getName());
    talentField.setText(String.valueOf(jockey.getTalent()));
    ageField.setText(String.valueOf(jockey.getAge()));
  }

  /**
   * Sets whether we create a new jockey or just edit.
   *
   * @param bool the bool
   */
  public void setNewJockeyCreation(boolean bool) {
    this.newJockeyCreation = bool;
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
      jockey.setName(nameField.getText());
      jockey.setTalent(Double.parseDouble(talentField.getText()));
      jockey.setAge(Integer.parseInt(ageField.getText()));
      if (newJockeyCreation) {
        jockeyService.create(jockey);
      } else {
        jockeyService.update(jockey);
      }
      okClicked = true;
      dialogStage.close();

      Alert alert = new Alert(Alert.AlertType.INFORMATION);
      alert.setTitle("Erfolg");
      alert.setHeaderText(null);
      alert.setContentText("Daten wurden erfolgreich gespeichert!");

      alert.show();
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
    if (!validator.isValidTalent(talentField.getText())) {
      errorMessage += "Talent fehlt oder ist falsch!\n";
    }
    if (!validator.isValidAge(ageField.getText())) {
      errorMessage += "Alter fehlt oder ist falsch!\n";
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
