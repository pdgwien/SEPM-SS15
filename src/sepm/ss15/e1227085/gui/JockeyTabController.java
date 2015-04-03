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
import sepm.ss15.e1227085.domain.Jockey;
import sepm.ss15.e1227085.service.IJockeyService;
import sepm.ss15.e1227085.service.impl.JDBCJockeyService;

import java.io.IOException;


/**
 * Created by Patrick Grosslicht <e1227085@student.tuwien.ac.at>.
 */
public class JockeyTabController {
  private static final Logger LOGGER = LogManager.getLogger();
  @FXML
  private TableView<Jockey> jockeyTable;
  @FXML
  private TableColumn<Jockey, String> nameColumn;
  @FXML
  private TableColumn<Jockey, Double> talentColumn;
  @FXML
  private TableColumn<Jockey, Integer> ageColumn;
  @FXML
  private TextField filterField;
  private IJockeyService jockeyService = new JDBCJockeyService();
  private ObservableList<Jockey> jockeyList;
  private FilteredList<Jockey> filteredJockeyList;

  /**
   * Automatically called when tab is created, binds the correct data to the columns and adds listeners
   * for filtering and handling deletion.
   */
  @FXML
  private void initialize() {
    nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
    talentColumn.setCellValueFactory(new PropertyValueFactory<Jockey, Double>("talent"));
    ageColumn.setCellValueFactory(new PropertyValueFactory<Jockey, Integer>("age"));
    jockeyList = FXCollections.observableList(jockeyService.findAll());
    filteredJockeyList = new FilteredList<Jockey>(jockeyList, jockey -> true);

    filterField.textProperty().addListener((observable, oldValue, newValue) -> {
      filteredJockeyList.setPredicate(jockey -> newValue == null
          || newValue.isEmpty()
          || jockey.getName().toLowerCase().contains(newValue.toLowerCase()));
    });

    jockeyList.addListener((ListChangeListener<Jockey>) change -> {
      while (change.next()) {
        if (change.wasRemoved()) {
          change.getRemoved().forEach(jockeyService::delete);
          LOGGER.debug(change);
        }
      }
    });
    jockeyTable.setItems(filteredJockeyList);
  }

  /**
   * shows the edit dialog, returns true if user clicked ok, false otherwise
   *
   * @param jockey            the jockey to edit
   * @param newJockeyCreation whether we create a new jockey or edit an old one
   * @return true if user clicked ok, false otherwise.
   */
  private boolean showJockeyEditDialog(Jockey jockey, boolean newJockeyCreation) {
    try {
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(Main.class.getResource("JockeyEditDialog.fxml"));
      GridPane page = loader.load();

      Stage dialogStage = new Stage();
      dialogStage.setTitle("Jockey ändern");
      dialogStage.initModality(Modality.WINDOW_MODAL);
      Scene scene = new Scene(page);
      dialogStage.setScene(scene);

      JockeyEditDialogController controller = loader.getController();
      controller.setDialogStage(dialogStage);
      controller.setNewJockeyCreation(newJockeyCreation);
      controller.setJockey(jockey);

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
  private void handleDeleteJockey() {
    int selectedIndex = jockeyTable.getSelectionModel().getSelectedIndex();
    if (selectedIndex >= 0) {
      selectedIndex = filteredJockeyList.getSourceIndex(selectedIndex);
      jockeyList.remove(selectedIndex);
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
  private void handleEditJockey() {
    Jockey selectedJockey = jockeyTable.getSelectionModel().getSelectedItem();
    if (selectedJockey != null) {
      this.showJockeyEditDialog(selectedJockey, false);
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
  private void handleNewJockey() {
    Jockey tmpJockey = new Jockey();
    if (this.showJockeyEditDialog(tmpJockey, true)) {
      jockeyList.add(tmpJockey);
    }
  }
}
