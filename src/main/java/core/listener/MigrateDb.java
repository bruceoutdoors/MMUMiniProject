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
    /** SET LOCAL DATABASE SETTINGS HERE: **/
    private static String DB_URL = "jdbc:mysql://localhost:3306/";
    private static String DB_NAME = "mmuminiproject";
    private static String DB_USER = "root";
    private static String DB_PWD = null;

//Run this before web application is started
    @Override
    public void contextInitialized(ServletContextEvent arg0) {
        // Test whether we are in OpenShift or Localhost
        if (System.getenv("OPENSHIFT_APP_NAME") == null) {
            // Localhost:
            try { // creates database if doesn't exist. Doesn't affect production.
                DriverManager.registerDriver(new com.mysql.jdbc.Driver());
                DriverManager.getConnection(DB_URL, DB_USER, DB_PWD)
                        .createStatement()
                        .executeUpdate("CREATE DATABASE IF NOT EXISTS " + DB_NAME);
            } catch (SQLException ex) {}
        } else {
            // OpenShift:
            DB_NAME = System.getenv("OPENSHIFT_APP_NAME");
            DB_USER = System.getenv("OPENSHIFT_MYSQL_DB_USERNAME");
            DB_PWD = System.getenv("OPENSHIFT_MYSQL_DB_PASSWORD");
            DB_URL = "jdbc:mysql://"
                    + System.getenv("OPENSHIFT_MYSQL_DB_HOST")
                    + ":" + System.getenv("OPENSHIFT_MYSQL_DB_PORT")
                    + "/";
        }

        Flyway flyway = new Flyway();
        flyway.setDataSource(DB_URL + DB_NAME, DB_USER, DB_PWD);
        flyway.migrate();
    }

    @Override
    public void contextDestroyed(ServletContextEvent arg0) {

    }

}
