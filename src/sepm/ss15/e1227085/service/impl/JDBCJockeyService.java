package sepm.ss15.e1227085.service.impl;

import sepm.ss15.e1227085.dao.IJockeyDAO;
import sepm.ss15.e1227085.dao.impl.JDBCJockeyDAO;
import sepm.ss15.e1227085.domain.Jockey;
import sepm.ss15.e1227085.service.IJockeyService;

import java.util.List;

/**
 * Created by Patrick Grosslicht <e1227085@student.tuwien.ac.at>.
 */
public class JDBCJockeyService implements IJockeyService {
    private IJockeyDAO jockeyDAO = new JDBCJockeyDAO();
    /**
     * Create a jockey.
     *
     * @param jockey the jockey
     * @return the jockey
     * @throws IllegalArgumentException if argument is not acceptable
     */
    @Override
    public Jockey create(Jockey jockey) throws IllegalArgumentException {
        return jockeyDAO.create(jockey);
    }

    /**
     * Delete a jockey.
     *
     * @param jockey the jockey
     * @return the jockey
     * @throws IllegalArgumentException if argument is not acceptable
     */
    @Override
    public Jockey delete(Jockey jockey) throws IllegalArgumentException {
        return jockeyDAO.delete(jockey);
    }

    /**
     * Update a jockey.
     *
     * @param jockey the jockey
     * @return the jockey
     * @throws IllegalArgumentException if argument is not acceptable
     */
    @Override
    public Jockey update(Jockey jockey) throws IllegalArgumentException {
        return jockeyDAO.update(jockey);
    }

    /**
     * Find all jockeys.
     *
     * @return the list
     */
    @Override
    public List<Jockey> findAll() {
        return jockeyDAO.findAll();
    }

    /**
     * Find jockeys by name.
     *
     * @param name the name
     * @return the list
     */
    @Override
    public List<Jockey> findByName(String name) {
        return jockeyDAO.findByName(name);
    }

    /**
     * Find one jockey by id.
     *
     * @param id the id
     * @return the jockey
     * @throws IllegalArgumentException if ID is not found
     */
    @Override
    public Jockey findById(long id) throws IllegalArgumentException {
        return jockeyDAO.findById(id);
    }
}
