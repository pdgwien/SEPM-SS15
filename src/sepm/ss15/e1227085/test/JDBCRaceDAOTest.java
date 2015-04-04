package sepm.ss15.e1227085.test;

import org.junit.After;
import org.junit.Before;
import sepm.ss15.e1227085.dao.DataSource;
import sepm.ss15.e1227085.dao.IHorseDAO;
import sepm.ss15.e1227085.dao.IJockeyDAO;
import sepm.ss15.e1227085.dao.IRaceDAO;
import sepm.ss15.e1227085.dao.impl.JDBCHorseDAO;
import sepm.ss15.e1227085.dao.impl.JDBCJockeyDAO;
import sepm.ss15.e1227085.dao.impl.JDBCRaceDAO;

import java.sql.SQLException;


/**
 * Created by Patrick Grosslicht <e1227085@student.tuwien.ac.at>.
 */
public class JDBCRaceDAOTest extends AbstractJDBCRaceDAOTest {
  private DataSource dataSource;

  @Before
  public void setUp() throws SQLException {
    IRaceDAO raceDAO = new JDBCRaceDAO();
    setJDBCRaceDAO(raceDAO);
    IHorseDAO horseDAO = new JDBCHorseDAO();
    setJDBCHorseDAO(horseDAO);
    IJockeyDAO jockeyDAO = new JDBCJockeyDAO();
    setJDBCJockeyDAO(jockeyDAO);
    DataSource.getConnection().setAutoCommit(false);
  }

  @After
  public void tearDown() throws SQLException {
    DataSource.getConnection().rollback();
  }
}
