package ro.satrapu.homebudget.ui.model;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.model.LazyDataModel;
import ro.satrapu.homebudget.services.persistence.PersistenceService;
import ro.satrapu.homebudget.services.persistence.entities.Category;

/**
 *
 * @author satrapu
 */
@Named
@SessionScoped
public class CategoryList
        implements Serializable {

    private static final long serialVersionUID = 1L;
    @Inject
    private PersistenceService persistenceService;
    private LazyDataModel<Category> categories;
    private static final int pageSize = 10;

    @PostConstruct
    public void init() {
        categories = new LazyDataModel<Category>() {

            private static final long serialVersionUID = 1L;

            @Override
            public List<Category> load(int first, int pageSize, String sortField, boolean sortOrder,
                                       Map<String, String> filters) {
                return persistenceService.list(Category.class, first, pageSize);
            }
        };
        categories.setRowCount((int) persistenceService.count(Category.class));
    }

    public LazyDataModel<Category> getData() {
        return categories;
    }

    public int getPageSize() {
        return pageSize;
    }
}
