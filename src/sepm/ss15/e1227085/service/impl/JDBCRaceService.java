package sepm.ss15.e1227085.service.impl;

import sepm.ss15.e1227085.dao.IRaceDAO;
import sepm.ss15.e1227085.dao.impl.JDBCRaceDAO;
import sepm.ss15.e1227085.domain.Horse;
import sepm.ss15.e1227085.domain.Jockey;
import sepm.ss15.e1227085.domain.Race;
import sepm.ss15.e1227085.domain.RaceEntry;
import sepm.ss15.e1227085.service.IRaceService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Patrick Grosslicht <e1227085@student.tuwien.ac.at>.
 */
public class JDBCRaceService implements IRaceService {
  private final IRaceDAO raceDAO = new JDBCRaceDAO();

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

  /**
   * Find all entries for horse and jockey.
   *
   * @param horse  the horse
   * @param jockey the jockey
   * @return the list
   */
  @Override
  public Map<Integer, Integer> getRankingsForHorseAndJockey(Horse horse, Jockey jockey) {
    List<RaceEntry> list;
    if (horse == null && jockey != null) {
      list = raceDAO.findEntriesForJockey(jockey);
    } else if (jockey == null && horse != null) {
      list = raceDAO.findEntriesForHorse(horse);
    } else if (jockey != null && horse != null) {
      list = raceDAO.findEntriesForHorseAndJockey(horse, jockey);
    } else {
      throw new IllegalArgumentException();
    }
    //Count occurrences of every rank
    return list.stream()
        .collect(HashMap<Integer, Integer>::new,
            (map, entry) -> {
              if (!map.containsKey(entry.getRank())) {
                map.put(entry.getRank(), 1);
              } else {
                map.put(entry.getRank(), map.get(entry.getRank()) + 1);
              }
            },
            HashMap<Integer, Integer>::putAll);
  }
}
