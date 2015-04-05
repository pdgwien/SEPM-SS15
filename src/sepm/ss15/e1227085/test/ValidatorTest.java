package sepm.ss15.e1227085.test;

import org.junit.Test;
import sepm.ss15.e1227085.service.Validator;


/**
 * Created by Patrick Grosslicht <e1227085@student.tuwien.ac.at>.
 */
public class ValidatorTest {
  private Validator validator = new Validator();

  @Test
  public void shouldValidateSpeedsCorrectly() {
    String invalidSpeed = "49.50";
    String invalidSpeed2 = "111.11";
    String invalidSpeed3 = "";
    String invalidSpeed4 = null;
    String validSpeed = "58.49";
    org.junit.Assert.assertFalse(validator.isValidSpeed(invalidSpeed));
    org.junit.Assert.assertFalse(validator.isValidSpeed(invalidSpeed2));
    org.junit.Assert.assertFalse(validator.isValidSpeed(invalidSpeed3));
    org.junit.Assert.assertFalse(validator.isValidSpeed(invalidSpeed4));
    org.junit.Assert.assertTrue(validator.isValidSpeed(validSpeed));
  }

  @Test
  public void ageShouldNotValidateIfNegative() {
    String invalidAge = "-100";
    String validAge = "50";
    org.junit.Assert.assertFalse(validator.isValidAge(invalidAge));
    org.junit.Assert.assertTrue(validator.isValidAge(validAge));
  }
}
