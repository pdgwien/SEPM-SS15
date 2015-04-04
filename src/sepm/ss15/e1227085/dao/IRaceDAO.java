package sepm.ss15.e1227085.dao;

import sepm.ss15.e1227085.domain.Horse;
import sepm.ss15.e1227085.domain.Jockey;
import sepm.ss15.e1227085.domain.Race;
import sepm.ss15.e1227085.domain.RaceEntry;

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
   * Find all races.
   *
   * @return the list
   */
  public List<Race> findAll();

  /**
   * Find all entries for horse.
   *
   * @param horse the horse
   * @return the list
   */
  public List<RaceEntry> findEntriesForHorse(Horse horse);

  /**
   * Find all entries for jockey.
   *
   * @param jockey the jockey
   * @return the list
   */
  public List<RaceEntry> findEntriesForJockey(Jockey jockey);

  /**
   * Find all entries for horse and jockey.
   *
   * @param horse  the horse
   * @param jockey the jockey
   * @return the list
   */
  public List<RaceEntry> findEntriesForHorseAndJockey(Horse horse, Jockey jockey);
}
