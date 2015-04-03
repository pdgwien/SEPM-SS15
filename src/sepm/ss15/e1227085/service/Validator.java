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
    if (string == null || string.length() == 0) {
      return false;
    }
    return true;
  }
}
