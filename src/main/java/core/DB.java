package core;

import java.sql.SQLException;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

/**
 *
 * @author Lee Zhen Yong (bruceoutdoors@gmail.com)
 */
public class DB {

    private static String PERSISTENCE_UNIT_NAME;
    private static DB instance = null;

    private final EntityManager em;

    public interface Transaction {

        public void execute() throws Exception;
    }

    private DB() throws SQLException {
        // Test whether we are in OpenShift or Localhost
        if (System.getenv("OPENSHIFT_APP_NAME") == null) {
            PERSISTENCE_UNIT_NAME = "Localhost";
        } else {
            PERSISTENCE_UNIT_NAME = "OpenShift";
        }

        em = Persistence
                .createEntityManagerFactory(PERSISTENCE_UNIT_NAME)
                .createEntityManager();
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

    public Query createQuery(String q) {
        return em.createQuery(q);
    }

    public Query createNamedQuery(String q) {
        return em.createNamedQuery(q);
    }
    
    public <T> T find(java.lang.Class<T> entityClass, java.lang.Object primaryKey) {
        return em.find(entityClass, primaryKey);
    }

    public void execTransaction(Transaction t) throws Exception {
        try {
            em.getTransaction().begin();
            t.execute();
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new Exception(ex);
        } 
    }

    public void persist(final Object entity) throws Exception {
        execTransaction(new Transaction() {
            @Override
            public void execute() throws Exception {
                em.persist(entity);
            }
        });
    }

    public void remove(final Object entity) throws Exception {
        execTransaction(new Transaction() {
            @Override
            public void execute() throws Exception {
                em.remove(entity);
            }
        });
    }
    
    public EntityManager getEntityManager() {
        return em;
    }

}
