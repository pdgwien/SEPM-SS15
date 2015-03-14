package sepm.ss15.e1227085.dao;

import sepm.ss15.e1227085.domain.Jockey;

import java.util.List;

/**
 * Created by Patrick Grosslicht <e1227085@student.tuwien.ac.at>.
 */
public interface IJockeyDAO {
    /**
     * Create a jockey in the database. Return the jockey back if successful, null otherwise.
     *
     * @param jockey the jockey about to be created
     * @return the jockey
     */
    public Jockey create(Jockey jockey);

    /**
     * Update a jockey in the database.
     *
     * @param jockey the jockey
     * @return the jockey
     */
    public Jockey update(Jockey jockey);

    /**
     * Delete a jockey from the database.
     *
     * @param jockey the jockey
     * @return the jockey
     */
    public Jockey delete(Jockey jockey);

    /**
     * Search all jockeys.
     *
     * @return the list of all jockeys
     */
    public List<Jockey> findAll();

    /**
     * Search by name.
     *
     * @return the list of jockeys according to the specified attribute
     */
    public List<Jockey> findByName();
}
