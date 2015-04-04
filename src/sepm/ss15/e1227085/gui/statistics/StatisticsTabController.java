package sepm.ss15.e1227085.gui.statistics;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sepm.ss15.e1227085.domain.Horse;
import sepm.ss15.e1227085.domain.Jockey;
import sepm.ss15.e1227085.gui.Main;
import sepm.ss15.e1227085.service.IHorseService;
import sepm.ss15.e1227085.service.IJockeyService;
import sepm.ss15.e1227085.service.impl.JDBCHorseService;
import sepm.ss15.e1227085.service.impl.JDBCJockeyService;

import java.io.IOException;


/**
 * Created by Patrick Grosslicht <e1227085@student.tuwien.ac.at>.
 */
public class StatisticsTabController {
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
  private IHorseService horseService;
  private IJockeyService jockeyService;
  private ObservableList<Horse> horseList;
  private ObservableList<Jockey> jockeyList;

  /**
   * Automatically called when tab is created, binds the correct data to the columns and adds listeners
   * for handling deletion.
   */
  @FXML
  private void initialize() {
    horseService = new JDBCHorseService();
    horseNameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
    maxSpeedColumn.setCellValueFactory(new PropertyValueFactory<Horse, Double>("maxSpeed"));
    minSpeedColumn.setCellValueFactory(new PropertyValueFactory<Horse, Double>("minSpeed"));
    horseList = FXCollections.observableList(horseService.findAll());
    horseTable.setItems(horseList);

    jockeyService = new JDBCJockeyService();
    jockeyNameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
    talentColumn.setCellValueFactory(new PropertyValueFactory<Jockey, Double>("talent"));
    ageColumn.setCellValueFactory(new PropertyValueFactory<Jockey, Integer>("age"));
    jockeyList = FXCollections.observableList(jockeyService.findAll());
    jockeyTable.setItems(jockeyList);
  }

  /**
   * shows the create dialog, returns true if user clicked ok, false otherwise
   *
   * @param horse  the horse to show statistics for
   * @param jockey the jockey to show statics for
   */
  private void showStatisticsDialog(Horse horse, Jockey jockey) {
    try {
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(Main.class.getResource("statistics/StatisticsDialog.fxml"));
      AnchorPane page = loader.load();

      Stage dialogStage = new Stage();
      dialogStage.setTitle("Statistik");
      dialogStage.initModality(Modality.WINDOW_MODAL);
      Scene scene = new Scene(page);
      dialogStage.setScene(scene);

      StatisticsDialogController controller = loader.getController();
      controller.setDialogStage(dialogStage);
      controller.setStatistics(horse, jockey);

      dialogStage.show();
    } catch (IOException e) {
      LOGGER.error(e);
    }
  }

  /**
   * Called when edit button is pressed, opens edit dialog if able, error dialog otherwise
   */
  @FXML
  private void handleShowStatistics() {
    Horse selectedHorse = horseTable.getSelectionModel().getSelectedItem();
    Jockey selectedJockey = jockeyTable.getSelectionModel().getSelectedItem();
    if (selectedHorse == null && selectedJockey == null) {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setTitle("Fehler");
      alert.setHeaderText(null);
      alert.setContentText("Du musst erst etwas auswählen, bevor du es anzeigen kannst!");
      alert.show();
    } else {
      showStatisticsDialog(selectedHorse, selectedJockey);
    }
  }
}
