package sepm.ss15.e1227085.dao;

import sepm.ss15.e1227085.domain.Race;

import java.util.List;

/**
 * Created by Patrick Grosslicht <e1227085@student.tuwien.ac.at>.
 */
public interface IRaceDAO {
  /**
   * Create a race in the database. Returns a Race object if successful, null otherwise.
   *
   * @param race the race
   * @return the race
   */
  public Race create(Race race);

  /**
   * Delete race from the database.
   *
   * @param race the race
   * @return the race
   */
  public Race delete(Race race);

  /**
   * Search all races.
   *
   * @return the list
   */
  public List<Race> findAll();
}
