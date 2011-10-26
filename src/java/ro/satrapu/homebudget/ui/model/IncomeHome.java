package ro.satrapu.homebudget.ui.model;

import javax.enterprise.context.ConversationScoped;
import javax.inject.Named;
import ro.satrapu.homebudget.services.persistence.model.Income;

/**
 *
 * @author satrapu
 */
@Named
@ConversationScoped
public class IncomeHome extends EntityEditor<Income> {

    @Override
    public Income getInstance() {
        return super.getInstance();
    }

    @Override
    protected String getOutcome() {
        return "/admin/incomes/list";
    }
}
