package ro.satrapu.homebudget.services.logging;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Named;
import javax.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Produces {@link org.slf4j.Logger} instances.
 * @author satrapu
 */
@Named
@Singleton
public class LoggerProducer {

    /**
     * Creates a new {@link Logger} instance for the given {@link InjectionPoint} instance.
     * @param injectionPoint
     * @return A new {@link Logger} instance.
     */
    @Produces
    public static Logger produce(InjectionPoint injectionPoint) {
        return produce(injectionPoint.getBean().getBeanClass());
    }

    /**
     * Creates a new {@link Logger} instance for the given {@link Class} instance.
     * @param clazz
     * @return A new {@link Logger} instance.
     */
    public static Logger produce(Class<?> clazz) {
        return LoggerFactory.getLogger(clazz);
    }
}
