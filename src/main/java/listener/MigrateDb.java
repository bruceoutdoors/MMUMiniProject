package listener;


import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.flywaydb.core.Flyway;

/**
 *
 * @author bruceoutdoors
 */
@SuppressWarnings("serial")
public class MigrateDb implements ServletContextListener {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/mmuminiproject";

//Run this before web application is started
    @Override
    public void contextInitialized(ServletContextEvent arg0) {
        Flyway flyway = new Flyway();
        flyway.setDataSource(DB_URL, "root", null);
        flyway.migrate();
    }

    @Override
    public void contextDestroyed(ServletContextEvent arg0) {

    }

}
