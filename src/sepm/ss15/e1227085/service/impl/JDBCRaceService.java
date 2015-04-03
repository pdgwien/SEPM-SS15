package sepm.ss15.e1227085.service.impl;

import sepm.ss15.e1227085.dao.IRaceDAO;
import sepm.ss15.e1227085.dao.impl.JDBCRaceDAO;
import sepm.ss15.e1227085.domain.Race;
import sepm.ss15.e1227085.service.IRaceService;

import java.util.List;

/**
 * Created by Patrick Grosslicht <e1227085@student.tuwien.ac.at>.
 */
public class JDBCRaceService implements IRaceService {
    private IRaceDAO raceDAO = new JDBCRaceDAO();
    /**
     * Create race.
     *
     * @param race the race
     * @return the race
     * @throws IllegalArgumentException the illegal argument exception
     */
    @Override
    public Race create(Race race) throws IllegalArgumentException {
        return raceDAO.create(race);
    }

    /**
     * Delete race.
     *
     * @param race the race
     * @return the race
     * @throws IllegalArgumentException the illegal argument exception
     */
    @Override
    public Race delete(Race race) throws IllegalArgumentException {
        return raceDAO.delete(race);
    }

    /**
     * Find all.
     *
     * @return the list
     */
    @Override
    public List<Race> findAll() {
        return raceDAO.findAll();
    }
}
