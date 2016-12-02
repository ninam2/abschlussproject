package axelspringer.models;

import org.springframework.core.env.SystemEnvironmentPropertySource;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Locale;

/**
 * The type Vertrag dao.
 */
@Repository
@Transactional
public class VertragDao {

    // An EntityManager will be automatically injected from entityManagerFactory
    // setup on DatabaseConfig class.
    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Gets all vertraege.
     *
     * @return the all vertraege
     */
    @SuppressWarnings("unchecked")
    public List<Vertrag> getAllVertraege() {

        return entityManager.createQuery(
                "from Vertrag")
                .getResultList();
    }


    /**
     * Gets all vertragsarten.
     *
     * @return the all vertragsarten
     */
    public List<String> getAllVertragsarten() {

        return entityManager.createQuery(
                "select distinct vertragsart " +
                        "from Vertrag")
                .getResultList();
    }

    /**
     * Gets all vertragsarten by azubi id.
     *
     * @param azubi_id the azubi id
     * @return the all vertragsarten by azubi id
     */
    public List<Vertrag> getAllVertragsartenByAzubiID(Long azubi_id) {
        return entityManager.createQuery(
                "from Vertrag where azubi_id = :azubi_id")
                .setParameter("azubi_id", azubi_id)
                .getResultList();
    }


    /**
     * Update vertraege.
     *
     * @param vertrag the vertrag
     */
    public void updateVertraege(Vertrag vertrag) {
        entityManager.merge(vertrag);
    }


    /**
     * Create vertraege.
     *
     * @param vertrag the vertrag
     */
    public void createVertraege(Vertrag vertrag) {
        entityManager.persist(vertrag);
    }

    /**
     * Delete vertrag by id.
     *
     * @param vertrag the vertrag
     */
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

    /**
     * Gets vertrag by id.
     *
     * @param azubi_id    the azubi id
     * @param vertragsart the vertragsart
     * @return the vertrag by id
     */
    public Vertrag getVertragById(Long azubi_id, String vertragsart) {
        return (Vertrag) entityManager.createQuery(
                "from Vertrag " +
                        "where azubi_id = :azubi_id " +
                        "and vertragsart = :vertragsart")
                .setParameter("azubi_id", azubi_id)
                .setParameter("vertragsart", vertragsart)
                .getSingleResult();
    }


    /**
     * Gets all azubi ids.
     *
     * @return the all azubi ids
     */
    public List getAllAzubiIds() {
        return entityManager.createQuery("" +
                "select distinct id " +
                "from Azubi").getResultList();

    }
}
