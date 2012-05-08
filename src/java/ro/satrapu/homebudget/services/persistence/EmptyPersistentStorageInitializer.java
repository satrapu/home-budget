package ro.satrapu.homebudget.services.persistence;

import javax.enterprise.inject.Alternative;
import javax.inject.Inject;
import org.slf4j.Logger;

/**
 *
 * @author Satrapu
 */
@Alternative
public class EmptyPersistentStorageInitializer implements PersistentStoreInitializer {

    @Inject
    Logger logger;

    @Override
    public void init() {
        logger.debug("No entity will be added to the persistent storage");
    }
}
