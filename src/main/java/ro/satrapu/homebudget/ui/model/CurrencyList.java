package ro.satrapu.homebudget.ui.model;

import javax.enterprise.inject.Model;
import ro.satrapu.homebudget.services.persistence.model.Currency;

/**
 *
 * @author satrapu
 */
@Model
public class CurrencyList extends EntityList<Currency> {

    private static final long serialVersionUID = 1L;
}
