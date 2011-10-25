package ro.satrapu.homebudget.ui.model;

import javax.enterprise.context.ConversationScoped;
import javax.inject.Named;
import ro.satrapu.homebudget.services.persistence.Income;

/**
 *
 * @author satrapu
 */
@Named
@ConversationScoped
public class IncomeHome extends EntityHome<Income> {

    @Override
    public Income getInstance() {
        return super.getInstance();
    }
    
}
