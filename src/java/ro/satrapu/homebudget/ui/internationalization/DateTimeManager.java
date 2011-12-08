package ro.satrapu.homebudget.ui.internationalization;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import javax.enterprise.inject.Model;
import javax.inject.Inject;

/**
 *
 * @author Satrapu
 */
@Model
public class DateTimeManager {

    @Inject
    @CurrentLocale
    Locale currentLocale;

    public String getDisplayValue(Date date) {
        return DateFormat.getDateInstance(DateFormat.MEDIUM, currentLocale).format(date);
    }
}
