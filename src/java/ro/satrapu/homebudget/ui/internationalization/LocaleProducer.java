package ro.satrapu.homebudget.ui.internationalization;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Locale;
import javax.annotation.PostConstruct;
import javax.enterprise.inject.Produces;
import javax.faces.FacesException;
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

    private Collection<Locale> availableLocales;

    @PostConstruct
    public void init() {
        availableLocales = new ArrayList<>();

        Application facesApplication = FacesContext.getCurrentInstance().getApplication();
        Locale defaultLocale = facesApplication.getDefaultLocale();

        if (defaultLocale == null) {
            defaultLocale = Locale.getDefault();
        }

        if (defaultLocale.getCountry() == null || defaultLocale.getCountry().isEmpty()) {
            throw new FacesException(MessageFormat.format("Default locale {0} must have the country specified. Please add the country info inside faces.config file.", defaultLocale));
        }

        Iterator<Locale> supportedLocalesIterator = facesApplication.getSupportedLocales();
        Locale[] javaAvailableLocales = Locale.getAvailableLocales();

        while (supportedLocalesIterator.hasNext()) {
            Locale locale = supportedLocalesIterator.next();

            if (locale.getCountry() == null || locale.getCountry().isEmpty()) {
                throw new FacesException(MessageFormat.format("Locale {0} must have the country specified. Please add the country info inside faces.config file.", locale));
            }

            for (Locale availableLocale : javaAvailableLocales) {
                if (availableLocale.getLanguage().compareToIgnoreCase(locale.getLanguage()) == 0) {
                    availableLocales.add(availableLocale);
                    break;
                }
            }
        }

        boolean containsDefaultLocale = false;

        for (Locale supportedLocale : availableLocales) {
            if (supportedLocale.getLanguage().compareToIgnoreCase(defaultLocale.getLanguage()) == 0) {
                containsDefaultLocale = true;
                break;
            }
        }

        if (!containsDefaultLocale) {
            availableLocales.add(defaultLocale);
        }
    }

    /**
     * Gets the {@link Locale} instance associated with the current Faces request.
     * @return
     */
    @Produces
    @CurrentLocale
    public Locale getCurrentLocale() {
        return FacesContext.getCurrentInstance().getViewRoot().getLocale();
    }

    @Produces
    @AvailableLocales
    public Collection<Locale> getAvailableLocales() {
        return Collections.unmodifiableCollection(availableLocales);
    }
}
