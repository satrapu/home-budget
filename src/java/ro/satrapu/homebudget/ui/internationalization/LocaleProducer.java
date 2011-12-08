package ro.satrapu.homebudget.ui.internationalization;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
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
    
    private List<Locale> supportedLocales;
    
    @PostConstruct
    public void init() {
        supportedLocales = new ArrayList<>();
        
        Application facesApplication = FacesContext.getCurrentInstance().getApplication();
        Locale defaultLocale = facesApplication.getDefaultLocale();
        
        if (defaultLocale == null) {
            defaultLocale = Locale.getDefault();
        }
        
        if (defaultLocale.getCountry() == null || defaultLocale.getCountry().isEmpty()) {
            throw new FacesException(MessageFormat.format("Default locale {0} must have the country specified. Please add the country info inside faces.config file.", defaultLocale));
        }
        
        Iterator<Locale> supportedLocalesIterator = facesApplication.getSupportedLocales();
        Locale[] availableLocales = Locale.getAvailableLocales();
        
        while (supportedLocalesIterator.hasNext()) {
            Locale locale = supportedLocalesIterator.next();
            
            if (locale.getCountry() == null || locale.getCountry().isEmpty()) {
                throw new FacesException(MessageFormat.format("Locale {0} must have the country specified. Please add the country info inside faces.config file.", locale));
            }
            
            for (Locale availableLocale : availableLocales) {
                if (availableLocale.getLanguage().compareToIgnoreCase(locale.getLanguage()) == 0) {
                    supportedLocales.add(availableLocale);
                    break;
                }
            }
        }
        
        boolean containsDefaultLocale = false;
        
        for (Locale supportedLocale : supportedLocales) {
            if (supportedLocale.getLanguage().compareToIgnoreCase(defaultLocale.getLanguage()) == 0) {
                containsDefaultLocale = true;
                break;
            }
        }
        
        if (!containsDefaultLocale) {
            supportedLocales.add(defaultLocale);
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
