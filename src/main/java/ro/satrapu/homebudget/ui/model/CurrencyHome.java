package ro.satrapu.homebudget.ui.model;

import javax.enterprise.context.ConversationScoped;
import javax.inject.Named;
import ro.satrapu.homebudget.services.persistence.model.Currency;

/**
 *
 * @author satrapu
 */
@Named
@ConversationScoped
public class CurrencyHome extends EntityEditor<Currency> {

    private static final long serialVersionUID = 1L;

    @Override
    public Currency getInstance() {
        return super.getInstance();
    }

    @Override
    protected String getOutcome() {
        return "/admin/currencies/list";
    }
}
