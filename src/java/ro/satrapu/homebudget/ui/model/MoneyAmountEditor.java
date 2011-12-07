package ro.satrapu.homebudget.ui.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.faces.model.SelectItem;
import ro.satrapu.homebudget.services.persistence.model.Category;
import ro.satrapu.homebudget.services.persistence.model.MoneyAmount;

/**
 *
 * @param <E> 
 * @author satrapu
 */
public abstract class MoneyAmountEditor<E extends MoneyAmount> extends EntityEditor<E> {

    private List<SelectItem> categories;
    private Long categoryId;

    public Collection<SelectItem> getCategories() {
        if (categories == null) {
            List<Category> categoryList = persistenceService.listAll(Category.class);
            categories = new ArrayList<>(categoryList.size());

            for (Category category : categoryList) {
                categories.add(new SelectItem(category.getId(), category.getName()));
            }
        }

        return categories;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public E loadInstance() {
        E instance = super.loadInstance();
        categoryId = instance.getCategory().getId();
        return instance;
    }

    @Override
    public String save() {
        Category selectedCategory = persistenceService.find(Category.class, categoryId);
        getInstance().setCategory(selectedCategory);
        return super.save();
    }
}
