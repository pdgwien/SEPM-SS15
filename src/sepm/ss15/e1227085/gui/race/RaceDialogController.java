package sepm.ss15.e1227085.gui.race;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sepm.ss15.e1227085.domain.Race;
import sepm.ss15.e1227085.domain.RaceEntry;
import sepm.ss15.e1227085.gui.Main;
import sepm.ss15.e1227085.service.IRaceService;
import sepm.ss15.e1227085.service.Validator;
import sepm.ss15.e1227085.service.impl.JDBCRaceService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

/**
 * Created by Patrick Grosslicht <e1227085@student.tuwien.ac.at>.
 */
public class RaceDialogController {
  private static final Logger LOGGER = LogManager.getLogger();
  @FXML
  private TextField idField;
  @FXML
  private Button addButton;
  @FXML
  private Button deleteButton;
  @FXML
  private Button saveButton;
  @FXML
  private TableView<RaceEntry> raceEntriesTable;
  @FXML
  private TableColumn<RaceEntry, String> jockeyColumn;
  @FXML
  private TableColumn<RaceEntry, String> talentColumn;
  @FXML
  private TableColumn<RaceEntry, String> horseColumn;
  @FXML
  private TableColumn<RaceEntry, String> luckyNumberColumn;
  @FXML
  private TableColumn<RaceEntry, String> speedColumn;
  @FXML
  private TextField winnerField;
  private IRaceService raceService;
  private Race race;
  private List<RaceEntry> raceEntries;
  private Stage dialogStage;
  private boolean newRaceCreation;
  private boolean okClicked;
  private Validator validator = new Validator();
  private ObservableList<RaceEntry> raceEntriesList;
  private SortedList<RaceEntry> sortedRaceEntriesList;

  /**
   * Initializes the controller class. This method is automatically called
   * after the fxml file has been loaded.
   */
  @FXML
  private void initialize() {
    raceService = new JDBCRaceService();
    horseColumn.setCellValueFactory(cellData -> cellData.getValue().getHorse().nameProperty());
    jockeyColumn.setCellValueFactory(cellData -> cellData.getValue().getJockey().nameProperty());
    talentColumn.setCellValueFactory(new PropertyValueFactory<RaceEntry, String>("talent"));
    luckyNumberColumn.setCellValueFactory(new PropertyValueFactory<RaceEntry, String>("luckyNumber"));
    speedColumn.setCellValueFactory(new PropertyValueFactory<RaceEntry, String>("speed"));
    speedColumn.setSortType(TableColumn.SortType.DESCENDING);
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
   * Sets the race to be edited in the dialog.
   *
   * @param race
   */
  public void setRace(Race race) {
    this.race = race;
    this.raceEntries = race.getEntries();
    raceEntriesList = FXCollections.observableList(this.raceEntries);
    sortedRaceEntriesList = new SortedList<>(raceEntriesList);
    sortedRaceEntriesList.comparatorProperty().bind(raceEntriesTable.comparatorProperty());
    raceEntriesTable.setItems(sortedRaceEntriesList);
    raceEntriesTable.getSortOrder().add(speedColumn);
    raceEntriesList.addListener((ListChangeListener<RaceEntry>) change -> {
      if (!sortedRaceEntriesList.isEmpty()) {
        winnerField.setText(sortedRaceEntriesList.get(0).getJockey().getName() + " mit " + sortedRaceEntriesList.get(0).getHorse().getName());
      } else {
        winnerField.setText("");
      }
    });
    if (!sortedRaceEntriesList.isEmpty())
      winnerField.setText(sortedRaceEntriesList.get(0).getJockey().getName() + " mit " + sortedRaceEntriesList.get(0).getHorse().getName());
    idField.setText(race.getId().toString());
  }

  /**
   * Sets whether we create a new race or just edit.
   *
   * @param bool the bool
   */
  public void setNewRaceCreation(boolean bool) {
    this.newRaceCreation = bool;
    saveButton.setDisable(!bool);
    deleteButton.setDisable(!bool);
    addButton.setDisable(!bool);
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
    if (isInputValid() && newRaceCreation) {
      for (RaceEntry raceEntry : raceEntries) {
        raceEntry.setRank(sortedRaceEntriesList.indexOf(raceEntry) + 1);
      }
      race.setEntries(raceEntries);
      raceService.create(race);
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

  @FXML
  private void handleAddRaceEntry() {
    try {
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(Main.class.getResource("race/RaceEntryAdd.fxml"));
      BorderPane page = loader.load();
      Stage addDialogStage = new Stage();
      addDialogStage.setTitle("Neuer Teilnehmer");
      addDialogStage.initModality(Modality.WINDOW_MODAL);
      Scene scene = new Scene(page);
      addDialogStage.setScene(scene);

      RaceEntryAddController controller = loader.getController();
      controller.setDialogStage(addDialogStage);

      addDialogStage.showAndWait();
      RaceEntry tmpRace = controller.getRaceEntry();
      if (tmpRace != null) {
        raceEntriesList.add(tmpRace);
      }
    } catch (IOException e) {
      LOGGER.error(e);
    }
  }

  /**
   * Called when delete button is pressed, tries to delete element, opens error dialog if unable to
   */
  @FXML
  private void handleDeleteRaceEntry() {
    int selectedIndex = raceEntriesTable.getSelectionModel().getSelectedIndex();
    if (selectedIndex >= 0) {
      Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
      alert.setTitle("Sicher?");
      alert.setHeaderText(null);
      alert.setContentText("Willst du das wirklich löschen?");
      Optional<ButtonType> result = alert.showAndWait();
      if (result.isPresent() && result.get() == ButtonType.OK) {
        raceEntriesList.remove(selectedIndex);
      }
    } else {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setTitle("Fehler");
      alert.setHeaderText(null);
      alert.setContentText("Du musst erst etwas auswählen, bevor du es löschen kannst!");
      alert.showAndWait();
    }
  }

  /**
   * Validates the user input in the text fields.
   *
   * @return true if the input is valid
   */
  private boolean isInputValid() {
    String errorMessage = "";

    if (!validator.areValidRaceEntries(raceEntries)) {
      errorMessage += "Renneinträge fehlerhaft!";
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
