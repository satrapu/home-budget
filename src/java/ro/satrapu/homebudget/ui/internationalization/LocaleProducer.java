package ro.satrapu.homebudget.ui.internationalization;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import javax.annotation.PostConstruct;
import javax.enterprise.inject.Produces;
import javax.faces.application.Application;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.inject.Singleton;

/**
 *
 * @author satrapu
 */
@Named
@Singleton
public class LocaleProducer {

    private List<Locale> supportedLocales;

    @PostConstruct
    public void init() {
        supportedLocales = new ArrayList<>();
        Locale[] availableLocales = Locale.getAvailableLocales();

        Application facesApplication = FacesContext.getCurrentInstance().getApplication();
        Locale defaultLocale = facesApplication.getDefaultLocale();

        if (defaultLocale == null) {
            defaultLocale = Locale.getDefault();
        }

        Iterator<Locale> supportedLocalesIterator = facesApplication.getSupportedLocales();

        while (supportedLocalesIterator.hasNext()) {
            Locale locale = supportedLocalesIterator.next();

            for (Locale availableLocale : availableLocales) {
                if (availableLocale.getLanguage().compareToIgnoreCase(locale.getLanguage()) == 0) {
                    supportedLocales.add(availableLocale);
                    break;
                }
            }
        }
    }

    /**
     * Gets the {@link Locale} instance associated with the current Faces request.
     * @return
     */
    @Produces
    public Locale getCurrentLocale() {
        return FacesContext.getCurrentInstance().getViewRoot().getLocale();
    }

    @Produces
    public Collection<Locale> getSupportedLocales() {
        return supportedLocales;
    }
}
