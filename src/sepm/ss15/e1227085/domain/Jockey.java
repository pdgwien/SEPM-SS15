package sepm.ss15.e1227085.domain;

/**
 * Created by Patrick Grosslicht <e1227085@student.tuwien.ac.at>.
 */
public class Jockey {
    private long id = -1;
    private String name = "";
    private double talent = 0.00;
    private int age = 0;
    private boolean isDeleted = false;

    /**
     * Instantiates a new Jockey.
     *
     * @param name the name
     * @param talent the talent
     * @param age the age
     * @param isDeleted the is deleted
     */
    public Jockey(String name, double talent, int age, boolean isDeleted) {
        this.name = name;
        this.talent = talent;
        this.age = age;
        this.isDeleted = isDeleted;
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
        this.id = id;
        this.name = name;
        this.talent = talent;
        this.age = age;
        this.isDeleted = isDeleted;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
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
     * Gets age.
     *
     * @return the age
     */
    public int getAge() {
        return age;
    }

    /**
     * Sets age.
     *
     * @param age the age
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Is deleted.
     *
     * @return the boolean
     */
    public boolean isDeleted() {
        return isDeleted;
    }

    /**
     * Sets deleted.
     *
     * @param isDeleted the is deleted
     */
    public void setDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Jockey jockey = (Jockey) o;

        if (age != jockey.age) return false;
        if (id != jockey.id) return false;
        if (isDeleted != jockey.isDeleted) return false;
        if (Double.compare(jockey.talent, talent) != 0) return false;
        if (!name.equals(jockey.name)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) (id ^ (id >>> 32));
        result = 31 * result + name.hashCode();
        temp = Double.doubleToLongBits(talent);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + age;
        result = 31 * result + (isDeleted ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Jockey{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", talent=" + talent +
                ", age=" + age +
                ", isDeleted=" + isDeleted +
                '}';
    }
}
