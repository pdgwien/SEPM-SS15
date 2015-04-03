package sepm.ss15.e1227085.domain;

import sepm.ss15.e1227085.util.Util;


/**
 * Created by Patrick Grosslicht <e1227085@student.tuwien.ac.at>.
 */
public class RaceEntry {
  private Horse horse;
  private Jockey jockey;
  private double talent;
  private double speed;
  private double luckyNumber;

  /**
   * Instantiates a new Race entry.
   *
   * @param horse       the horse
   * @param jockey      the jockey
   * @param talent      the talent
   * @param luckyNumber the lucky number
   * @param speed       the speed
   */
  public RaceEntry(Horse horse, Jockey jockey, double talent, double speed, double luckyNumber) {
    this.horse = horse;
    this.jockey = jockey;
    this.talent = talent;
    this.speed = speed;
    this.luckyNumber = luckyNumber;
  }

  /**
   * Instantiates a new Race entry with a known horse and jockey and populates the rest of the fields.
   *
   * @param horse  the horse
   * @param jockey the jockey
   */
  public RaceEntry(Horse horse, Jockey jockey) {
    this.horse = horse;
    this.jockey = jockey;
    this.luckyNumber = Util.randomDoubleBetween(0.95, 1.05);
    this.talent = 1 + (0.15 * (1 / Math.PI) * Math.atan(0.2 * jockey.getTalent()));
    this.speed = Util.randomDoubleBetween(horse.getMinSpeed(), horse.getMaxSpeed()) * this.talent * this.luckyNumber;
  }

  /**
   * Gets horse.
   *
   * @return the horse
   */
  public Horse getHorse() {
    return horse;
  }

  /**
   * Sets horse.
   *
   * @param horse the horse
   */
  public void setHorse(Horse horse) {
    this.horse = horse;
  }

  /**
   * Gets jockey.
   *
   * @return the jockey
   */
  public Jockey getJockey() {
    return jockey;
  }

  /**
   * Sets jockey.
   *
   * @param jockey the jockey
   */
  public void setJockey(Jockey jockey) {
    this.jockey = jockey;
  }

  /**
   * Gets talent.
   *
   * @return the talent
   */
  public double getTalent() {
    return talent;
  }

  /**
   * Sets talent.
   *
   * @param talent the talent
   */
  public void setTalent(double talent) {
    this.talent = talent;
  }

  /**
   * Gets lucky number.
   *
   * @return the lucky number
   */
  public double getLuckyNumber() {
    return luckyNumber;
  }

  /**
   * Sets lucky number.
   *
   * @param luckyNumber the lucky number
   */
  public void setLuckyNumber(double luckyNumber) {
    this.luckyNumber = luckyNumber;
  }

  /**
   * Gets speed.
   *
   * @return the speed
   */
  public double getSpeed() {
    return speed;
  }

  /**
   * Sets speed.
   *
   * @param speed the speed
   */
  public void setSpeed(double speed) {
    this.speed = speed;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    RaceEntry raceEntry = (RaceEntry) o;

    if (Double.compare(raceEntry.luckyNumber, luckyNumber) != 0) return false;
    if (Double.compare(raceEntry.speed, speed) != 0) return false;
    if (Double.compare(raceEntry.talent, talent) != 0) return false;
    if (!horse.equals(raceEntry.horse)) return false;
    if (!jockey.equals(raceEntry.jockey)) return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result;
    long temp;
    result = horse.hashCode();
    result = 31 * result + jockey.hashCode();
    temp = Double.doubleToLongBits(talent);
    result = 31 * result + (int) (temp ^ (temp >>> 32));
    temp = Double.doubleToLongBits(luckyNumber);
    result = 31 * result + (int) (temp ^ (temp >>> 32));
    temp = Double.doubleToLongBits(speed);
    result = 31 * result + (int) (temp ^ (temp >>> 32));
    return result;
  }

  @Override
  public String toString() {
    return "RaceEntry{" +
        "horse=" + horse +
        ", jockey=" + jockey +
        ", talent=" + talent +
        ", luckyNumber=" + luckyNumber +
        ", speed=" + speed +
        '}';
  }
}
