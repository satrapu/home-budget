package ro.satrapu.homebudget.services.persistence;

import java.io.Serializable;

/**
 *
 * @author satrapu
 */
public interface ManagedEntity
        extends Serializable {

    public Serializable getId();
}
