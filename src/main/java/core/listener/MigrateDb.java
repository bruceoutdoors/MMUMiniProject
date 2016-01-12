package core.listener;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.flywaydb.core.Flyway;

/**
 *
 * @author bruceoutdoors
 */
@SuppressWarnings("serial")
public class MigrateDb implements ServletContextListener {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/";
    private static final String DB_NAME = "mmuminiproject";
    private static final String DB_USER = "root";
    private static final String DB_PWD = null;

//Run this before web application is started
    @Override
    public void contextInitialized(ServletContextEvent arg0) {
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            DriverManager.getConnection(DB_URL, DB_USER, DB_PWD)
                    .createStatement()
                    .executeUpdate("CREATE DATABASE IF NOT EXISTS " + DB_NAME);
        } catch (SQLException ex) {
            Logger.getLogger("SQL EXCEPTION: " + MigrateDb.class.getName()).log(Level.SEVERE, null, ex);
        }

        Flyway flyway = new Flyway();
        flyway.setDataSource(DB_URL + DB_NAME, DB_USER, DB_PWD);
        flyway.migrate();
    }

    @Override
    public void contextDestroyed(ServletContextEvent arg0) {

    }

}
