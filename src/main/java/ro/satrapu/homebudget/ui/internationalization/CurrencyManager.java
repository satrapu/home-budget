package ro.satrapu.homebudget.ui.internationalization;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Currency;
import java.util.List;
import java.util.Locale;
import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.model.SelectItem;
import javax.inject.Inject;

/**
 * Manages currencies using the current {@link Locale} instance.
 *
 * @author satrapu
 */
@Model
public class CurrencyManager {

    @Inject
    @CurrentLocale
    Locale currentLocale;
    private List<SelectItem> currencies;

    @PostConstruct
    public void init() {
        currencies = new ArrayList<>();

        for (Currency currency : Currency.getAvailableCurrencies()) {
            currencies.add(new SelectItem(currency.getCurrencyCode(),
                    MessageFormat.format("{0} ({1})", currency.getDisplayName(currentLocale), currency.getCurrencyCode())));
        }

        Collections.sort(currencies, new Comparator<SelectItem>() {
            @Override
            public int compare(SelectItem leftCurrency, SelectItem rightCurrency) {
                return leftCurrency.getLabel().compareTo(rightCurrency.getLabel());
            }
        });
    }

    public Collection<SelectItem> getCurrencies() {
        return currencies;
    }
}
