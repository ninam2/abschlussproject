package axelspringer.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

/**
 * This class is used to access data for the Azubi entity.
 * Repository annotation allows the component scanning support to find and
 * configure the DAO wihtout any XML configuration and also provide the Spring
 * exceptiom translation.
 * Since we've setup setPackagesToScan and transaction manager on
 * DatabaseConfig, any bean method annotated with Transactional will cause
 * Spring to magically call begin() and commit() at the start/end of the
 * method. If exception occurs it will also call rollback().
 */
@Repository
@Transactional
public class AzubiDao {

    // ------------------------
    // PUBLIC METHODS
    // ------------------------

    // An EntityManager will be automatically injected from entityManagerFactory
    // setup on DatabaseConfig class.
    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Save the Azubi in the database.
     */
    public void create(Azubi Azubi) {
        entityManager.persist(Azubi);
        return;
    }

    /**
     * Delete the Azubi from the database.
     */
    public void delete(Azubi Azubi) {
        if (entityManager.contains(Azubi))
            entityManager.remove(Azubi);
        else
            entityManager.remove(entityManager.merge(Azubi));
        return;
    }

    /**
     * Return all the Azubis stored in the database.
     */
    @SuppressWarnings("unchecked")
    public List<Azubi> getAll() {
        return entityManager.createQuery("from Azubi").getResultList();
    }

    /**
     * Return the Azubi having the passed email.
     */
    public Azubi getByEmail(String email) {
        return (Azubi) entityManager.createQuery(
                "from Azubi where email = :email")
                .setParameter("email", email)
                .getSingleResult();
    }

    /**
     * Return the Azubi having the passed id.
     */
    public Azubi getById(long id) {
        return entityManager.find(Azubi.class, id);
    }

    // ------------------------
    // PRIVATE FIELDS
    // ------------------------

    /**
     * Update the passed Azubi in the database.
     */
    public void update(Azubi Azubi) {
        entityManager.merge(Azubi);
        return;
    }

} // class AzubiDao
