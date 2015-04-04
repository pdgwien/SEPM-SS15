package sepm.ss15.e1227085.gui.statistics;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sepm.ss15.e1227085.domain.Horse;
import sepm.ss15.e1227085.domain.Jockey;
import sepm.ss15.e1227085.service.IRaceService;
import sepm.ss15.e1227085.service.impl.JDBCRaceService;

import java.util.Map;

/**
 * Created by Patrick Grosslicht <e1227085@student.tuwien.ac.at>.
 */
public class StatisticsDialogController {
  private static final Logger LOGGER = LogManager.getLogger();
  @FXML
  private TextArea statisticsField;
  private IRaceService raceService;
  private Stage dialogStage;

  /**
   * Initializes the controller class. This method is automatically called
   * after the fxml file has been loaded.
   */
  @FXML
  private void initialize() {
    raceService = new JDBCRaceService();
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
   * Shows stats for horse, jockey, or horse and jockey
   *
   * @param horse horse to show stats for, can be null
   * @param jockey jockey to show stats for, can be null
   */
  public void setStatistics(Horse horse, Jockey jockey) {
    Map<Integer, Integer> ranks = raceService.getRankingsForHorseAndJockey(horse, jockey);
    String stats = "";
    for (Map.Entry<Integer, Integer> entry : ranks.entrySet()) {
      stats += "Rang: " + entry.getKey() + ", Anzahl: " + entry.getValue() + "\n";
    }
    statisticsField.setText(stats);
  }


  /**
   * Called when the user clicks cancel.
   */
  @FXML
  private void handleCancel() {
    dialogStage.close();
  }

}
