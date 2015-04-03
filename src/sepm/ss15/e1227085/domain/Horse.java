package sepm.ss15.e1227085.domain;

import javafx.beans.property.*;

/**
 * Created by Patrick Grosslicht <e1227085@student.tuwien.ac.at>.
 */
public class Horse {
  private LongProperty id = new SimpleLongProperty(-1);
  private StringProperty name = new SimpleStringProperty("");
  private StringProperty imagePath = new SimpleStringProperty("");
  private DoubleProperty minSpeed = new SimpleDoubleProperty(0.00);
  private DoubleProperty maxSpeed = new SimpleDoubleProperty(0.00);
  private BooleanProperty isDeleted = new SimpleBooleanProperty(false);

  /**
   * Instantiates a new Horse without an id.
   *
   * @param name      the name
   * @param imagePath the image path
   * @param minSpeed  the min speed
   * @param maxSpeed  the max speed
   */
  public Horse(String name, String imagePath, double minSpeed, double maxSpeed, boolean isDeleted) {
    this.name = new SimpleStringProperty(name);
    this.imagePath = new SimpleStringProperty(imagePath);
    this.minSpeed = new SimpleDoubleProperty(minSpeed);
    this.maxSpeed = new SimpleDoubleProperty(maxSpeed);
    this.isDeleted = new SimpleBooleanProperty(isDeleted);
  }

  /**
   * Instantiates a new Horse with a known ID.
   *
   * @param id        the id
   * @param name      the name
   * @param imagePath the image path
   * @param minSpeed  the min speed
   * @param maxSpeed  the max speed
   */
  public Horse(long id, String name, String imagePath, double minSpeed, double maxSpeed, boolean isDeleted) {
    this.id = new SimpleLongProperty(id);
    this.name = new SimpleStringProperty(name);
    this.imagePath = new SimpleStringProperty(imagePath);
    this.minSpeed = new SimpleDoubleProperty(minSpeed);
    this.maxSpeed = new SimpleDoubleProperty(maxSpeed);
    this.isDeleted = new SimpleBooleanProperty(isDeleted);
  }

  /**
   * Instantiates a new Horse without anything.
   */
  public Horse() {
  }

  /**
   * Gets id.
   *
   * @return the id
   */
  public long getId() {
    return id.get();
  }

  /**
   * Sets id.
   *
   * @param id the id
   */
  public void setId(long id) {
    this.id.set(id);
  }

  /**
   * Gets name.
   *
   * @return the name
   */
  public String getName() {
    return name.get();
  }

  /**
   * Sets name.
   *
   * @param name the name
   */
  public void setName(String name) {
    this.name.set(name);
  }

  /**
   * Gets image path.
   *
   * @return the image path
   */
  public String getImagePath() {
    return imagePath.get();
  }

  /**
   * Sets image path.
   *
   * @param imagePath the image path
   */
  public void setImagePath(String imagePath) {
    this.imagePath.set(imagePath);
  }

  /**
   * Gets min speed.
   *
   * @return the min speed
   */
  public double getMinSpeed() {
    return minSpeed.get();
  }

  /**
   * Sets min speed.
   *
   * @param minSpeed the min speed
   */
  public void setMinSpeed(double minSpeed) {
    this.minSpeed.set(minSpeed);
  }

  /**
   * Gets max speed.
   *
   * @return the max speed
   */
  public double getMaxSpeed() {
    return maxSpeed.get();
  }

  /**
   * Sets max speed.
   *
   * @param maxSpeed the max speed
   */
  public void setMaxSpeed(double maxSpeed) {
    this.maxSpeed.set(maxSpeed);
  }

  public boolean isDeleted() {
    return isDeleted.get();
  }

  public void setDeleted(boolean isDeleted) {
    this.isDeleted.set(isDeleted);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Horse horse = (Horse) o;

    if (id.get() != horse.id.get()) return false;
    if (isDeleted.get() != horse.isDeleted.get()) return false;
    if (Double.compare(horse.maxSpeed.get(), maxSpeed.get()) != 0) return false;
    if (Double.compare(horse.minSpeed.get(), minSpeed.get()) != 0) return false;
    if (!imagePath.get().equals(horse.imagePath.get())) return false;
    if (!name.get().equals(horse.name.get())) return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result;
    long temp;
    result = (int) (id.get() ^ (id.get() >>> 32));
    result = 31 * result + name.get().hashCode();
    result = 31 * result + imagePath.get().hashCode();
    temp = Double.doubleToLongBits(minSpeed.get());
    result = 31 * result + (int) (temp ^ (temp >>> 32));
    temp = Double.doubleToLongBits(maxSpeed.get());
    result = 31 * result + (int) (temp ^ (temp >>> 32));
    result = 31 * result + (isDeleted.get() ? 1 : 0);
    return result;
  }

  public LongProperty idProperty() {
    return id;
  }

  public StringProperty nameProperty() {
    return name;
  }

  public StringProperty imagePathProperty() {
    return imagePath;
  }

  public DoubleProperty minSpeedProperty() {
    return minSpeed;
  }

  public DoubleProperty maxSpeedProperty() {
    return maxSpeed;
  }

  public boolean getIsDeleted() {
    return isDeleted.get();
  }

  public void setIsDeleted(boolean isDeleted) {
    this.isDeleted.set(isDeleted);
  }

  public BooleanProperty isDeletedProperty() {
    return isDeleted;
  }
}
