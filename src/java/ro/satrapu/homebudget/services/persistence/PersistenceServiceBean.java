package ro.satrapu.homebudget.services.persistence;

import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author satrapu
 */
@Stateless
@LocalBean
public class PersistenceServiceBean implements PersistenceService {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public <T> T persist(T entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Override
    public <T> void remove(T entity) {
        entityManager.remove(entity);
    }

    @Override
    public <T> T merge(T entity) {
        entityManager.merge(entity);
        return entity;
    }

    @Override
    public <T> List<T> findAll(Class<T> entityClass) {
        Query query = entityManager.createQuery("from " + entityClass.getName(), entityClass);
        return query.getResultList();
    }
}
