package sepm.ss15.e1227085.service;

import sepm.ss15.e1227085.domain.Jockey;

import java.util.List;

/**
 * Created by Patrick Grosslicht <e1227085@student.tuwien.ac.at>.
 */
public interface IJockeyService {
    /**
     * Create a jockey.
     *
     * @param jockey the jockey
     * @return the jockey
     * @throws IllegalArgumentException if argument is not acceptable
     */
    public Jockey create(Jockey jockey) throws IllegalArgumentException;

    /**
     * Delete a jockey.
     *
     * @param jockey the jockey
     * @return the jockey
     * @throws IllegalArgumentException if argument is not acceptable
     */
    public Jockey delete(Jockey jockey) throws IllegalArgumentException;

    /**
     * Update a jockey.
     *
     * @param jockey the jockey
     * @return the jockey
     * @throws IllegalArgumentException if argument is not acceptable
     */
    public Jockey update(Jockey jockey) throws IllegalArgumentException;

    /**
     * Find all jockeys.
     *
     * @return the list
     */
    public List<Jockey> findAll();

    /**
     * Find jockeys by name.
     *
     * @param name the name
     * @return the list
     */
    public List<Jockey> findByName(String name);

    /**
     * Find one jockey by id.
     *
     * @param id the id
     * @return the jockey
     * @throws IllegalArgumentException if ID is not found
     */
    public Jockey findById(long id) throws IllegalArgumentException;
}
