package core.listener;

import java.sql.DriverManager;
import java.sql.SQLException;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.flywaydb.core.Flyway;

/**
 *
 * @author bruceoutdoors
 */
@SuppressWarnings("serial")
public class MigrateDb implements ServletContextListener {
	//Run this before web application is started
    @Override
    public void contextInitialized(ServletContextEvent arg0) {
		String DB_NAME = System.getenv("OPENSHIFT_APP_NAME");
		String DB_USER = System.getenv("OPENSHIFT_MYSQL_DB_USERNAME");
		String DB_PWD  = System.getenv("OPENSHIFT_MYSQL_DB_PASSWORD");
		String DB_URL  = "jdbc:mysql://"  
							+ System.getenv("OPENSHIFT_MYSQL_DB_HOST") 
							+ ":"  + System.getenv("OPENSHIFT_MYSQL_DB_PORT") 
							+ "/" + DB_NAME;
			
        Flyway flyway = new Flyway();
        flyway.setDataSource(DB_URL, DB_USER, DB_PWD);
        flyway.migrate();
    }

    @Override
    public void contextDestroyed(ServletContextEvent arg0) {

    }

}
