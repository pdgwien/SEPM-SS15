package sepm.ss15.e1227085.gui.race;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sepm.ss15.e1227085.domain.Race;
import sepm.ss15.e1227085.domain.RaceEntry;
import sepm.ss15.e1227085.gui.Main;
import sepm.ss15.e1227085.service.IRaceService;
import sepm.ss15.e1227085.service.impl.JDBCRaceService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


/**
 * Created by Patrick Grosslicht <e1227085@student.tuwien.ac.at>.
 */
public class RaceTabController {
  private static final Logger LOGGER = LogManager.getLogger();
  private final IRaceService raceService = new JDBCRaceService();
  @FXML
  private TableView<Race> raceTable;
  @FXML
  private TableColumn<Race, UUID> nameColumn;
  @FXML
  private TableColumn<Race, String> horsesColumn;
  @FXML
  private TableColumn<Race, String> jockeysColumn;
  @FXML
  private TextField idSearchField;
  @FXML
  private TextField jockeySearchField;
  @FXML
  private TextField horseSearchField;
  private ObservableList<Race> raceList;
  private FilteredList<Race> filteredRaceList;

  /**
   * Automatically called when tab is created, binds the correct data to the columns and adds listeners
   * for handling deletion.
   */
  @FXML
  private void initialize() {
    nameColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
    horsesColumn.setCellValueFactory(new PropertyValueFactory<>("horsesNames"));
    jockeysColumn.setCellValueFactory(new PropertyValueFactory<>("jockeysNames"));
    raceList = FXCollections.observableList(raceService.findAll());

    filteredRaceList = new FilteredList<>(raceList, race -> true);

    idSearchField.textProperty().addListener((observable, oldValue, newValue) -> {
      filteredRaceList.setPredicate(race -> race.getId().toString().toLowerCase().contains(newValue.toLowerCase())
          && race.getHorsesNames().toLowerCase().contains(horseSearchField.getText().toLowerCase())
          && race.getJockeysNames().toLowerCase().contains(jockeySearchField.getText().toLowerCase()));
    });

    horseSearchField.textProperty().addListener((observable, oldValue, newValue) -> {
      filteredRaceList.setPredicate(race -> race.getId().toString().toLowerCase().contains(idSearchField.getText().toLowerCase())
          && race.getHorsesNames().toLowerCase().contains(newValue.toLowerCase())
          && race.getJockeysNames().toLowerCase().contains(jockeySearchField.getText().toLowerCase()));
    });

    jockeySearchField.textProperty().addListener((observable, oldValue, newValue) -> {
      filteredRaceList.setPredicate(race -> race.getId().toString().toLowerCase().contains(idSearchField.getText().toLowerCase())
          && race.getHorsesNames().toLowerCase().contains(horseSearchField.getText().toLowerCase())
          && race.getJockeysNames().toLowerCase().contains(newValue.toLowerCase()));
    });

    raceList.addListener((ListChangeListener<Race>) change -> {
      while (change.next()) {
        if (change.wasRemoved()) {
          change.getRemoved().forEach(raceService::delete);
          LOGGER.debug(change);
        }
      }
    });
    raceTable.setItems(filteredRaceList);
  }

  /**
   * shows the create dialog, returns true if user clicked ok, false otherwise
   *
   * @param race            the race to show
   * @param newRaceCreation whether a new race is being created
   * @return true if user clicked ok, false otherwise.
   */
  private boolean showRaceNewDialog(Race race, boolean newRaceCreation) {
    try {
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(Main.class.getResource("race/RaceDialog.fxml"));
      AnchorPane page = loader.load();

      Stage dialogStage = new Stage();
      dialogStage.setTitle("Rennen erstellen");
      dialogStage.initModality(Modality.WINDOW_MODAL);
      Scene scene = new Scene(page);
      dialogStage.setScene(scene);

      RaceDialogController controller = loader.getController();
      controller.setDialogStage(dialogStage);
      controller.setNewRaceCreation(newRaceCreation);
      controller.setRace(race);

      dialogStage.showAndWait();
      return controller.isOkClicked();
    } catch (IOException e) {
      LOGGER.error(e);
      return false;
    }
  }

  /**
   * Called when edit button is pressed, opens edit dialog if able, error dialog otherwise
   */
  @FXML
  private void handleShowRace() {
    Race selectedRace = raceTable.getSelectionModel().getSelectedItem();
    if (selectedRace != null) {
      this.showRaceNewDialog(selectedRace, false);
    } else {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setTitle("Fehler");
      alert.setHeaderText(null);
      alert.setContentText("Du musst erst etwas auswählen, bevor du es anzeigen kannst!");
      alert.show();
    }
  }

  /**
   * Called when new button is pressed, opens edit dialog and adds element to list if creation was successful
   */
  @FXML
  private void handleNewRace() {
    List<RaceEntry> entries = new ArrayList<>();
    Race tmpRace = new Race(entries);
    if (this.showRaceNewDialog(tmpRace, true)) {
      raceList.add(tmpRace);
    }
  }
}
