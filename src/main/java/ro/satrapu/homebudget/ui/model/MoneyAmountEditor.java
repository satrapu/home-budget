package ro.satrapu.homebudget.ui.model;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.faces.model.SelectItem;
import ro.satrapu.homebudget.services.persistence.model.Category;
import ro.satrapu.homebudget.services.persistence.model.Currency;
import ro.satrapu.homebudget.services.persistence.model.MoneyAmount;

/**
 *
 * @param <E>
 * @author satrapu
 */
public abstract class MoneyAmountEditor<E extends MoneyAmount> extends EntityEditor<E> {

    private List<SelectItem> categories;
    private List<SelectItem> currencies;
    private Long categoryId;
    private Long currencyId;

    public Collection<SelectItem> getCategories() {
        if (categories == null) {
            List<Category> categoryList = persistenceService.fetch(Category.class);
            categories = new ArrayList<>(categoryList.size());

            for (Category category : categoryList) {
                categories.add(new SelectItem(category.getId(), category.getName()));
            }
        }

        return categories;
    }

    public Collection<SelectItem> getCurrencies() {
        if (currencies == null) {
            List<Currency> currencyList = persistenceService.fetch(Currency.class);
            currencies = new ArrayList<>(currencyList.size());

            for (Currency currency : currencyList) {
                currencies.add(new SelectItem(currency.getId(), 
                        MessageFormat.format("{0} ({1})", currency.getName(), currency.getThreeLettersIsoName())));
            }
        }

        return currencies;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(Long currencyId) {
        this.currencyId = currencyId;
    }

    @Override
    public E loadInstance() {
        E instance = super.loadInstance();
        categoryId = instance.getCategory().getId();
        currencyId = instance.getCurrency().getId();
        return instance;
    }

    @Override
    public String save() {
        Category selectedCategory = persistenceService.fetchReference(Category.class, categoryId);
        Currency selectedCurrency = persistenceService.fetchReference(Currency.class, currencyId);

        MoneyAmount managedMoneyAmount = getInstance();
        managedMoneyAmount.setCategory(selectedCategory);
        managedMoneyAmount.setCurrency(selectedCurrency);

        return super.save();
    }
}
