package sepm.ss15.e1227085.service;

import sepm.ss15.e1227085.domain.Horse;
import sepm.ss15.e1227085.domain.Jockey;
import sepm.ss15.e1227085.domain.Race;

import java.util.List;
import java.util.Map;

/**
 * Created by Patrick Grosslicht <e1227085@student.tuwien.ac.at>.
 */
public interface IRaceService {
  /**
   * Create race.
   *
   * @param race the race
   * @return the race
   * @throws IllegalArgumentException the illegal argument exception
   */
  public Race create(Race race) throws IllegalArgumentException;

  /**
   * Delete race.
   *
   * @param race the race
   * @return the race
   * @throws IllegalArgumentException the illegal argument exception
   */
  public Race delete(Race race) throws IllegalArgumentException;

  /**
   * Find all.
   *
   * @return the list
   */
  public List<Race> findAll();

  /**
   * Find all entries for horse and jockey.
   *
   * @param horse  the horse
   * @param jockey the jockey
   * @return the list
   */
  public Map<Integer, Integer> getRankingsForHorseAndJockey(Horse horse, Jockey jockey);
}
