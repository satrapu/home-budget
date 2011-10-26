package ro.satrapu.homebudget.ui.model;

import ro.satrapu.homebudget.services.persistence.Entity;

/**
 *
 * @author satrapu
 */
public abstract class EntityEditor<T extends Entity> extends EntityHome<T> {

    @Override
    protected String getCancelledOutcome() {
        return getOutcome();
    }

    @Override
    protected String getMergedOutcome() {
        return getOutcome();
    }

    @Override
    protected String getPersistedOutcome() {
        return getOutcome();
    }

    @Override
    protected String getRemovedOutcome() {
        return getOutcome();
    }

    protected abstract String getOutcome();
}
