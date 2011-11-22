package ro.satrapu.homebudget.ui.model;

import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import ro.satrapu.homebudget.services.persistence.PersistenceService;
import ro.satrapu.homebudget.services.persistence.model.Category;
import ro.satrapu.homebudget.services.persistence.Entity;

/**
 *
 * @param <T> 
 * @author satrapu
 */
public class EntityList<T extends Entity> extends EntityManager<T> {

    private static final long serialVersionUID = 1L;
    @Inject
    PersistenceService persistenceService;
    LazyDataModel<T> data;

    @PostConstruct
    public void init() {
        data = new LazyDataModel<T>() {

            private static final long serialVersionUID = 1L;

            @Override
            public List<T> load(int first, int pageSize, String sortField, SortOrder sortOrder,
                    Map<String, String> filters) {
                return persistenceService.list(getEntityType(), first, pageSize);
            }
        };
        data.setRowCount((int) persistenceService.count(Category.class));
    }

    public LazyDataModel<T> getData() {
        return data;
    }
}
