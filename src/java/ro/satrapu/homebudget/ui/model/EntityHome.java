package ro.satrapu.homebudget.ui.model;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.text.MessageFormat;
import javax.enterprise.context.Conversation;
import javax.inject.Inject;
import ro.satrapu.homebudget.services.persistence.ManagedEntity;
import ro.satrapu.homebudget.services.persistence.PersistenceService;

/**
 * 
 * @author satrapu
 * @param <T>
 */
public class EntityHome<T extends ManagedEntity>
        implements Serializable {

    private static final long serialVersionUID = 1L;
    @Inject
    private PersistenceService persistenceService;
    private Serializable id;
    private T instance;
    @Inject
    private Conversation conversation;
    private Class<T> entityType;

    public T getInstance() {
        if (instance == null) {
            if (id != null) {
                instance = loadInstance();
            } else {
                instance = createInstance();
            }
        }

        return instance;
    }

    public Serializable getId() {
        return id;
    }

    public void setId(Serializable id) {
        this.id = id;
    }

    public T loadInstance() {
        return persistenceService.find(getEntityType(), getId());
    }

    public T createInstance() {
        try {
            return getEntityType().newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(MessageFormat.format("Could not instantiate class {0} using default ctor.",
                                                            getEntityType().getCanonicalName()), e);
        }
    }

    @SuppressWarnings("unchecked")
    private Class<T> getEntityType() {
        if (entityType == null) {
            ParameterizedType parameterizedType = (ParameterizedType) getClass().getGenericSuperclass();
            entityType = (Class<T>) parameterizedType.getActualTypeArguments()[0];
        }

        return entityType;
    }

    public boolean isManaged() {
        return getInstance().getId() != null;
    }

    public String save() {
        if (isManaged()) {
            persistenceService.merge(getInstance());
        } else {
            persistenceService.persist(getInstance());
        }

        conversation.end();
        return "saved";
    }

    public String cancel() {
        conversation.end();
        return "cancelled";
    }

    public void initConversation() {
        if (conversation.isTransient()) {
            conversation.begin();
        }
    }

    public String remove() {
        persistenceService.remove(getInstance());
        conversation.end();
        return "removed";
    }
}