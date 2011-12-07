package ro.satrapu.homebudget.ui.internationalization;

import java.util.Locale;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
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

    /**
     * Creates a new {@link Locale} instance.
     * @param injectionPoint
     * @return
     */
    @Produces
    public Locale produce(InjectionPoint injectionPoint) {
        return FacesContext.getCurrentInstance().getViewRoot().getLocale();
    }
}
