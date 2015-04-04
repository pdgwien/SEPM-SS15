package sepm.ss15.e1227085.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class Main extends Application {

  private static final Logger LOGGER = LogManager.getLogger();
  private Stage primaryStage;

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) {
    this.primaryStage = primaryStage;
    this.primaryStage.setTitle("Pferderennenmanager");

    initRootLayout();
  }

  /**
   * Initializes the root layout.
   */
  public void initRootLayout() {
    try {
      // Load root layout from fxml file.
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(Main.class.getResource("RootLayout.fxml"));
      TabPane rootLayout = loader.load();

      // Show the scene containing the root layout.
      Scene scene = new Scene(rootLayout);
      primaryStage.setScene(scene);
      primaryStage.show();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Returns the main stage.
   *
   * @return
   */
  public Stage getPrimaryStage() {
    return primaryStage;
  }

}
