package sepm.ss15.e1227085.test;

import org.junit.Test;
import sepm.ss15.e1227085.dao.IJockeyDAO;
import sepm.ss15.e1227085.domain.Jockey;

import java.util.List;

/**
 * Created by Patrick Grosslicht <e1227085@student.tuwien.ac.at>.
 */
public abstract class AbstractJDBCJockeyDAOTest {

    protected IJockeyDAO jockeyDAO;

    protected void setJDBCJockeyDAO(IJockeyDAO jockeyDAO) {
        this.jockeyDAO = jockeyDAO;
    }

    /**
     * Versucht einen NULL-Wert in die DB zu speichern. Sollte fehlschlagen.
     */
    @Test(expected = IllegalArgumentException.class)
    public void createWithNullShouldThrowException() {
        jockeyDAO.create(null);
    }

    @Test
    public void createWithValidParametersShouldPersist() {
        Jockey jockey = new Jockey("Test", 57.4, 66, false);
        List<Jockey> jockeys = jockeyDAO.findAll();
        org.junit.Assert.assertFalse(jockeys.contains(jockey));
        jockeyDAO.create(jockey);
        jockeys = jockeyDAO.findAll();
        org.junit.Assert.assertTrue(jockeys.contains(jockey));
    }

    @Test(expected = IllegalArgumentException.class)
    public void updateWithNullShouldThrowException() {
        jockeyDAO.update(null);
    }

    @Test
    public void updateWithValidParametersShouldPersist() {
        Jockey jockey = new Jockey("Test", 57.4, 66, false);
        jockeyDAO.create(jockey);
        List<Jockey> jockeys = jockeyDAO.findAll();
        org.junit.Assert.assertTrue(jockeys.contains(jockey));
        jockey.setName("TestPassed");
        jockeyDAO.update(jockey);
        jockeys = jockeyDAO.findAll();
        org.junit.Assert.assertTrue(jockeys.contains(jockey));
    }

    @Test(expected = IllegalArgumentException.class)
    public void deleteWithNullShouldThrowException() {
        jockeyDAO.delete(null);
    }

    @Test
    public void deleteWithValidParametersShouldPersist() {
        Jockey jockey = new Jockey("Test", 57.4, 66, false);
        jockeyDAO.create(jockey);
        List<Jockey> jockeys = jockeyDAO.findAll();
        org.junit.Assert.assertTrue(jockeys.contains(jockey));
        jockeyDAO.delete(jockey);
        jockeys = jockeyDAO.findAll();
        org.junit.Assert.assertFalse(jockeys.contains(jockey));
    }

    @Test
    public void findByNameShouldFindMatchingItems() {
        Jockey jockey = new Jockey("Bertold", 57.4, 66, false);
        Jockey jockey2 = new Jockey("Günther", 37.4, 36, false);
        jockeyDAO.create(jockey);
        jockeyDAO.create(jockey2);
        List<Jockey> jockeys = jockeyDAO.findByName("Bertold");
        org.junit.Assert.assertTrue(jockeys.contains(jockey));
        org.junit.Assert.assertFalse(jockeys.contains(jockey2));
    }

    @Test
    public void findByNameShouldReturnEmptyListWhenNoMatches() {
        Jockey jockey = new Jockey("Bertold", 57.4, 66, false);
        Jockey jockey2 = new Jockey("Günther", 37.4, 36, false);
        jockeyDAO.create(jockey);
        jockeyDAO.create(jockey2);
        List<Jockey> jockeys = jockeyDAO.findByName("Gudrun");
        org.junit.Assert.assertTrue(jockeys.isEmpty());
    }
}
