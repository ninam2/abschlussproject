package axelspringer.models;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * The type Azubi dao.
 */
@Component
@Repository
@Transactional
public class AzubiDao {

    // An EntityManager will be automatically injected from entityManagerFactory
    // setup on DatabaseConfig class.
    @PersistenceContext
    private EntityManager entityManager;


    /**
     * Create.
     *
     * @param Azubi the azubi
     */
    public void create(Azubi Azubi) {
        entityManager.persist(Azubi);
    }


    /**
     * Delete.
     *
     * @param Azubi the azubi
     */
    public void delete(Azubi Azubi) {
        if (entityManager.contains(Azubi))
            entityManager.remove(Azubi);
        else
            entityManager.remove(entityManager.merge(Azubi));
    }


    /**
     * Gets all.
     *
     * @return the all
     */
    @SuppressWarnings("unchecked")
    public List getAll() {

        return entityManager.createQuery("from Azubi").getResultList();
    }

    /**
     * Gets all by vertrag.
     *
     * @param vertragsart the vertragsart
     * @return the all by vertrag
     */
    public List getAllByVertrag(String vertragsart) {

        return entityManager.createQuery(
                "from Azubi " +
                        "where id in" +
                        "(select azubi_id " +
                        "from Vertrag " +
                        "where vertragsart = :vertragsart)")
                .setParameter("vertragsart", vertragsart)
                .getResultList();
    }

    /**
     * Gets all by vertragsart and value.
     *
     * @param vertragsart   the vertragsart
     * @param vertragsvalue the vertragsvalue
     * @return the all by vertragsart and value
     */
    public List getAllByVertragsartAndValue(String vertragsart, String vertragsvalue) {

        return entityManager.createQuery(
                "from Azubi " +
                        "where id in" +
                        "(select azubi_id " +
                        "from Vertrag " +
                        "where vertragsart = :vertragsart " +
                        "and vertragsvalue = :vertragsvalue)")
                .setParameter("vertragsart", vertragsart)
                .setParameter("vertragsvalue", vertragsvalue)
                .getResultList();
    }

    /**
     * Gets all by year.
     *
     * @param ausbildungsstart the ausbildungsstart
     * @return the all by year
     */
    public List getAllByYear(int ausbildungsstart) {

        return entityManager.createQuery(
                "from Azubi where ausbildungsstart = :ausbildungsstart")
                .setParameter("ausbildungsstart", ausbildungsstart)
                .getResultList();
    }

    /**
     * Gets all by job.
     *
     * @param beruf the beruf
     * @return the all by job
     */
    public List getAllByJob(String beruf) {

        return entityManager.createQuery(
                "from Azubi where beruf = :beruf")
                .setParameter("beruf", beruf)
                .getResultList();
    }

    /**
     * Gets all jobs.
     *
     * @return the all jobs
     */
    public List getAllJobs() {

        return entityManager.createQuery("" +
                "select distinct beruf " +
                "from Azubi").getResultList();
    }

    /**
     * Gets all years.
     *
     * @return the all years
     */
    public List getAllYears() {
        return entityManager.createQuery("" +
                "select distinct ausbildungsstart " +
                "from Azubi").getResultList();
    }

    /**
     * Gets Azubi by id.
     *
     * @param id the id
     * @return Azubi by id
     */
    public Azubi getById(long id) {

        return entityManager.find(Azubi.class, id);
    }

    /**
     * Update Azubi.
     *
     * @param Azubi the azubi
     */
    public void update(Azubi Azubi) {

        entityManager.merge(Azubi);
    }

}
