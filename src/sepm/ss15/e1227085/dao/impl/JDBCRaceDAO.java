package sepm.ss15.e1227085.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sepm.ss15.e1227085.dao.IRaceDAO;
import sepm.ss15.e1227085.domain.Race;

import java.util.List;

/**
 * Created by Patrick Grosslicht <e1227085@student.tuwien.ac.at>.
 */
public class JDBCRaceDAO implements IRaceDAO {
    private static final Logger LOGGER = LogManager.getLogger();

    /**
     * Create a race in the database. Returns a Race object if successful, null otherwise.
     *
     * @param race the race
     * @return the race
     */
    @Override
    public Race create(Race race) {
        LOGGER.entry(race);
        return null;
    }

    /**
     * Update a race in the database.
     *
     * @param race the race
     * @return the race
     */
    @Override
    public Race update(Race race) {
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
        return null;
    }

    /**
     * Search all races.
     *
     * @return the list
     */
    @Override
    public List<Race> findAll() {
        return null;
    }
}
