package ro.satrapu.homebudget.ui.model;

import javax.enterprise.context.ConversationScoped;
import javax.inject.Named;
import ro.satrapu.homebudget.services.persistence.model.Expense;

/**
 *
 * @author satrapu
 */
@Named
@ConversationScoped
public class ExpenseHome extends EntityEditor<Expense> {

    @Override
    public Expense getInstance() {
        return super.getInstance();
    }

    @Override
    protected String getOutcome() {
        return "/admin/expenses/list";
    }
}