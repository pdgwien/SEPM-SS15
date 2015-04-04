package sepm.ss15.e1227085.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sepm.ss15.e1227085.dao.DataSource;
import sepm.ss15.e1227085.dao.IHorseDAO;
import sepm.ss15.e1227085.domain.Horse;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Patrick Grosslicht <e1227085@student.tuwien.ac.at>.
 */
public class JDBCHorseDAO implements IHorseDAO {
  private static final Logger LOGGER = LogManager.getLogger();
  private PreparedStatement createStmt;
  private PreparedStatement updateStmt;
  private PreparedStatement deleteStmt;
  private PreparedStatement findAllStmt;
  private PreparedStatement findByNameStmt;
  private PreparedStatement findByIdStmt;

  public JDBCHorseDAO() {
    try {
      Connection conn = DataSource.getConnection();
      createStmt = conn.prepareStatement("INSERT INTO Horse (name, imgPath, minSpeed, maxSpeed, isDeleted) VALUES (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
      updateStmt = conn.prepareStatement("UPDATE Horse SET name = ?, imgPath = ?, minSpeed = ?, maxSpeed = ?, isDeleted = ? WHERE id = ?");
      deleteStmt = conn.prepareStatement("UPDATE Horse SET isDeleted = true WHERE id = ?");
      findAllStmt = conn.prepareStatement("SELECT * FROM Horse WHERE isDeleted = false");
      findByNameStmt = conn.prepareStatement("SELECT * FROM Horse WHERE isDeleted = false AND name LIKE ?");
      findByIdStmt = conn.prepareStatement("SELECT * FROM Horse WHERE id = ?");
    } catch (SQLException e) {
      LOGGER.error(e);
      throw new RuntimeException("Something is wrong, cannot prepare SQL statements.");
    }
  }

  /**
   * Create a horse in the database. Returns the object if successful, null otherwise.
   *
   * @param horse the horse about to be saved
   * @return the horse
   */
  @Override
  public Horse create(Horse horse) {
    if (horse == null) {
      throw new IllegalArgumentException("Horse cannot be null");
    }
    try {
      createStmt.setString(1, horse.getName());
      createStmt.setString(2, horse.getImagePath());
      createStmt.setDouble(3, horse.getMinSpeed());
      createStmt.setDouble(4, horse.getMaxSpeed());
      createStmt.setBoolean(5, horse.isDeleted());
      int affectedRows = createStmt.executeUpdate();
      if (affectedRows == 0) {
        throw new SQLException("Creating horse failed, no rows affected.");
      }

      ResultSet generatedKeys = createStmt.getGeneratedKeys();
      if (generatedKeys.next()) {
        horse.setId(generatedKeys.getLong(1));
      } else {
        throw new SQLException("Creating horse failed, no ID obtained.");
      }
      return horse;
    } catch (SQLException e) {
      LOGGER.error(e);
    }
    return null;
  }

  /**
   * Update a horse in the database.
   *
   * @param horse the horse
   * @return the horse
   */
  @Override
  public Horse update(Horse horse) {
    if (horse == null) {
      throw new IllegalArgumentException("Horse cannot be null");
    }
    try {
      updateStmt.setString(1, horse.getName());
      updateStmt.setString(2, horse.getImagePath());
      updateStmt.setDouble(3, horse.getMinSpeed());
      updateStmt.setDouble(4, horse.getMaxSpeed());
      updateStmt.setBoolean(5, horse.isDeleted());
      updateStmt.setLong(6, horse.getId());
      int affectedRows = updateStmt.executeUpdate();
      if (affectedRows == 0) {
        throw new SQLException("Updating horse failed, no rows affected.");
      }
      return horse;
    } catch (SQLException e) {
      LOGGER.error(e);
    }
    return null;
  }

  /**
   * Delete a horse.
   *
   * @param horse the horse
   * @return the horse
   */
  @Override
  public Horse delete(Horse horse) {
    if (horse == null) {
      throw new IllegalArgumentException("Horse cannot be null");
    }
    try {
      deleteStmt.setLong(1, horse.getId());
      int affectedRows = deleteStmt.executeUpdate();
      if (affectedRows == 0) {
        throw new SQLException("Deleting horse failed, no rows affected.");
      }
      return horse;
    } catch (SQLException e) {
      LOGGER.error(e);
    }
    return null;
  }

  /**
   * Search all horses.
   *
   * @return the list of all horses
   */
  @Override
  public List<Horse> findAll() {
    ArrayList<Horse> horseList = null;
    try {
      ResultSet rs = findAllStmt.executeQuery();
      horseList = new ArrayList<>();
      Horse tmpHorse;
      while (rs.next()) {
        tmpHorse = new Horse(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getDouble(5), rs.getBoolean(6));
        horseList.add(tmpHorse);
      }
    } catch (SQLException e) {
      LOGGER.error(e);
    }
    return horseList;
  }

  /**
   * Search horses by name.
   *
   * @param name the name
   * @return the list of all horses having the specified value
   */
  @Override
  public List<Horse> findByName(String name) {
    ArrayList<Horse> horseList = null;
    try {
      findByNameStmt.setString(1, name + "%");
      ResultSet rs = findByNameStmt.executeQuery();
      horseList = new ArrayList<>();
      Horse tmpHorse;
      while (rs.next()) {
        tmpHorse = new Horse(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getDouble(5), rs.getBoolean(6));
        horseList.add(tmpHorse);
      }
    } catch (SQLException e) {
      LOGGER.error(e);
    }
    return horseList;
  }

  /**
   * Find by id.
   *
   * @param id the id
   * @return the horse
   */
  @Override
  public Horse findById(long id) {
    Horse horse = null;
    try {
      findByIdStmt.setLong(1, id);
      ResultSet rs = findByIdStmt.executeQuery();
      while (rs.next()) {
        horse = new Horse(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getDouble(5), rs.getBoolean(6));
      }
    } catch (SQLException e) {
      LOGGER.error(e);
    }
    if (horse == null) {
      throw new IllegalArgumentException("ID could not be found");
    }
    return horse;
  }
}
