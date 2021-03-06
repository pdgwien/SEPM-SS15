package sepm.ss15.e1227085.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Patrick Grosslicht <e1227085@student.tuwien.ac.at>.
 */
public class DataSource {
  private static final Logger LOGGER = LogManager.getLogger();
  private static final DataSource instance = new DataSource();
  private Connection connection;

  private DataSource() {
    try {
      Class.forName("org.h2.Driver");
    } catch (Exception e) {
      LOGGER.error(e);
      throw new RuntimeException();
    }
    try {
      this.connection = DriverManager.getConnection("jdbc:h2:./db;INIT=RUNSCRIPT FROM './create.sql'\\;", "sa", "");
    } catch (SQLException e) {
      LOGGER.error(e);
      throw new RuntimeException();
    }
  }

  public static Connection getConnection() {
    return instance.connection;
  }

}
