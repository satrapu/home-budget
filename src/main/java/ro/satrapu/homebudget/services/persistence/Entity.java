package ro.satrapu.homebudget.services.persistence;

import java.io.Serializable;

/**
 * Represents an entity managed by an {@link PersistenceService} instance.
 * @author satrapu
 */
public interface Entity extends Serializable {

    /**
     * Gets the entity identifier.
     * @return A non-null value if the entity was already persisted; null, otherwise.
     */
    public Serializable getId();

    /**
     * Gets the entity version.
     * @return A positive value, if the entity was persisted; 0, otherwise.
     */
    public Long getVersion();
}
