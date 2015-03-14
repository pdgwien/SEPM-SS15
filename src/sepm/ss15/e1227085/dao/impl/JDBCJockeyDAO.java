package sepm.ss15.e1227085.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sepm.ss15.e1227085.dao.IJockeyDAO;
import sepm.ss15.e1227085.domain.Jockey;

import java.util.List;

/**
 * Created by Patrick Grosslicht <e1227085@student.tuwien.ac.at>.
 */
public class JDBCJockeyDAO implements IJockeyDAO {
    private static final Logger LOGGER = LogManager.getLogger();

    /**
     * Create a jockey in the database. Return the jockey back if successful, null otherwise.
     *
     * @param jockey the jockey about to be created
     * @return the jockey
     */
    @Override
    public Jockey create(Jockey jockey) {
        LOGGER.entry(jockey);
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
        return null;
    }

    /**
     * Search all jockeys.
     *
     * @return the list of all jockeys
     */
    @Override
    public List<Jockey> findAll() {
        return null;
    }

    /**
     * Search by name.
     *
     * @return the list of jockeys according to the specified attribute
     */
    @Override
    public List<Jockey> findByName() {
        return null;
    }
}
