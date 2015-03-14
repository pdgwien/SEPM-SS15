package sepm.ss15.e1227085.domain;

import java.util.List;

/**
 * Created by Patrick Grosslicht <e1227085@student.tuwien.ac.at>.
 */
public class Race {
    private int id;
    private List<RaceEntry> entries;

    /**
     * Instantiates a new Race.
     *
     * @param entries the entries
     */
    public Race(int id, List<RaceEntry> entries) {
        this.id = id;
        this.entries = entries;
    }


    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(int id) {
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
}
