package ro.satrapu.homebudget.ui.model;

import javax.enterprise.inject.Model;
import org.primefaces.model.LazyDataModel;
import ro.satrapu.homebudget.services.persistence.model.Income;

/**
 *
 * @author satrapu
 */
@Model
public class IncomeList extends EntityList<Income> {

    @Override
    public LazyDataModel<Income> getData() {
        return super.getData();
    }
}
