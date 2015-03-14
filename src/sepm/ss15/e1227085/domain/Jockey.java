package sepm.ss15.e1227085.domain;

/**
 * Created by Patrick Grosslicht <e1227085@student.tuwien.ac.at>.
 */
public class Jockey {
    private final String name;
    private double talent = 0.00;
    private int age = 0;

    /**
     * Instantiates a new Jockey with a random UUID.
     *
     * @param name   the name
     * @param talent the talent
     * @param age    the age
     */
    public Jockey(String name, double talent, int age) {
        this.name = name;
        this.talent = talent;
        this.age = age;
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
}
