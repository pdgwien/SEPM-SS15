package sepm.ss15.e1227085.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sepm.ss15.e1227085.dao.DataSource;
import sepm.ss15.e1227085.dao.IHorseDAO;
import sepm.ss15.e1227085.domain.Horse;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Patrick Grosslicht <e1227085@student.tuwien.ac.at>.
 */
public class JDBCHorseDAO implements IHorseDAO {
    private static final Logger LOGGER = LogManager.getLogger();
    private PreparedStatement createStmt;
    private PreparedStatement findAllStmt;

    public JDBCHorseDAO() {
        try {
            Connection conn = DataSource.getConnection();
            createStmt = conn.prepareStatement("INSERT INTO Horse (name, imgPath, minSpeed, maxSpeed) VALUES (?, ?, ?, ?)");
            findAllStmt = conn.prepareStatement("SELECT * FROM Horse");
        } catch (SQLException e) {
            LOGGER.error(e);
            //TODO: PersistenceException
            throw new RuntimeException();
        }
    }

    /**
     * Create a horse in the database. Returns the object if successful, null otherwise.
     *
     * @param horse the horse about to be saved
     * @return the horse
     */
    @Override
    public Horse create(Horse horse) {
        if (horse == null) {
            throw new IllegalArgumentException();
        }
        try {
            createStmt.setString(1, horse.getName());
            createStmt.setString(2, horse.getImagePath());
            createStmt.setDouble(3, horse.getMinSpeed());
            createStmt.setDouble(4, horse.getMaxSpeed());
            createStmt.executeUpdate();
            return horse;
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return null;
    }

    /**
     * Update a horse in the database.
     *
     * @param horse the horse
     * @return the horse
     */
    @Override
    public Horse update(Horse horse) {
        return null;
    }

    /**
     * Delete a horse.
     *
     * @param horse the horse
     * @return the horse
     */
    @Override
    public Horse delete(Horse horse) {
        return null;
    }

    /**
     * Search all horses.
     *
     * @return the list of all horses
     */
    @Override
    public List<Horse> findAll() {
        LOGGER.entry();
        ArrayList<Horse> horseList = null;
        try {
            ResultSet rs = findAllStmt.executeQuery();
            horseList = new ArrayList<Horse>();
            Horse tmpHorse = null;
            while (rs.next()) {
                tmpHorse = new Horse(rs.getString(1), rs.getString(2), rs.getDouble(3), rs.getDouble(4));
                horseList.add(tmpHorse);
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return horseList;
    }

    /**
     * Search horses by name.
     *
     * @param name the name
     * @return the list of all horses having the specified value
     */
    @Override
    public List<Horse> findByName(String name) {
        return null;
    }
}
