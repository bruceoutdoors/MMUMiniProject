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

    private static final String PERSISTENCE_UNIT_NAME = "MMUMiniProjectMavenU";
    private static DB instance = null;
    
    private final EntityManager em;
    
    private DB() throws SQLException {
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

    public void execTransaction(Transaction t) throws PersistenceException {
        em.getTransaction().begin();
        t.execute();
        em.getTransaction().commit();
    }
    
    public void persist(final Object entity) throws PersistenceException {
        execTransaction(new Transaction() {
            @Override
            public void execute() throws PersistenceException {
                em.persist(entity);
            }
        });
    }
    
    public void remove(final Object entity) throws PersistenceException {
        execTransaction(new Transaction() {
            @Override
            public void execute() throws PersistenceException {
                em.remove(entity);
            }
        });
    }

}
