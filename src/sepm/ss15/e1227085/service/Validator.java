package sepm.ss15.e1227085.service;

import sepm.ss15.e1227085.domain.Horse;
import sepm.ss15.e1227085.domain.Jockey;
import sepm.ss15.e1227085.domain.RaceEntry;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Patrick Grosslicht <e1227085@student.tuwien.ac.at>.
 */
public class Validator {
  public Validator() {
  }

  /**
   * Checks if all race entries in list are valid.
   *
   * @param raceEntries the race entries
   * @return the boolean
   */
  public boolean areValidRaceEntries(List<RaceEntry> raceEntries) {
    List<Horse> horses = raceEntries.stream()
        .map(RaceEntry::getHorse)
        .collect(Collectors.toList());
    List<Jockey> jockeys = raceEntries.stream()
        .map(RaceEntry::getJockey)
        .collect(Collectors.toList());
    return isListUnique(horses) && isListUnique(jockeys);
  }

  /**
   * Checks if string is a valid speed.
   *
   * @param speed the min speed to check
   * @return the boolean
   */
  public boolean isValidSpeed(String speed) {
    return isStringValidDouble(speed)
        && Double.parseDouble(speed) >= 50.0
        && Double.parseDouble(speed) <= 100.0;
  }

  /**
   * Checks if string is valid talent.
   *
   * @param talent the talent
   * @return the boolean
   */
  public boolean isValidTalent(String talent) {
    return isStringValidDouble(talent);
  }

  /**
   * Checks if string is valid age.
   *
   * @param age the age
   * @return the boolean
   */
  public boolean isValidAge(String age) {
    return isStringValidInteger(age)
        && Integer.parseInt(age) >= 0;
  }

  /**
   * Checks if string is a valid name.
   *
   * @param name the name
   * @return the boolean
   */
  public boolean isValidName(String name) {
    return isNonEmptyString(name);
  }

  public boolean isListUnique(List<?> list) {
    Set<?> inputSet = new HashSet<>(list);
    return inputSet.size() == list.size();
  }

  /**
   * Checks if string is a valid double.
   *
   * @param string the string
   * @return the boolean
   */
  public boolean isStringValidDouble(String string) {
    if (!isNonEmptyString(string)) {
      return false;
    }
    try {
      Double.parseDouble(string);
    } catch (NumberFormatException e) {
      return false;
    }
    return true;
  }

  /**
   * Checks if string valid integer.
   *
   * @param string the string
   * @return the boolean
   */
  public boolean isStringValidInteger(String string) {
    if (!isNonEmptyString(string)) {
      return false;
    }
    try {
      Integer.parseInt(string);
    } catch (NumberFormatException e) {
      return false;
    }
    return true;
  }

  /**
   * Checks if string is not empty.
   *
   * @param string the string
   * @return the boolean
   */
  public boolean isNonEmptyString(String string) {
    return !(string == null || string.length() == 0);
  }
}
