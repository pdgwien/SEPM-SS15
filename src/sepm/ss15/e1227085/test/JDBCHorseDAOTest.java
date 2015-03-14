package sepm.ss15.e1227085.test;

import org.junit.After;
import org.junit.Before;
import sepm.ss15.e1227085.dao.DataSource;
import sepm.ss15.e1227085.dao.IHorseDAO;
import sepm.ss15.e1227085.dao.impl.JDBCHorseDAO;

import java.sql.SQLException;


/**
 * Created by Patrick Grosslicht <e1227085@student.tuwien.ac.at>.
 */
public class JDBCHorseDAOTest extends AbstractJDBCHorseDAOTest {
    private DataSource dataSource;

    @Before
    public void setUp() throws SQLException {
        IHorseDAO horseDAO = new JDBCHorseDAO();
        setJDBCHorseDAO(horseDAO);
        dataSource.getConnection().setAutoCommit(false);
    }

    @After
    public void tearDown() throws SQLException {
        dataSource.getConnection().rollback();
    }
}
