package ro.satrapu.homebudget.ui.model;

import javax.enterprise.inject.Model;
import org.primefaces.model.LazyDataModel;
import ro.satrapu.homebudget.services.persistence.Category;

/**
 *
 * @author satrapu
 */
@Model
public class CategoryList extends EntityList<Category> {

    @Override
    public LazyDataModel<Category> getData() {
        return super.getData();
    }
}
