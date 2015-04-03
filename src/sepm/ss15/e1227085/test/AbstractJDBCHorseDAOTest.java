package sepm.ss15.e1227085.test;

import org.junit.Test;
import sepm.ss15.e1227085.dao.IHorseDAO;
import sepm.ss15.e1227085.domain.Horse;

import java.util.List;

/**
 * Created by Patrick Grosslicht <e1227085@student.tuwien.ac.at>.
 */
public abstract class AbstractJDBCHorseDAOTest {

  protected IHorseDAO horseDAO;

  protected void setJDBCHorseDAO(IHorseDAO horseDAO) {
    this.horseDAO = horseDAO;
  }

  /**
   * Versucht einen NULL-Wert in die DB zu speichern. Sollte fehlschlagen.
   */
  @Test(expected = IllegalArgumentException.class)
  public void createWithNullShouldThrowException() {
    horseDAO.create(null);
  }

  @Test
  public void createWithValidParametersShouldPersist() {
    Horse horse = new Horse("Test", "/home/test/test.png", 75.47, 85.48, false);
    List<Horse> horses = horseDAO.findAll();
    org.junit.Assert.assertFalse(horses.contains(horse));
    horseDAO.create(horse);
    horses = horseDAO.findAll();
    org.junit.Assert.assertTrue(horses.contains(horse));
  }

  @Test(expected = IllegalArgumentException.class)
  public void updateWithNullShouldThrowException() {
    horseDAO.update(null);
  }

  @Test
  public void updateWithValidParametersShouldPersist() {
    Horse horse = new Horse("Test", "/home/test/test.png", 75.47, 85.48, false);
    horseDAO.create(horse);
    List<Horse> horses = horseDAO.findAll();
    org.junit.Assert.assertTrue(horses.contains(horse));
    horse.setName("TestPassed");
    horseDAO.update(horse);
    horses = horseDAO.findAll();
    org.junit.Assert.assertTrue(horses.contains(horse));
  }

  @Test(expected = IllegalArgumentException.class)
  public void deleteWithNullShouldThrowException() {
    horseDAO.delete(null);
  }

  @Test
  public void deleteWithValidParametersShouldPersist() {
    Horse horse = new Horse("Test", "/home/test/test.png", 75.47, 85.48, false);
    horseDAO.create(horse);
    List<Horse> horses = horseDAO.findAll();
    org.junit.Assert.assertTrue(horses.contains(horse));
    horseDAO.delete(horse);
    horses = horseDAO.findAll();
    org.junit.Assert.assertFalse(horses.contains(horse));
  }

  @Test
  public void findByNameShouldFindMatchingItems() {
    Horse horse = new Horse("Bertold", "/home/test/test.png", 75.47, 85.48, false);
    Horse horse2 = new Horse("Günther", "/home/test/test.png", 45.47, 83.48, false);
    horseDAO.create(horse);
    horseDAO.create(horse2);
    List<Horse> horses = horseDAO.findByName("Bertold");
    org.junit.Assert.assertTrue(horses.contains(horse));
    org.junit.Assert.assertFalse(horses.contains(horse2));
  }

  @Test
  public void findByNameShouldReturnEmptyListWhenNoMatches() {
    Horse horse = new Horse("Bertold", "/home/test/test.png", 75.47, 85.48, false);
    Horse horse2 = new Horse("Günther", "/home/test/test.png", 45.47, 83.48, false);
    horseDAO.create(horse);
    horseDAO.create(horse2);
    List<Horse> horses = horseDAO.findByName("Gudrun");
    org.junit.Assert.assertTrue(horses.isEmpty());
  }
}
