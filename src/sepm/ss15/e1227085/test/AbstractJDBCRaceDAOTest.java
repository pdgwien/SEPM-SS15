package sepm.ss15.e1227085.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import sepm.ss15.e1227085.dao.IHorseDAO;
import sepm.ss15.e1227085.dao.IJockeyDAO;
import sepm.ss15.e1227085.dao.IRaceDAO;
import sepm.ss15.e1227085.domain.Race;
import sepm.ss15.e1227085.domain.RaceEntry;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Patrick Grosslicht <e1227085@student.tuwien.ac.at>.
 */
public abstract class AbstractJDBCRaceDAOTest {

  private static final Logger LOGGER = LogManager.getLogger();
  protected IRaceDAO raceDAO;
  protected IHorseDAO horseDAO;
  protected IJockeyDAO jockeyDAO;

  protected void setJDBCRaceDAO(IRaceDAO raceDAO) {
    this.raceDAO = raceDAO;
  }

  protected void setJDBCHorseDAO(IHorseDAO horseDAO) {
    this.horseDAO = horseDAO;
  }

  protected void setJDBCJockeyDAO(IJockeyDAO jockeyDAO) {
    this.jockeyDAO = jockeyDAO;
  }

  /**
   * Versucht einen NULL-Wert in die DB zu speichern. Sollte fehlschlagen.
   */
  @Test(expected = IllegalArgumentException.class)
  public void createWithNullShouldThrowException() {
    raceDAO.create(null);
  }

  @Test
  public void createWithValidParametersShouldPersist() {
    List<RaceEntry> raceEntries = new ArrayList<>();
    RaceEntry raceEntry = new RaceEntry(horseDAO.findById(1), jockeyDAO.findById(1));
    raceEntry.setRank(1);
    RaceEntry raceEntry2 = new RaceEntry(horseDAO.findById(2), jockeyDAO.findById(2));
    raceEntry2.setRank(2);
    raceEntries.add(raceEntry);
    raceEntries.add(raceEntry2);
    Race race = new Race(raceEntries);
    List<Race> races = raceDAO.findAll();
    org.junit.Assert.assertFalse(races.contains(race));
    raceDAO.create(race);
    races = raceDAO.findAll();
    org.junit.Assert.assertTrue(races.contains(race));
  }

  @Test(expected = IllegalArgumentException.class)
  public void deleteWithNullShouldThrowException() {
    raceDAO.delete(null);
  }

  @Test
  public void findAllShouldFindAllRaces() {
    List<Race> races = raceDAO.findAll();
    org.junit.Assert.assertFalse(races.isEmpty());
  }
}
