package ro.satrapu.homebudget.services.logging;

import java.io.Serializable;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import org.slf4j.Logger;

/**
 *
 * @author satrapu
 */
@Traced
@Interceptor
public class TraceInterceptor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Inject
    Logger logger;

    @AroundInvoke
    public Object traceMethodCall(InvocationContext invocationContext) throws Exception {
        logger.debug("Calling method: {} ...", invocationContext.getMethod().toGenericString());
        Object result = invocationContext.proceed();

        if (result != null) {
            logger.debug("Call has returned: {}", result);
        }

        return result;
    }
}
