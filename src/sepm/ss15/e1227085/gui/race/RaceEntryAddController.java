package sepm.ss15.e1227085.gui.race;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sepm.ss15.e1227085.domain.Horse;
import sepm.ss15.e1227085.domain.Jockey;
import sepm.ss15.e1227085.domain.RaceEntry;
import sepm.ss15.e1227085.service.IHorseService;
import sepm.ss15.e1227085.service.IJockeyService;
import sepm.ss15.e1227085.service.impl.JDBCHorseService;
import sepm.ss15.e1227085.service.impl.JDBCJockeyService;

/**
 * Created by Patrick Grosslicht <e1227085@student.tuwien.ac.at>.
 */
public class RaceEntryAddController {
  private static final Logger LOGGER = LogManager.getLogger();
  @FXML
  private TableView<Horse> horseTable;
  @FXML
  private TableColumn<Horse, String> horseNameColumn;
  @FXML
  private TableColumn<Horse, Double> maxSpeedColumn;
  @FXML
  private TableColumn<Horse, Double> minSpeedColumn;
  @FXML
  private TableView<Jockey> jockeyTable;
  @FXML
  private TableColumn<Jockey, String> jockeyNameColumn;
  @FXML
  private TableColumn<Jockey, Double> talentColumn;
  @FXML
  private TableColumn<Jockey, Integer> ageColumn;
  private Stage dialogStage;
  private RaceEntry tmpRace;

  /**
   * Initializes the controller class. This method is automatically called
   * after the fxml file has been loaded.
   */
  @FXML
  private void initialize() {
    IHorseService horseService = new JDBCHorseService();
    horseNameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
    maxSpeedColumn.setCellValueFactory(new PropertyValueFactory<>("maxSpeed"));
    minSpeedColumn.setCellValueFactory(new PropertyValueFactory<>("minSpeed"));
    ObservableList<Horse> horseList = FXCollections.observableList(horseService.findAll());
    horseTable.setItems(horseList);

    IJockeyService jockeyService = new JDBCJockeyService();
    jockeyNameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
    talentColumn.setCellValueFactory(new PropertyValueFactory<>("talent"));
    ageColumn.setCellValueFactory(new PropertyValueFactory<>("age"));
    ObservableList<Jockey> jockeyList = FXCollections.observableList(jockeyService.findAll());
    jockeyTable.setItems(jockeyList);
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
   * Returns the temporary race that contains the selection
   *
   * @return
   */
  public RaceEntry getRaceEntry() {
    return tmpRace;
  }

  /**
   * Called when the user clicks ok.
   */
  @FXML
  private void handleOk() {
    Horse tmpHorse = horseTable.getSelectionModel().getSelectedItem();
    Jockey tmpJockey = jockeyTable.getSelectionModel().getSelectedItem();
    if ((tmpHorse == null) || (tmpJockey == null)) {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setTitle("Fehler");
      alert.setHeaderText(null);
      alert.setContentText("Bitte Pferd und Jockey auswählen!");

      alert.show();
    } else {
      tmpRace = new RaceEntry(tmpHorse, tmpJockey);
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
}
