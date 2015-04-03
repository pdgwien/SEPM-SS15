package sepm.ss15.e1227085.util;

import java.util.Random;

/**
 * Created by Patrick Grosslicht <e1227085@student.tuwien.ac.at>.
 */
public final class Util {

  private Util() {

  }

  /**
   * Random double in range.
   *
   * @param a minimum
   * @param b maximum
   * @return the double
   */
  public static double randomDoubleBetween(double a, double b) {
    if (a > b) {
      return randomDoubleBetween(b, a);
    }
    Random r = new Random();
    return a + (b - a) * r.nextDouble();
  }
}
