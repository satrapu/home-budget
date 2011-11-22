package ro.satrapu.homebudget.ui.model;

import java.util.ArrayList;
import java.util.List;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import ro.satrapu.homebudget.services.persistence.Entity;
import ro.satrapu.homebudget.services.persistence.PersistenceService;
import ro.satrapu.homebudget.services.persistence.model.Category;
import ro.satrapu.homebudget.services.persistence.model.Expense;

/**
 *
 * @author Satrapu
 */
public abstract class MoneyAmountEditor<E extends Entity> extends EntityEditor<Expense> {

    private List<SelectItem> categories;
    @Inject
    PersistenceService persistenceService;

    public List<SelectItem> getCategories() {
        if (categories == null) {
            List<Category> categoryList = persistenceService.listAll(Category.class);
            categories = new ArrayList<SelectItem>(categoryList.size());

            for (Category category : categoryList) {
                categories.add(new SelectItem(category.getId(), category.getName()));
            }
        }

        return categories;
    }
}
