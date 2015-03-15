package sepm.ss15.e1227085.test;

import org.junit.Test;
import sepm.ss15.e1227085.dao.IHorseDAO;
import sepm.ss15.e1227085.dao.IJockeyDAO;
import sepm.ss15.e1227085.dao.IRaceDAO;
import sepm.ss15.e1227085.dao.impl.JDBCHorseDAO;
import sepm.ss15.e1227085.dao.impl.JDBCJockeyDAO;
import sepm.ss15.e1227085.domain.Race;
import sepm.ss15.e1227085.domain.RaceEntry;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Patrick Grosslicht <e1227085@student.tuwien.ac.at>.
 */
public abstract class AbstractJDBCRaceDAOTest {

    protected IRaceDAO raceDAO;

    protected void setJDBCRaceDAO(IRaceDAO raceDAO) {
        this.raceDAO = raceDAO;
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
        IHorseDAO horseDAO = new JDBCHorseDAO();
        IJockeyDAO jockeyDAO = new JDBCJockeyDAO();
        List<RaceEntry> raceEntries = new ArrayList<RaceEntry>();
        RaceEntry raceEntry = new RaceEntry(horseDAO.findById(1), jockeyDAO.findById(1), 58.4, 43.5, 0.95);
        RaceEntry raceEntry2 = new RaceEntry(horseDAO.findById(2), jockeyDAO.findById(2), 38.4, 53.5, 1.05);
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
    }
}
