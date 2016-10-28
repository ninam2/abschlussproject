package axelspringer.models;

import org.springframework.beans.propertyeditors.StringArrayPropertyEditor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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


    public List getAllVertragsarten() {

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
        if (entityManager.contains(vertrag))
            entityManager.remove(vertrag);
        else
            entityManager.remove(entityManager.merge(vertrag));
    }

    public Vertrag getVertragById(Long azubi_id, String vertragsart) {
        System.out.println("a");
        Vertrag vertrag = entityManager.find(Vertrag.class, new VertragId(vertragsart, azubi_id));

        System.out.println(vertrag.getVertragsvalue());
        return vertrag;
    }

}
