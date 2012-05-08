package ro.satrapu.homebudget.services.persistence;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
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
 * Manages all CRUD operations.
 *
 * @author satrapu
 */
@Stateless
@LocalBean
public class PersistenceService {

    @Inject
    PersistentStorageInitializerService persistentStorageInitializerService;
    @Inject
    Logger logger;
    @PersistenceContext
    EntityManager entityManager;

    @PostConstruct
    public void init() {
        persistentStorageInitializerService.init(); 
    }

    public <T extends Entity> T persist(T entity) {
        if (entity == null) {
            throw new PersistenceException("Cannot persist null entity");
        }

        logger.debug("Persisting entity {} ...", entity);
        entityManager.persist(entity);
        logger.debug("Persisted entity: {}", entity);
        return entity;
    }

    public <T extends Entity> void remove(T entity) {
        if (entity == null) {
            throw new PersistenceException("Cannot remove null entity");
        }

        logger.debug("Removing entity {} ...", entity);
        T mergedEntity = entityManager.merge(entity);
        entityManager.remove(mergedEntity);
        logger.debug("Removed entity: {}", entity);
    }

    public <T extends Entity> T merge(T entity) {
        if (entity == null) {
            throw new PersistenceException("Cannot merge null entity");
        }

        logger.debug("Merging entity {} ...", entity);
        T mergedEntity = entityManager.merge(entity);
        logger.debug("Merged entity: {}", mergedEntity);
        return mergedEntity;
    }

    public <T extends Entity> List<T> fetch(Class<T> entityClass) {
        if (entityClass == null) {
            throw new PersistenceException("Cannot fetch entities by using null as entity class");
        }

        logger.debug("Fetching all entities of type {} ...", entityClass);
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteria = builder.createQuery(entityClass);
        Root<T> root = criteria.from(entityClass);
        criteria.select(root);

        TypedQuery<T> query = entityManager.createQuery(criteria);
        List<T> resultList = query.getResultList();
        logger.debug("Fetched {} entities of type {}", resultList.size(), entityClass);
        return resultList;
    }

    public <T extends Entity> T fetch(Class<T> entityClass, Serializable entityId) {
        if (entityClass == null) {
            throw new PersistenceException("Cannot fetch entity by using null as entity class");
        }

        if (entityId == null) {
            throw new PersistenceException("Cannot fetch entity by using null as entity id");
        }

        logger.debug("Fetching entity of type {}, using id {} ...", entityClass, entityId);
        T entity = entityManager.find(entityClass, entityId);
        logger.debug("Fetched entity: {}", entity);
        return entity;
    }

    public <T extends Entity> T fetchReference(Class<T> entityClass, Serializable entityId) {
        if (entityClass == null) {
            throw new PersistenceException("Cannot fetch entity reference by using null as entity class");
        }

        if (entityId == null) {
            throw new PersistenceException("Cannot fetch entity reference by using null as entity id");
        }

        logger.debug("Fetching entity reference of type {}, using id {} ...", entityClass, entityId);
        T entity = entityManager.getReference(entityClass, entityId);
        logger.debug("Fetched entity reference: {}", entity);
        return entity;
    }

    public <T extends Entity> List<T> fetch(Class<T> entityClass, int firstResult, int maxResults) {
        if (entityClass == null) {
            throw new PersistenceException("Cannot fetch entities using null as entity class");
        }

        logger.debug("Fetching maximum {} entities of type {}, starting from index {} ...",
                new Object[]{maxResults, entityClass, firstResult});
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteria = builder.createQuery(entityClass);
        Root<T> root = criteria.from(entityClass);
        criteria.select(root);

        TypedQuery<T> query = entityManager.createQuery(criteria);
        query.setFirstResult(firstResult);
        query.setMaxResults(maxResults);
        List<T> resultList = query.getResultList();
        logger.debug("Fetched {} entities of type {}", resultList.size(), entityClass);
        return resultList;
    }

    public <T extends Entity> long count(Class<T> entityClass) {
        if (entityClass == null) {
            throw new PersistenceException("Cannot count entities by using null as entity class");
        }

        logger.debug("Counting entities {} ...", entityClass);
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
        criteriaQuery.select(criteriaBuilder.count(criteriaQuery.from(entityClass)));
        long count = entityManager.createQuery(criteriaQuery).getSingleResult();
        logger.debug("Counted {} entities of type {}", count, entityClass);
        return count;
    }
}
