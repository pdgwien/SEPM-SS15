package sepm.ss15.e1227085.domain;

/**
 * Created by Patrick Grosslicht <e1227085@student.tuwien.ac.at>.
 */
public class Horse {
    private long id = -1;
    private String name = "";
    private String imagePath = "";
    private double minSpeed = 0.00;
    private double maxSpeed = 0.00;
    private boolean isDeleted = false;

    /**
     * Instantiates a new Horse without an id.
     *
     * @param name the name
     * @param imagePath the image path
     * @param minSpeed the min speed
     * @param maxSpeed the max speed
     */
    public Horse(String name, String imagePath, double minSpeed, double maxSpeed, boolean isDeleted) {
        this.name = name;
        this.imagePath = imagePath;
        this.minSpeed = minSpeed;
        this.maxSpeed = maxSpeed;
        this.isDeleted = isDeleted;
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
        this.id = id;
        this.name = name;
        this.imagePath = imagePath;
        this.minSpeed = minSpeed;
        this.maxSpeed = maxSpeed;
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

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Horse horse = (Horse) o;

        if (id != horse.id) return false;
        if (isDeleted != horse.isDeleted) return false;
        if (Double.compare(horse.maxSpeed, maxSpeed) != 0) return false;
        if (Double.compare(horse.minSpeed, minSpeed) != 0) return false;
        if (!imagePath.equals(horse.imagePath)) return false;
        if (!name.equals(horse.name)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) (id ^ (id >>> 32));
        result = 31 * result + name.hashCode();
        result = 31 * result + imagePath.hashCode();
        temp = Double.doubleToLongBits(minSpeed);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(maxSpeed);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (isDeleted ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Horse{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", imagePath='" + imagePath + '\'' +
                ", minSpeed=" + minSpeed +
                ", maxSpeed=" + maxSpeed +
                ", isDeleted=" + isDeleted +
                '}';
    }
}
