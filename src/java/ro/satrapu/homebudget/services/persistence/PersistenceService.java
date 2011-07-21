package ro.satrapu.homebudget.services.persistence;

import java.util.List;

/**
 *
 * @author satrapu
 */
public interface PersistenceService {

    public <T> T persist(T entity);

    public <T> void remove(T entity);

    public <T> T merge(T entity);

    public <T> List<T> findAll(Class<T> entityClass);
}
