package sepm.ss15.e1227085.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sepm.ss15.e1227085.dao.DataSource;
import sepm.ss15.e1227085.dao.IJockeyDAO;
import sepm.ss15.e1227085.domain.Jockey;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Patrick Grosslicht <e1227085@student.tuwien.ac.at>.
 */
public class JDBCJockeyDAO implements IJockeyDAO {
  private static final Logger LOGGER = LogManager.getLogger();
  private PreparedStatement createStmt;
  private PreparedStatement updateStmt;
  private PreparedStatement deleteStmt;
  private PreparedStatement findAllStmt;
  private PreparedStatement findByNameStmt;
  private PreparedStatement findByIdStmt;

  public JDBCJockeyDAO() {
    try {
      Connection conn = DataSource.getConnection();
      createStmt = conn.prepareStatement("INSERT INTO Jockey (name, talent, age, isDeleted) VALUES (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
      updateStmt = conn.prepareStatement("UPDATE Jockey SET name = ?, talent = ?, age = ?, isDeleted = ? WHERE id = ?");
      deleteStmt = conn.prepareStatement("UPDATE Jockey SET isDeleted = true WHERE id = ?");
      findAllStmt = conn.prepareStatement("SELECT * FROM Jockey WHERE isDeleted = false");
      findByNameStmt = conn.prepareStatement("SELECT * FROM Jockey WHERE isDeleted = false AND name LIKE ?");
      findByIdStmt = conn.prepareStatement("SELECT * FROM Jockey WHERE id = ?");
    } catch (SQLException e) {
      LOGGER.error(e);
      throw new RuntimeException("Something is wrong, cannot prepare SQL statements.");
    }
  }

  /**
   * Create a jockey in the database. Return the jockey back if successful, null otherwise.
   *
   * @param jockey the jockey about to be created
   * @return the jockey
   */
  @Override
  public Jockey create(Jockey jockey) {
    if (jockey == null) {
      throw new IllegalArgumentException("Jockey cannot be null");
    }
    try {
      createStmt.setString(1, jockey.getName());
      createStmt.setDouble(2, jockey.getTalent());
      createStmt.setInt(3, jockey.getAge());
      createStmt.setBoolean(4, jockey.isDeleted());
      int affectedRows = createStmt.executeUpdate();
      if (affectedRows == 0) {
        throw new SQLException("Creating jockey failed, no rows affected.");
      }

      ResultSet generatedKeys = createStmt.getGeneratedKeys();
      if (generatedKeys.next()) {
        jockey.setId(generatedKeys.getLong(1));
      } else {
        throw new SQLException("Creating jockey failed, no ID obtained.");
      }
      return jockey;
    } catch (SQLException e) {
      LOGGER.error(e);
    }
    return null;
  }

  /**
   * Update a jockey in the database.
   *
   * @param jockey the jockey
   * @return the jockey
   */
  @Override
  public Jockey update(Jockey jockey) {
    if (jockey == null) {
      throw new IllegalArgumentException("Jockey cannot be null");
    }
    try {
      updateStmt.setString(1, jockey.getName());
      updateStmt.setDouble(2, jockey.getTalent());
      updateStmt.setInt(3, jockey.getAge());
      updateStmt.setBoolean(4, jockey.isDeleted());
      updateStmt.setLong(5, jockey.getId());
      int affectedRows = updateStmt.executeUpdate();
      if (affectedRows == 0) {
        throw new SQLException("Updating jockey failed, no rows affected.");
      }
      return jockey;
    } catch (SQLException e) {
      LOGGER.error(e);
    }
    return null;
  }

  /**
   * Delete a jockey from the database.
   *
   * @param jockey the jockey
   * @return the jockey
   */
  @Override
  public Jockey delete(Jockey jockey) {
    if (jockey == null) {
      throw new IllegalArgumentException("Jockey cannot be null");
    }
    try {
      deleteStmt.setLong(1, jockey.getId());
      int affectedRows = deleteStmt.executeUpdate();
      if (affectedRows == 0) {
        throw new SQLException("Deleting jockey failed, no rows affected.");
      }
      return jockey;
    } catch (SQLException e) {
      LOGGER.error(e);
    }
    return null;
  }

  /**
   * Search all jockeys.
   *
   * @return the list of all jockeys
   */
  @Override
  public List<Jockey> findAll() {
    ArrayList<Jockey> jockeyList = null;
    try {
      ResultSet rs = findAllStmt.executeQuery();
      jockeyList = new ArrayList<Jockey>();
      Jockey tmpJockey = null;
      while (rs.next()) {
        tmpJockey = new Jockey(rs.getLong(1), rs.getString(2), rs.getDouble(3), rs.getInt(4), rs.getBoolean(5));
        jockeyList.add(tmpJockey);
      }
    } catch (SQLException e) {
      LOGGER.error(e);
    }
    return jockeyList;
  }

  /**
   * Search jockeys by name.
   *
   * @param name the name to search for
   * @return the list of jockeys according to the specified attribute
   */
  @Override
  public List<Jockey> findByName(String name) {
    ArrayList<Jockey> jockeyList = null;
    try {
      findByNameStmt.setString(1, name);
      ResultSet rs = findByNameStmt.executeQuery();
      jockeyList = new ArrayList<Jockey>();
      Jockey tmpJockey = null;
      while (rs.next()) {
        tmpJockey = new Jockey(rs.getLong(1), rs.getString(2), rs.getDouble(3), rs.getInt(4), rs.getBoolean(5));
        jockeyList.add(tmpJockey);
      }
    } catch (SQLException e) {
      LOGGER.error(e);
    }
    return jockeyList;
  }

  /**
   * Find by id.
   *
   * @param id the id
   * @return the jockey
   */
  @Override
  public Jockey findById(long id) {
    Jockey jockey = null;
    try {
      findByIdStmt.setLong(1, id);
      ResultSet rs = findByIdStmt.executeQuery();
      while (rs.next()) {
        jockey = new Jockey(rs.getLong(1), rs.getString(2), rs.getDouble(3), rs.getInt(4), rs.getBoolean(5));
      }
    } catch (SQLException e) {
      LOGGER.error(e);
    }
    if (jockey == null) {
      throw new IllegalArgumentException("ID could not be found");
    }
    return jockey;
  }
}
