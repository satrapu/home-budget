package ro.satrapu.homebudget.ui.model;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import ro.satrapu.homebudget.services.persistence.PersistenceService;
import ro.satrapu.homebudget.services.persistence.entities.Category;

/**
 *
 * @author satrapu
 */
@Model
public class CategoryList
        implements Serializable {

    private static final long serialVersionUID = 1L;
    @Inject
    private PersistenceService persistenceService;
    private List<Category> categories;

    @PostConstruct
    public void init() {
        categories = persistenceService.listAll(Category.class);
    }

    public List<Category> getCategories() {
        return categories;
    }
}
