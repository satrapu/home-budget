package ro.satrapu.homebudget.services.persistence;

import javax.enterprise.inject.Alternative;
import javax.inject.Inject;
import org.slf4j.Logger;

/**
 *
 * @author Satrapu
 */
@Alternative
public class RandomizedPersistentStorageInitializer implements PersistentStoreInitializer {
    
    @Inject
    Logger logger;
    
    @Override
    public void init() {
        logger.debug("Start initializing the persistent storage using random entitites");
    }
}
