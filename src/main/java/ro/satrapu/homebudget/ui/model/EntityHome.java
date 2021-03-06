package ro.satrapu.homebudget.ui.model;

import java.io.Serializable;
import java.text.MessageFormat;
import javax.enterprise.context.Conversation;
import javax.inject.Inject;
import org.slf4j.Logger;
import ro.satrapu.homebudget.services.persistence.Entity;
import ro.satrapu.homebudget.services.persistence.EntityClass;
import ro.satrapu.homebudget.services.persistence.PersistenceService;
import ro.satrapu.homebudget.ui.internationalization.Messages;

/**
 * Manages an entity from an persistent storage. Base class for all CRUD related JSF beans.
 *
 * @see <a href="http://www.andygibson.net/blog/tutorial/pattern-for-conversational-crud-in-java-ee-6"> 
 * Conversational CRUD in Java EE 6</a> by Andy Gibson.
 * @author satrapu
 * @param <T>
 */
public abstract class EntityHome<T extends Entity> implements Serializable {

    private static final long serialVersionUID = 1L;
    @Inject
    PersistenceService persistenceService;
    @Inject
    Conversation conversation;
    @Inject
    Messages messages;
    @Inject
    transient Logger logger;
    @Inject
    @EntityClass
    Class entityClass;
    private Serializable id;
    private T instance;

    /**
     * Gets the entity managed by this instance.
     *
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
     *
     * @return The entity identifier.
     */
    public Serializable getId() {
        return id;
    }

    /**
     * Sets the entity identifier.
     *
     * @param id The identifier to set.
     */
    public void setId(Serializable id) {
        this.id = id;
    }

    /**
     * Loads an entity based on the value returned by the {@link EntityHome#getId()} method.
     *
     * @return
     */
    @SuppressWarnings("unchecked")
    public T loadInstance() {
        logger.debug("Loading instance: {} using id: {}", entityClass, getId());
        return persistenceService.fetch((Class<T>) entityClass, getId());
    }

    /**
     * Creates a new entity.
     *
     * @return A new entity.
     */
    @SuppressWarnings("unchecked")
    public T createInstance() {
        logger.debug("Creating instance: {}", entityClass);

        try {
            return (T) entityClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            logger.error("Could not create instance", e);
            throw new RuntimeException(MessageFormat.format("Could not instantiate class {0} using default ctor.",
                    entityClass.getCanonicalName()), e);
        }
    }

    /**
     * Gets whether the current entity is managed or not.
     *
     * @return True, if the entity is managed; false, otherwise.
     */
    public boolean isManaged() {
        return getInstance().getId() != null;
    }

    /**
     * Saves the changes of the current entity to the underlying persistent storage.
     *
     * @return The operation outcome, if successful; null, otherwise.
     */
    public String save() {
        if (isManaged()) {
            try {
                logger.debug("Merging instance: {}", getInstance());
                persistenceService.merge(getInstance());
                showSuccessfulMergeMessage();
                conversation.end();
                return getMergedOutcome();
            } catch (Exception e) {
                logger.error("Could not merge instance", e);
                showFailedMergeMessage();
            }
        } else {
            try {
                logger.debug("Persisting instance: {}", getInstance());
                persistenceService.persist(getInstance());
                showSuccessfulPersistMessage();
                conversation.end();
                return getPersistedOutcome();
            } catch (Exception e) {
                logger.error("Could not persist instance", e);
                showFailedPersistMessage();
            }
        }

        return null;
    }

    /**
     * Cancels the current operation and closes the current conversation.
     *
     * @return The operation outcome.
     */
    public String cancel() {
        logger.debug("Cancel editor");
        conversation.end();
        return getCancelledOutcome();
    }

    /**
     * Initializes a long-running conversation.
     */
    public void beginConversation() {
        if (conversation.isTransient()) {
            conversation.begin();
            logger.debug("Starting conversation with id: {}", conversation.getId());
        }
    }

    /**
     * Removes the current entity from the underlying persistent storage.
     *
     * @return The operation outcome, if successful; null, otherwise.
     */
    public String remove() {
        logger.debug("Removing instance: {}", getInstance());

        try {
            persistenceService.remove(getInstance());
            showSuccessfulRemoveMessage();
            conversation.end();
            return getRemovedOutcome();
        } catch (Exception e) {
            logger.error("Could not remove instance", e);
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

    protected String getRecordDoesNotExistsMessageKey() {
        return "global.recordDoesNotExist";
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

    protected void showRecordDoesNotExistMessage() {
        messages.warning(getRecordDoesNotExistsMessageKey());
    }

    protected String getEntityCrudMessageIdentifier() {
        return entityClass.getSimpleName().toLowerCase();
    }

    protected String getPersistedOutcome() {
        return "persisted";
    }

    protected String getMergedOutcome() {
        return "merged";
    }

    protected String getRemovedOutcome() {
        return "removed";
    }

    protected String getCancelledOutcome() {
        return "cancelled";
    }
}
