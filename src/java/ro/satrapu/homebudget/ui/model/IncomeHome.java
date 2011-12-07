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
public class IncomeHome extends MoneyAmountEditor<Income> {

    private static final long serialVersionUID = 1L;

    @Override
    protected String getOutcome() {
        return "/admin/incomes/list";
    }
}
