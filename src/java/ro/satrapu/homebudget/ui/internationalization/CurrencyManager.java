package ro.satrapu.homebudget.ui.internationalization;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Currency;
import java.util.List;
import java.util.Locale;
import javax.enterprise.inject.Model;
import javax.faces.model.SelectItem;
import javax.inject.Inject;

/**
 * Manages currencies using the current {@link Locale} instance.
 * @author Satrapu
 */
@Model
public class CurrencyManager {

    @Inject
    Locale currentLocale;
    private List<SelectItem> currencies;

    public Collection<SelectItem> getAllCurrencies() {
        if (currencies == null) {
            currencies = new ArrayList<>();

            for (Currency currency : Currency.getAvailableCurrencies()) {
                currencies.add(new SelectItem(currency.getCurrencyCode(), currency.getDisplayName(currentLocale)));
            }

            Collections.sort(currencies, new Comparator<SelectItem>() {

                @Override
                public int compare(SelectItem leftCurrency, SelectItem rightCurrency) {
                    return leftCurrency.getLabel().compareTo(rightCurrency.getLabel());
                }
            });
        }

        return currencies;
    }

    public String getDisplayableName(String currencyCode) {
        return Currency.getInstance(currencyCode).getDisplayName(currentLocale);
    }
}
