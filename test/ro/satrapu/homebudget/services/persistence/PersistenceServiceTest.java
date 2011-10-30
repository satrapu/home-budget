package ro.satrapu.homebudget.services.persistence;

import org.slf4j.LoggerFactory;
import javax.persistence.PersistenceException;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests the {@link PersistenceService} class.
 * @author satrapu
 */
public class PersistenceServiceTest {

    @Test
    public void must_not_count_using_null_for_entity_class() {
        Class<Entity> nullEntityClass = null;
        PersistenceService persistenceService = createPersistenceService();

        try {
            persistenceService.count(nullEntityClass);
            fail("Expected not to be able to count null for entity class");
        } catch (PersistenceException e) {
            e.printStackTrace(System.out);
        }
    }

    private PersistenceService createPersistenceService() {
        PersistenceService persistenceService = new PersistenceService();
        persistenceService.logger = LoggerFactory.getLogger(persistenceService.getClass());
        return persistenceService;
    }
}
