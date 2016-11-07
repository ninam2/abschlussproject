package axelspringer.models;

import org.springframework.core.env.SystemEnvironmentPropertySource;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Locale;

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
public class VertragDao {

    // An EntityManager will be automatically injected from entityManagerFactory
    // setup on DatabaseConfig class.
    @PersistenceContext
    private EntityManager entityManager;

    @SuppressWarnings("unchecked")
    public List<Vertrag> getAllVertraege() {

        return entityManager.createQuery(
                "from Vertrag")
                .getResultList();
    }


    public List<String> getAllVertragsarten() {

        return entityManager.createQuery(
                "select distinct vertragsart " +
                        "from Vertrag")
                .getResultList();
    }

    public List<Vertrag> getAllVertragsartenByAzubiID(Long azubi_id) {
        return entityManager.createQuery(
                "from Vertrag where azubi_id = :azubi_id")
                .setParameter("azubi_id", azubi_id)
                .getResultList();
    }


    /**
     * Update the passed Vertrag in the database.
     */
    public void updateVertraege(Vertrag vertrag) {
        entityManager.merge(vertrag);
    }


    public void createVertraege(Vertrag vertrag) {
        entityManager.persist(vertrag);
    }

    public void deleteVertragById(Vertrag vertrag) {
        Long azubi_id = vertrag.getAzubi_id();
        List<Vertrag> vertragList =
                entityManager.createQuery(
                        "FROM Vertrag " +
                                "where azubi_id = :azubi_id")
                        .setParameter("azubi_id", azubi_id)
                        .getResultList();
        System.out.println(vertragList);
        for (Vertrag curvertrag : vertragList) {
            vertrag.setVertragsart(curvertrag.getVertragsart());

            entityManager.remove(entityManager.merge(vertrag));
        }
    }

    public Vertrag getVertragById(Long azubi_id, String vertragsart) {
        return (Vertrag) entityManager.createQuery(
                "from Vertrag " +
                        "where azubi_id = :azubi_id " +
                        "and vertragsart = :vertragsart")
                .setParameter("azubi_id", azubi_id)
                .setParameter("vertragsart", vertragsart)
                .getSingleResult();
    }


    public List getAllAzubiIds() {
        return entityManager.createQuery("" +
                "select distinct id " +
                "from Azubi").getResultList();

    }
}
