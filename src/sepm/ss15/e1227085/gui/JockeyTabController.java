package sepm.ss15.e1227085.gui;

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
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sepm.ss15.e1227085.domain.Horse;
import sepm.ss15.e1227085.service.IHorseService;
import sepm.ss15.e1227085.service.impl.JDBCHorseService;

import java.io.IOException;


/**
 * Created by Patrick Grosslicht <e1227085@student.tuwien.ac.at>.
 */
public class JockeyTabController {
  private static final Logger LOGGER = LogManager.getLogger();
  @FXML
  private TableView<Horse> horseTable;
  @FXML
  private TableColumn<Horse, String> nameColumn;
  @FXML
  private TableColumn<Horse, Double> maxSpeedColumn;
  @FXML
  private TableColumn<Horse, Double> minSpeedColumn;
  @FXML
  private TextField filterField;
  private IHorseService horseService = new JDBCHorseService();
  private ObservableList<Horse> horseList;
  private FilteredList<Horse> filteredHorseList;

  /**
   * Automatically called when tab is created, binds the correct data to the columns and adds listeners
   * for filtering and handling deletion.
   */
  @FXML
  private void initialize() {
    nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
    maxSpeedColumn.setCellValueFactory(new PropertyValueFactory<Horse, Double>("maxSpeed"));
    minSpeedColumn.setCellValueFactory(new PropertyValueFactory<Horse, Double>("minSpeed"));
    horseList = FXCollections.observableList(horseService.findAll());
    filteredHorseList = new FilteredList<Horse>(horseList, horse -> true);

    filterField.textProperty().addListener((observable, oldValue, newValue) -> {
      filteredHorseList.setPredicate(horse -> newValue == null
          || newValue.isEmpty()
          || horse.getName().toLowerCase().contains(newValue.toLowerCase()));
    });

    horseList.addListener((ListChangeListener<Horse>) change -> {
      while (change.next()) {
        if (change.wasRemoved()) {
          change.getRemoved().forEach(horseService::delete);
          LOGGER.debug(change);
        }
      }
    });
    horseTable.setItems(filteredHorseList);
  }

  /**
   * shows the edit dialog, returns true if user clicked ok, false otherwise
   *
   * @param horse            the horse to edit
   * @param newHorseCreation whether we create a new horse or edit an old one
   * @return true if user clicked ok, false otherwise.
   */
  private boolean showHorseEditDialog(Horse horse, boolean newHorseCreation) {
    try {
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(Main.class.getResource("HorseEditDialog.fxml"));
      GridPane page = loader.load();

      Stage dialogStage = new Stage();
      dialogStage.setTitle("Pferd ändern");
      dialogStage.initModality(Modality.WINDOW_MODAL);
      Scene scene = new Scene(page);
      dialogStage.setScene(scene);

      HorseEditDialogController controller = loader.getController();
      controller.setDialogStage(dialogStage);
      controller.setNewHorseCreation(newHorseCreation);
      controller.setHorse(horse);

      dialogStage.showAndWait();
      return controller.isOkClicked();
    } catch (IOException e) {
      LOGGER.error(e);
      return false;
    }
  }

  /**
   * Called when delete button is pressed, tries to delete element, opens error dialog if unable to
   */
  @FXML
  private void handleDeleteHorse() {
    int selectedIndex = horseTable.getSelectionModel().getSelectedIndex();
    if (selectedIndex >= 0) {
      selectedIndex = filteredHorseList.getSourceIndex(selectedIndex);
      horseList.remove(selectedIndex);
    } else {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setTitle("Fehler");
      alert.setHeaderText(null);
      alert.setContentText("Du musst erst etwas auswählen, bevor du es löschen kannst!");
      alert.showAndWait();
    }
  }

  /**
   * Called when edit button is pressed, opens edit dialog if able, error dialog otherwise
   */
  @FXML
  private void handleEditHorse() {
    Horse selectedHorse = horseTable.getSelectionModel().getSelectedItem();
    if (selectedHorse != null) {
      this.showHorseEditDialog(selectedHorse, false);
    } else {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setTitle("Fehler");
      alert.setHeaderText(null);
      alert.setContentText("Du musst erst etwas auswählen, bevor du es ändern kannst!");
      alert.showAndWait();
    }
  }

  /**
   * Called when new button is pressed, opens edit dialog and adds element to list if creation was successful
   */
  @FXML
  private void handleNewHorse() {
    Horse tmpHorse = new Horse();
    if (this.showHorseEditDialog(tmpHorse, true)) {
      horseList.add(tmpHorse);
    }
  }
}
