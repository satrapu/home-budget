package ro.satrapu.homebudget.ui.model;

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
public class CategoryList {

    @Inject
    private PersistenceService persistenceService;
    private List<Category> categories;

    @PostConstruct
    public void init() {
        categories = persistenceService.findAll(Category.class);
    }

    public List<Category> getCategories() {
        return categories;
    }
}
