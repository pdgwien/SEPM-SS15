package sepm.ss15.e1227085.service.impl;

import sepm.ss15.e1227085.dao.IHorseDAO;
import sepm.ss15.e1227085.dao.impl.JDBCHorseDAO;
import sepm.ss15.e1227085.domain.Horse;
import sepm.ss15.e1227085.service.IHorseService;

import java.util.List;

/**
 * Created by Patrick Grosslicht <e1227085@student.tuwien.ac.at>.
 */
public class JDBCHorseService implements IHorseService {
  private IHorseDAO horseDAO = new JDBCHorseDAO();

  /**
   * Create a horse.
   *
   * @param horse the horse
   * @return the horse
   * @throws IllegalArgumentException if argument is not acceptable
   */
  @Override
  public Horse create(Horse horse) throws IllegalArgumentException {
    return horseDAO.create(horse);
  }

  /**
   * Delete a horse.
   *
   * @param horse the horse
   * @return the horse
   * @throws IllegalArgumentException if argument is not acceptable
   */
  @Override
  public Horse delete(Horse horse) throws IllegalArgumentException {
    return horseDAO.delete(horse);
  }

  /**
   * Update a horse.
   *
   * @param horse the horse
   * @return the horse
   * @throws IllegalArgumentException if argument is not acceptable
   */
  @Override
  public Horse update(Horse horse) throws IllegalArgumentException {
    return horseDAO.update(horse);
  }

  /**
   * Find all horses.
   *
   * @return the list
   */
  @Override
  public List<Horse> findAll() {
    return horseDAO.findAll();
  }

  /**
   * Find horses by name.
   *
   * @param name the name
   * @return the list
   */
  @Override
  public List<Horse> findByName(String name) {
    return horseDAO.findByName(name);
  }

  /**
   * Find one horse by id.
   *
   * @param id the id
   * @return the horse
   * @throws IllegalArgumentException if ID is not found
   */
  @Override
  public Horse findById(long id) throws IllegalArgumentException {
    return horseDAO.findById(id);
  }
}
