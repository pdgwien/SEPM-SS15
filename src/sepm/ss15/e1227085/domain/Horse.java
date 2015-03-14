package sepm.ss15.e1227085.domain;

/**
 * Created by Patrick Grosslicht <e1227085@student.tuwien.ac.at>.
 */
public class Horse {
    private final String name;
    private String imagePath = "";
    private double minSpeed = 0.00;
    private double maxSpeed = 0.00;

    /**
     * Instantiates a new Horse.
     *
     * @param name      the name
     * @param imagePath the image path
     * @param minSpeed  the min speed
     * @param maxSpeed  the max speed
     */
    public Horse(String name, String imagePath, double minSpeed, double maxSpeed) {
        this.name = name;
        this.imagePath = imagePath;
        this.minSpeed = minSpeed;
        this.maxSpeed = maxSpeed;
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
     * Gets image path.
     *
     * @return the image path
     */
    public String getImagePath() {
        return imagePath;
    }

    /**
     * Sets image path.
     *
     * @param imagePath the image path
     */
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    /**
     * Gets min speed.
     *
     * @return the min speed
     */
    public double getMinSpeed() {
        return minSpeed;
    }

    /**
     * Sets min speed.
     *
     * @param minSpeed the min speed
     */
    public void setMinSpeed(double minSpeed) {
        this.minSpeed = minSpeed;
    }

    /**
     * Gets max speed.
     *
     * @return the max speed
     */
    public double getMaxSpeed() {
        return maxSpeed;
    }

    /**
     * Sets max speed.
     *
     * @param maxSpeed the max speed
     */
    public void setMaxSpeed(double maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     *
     * @param obj the reference object with which to compare.
     * @return {@code true} if this object is the same as the obj
     * argument; {@code false} otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        Horse otherHorse = (Horse) obj;
        return obj != null && this.getName().equals(otherHorse.getName()) && this.getImagePath().equals(otherHorse.getImagePath()) &&
                this.getMinSpeed() == otherHorse.getMinSpeed() && this.getMaxSpeed() == otherHorse.getMaxSpeed();
    }
}
