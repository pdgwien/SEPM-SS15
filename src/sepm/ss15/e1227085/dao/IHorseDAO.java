package sepm.ss15.e1227085.dao;

import sepm.ss15.e1227085.domain.Horse;

import java.util.List;

/**
 * Created by Patrick Grosslicht <e1227085@student.tuwien.ac.at>.
 */
public interface IHorseDAO {
    /**
     * Create a horse in the database. Returns the object if successful, null otherwise.
     *
     * @param horse the horse about to be saved
     * @return the horse
     */
    public Horse create(Horse horse);

    /**
     * Update a horse in the database. Returns the object if successful, null otherwise.
     *
     * @param horse the horse
     * @return the horse
     */
    public Horse update(Horse horse);

    /**
     * Delete a horse. Returns the object if sucessful, null otherwise.
     *
     * @param horse the horse
     * @return the horse
     */
    public Horse delete(Horse horse);

    /**
     * Search all horses.
     *
     * @return the list of all horses
     */
    public List<Horse> findAll();

    /**
     * Search horses by name.
     *
     * @param name the name
     * @return the list of all horses having the specified value
     */
    public List<Horse> findByName(String name);

    /**
     * Find by id.
     *
     * @param id the id
     * @return the horse
     */
    public Horse findById(long id);
}
