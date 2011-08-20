package ro.satrapu.homebudget.services.persistence;

import java.io.Serializable;
import java.util.List;
import javax.ejb.Local;
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
@Local
public class PersistenceServiceBean
        implements PersistenceService {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public <T extends Serializable> T persist(T entity) {
        if (entity == null) {
            throw new RuntimeException("Cannot persist null entity");
        }

        entityManager.persist(entity);
        return entity;
    }

    @Override
    public <T extends Serializable> void remove(T entity) {
        if (entity == null) {
            throw new RuntimeException("Cannot remove null entity");
        }

        T mergedEntity = entityManager.merge(entity);
        entityManager.remove(mergedEntity);
    }

    @Override
    public <T extends Serializable> T merge(T entity) {
        if (entity == null) {
            throw new RuntimeException("Cannot merge null entity");
        }

        return entityManager.merge(entity);
    }

    @Override
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

    @Override
    public <T extends Serializable> EntityList<T> list(Class<T> entityClass, int firstResult, int maxResults) {
        if (entityClass == null) {
            throw new RuntimeException("Cannot query for entities by using null as entity class");
        }

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteria = builder.createQuery(entityClass);
        Root<T> root = criteria.from(entityClass);
        criteria.select(root);

        CriteriaQuery<Long> countCriteria = builder.createQuery(Long.class);
        countCriteria.select(builder.count(root));

        TypedQuery<T> query = entityManager.createQuery(criteria);
        query.setFirstResult(firstResult);
        query.setMaxResults(maxResults);

        TypedQuery<Long> countQuery = entityManager.createQuery(countCriteria);

        return new EntityList<T>(query.getResultList(), countQuery.getSingleResult());
    }

    @Override
    public <T extends Serializable> T find(Class<T> entityClass, Serializable entityId) {
        if (entityClass == null) {
            throw new RuntimeException("Cannot find entity by using null as entity class");
        }

        if (entityId == null) {
            throw new RuntimeException("Cannot find entity by using null as entity id");
        }

        return entityManager.find(entityClass, entityId);
    }
}
