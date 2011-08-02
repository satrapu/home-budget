package ro.satrapu.homebudget.services.persistence;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author satrapu
 */
public interface PersistenceService {

    public <T extends Serializable> T persist(T entity);

    public <T extends Serializable> void remove(T entity);

    public <T extends Serializable> T merge(T entity);

    public <T extends Serializable> List<T> findAll(Class<T> entityClass);

    public <T extends Serializable> EntityPage<T> getPageFor(Class<T> entityClass, int firstResult, int maxResults);
}
