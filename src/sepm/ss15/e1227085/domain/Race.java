package sepm.ss15.e1227085.domain;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Created by Patrick Grosslicht <e1227085@student.tuwien.ac.at>.
 */
public class Race {
  private final UUID id;
  private List<RaceEntry> entries;

  /**
   * Instantiates a new Race with a known ID.
   *
   * @param id      the UUID
   * @param entries the entries
   */
  public Race(UUID id, List<RaceEntry> entries) {
    this.id = id;
    this.entries = entries;
  }

  /**
   * Instantiates a new Race.
   *
   * @param entries the entries
   */
  public Race(List<RaceEntry> entries) {
    this.id = UUID.randomUUID();
    this.entries = entries;
  }

  /**
   * Gets id.
   *
   * @return the id
   */
  public UUID getId() {
    return id;
  }

  /**
   * Gets entries.
   *
   * @return the entries
   */
  public List<RaceEntry> getEntries() {
    return entries;
  }

  /**
   * Sets entries.
   *
   * @param entries the entries
   */
  public void setEntries(List<RaceEntry> entries) {
    this.entries = entries;
  }

  /**
   * Gets all names of the participating horses as concatenated string.
   *
   * @return the horses names
   */
  public String getHorsesNames() {
    return this.entries.stream()
        .map(RaceEntry::getHorse)
        .map(Horse::getName)
        .collect(Collectors.joining(", "));
  }

  /**
   * Gets all names of the participating jockeys as concatenated string.
   *
   * @return the jockeys names
   */
  public String getJockeysNames() {
    return this.entries.stream()
        .map(RaceEntry::getJockey)
        .map(Jockey::getName)
        .collect(Collectors.joining(", "));
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Race race = (Race) o;

    if (!entries.equals(race.entries)) return false;
    if (!id.equals(race.id)) return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = id.hashCode();
    result = 31 * result + entries.hashCode();
    return result;
  }

  @Override
  public String toString() {
    return "Race{" +
        "id=" + id +
        ", entries=" + entries +
        '}';
  }
}
