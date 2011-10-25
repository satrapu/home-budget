package ro.satrapu.homebudget.services.persistence;

import java.io.Serializable;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.slf4j.Logger;

/**
 *
 * @author satrapu
 */
@Stateless
@LocalBean
public class PersistenceService {

    @Inject
    Logger logger;
    @PersistenceContext
    EntityManager entityManager;

    public <T extends Entity> T persist(T entity) {
        logger.debug("Persist entity: {}", entity);

        if (entity == null) {
            throw new PersistenceException("Cannot persist null entity");
        }

        entityManager.persist(entity);
        return entity;
    }

    public <T extends Entity> void remove(T entity) {
        logger.debug("Remove entity: {}", entity);

        if (entity == null) {
            throw new PersistenceException("Cannot remove null entity");
        }

        T mergedEntity = entityManager.merge(entity);
        entityManager.remove(mergedEntity);
    }

    public <T extends Entity> T merge(T entity) {
        logger.debug("Merge entity: {}", entity);

        if (entity == null) {
            throw new PersistenceException("Cannot merge null entity");
        }

        return entityManager.merge(entity);
    }

    public <T extends Entity> List<T> listAll(Class<T> entityClass) {
        logger.debug("List all entities of type: {}", entityClass);

        if (entityClass == null) {
            throw new PersistenceException("Cannot query for entities by using null as entity class");
        }

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteria = builder.createQuery(entityClass);
        Root<T> root = criteria.from(entityClass);
        criteria.select(root);

        TypedQuery<T> query = entityManager.createQuery(criteria);
        return query.getResultList();
    }

    public <T extends Entity> T find(Class<T> entityClass, Serializable entityId) {
        logger.debug("Find entity of type: {}, using id: {}", entityClass, entityId);

        if (entityClass == null) {
            throw new PersistenceException("Cannot find entity by using null as entity class");
        }

        if (entityId == null) {
            throw new PersistenceException("Cannot find entity by using null as entity id");
        }

        return entityManager.find(entityClass, entityId);
    }

    public <T extends Entity> List<T> list(Class<T> entityClass, int firstResult, int maxResults) {
        logger.debug("List maximum: {} entities: {}, starting from index: {} ",
                new Object[]{maxResults, entityClass, firstResult});

        if (entityClass == null) {
            throw new PersistenceException("Cannot query for entities by using null as entity class");
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

    public <T extends Entity> long count(Class<T> entityClass) {
        logger.debug("Count entities: {}", entityClass);

        if (entityClass == null) {
            throw new PersistenceException("Cannot count entities by using null as entity class");
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
