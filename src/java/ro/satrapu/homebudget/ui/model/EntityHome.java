package ro.satrapu.homebudget.ui.model;

import java.io.Serializable;
import java.text.MessageFormat;
import javax.enterprise.context.Conversation;
import javax.inject.Inject;
import ro.satrapu.homebudget.services.persistence.ManagedEntity;
import ro.satrapu.homebudget.services.persistence.PersistenceService;
import ro.satrapu.homebudget.ui.resources.Messages;

/**
 * Manages an entity from an persistent storage.
 * Base class for all CRUD related JSF beans.
 * @see <a href="http://www.andygibson.net/blog/tutorial/pattern-for-conversational-crud-in-java-ee-6/">
 * Conversational CRUD in Java EE 6 by Andy Gibson.</a>
 * @author satrapu
 * @param <T>
 */
public class EntityHome<T extends ManagedEntity>
        extends EntityManager<T> {

    private static final long serialVersionUID = 1L;
    @Inject
    private PersistenceService persistenceService;
    @Inject
    private Conversation conversation;
    @Inject
    private Messages messages;
    private Serializable id;
    private T instance;

    /**
     * Gets the entity managed by this instance.
     * @return An entity managed by this instance.
     */
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

    /**
     * Gets the entity identifier.
     * @return The entity identifier.
     */
    public Serializable getId() {
        return id;
    }

    /**
     * Sets the entity identifier.
     * @param id The identifier to set.
     */
    public void setId(Serializable id) {
        this.id = id;
    }

    /**
     * Loads an entity based on the value returned by the {@link EntityHome#getId()} method.
     * @return 
     */
    public T loadInstance() {
        return persistenceService.find(getEntityType(), getId());
    }

    /**
     * Creates a new entity.
     * @return A new entity.
     */
    public T createInstance() {
        try {
            return getEntityType().newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(MessageFormat.format("Could not instantiate class {0} using default ctor.",
                                                            getEntityType().getCanonicalName()), e);
        }
    }

    /**
     * Gets whether the current entity is managed or not.
     * @return True, if the entity is managed; false, otherwise.
     */
    public boolean isManaged() {
        return getInstance().getId() != null;
    }

    /**
     * Saves the changes of the current entity to the underlying persistent storage.
     * @return The operation outcome, if successful; null, otherwise.
     */
    public String save() {
//        if (isManaged()) {
//            try {
//                persistenceService.merge(getInstance());
//                showSuccessfulMergeMessage();
//                conversation.end();
//                return "merged";
//            } catch (Exception e) {
//                showFailedMergeMessage();
//            }
//        } else {
//            try {
//                persistenceService.persist(getInstance());
//                showSuccessfulPersistMessage();
//                conversation.end();
//                return "persisted";
//            } catch (Exception e) {
//                showFailedPersistMessage();
//            }
//        }
//
//        return null;
        if (isManaged()) {
            persistenceService.merge(getInstance());
            showSuccessfulMergeMessage();
            conversation.end();
        } else {
            persistenceService.persist(getInstance());
            showSuccessfulPersistMessage();
            conversation.end();
        }

        return null;
    }

    /**
     * Cancels the current operation and closes the current conversation.
     * @return The operation outcome.
     */
    public String cancel() {
        conversation.end();
        showCrudOperationCancelledMessage();
        return "cancelled";
    }

    /**
     * Initializes a long-running conversation.
     */
    public void initConversation() {
        if (conversation.isTransient()) {
            conversation.begin();
        }
    }

    /**
     * Removes the current entity from the underlying persistent storage.
     * @return The operation outcome, if successful; null, otherwise.
     */
    public String remove() {
//        try {
//            persistenceService.remove(getInstance());
//            showSuccessfulRemoveMessage();
//            conversation.end();
//            return "removed";
//        } catch (Exception e) {
//            showFailedRemoveMessage();
//        }
//
//        return null;
        persistenceService.remove(getInstance());
        showSuccessfulRemoveMessage();
        conversation.end();

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
