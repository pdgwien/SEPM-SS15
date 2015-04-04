package sepm.ss15.e1227085.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sepm.ss15.e1227085.dao.DataSource;
import sepm.ss15.e1227085.dao.IHorseDAO;
import sepm.ss15.e1227085.dao.IJockeyDAO;
import sepm.ss15.e1227085.dao.IRaceDAO;
import sepm.ss15.e1227085.domain.Horse;
import sepm.ss15.e1227085.domain.Jockey;
import sepm.ss15.e1227085.domain.Race;
import sepm.ss15.e1227085.domain.RaceEntry;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Patrick Grosslicht <e1227085@student.tuwien.ac.at>.
 */
public class JDBCRaceDAO implements IRaceDAO {
  private static final Logger LOGGER = LogManager.getLogger();
  private PreparedStatement createStmt;
  private PreparedStatement deleteStmt;
  private PreparedStatement findAllIdsStmt;
  private PreparedStatement findAllRaceEntriesStmt;
  private PreparedStatement findEntriesForHorseStmt;
  private PreparedStatement findEntriesForJockeyStmt;
  private PreparedStatement findEntriesForHorseAndJockeyStmt;

  public JDBCRaceDAO() {
    try {
      Connection conn = DataSource.getConnection();
      createStmt = conn.prepareStatement("INSERT INTO Race (id, horse, jockey, talent, speed, luckyNumber, rank) VALUES (?, ?, ?, ?, ?, ?, ?)");
      deleteStmt = conn.prepareStatement("DELETE FROM Race WHERE id = ?");
      findAllIdsStmt = conn.prepareStatement("SELECT DISTINCT(id) FROM Race");
      findAllRaceEntriesStmt = conn.prepareStatement("SELECT * FROM Race WHERE id = ?");
      findEntriesForHorseStmt = conn.prepareStatement("SELECT * FROM Race WHERE horse = ? ORDER BY rank");
      findEntriesForJockeyStmt = conn.prepareStatement("SELECT * FROM Race WHERE jockey = ? ORDER BY rank");
      findEntriesForHorseAndJockeyStmt = conn.prepareStatement("SELECT * FROM Race WHERE horse = ? AND jockey = ? ORDER BY rank");
    } catch (SQLException e) {
      LOGGER.error(e);
      throw new RuntimeException("Something is wrong, cannot prepare SQL statements.");
    }
  }

  /**
   * Create a race in the database. Returns a Race object if successful, null otherwise.
   *
   * @param race the race
   * @return the race
   */
  @Override
  public Race create(Race race) {
    if (race == null) {
      throw new IllegalArgumentException("Race cannot be null");
    }
    List<RaceEntry> raceEntries = race.getEntries();
    try {
      for (int i = 0; i < raceEntries.size(); i++) {
        RaceEntry raceEntry = raceEntries.get(i);
        createStmt.setObject(1, race.getId());
        createStmt.setLong(2, raceEntry.getHorse().getId());
        createStmt.setLong(3, raceEntry.getJockey().getId());
        createStmt.setDouble(4, raceEntry.getTalent());
        createStmt.setDouble(5, raceEntry.getSpeed());
        createStmt.setDouble(6, raceEntry.getLuckyNumber());
        createStmt.setInt(7, raceEntry.getRank());
        int affectedRows = createStmt.executeUpdate();
        if (affectedRows == 0) {
          throw new SQLException("Creating race failed, no affected rows");
        }
      }
      return race;
    } catch (SQLException e) {
      LOGGER.error(e);
    }
    return null;
  }

  /**
   * Delete race from the database.
   *
   * @param race the race
   * @return the race
   */
  @Override
  public Race delete(Race race) {
    if (race == null) {
      throw new IllegalArgumentException("Race cannot be null");
    }
    try {
      deleteStmt.setObject(1, race.getId());
      int affectedRows = deleteStmt.executeUpdate();
      if (affectedRows == 0) {
        throw new SQLException("Deleting race failed, no rows affected.");
      }
      return race;
    } catch (SQLException e) {
      LOGGER.error(e);
    }
    return null;
  }

  /**
   * Search all races.
   *
   * @return the list
   */
  @Override
  public List<Race> findAll() {
    ArrayList<Race> raceList = null;
    try {
      ResultSet rs = findAllIdsStmt.executeQuery();
      raceList = new ArrayList<Race>();
      Race tmpRace = null;
      while (rs.next()) {
        UUID id = (UUID) rs.getObject(1);
        tmpRace = new Race(id, findAllRaceEntriesById(id));
        raceList.add(tmpRace);
      }
    } catch (SQLException e) {
      LOGGER.error(e);
    }
    return raceList;
  }

  /**
   * Find all race entries by id.
   *
   * @return the list
   */
  private List<RaceEntry> findAllRaceEntriesById(UUID id) {
    ArrayList<RaceEntry> raceEntryList = null;
    IHorseDAO horseDAO = new JDBCHorseDAO();
    IJockeyDAO jockeyDAO = new JDBCJockeyDAO();
    try {
      findAllRaceEntriesStmt.setObject(1, id);
      ResultSet rs = findAllRaceEntriesStmt.executeQuery();
      raceEntryList = new ArrayList<RaceEntry>();
      RaceEntry tmpRaceEntry = null;
      Horse tmpHorse = null;
      Jockey tmpJockey = null;
      while (rs.next()) {
        tmpHorse = horseDAO.findById(rs.getLong(2));
        tmpJockey = jockeyDAO.findById(rs.getLong(3));
        tmpRaceEntry = new RaceEntry(tmpHorse, tmpJockey, rs.getDouble(4), rs.getDouble(5), rs.getDouble(6), rs.getInt(7));
        raceEntryList.add(tmpRaceEntry);
      }
    } catch (SQLException e) {
      LOGGER.error(e);
    }
    if (raceEntryList == null) {
      throw new IllegalArgumentException("ID could not be found");
    }
    return raceEntryList;
  }

  /**
   * Find all entries for horse.
   *
   * @param horse the horse
   * @return the list
   */
  @Override
  public List<RaceEntry> findEntriesForHorse(Horse horse) {
    IJockeyDAO jockeyDAO = new JDBCJockeyDAO();
    ArrayList<RaceEntry> raceEntryList = null;
    try {
      findEntriesForHorseStmt.setObject(1, horse.getId());
      ResultSet rs = findEntriesForHorseStmt.executeQuery();
      raceEntryList = new ArrayList<RaceEntry>();
      RaceEntry tmpRaceEntry = null;
      Jockey tmpJockey = null;
      while (rs.next()) {
        tmpJockey = jockeyDAO.findById(rs.getLong(3));
        tmpRaceEntry = new RaceEntry(horse, tmpJockey, rs.getDouble(4), rs.getDouble(5), rs.getDouble(6), rs.getInt(7));
        raceEntryList.add(tmpRaceEntry);
      }
    } catch (SQLException e) {
      LOGGER.error(e);
    }
    return raceEntryList;
  }

  /**
   * Find all entries for jockey.
   *
   * @param jockey the jockey
   * @return the list
   */
  @Override
  public List<RaceEntry> findEntriesForJockey(Jockey jockey) {
    IHorseDAO horseDAO = new JDBCHorseDAO();
    ArrayList<RaceEntry> raceEntryList = null;
    try {
      findEntriesForJockeyStmt.setObject(1, jockey.getId());
      ResultSet rs = findEntriesForJockeyStmt.executeQuery();
      raceEntryList = new ArrayList<RaceEntry>();
      RaceEntry tmpRaceEntry = null;
      Horse tmpHorse = null;
      while (rs.next()) {
        tmpHorse = horseDAO.findById(rs.getLong(2));
        tmpRaceEntry = new RaceEntry(tmpHorse, jockey, rs.getDouble(4), rs.getDouble(5), rs.getDouble(6), rs.getInt(7));
        raceEntryList.add(tmpRaceEntry);
      }
    } catch (SQLException e) {
      LOGGER.error(e);
    }
    return raceEntryList;
  }

  /**
   * Find all entries for horse and jockey.
   *
   * @param horse  the horse
   * @param jockey the jockey
   * @return the list
   */
  @Override
  public List<RaceEntry> findEntriesForHorseAndJockey(Horse horse, Jockey jockey) {
    ArrayList<RaceEntry> raceEntryList = null;
    try {
      findEntriesForHorseAndJockeyStmt.setObject(1, horse.getId());
      findEntriesForHorseAndJockeyStmt.setObject(2, jockey.getId());
      ResultSet rs = findEntriesForHorseAndJockeyStmt.executeQuery();
      raceEntryList = new ArrayList<RaceEntry>();
      RaceEntry tmpRaceEntry = null;
      while (rs.next()) {
        tmpRaceEntry = new RaceEntry(horse, jockey, rs.getDouble(4), rs.getDouble(5), rs.getDouble(6), rs.getInt(7));
        raceEntryList.add(tmpRaceEntry);
      }
    } catch (SQLException e) {
      LOGGER.error(e);
    }
    return raceEntryList;
  }
}
