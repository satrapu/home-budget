package ro.satrapu.homebudget.ui.model;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.text.MessageFormat;
import javax.enterprise.context.Conversation;
import javax.inject.Inject;
import ro.satrapu.homebudget.services.persistence.ManagedEntity;
import ro.satrapu.homebudget.services.persistence.PersistenceService;
import ro.satrapu.homebudget.ui.resources.Messages;

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
    @Inject
    private Messages messages;
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
            try {
                persistenceService.merge(getInstance());
                showSuccessfulMergeMessage();
                conversation.end();
                return "merged";
            } catch (Exception e) {
                showFailedMergeMessage();
            }
        } else {
            try {
                persistenceService.persist(getInstance());
                showSuccessfulPersistMessage();
                conversation.end();
                return "persisted";
            } catch (Exception e) {
                showFailedPersistMessage();
            }
        }

        return null;
    }

    public String cancel() {
        conversation.end();
        showCrudOperationCancelledMessage();
        return "cancelled";
    }

    public void initConversation() {
        if (conversation.isTransient()) {
            conversation.begin();
        }
    }

    public String remove() {
        try {
            persistenceService.remove(getInstance());
            showSuccessfulRemoveMessage();
            conversation.end();
            return "removed";
        } catch (Exception e) {
            showFailedRemoveMessage();
        }

        return null;
    }

    protected String getSuccessfulPersistMessageKey() {
        return getEntityCrudMessageIdentifier() + ".persist.successful";
    }

    protected String getFailedPersistMessageKey() {
        return getEntityCrudMessageIdentifier() + ".persist.failed";
    }

    protected String getSuccessfulMergeMessageKey() {
        return getEntityCrudMessageIdentifier() + ".merge.successful";
    }

    protected String getFailedMergeMessageKey() {
        return getEntityCrudMessageIdentifier() + ".merge.failed";
    }

    protected String getSuccessfulRemoveMessageKey() {
        return getEntityCrudMessageIdentifier() + ".remove.successful";
    }

    protected String getFailedRemoveMessageKey() {
        return getEntityCrudMessageIdentifier() + ".remove.failed";
    }

    protected String getCrudOperationCancelledMessageKey() {
        return "global.actions.crudCancelled";
    }

    protected void showSuccessfulPersistMessage() {
        messages.info(getSuccessfulPersistMessageKey());
    }

    protected void showFailedPersistMessage() {
        messages.error(getFailedPersistMessageKey());
    }

    protected void showSuccessfulMergeMessage() {
        messages.info(getSuccessfulMergeMessageKey());
    }

    protected void showFailedMergeMessage() {
        messages.error(getFailedMergeMessageKey());
    }

    protected void showSuccessfulRemoveMessage() {
        messages.info(getSuccessfulRemoveMessageKey());
    }

    protected void showFailedRemoveMessage() {
        messages.error(getFailedRemoveMessageKey());
    }

    protected void showCrudOperationCancelledMessage() {
        messages.warning(getCrudOperationCancelledMessageKey());
    }

    protected String getEntityCrudMessageIdentifier() {
        return getEntityType().getSimpleName().toLowerCase();
    }
}