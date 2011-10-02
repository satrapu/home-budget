package ro.satrapu.homebudget.services.logging;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import javax.inject.Qualifier;

/**
 *
 * @author satrapu
 */
@Qualifier
@Retention(RUNTIME)
@Target({METHOD, FIELD})
public @interface Logging {
}