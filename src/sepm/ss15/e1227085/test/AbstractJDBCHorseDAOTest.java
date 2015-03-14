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
        Horse horse = new Horse("Test", "/home/test/test.png", 75.47, 85.48);
        List<Horse> horses = horseDAO.findAll();
        org.junit.Assert.assertFalse(horses.contains(horse));
        horseDAO.create(horse);
        horses = horseDAO.findAll();
        org.junit.Assert.assertTrue(horses.contains(horse));
    }
}
