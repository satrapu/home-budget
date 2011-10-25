package ro.satrapu.homebudget.ui.model;

import javax.enterprise.context.ConversationScoped;
import javax.inject.Named;
import ro.satrapu.homebudget.services.persistence.Expense;

/**
 *
 * @author satrapu
 */
@Named
@ConversationScoped
public class ExpenseHome extends EntityHome<Expense> {

    @Override
    public Expense getInstance() {
        return super.getInstance();
    }
}
