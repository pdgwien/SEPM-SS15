package sepm.ss15.e1227085.domain;

import java.util.List;

/**
 * Created by Patrick Grosslicht <e1227085@student.tuwien.ac.at>.
 */
public class Race {
    private long id;
    private List<RaceEntry> entries;

    /**
     * Instantiates a new Race with a known ID.
     *
     * @param entries the entries
     */
    public Race(long id, List<RaceEntry> entries) {
        this.id = id;
        this.entries = entries;
    }

    /**
     * Instantiates a new Race.
     *
     * @param entries the entries
     */
    public Race(List<RaceEntry> entries) {
        this.entries = entries;
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
     * Gets entries.
     *
     * @return the entries
     */
    public List<RaceEntry> getEntries() {
        return entries;
    }

    /**
     * Sets entries.
     *
     * @param entries the entries
     */
    public void setEntries(List<RaceEntry> entries) {
        this.entries = entries;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Race race = (Race) o;

        if (id != race.id) return false;
        if (!entries.equals(race.entries)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + entries.hashCode();
        return result;
    }
}
