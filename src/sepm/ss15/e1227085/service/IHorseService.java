package sepm.ss15.e1227085.service;

import sepm.ss15.e1227085.domain.Horse;

import java.util.List;

/**
 * Created by Patrick Grosslicht <e1227085@student.tuwien.ac.at>.
 */
public interface IHorseService {
    /**
     * Create a horse.
     *
     * @param horse the horse
     * @return the horse
     * @throws IllegalArgumentException if argument is not acceptable
     */
    public Horse create(Horse horse) throws IllegalArgumentException;

    /**
     * Delete a horse.
     *
     * @param horse the horse
     * @return the horse
     * @throws IllegalArgumentException if argument is not acceptable
     */
    public Horse delete(Horse horse) throws IllegalArgumentException;

    /**
     * Update a horse.
     *
     * @param horse the horse
     * @return the horse
     * @throws IllegalArgumentException if argument is not acceptable
     */
    public Horse update(Horse horse) throws IllegalArgumentException;

    /**
     * Find all horses.
     *
     * @return the list
     */
    public List<Horse> findAll();

    /**
     * Find horses by name.
     *
     * @param name the name
     * @return the list
     */
    public List<Horse> findByName(String name);

    /**
     * Find one horse by id.
     *
     * @param id the id
     * @return the horse
     * @throws IllegalArgumentException if ID is not found
     */
    public Horse findById(long id) throws IllegalArgumentException;
}
