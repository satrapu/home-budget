package ro.satrapu.homebudget.ui.model;

import javax.enterprise.inject.Model;
import org.primefaces.model.LazyDataModel;
import ro.satrapu.homebudget.services.persistence.model.Expense;

/**
 *
 * @author satrapu
 */
@Model
public class ExpenseList extends EntityList<Expense> {

    @Override
    public LazyDataModel<Expense> getData() {
        return super.getData();
    }
}
