package ro.satrapu.homebudget.services.persistence;

import java.io.Serializable;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.slf4j.Logger;
import ro.satrapu.homebudget.services.logging.Logging;

/**
 *
 * @author satrapu
 */
@Stateless
@LocalBean
public class PersistenceService {

    @Inject
    @Logging
    private Logger logger;
    @PersistenceContext
    private EntityManager entityManager;

    public <T extends Serializable> T persist(T entity) {
        logger.debug("Persisting entity {} ...", entity);

        if (entity == null) {
            throw new RuntimeException("Cannot persist null entity");
        }

        entityManager.persist(entity);
        return entity;
    }

    public <T extends Serializable> void remove(T entity) {
        logger.debug("Removing entity {} ...", entity);

        if (entity == null) {
            throw new RuntimeException("Cannot remove null entity");
        }

        T mergedEntity = entityManager.merge(entity);
        entityManager.remove(mergedEntity);
    }

    public <T extends Serializable> T merge(T entity) {
        logger.debug("Merging entity {} ...", entity);

        if (entity == null) {
            throw new RuntimeException("Cannot merge null entity");
        }

        return entityManager.merge(entity);
    }

    public <T extends Serializable> List<T> listAll(Class<T> entityClass) {
        logger.debug("Listing all entities of type {} ...", entityClass);

        if (entityClass == null) {
            throw new RuntimeException("Cannot query for entities by using null as entity class");
        }

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteria = builder.createQuery(entityClass);
        Root<T> root = criteria.from(entityClass);
        criteria.select(root);

        TypedQuery<T> query = entityManager.createQuery(criteria);
        return query.getResultList();
    }

    public <T extends Serializable> T find(Class<T> entityClass, Serializable entityId) {
        logger.debug("Finding entity of type {} using id {} ...", entityClass, entityId);

        if (entityClass == null) {
            throw new RuntimeException("Cannot find entity by using null as entity class");
        }

        if (entityId == null) {
            throw new RuntimeException("Cannot find entity by using null as entity id");
        }

        return entityManager.find(entityClass, entityId);
    }

    public <T extends Serializable> List<T> list(Class<T> entityClass, int firstResult, int maxResults) {
        logger.debug("Listing maximum {} entities of type {} starting from index {} ...",
                     new Object[]{maxResults, entityClass, firstResult});

        if (entityClass == null) {
            throw new RuntimeException("Cannot query for entities by using null as entity class");
        }

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteria = builder.createQuery(entityClass);
        Root<T> root = criteria.from(entityClass);
        criteria.select(root);

        TypedQuery<T> query = entityManager.createQuery(criteria);
        query.setFirstResult(firstResult);
        query.setMaxResults(maxResults);
        return query.getResultList();
    }

    public <T extends Serializable> long count(Class<T> entityClass) {
        logger.debug("Counting entities of type {} ...", entityClass);

        if (entityClass == null) {
            throw new RuntimeException("Cannot count entities by using null as entity class");
        }

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteria = builder.createQuery(entityClass);
        Root<T> root = criteria.from(entityClass);

        CriteriaQuery<Long> countCriteria = builder.createQuery(Long.class);
        countCriteria.select(builder.count(root));

        TypedQuery<Long> countQuery = entityManager.createQuery(countCriteria);
        return countQuery.getSingleResult();
    }
}
