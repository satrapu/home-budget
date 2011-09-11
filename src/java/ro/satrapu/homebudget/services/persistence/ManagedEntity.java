package ro.satrapu.homebudget.services.persistence;

import java.io.Serializable;
import javax.persistence.EntityManager;

/**
 * Represents an entity managed by an {@link EntityManager} instance.
 * @author satrapu
 */
public interface ManagedEntity
        extends Serializable {

    /**
     * Gets the identifier.
     * @return A non-null value for a managed entity; a null value for a non-managed (not persisted or transient) entity.
     */
    public Serializable getId();
}
