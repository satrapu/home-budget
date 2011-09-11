package ro.satrapu.homebudget.services.persistence;

import java.io.Serializable;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author satrapu
 */
@Stateless
@LocalBean
public class PersistenceService {

    @PersistenceContext
    private EntityManager entityManager;

    public <T extends Serializable> T persist(T entity) {
        if (entity == null) {
            throw new RuntimeException("Cannot persist null entity");
        }

        entityManager.persist(entity);
        return entity;
    }

    public <T extends Serializable> void remove(T entity) {
        if (entity == null) {
            throw new RuntimeException("Cannot remove null entity");
        }

        T mergedEntity = entityManager.merge(entity);
        entityManager.remove(mergedEntity);
    }

    public <T extends Serializable> T merge(T entity) {
        if (entity == null) {
            throw new RuntimeException("Cannot merge null entity");
        }

        return entityManager.merge(entity);
    }

    public <T extends Serializable> List<T> listAll(Class<T> entityClass) {
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
        if (entityClass == null) {
            throw new RuntimeException("Cannot find entity by using null as entity class");
        }

        if (entityId == null) {
            throw new RuntimeException("Cannot find entity by using null as entity id");
        }

        return entityManager.find(entityClass, entityId);
    }

    public <T extends Serializable> List<T> list(Class<T> entityClass, int firstResult, int maxResults) {
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
