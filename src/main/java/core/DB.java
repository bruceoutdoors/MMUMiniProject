package core;

import java.sql.SQLException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Lee Zhen Yong (bruceoutdoors@gmail.com)
 */
public class DB {

    private static String PERSISTENCE_UNIT_NAME;
    private static DB instance = null;
    private final EntityManagerFactory emf;

    private DB() throws SQLException {
        // Test whether we are in OpenShift or Localhost
        if (System.getenv("OPENSHIFT_APP_NAME") == null) {
            PERSISTENCE_UNIT_NAME = "Localhost";
        } else {
            PERSISTENCE_UNIT_NAME = "OpenShift";
        }

        emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    }

    public static DB getInstance() {
        if (instance == null) {
            try {
                instance = new DB();
            } catch (SQLException ex) {
                System.err.println("Database Error: " + ex.toString());
                System.exit(1);
            }
        }

        return instance;
    }
    
    public EntityManager createEntityManager() {
        return emf.createEntityManager();
    }
}
