package sepm.ss15.e1227085.test;

import org.junit.After;
import org.junit.Before;
import sepm.ss15.e1227085.dao.DataSource;
import sepm.ss15.e1227085.dao.IJockeyDAO;
import sepm.ss15.e1227085.dao.impl.JDBCJockeyDAO;

import java.sql.SQLException;


/**
 * Created by Patrick Grosslicht <e1227085@student.tuwien.ac.at>.
 */
public class JDBCJockeyDAOTest extends AbstractJDBCJockeyDAOTest {
    private DataSource dataSource;

    @Before
    public void setUp() throws SQLException {
        IJockeyDAO jockeyDAO = new JDBCJockeyDAO();
        setJDBCJockeyDAO(jockeyDAO);
        dataSource.getConnection().setAutoCommit(false);
    }

    @After
    public void tearDown() throws SQLException {
        dataSource.getConnection().rollback();
    }
}
