package ro.satrapu.homebudget.services.persistence;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import org.slf4j.Logger;

/**
 *
 * @author Satrapu
 */
@ApplicationScoped
public class PersistentStorageInitializerService implements Serializable {

    private static final long serialVersionUID = 1L;
    @Inject
    Logger logger;
    @Inject
    PersistentStoreInitializer persistentStoreInitializer;

    @PostConstruct
    public void init() {
        logger.debug("Start initializing the persistent storage using initializer {}", persistentStoreInitializer.getClass().getCanonicalName());
        persistentStoreInitializer.init();
    }
}
