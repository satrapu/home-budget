package ro.satrapu.homebudget.services.persistence;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author satrapu
 */
public interface PersistenceService {

    public <T extends Serializable> T find(Class<T> entityClass, Serializable entityId);

    public <T extends Serializable> T persist(T entity);

    public <T extends Serializable> void remove(T entity);

    public <T extends Serializable> T merge(T entity);

    public <T extends Serializable> List<T> listAll(Class<T> entityClass);

    public <T extends Serializable> List<T> list(Class<T> entityClass, int firstResult, int maxResults);

    public <T extends Serializable> long count(Class<T> entityClass);
}
