package ro.satrapu.homebudget.services.persistence;

import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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
public class PersistenceServiceBean
        implements PersistenceService {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public <T extends Serializable> T persist(T entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Override
    public <T extends Serializable> void remove(T entity) {
        entityManager.remove(entity);
    }

    @Override
    public <T extends Serializable> T merge(T entity) {
        entityManager.merge(entity);
        return entity;
    }

    @Override
    public <T extends Serializable> List<T> findAll(Class<T> entityClass) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteria = builder.createQuery(entityClass);
        Root<T> root = criteria.from(entityClass);
        criteria.select(root);

        TypedQuery<T> query = entityManager.createQuery(criteria);
        return query.getResultList();
    }

    @Override
    public <T extends Serializable> EntityPage<T> getPageFor(Class<T> entityClass, int firstResult, int maxResults) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
