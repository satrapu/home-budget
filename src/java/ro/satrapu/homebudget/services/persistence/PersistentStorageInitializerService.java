package ro.satrapu.homebudget.services.persistence;

import java.io.Serializable;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import org.slf4j.Logger;

/**
 *
 * @author Satrapu
 */
@ApplicationScoped
public class PersistentStorageInitializerService implements Serializable {

    @Inject
    Logger logger;
    @Inject
    PersistentStoreInitializer persistentStoreInitializer;
    private boolean isInitialized;

    public void init() {
        if (!isInitialized) {
            logger.debug("Start initializing the persistent storage using initializer {}", persistentStoreInitializer.getClass().getCanonicalName());
            persistentStoreInitializer.init();
        }
    }
}
