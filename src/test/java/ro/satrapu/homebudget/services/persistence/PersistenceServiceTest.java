package ro.satrapu.homebudget.services.persistence;

import javax.persistence.PersistenceException;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Tests the {@link PersistenceService} class.
 *
 * @author satrapu
 */
public class PersistenceServiceTest {

    @Test
    public void must_not_count_using_null_for_entity_class() {
        Class<Entity> nullEntityClass = null;
        PersistenceService persistenceService = createPersistenceService();

        try {
            persistenceService.count(nullEntityClass);
            Assert.fail("Expected not to be able to count null for entity class");
        } catch (PersistenceException e) {
            //do nothing, exception expected
        }
    }

    private PersistenceService createPersistenceService() {
        PersistenceService persistenceService = new PersistenceService();
        persistenceService.logger = LoggerFactory.getLogger(persistenceService.getClass());
        return persistenceService;
    }
}
