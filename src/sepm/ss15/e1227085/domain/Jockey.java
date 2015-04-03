package sepm.ss15.e1227085.domain;

import javafx.beans.property.*;

/**
 * Created by Patrick Grosslicht <e1227085@student.tuwien.ac.at>.
 */
public class Jockey {
  private LongProperty id = new SimpleLongProperty(-1);
  private StringProperty name = new SimpleStringProperty("");
  private DoubleProperty talent = new SimpleDoubleProperty(0.00);
  private IntegerProperty age = new SimpleIntegerProperty(0);
  private BooleanProperty isDeleted = new SimpleBooleanProperty(false);

  /**
   * Instantiates a new Jockey.
   *
   * @param name the name
   * @param talent the talent
   * @param age the age
   * @param isDeleted the is deleted
   */
  public Jockey(String name, double talent, int age, boolean isDeleted) {
    this.name = new SimpleStringProperty(name);
    this.talent = new SimpleDoubleProperty(talent);
    this.age = new SimpleIntegerProperty(age);
    this.isDeleted = new SimpleBooleanProperty(isDeleted);
  }

  /**
   * Instantiates a new Jockey with a known ID
   *
   * @param id the id
   * @param name the name
   * @param talent the talent
   * @param age the age
   * @param isDeleted the is deleted
   */
  public Jockey(long id, String name, double talent, int age, boolean isDeleted) {
    this.id = new SimpleLongProperty(id);
    this.name = new SimpleStringProperty(name);
    this.talent = new SimpleDoubleProperty(talent);
    this.age = new SimpleIntegerProperty(age);
    this.isDeleted = new SimpleBooleanProperty(isDeleted);
  }

  /**
   * Instantiates a new Jockey.
   */
  public Jockey() {

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
   * Gets Id property.
   *
   * @return the long property
   */
  public LongProperty idProperty() {
    return id;
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
   * Gets Name property.
   *
   * @return the string property
   */
  public StringProperty nameProperty() {
    return name;
  }

  /**
   * Gets talent.
   *
   * @return the talent
   */
  public double getTalent() {
    return talent.get();
  }

  /**
   * Sets talent.
   *
   * @param talent the talent
   */
  public void setTalent(double talent) {
    this.talent.set(talent);
  }

  /**
   * Gets Talent property.
   *
   * @return the double property
   */
  public DoubleProperty talentProperty() {
    return talent;
  }

  /**
   * Gets age.
   *
   * @return the age
   */
  public int getAge() {
    return age.get();
  }

  /**
   * Sets age.
   *
   * @param age the age
   */
  public void setAge(int age) {
    this.age.set(age);
  }

  /**
   * Gets Age property.
   *
   * @return the integer property
   */
  public IntegerProperty ageProperty() {
    return age;
  }

  /**
   * Gets isDeleted.
   *
   * @return the is deleted
   */
  public boolean isDeleted() {
    return isDeleted.get();
  }

  /**
   * Gets deleted property.
   *
   * @return the boolean property
   */
  public BooleanProperty isDeletedProperty() {
    return isDeleted;
  }

  /**
   * Sets isDeleted.
   *
   * @param isDeleted the is deleted
   */
  public void setIsDeleted(boolean isDeleted) {
    this.isDeleted.set(isDeleted);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Jockey jockey = (Jockey) o;

    if (age.get() != jockey.age.get()) return false;
    if (id.get() != jockey.id.get()) return false;
    if (isDeleted.get() != jockey.isDeleted.get()) return false;
    if (Double.compare(jockey.talent.get(), talent.get()) != 0) return false;
    if (!name.get().equals(jockey.name.get())) return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result;
    long temp;
    result = (int) (id.get() ^ (id.get() >>> 32));
    result = 31 * result + name.get().hashCode();
    temp = Double.doubleToLongBits(talent.get());
    result = 31 * result + (int) (temp ^ (temp >>> 32));
    result = 31 * result + age.get();
    result = 31 * result + (isDeleted.get() ? 1 : 0);
    return result;
  }
}
