package axelspringer.models;

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

    // An EntityManager will be automatically injected from entityManagerFactory
    // setup on DatabaseConfig class.
    @PersistenceContext
    private EntityManager entityManager;


    public void create(Azubi Azubi) {
        entityManager.persist(Azubi);
    }


    public void delete(Azubi Azubi) {
        if (entityManager.contains(Azubi))
            entityManager.remove(Azubi);
        else
            entityManager.remove(entityManager.merge(Azubi));
    }


    @SuppressWarnings("unchecked")
    public List<Azubi> getAll() {

        return entityManager.createQuery("from Azubi").getResultList();
    }

    public List getAllByVertrag(String vertrag) {

        return entityManager.createQuery(
                "from Azubi " +
                        "where id in" +
                        "(select azubi_id " +
                        "from Vertrag " +
                        "where vertragsart = 'test')")
                .getResultList();
    }

    public List getAllByYear(int ausbildungsstart) {

        return entityManager.createQuery(
                "from Azubi where ausbildungsstart = :ausbildungsstart")
                .setParameter("ausbildungsstart", ausbildungsstart)
                .getResultList();
    }

    public List getAllByJob(String beruf) {

        return entityManager.createQuery(
                "from Azubi where beruf = :beruf")
                .setParameter("beruf", beruf)
                .getResultList();
    }

    public List getAllJobs() {

        return entityManager.createQuery("" +
                "select distinct beruf " +
                "from Azubi").getResultList();
    }

    public List getAllYears() {
        return entityManager.createQuery("" +
                "select distinct ausbildungsstart " +
                "from Azubi").getResultList();
    }

    public Azubi getByEmail(String email) {
        return (Azubi) entityManager.createQuery(
                "from Azubi where email = :email")
                .setParameter("email", email)
                .getSingleResult();
    }

    public Azubi getById(long id) {
        return entityManager.find(Azubi.class, id);
    }

    /**
     * Update the passed Azubi in the database.
     */
    public void update(Azubi Azubi) {
        entityManager.merge(Azubi);
    }

}
