package sepm.ss15.e1227085.service;

/**
 * Created by Patrick Grosslicht <e1227085@student.tuwien.ac.at>.
 */
public class Validator {
  public Validator() {
  }

  /**
   * Checks if string is a valid speed.
   *
   * @param speed the min speed to check
   * @return the boolean
   */
  public boolean isValidSpeed(String speed) {
    return isStringValidDouble(speed)
        && Double.parseDouble(speed) >= 0.0;
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
   * Checks if string is not empty.
   *
   * @param string the string
   * @return the boolean
   */
  public boolean isNonEmptyString(String string) {
    if (string == null || string.length() == 0) {
      return false;
    }
    return true;
  }
}
