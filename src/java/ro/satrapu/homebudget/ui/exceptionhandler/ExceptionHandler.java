package ro.satrapu.homebudget.ui.exceptionhandler;

import java.util.Iterator;
import javax.faces.FacesException;
import javax.faces.event.ExceptionQueuedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author satrapu
 */
public class ExceptionHandler
        extends javax.faces.context.ExceptionHandlerWrapper {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private javax.faces.context.ExceptionHandler wrapped;

    public ExceptionHandler(final javax.faces.context.ExceptionHandler wrapped) {
        this.wrapped = wrapped;
    }

    @Override
    public javax.faces.context.ExceptionHandler getWrapped() {
        return wrapped;
    }

    @Override
    public void handle() throws FacesException {
        logger.debug("handle() - start");
                
        for (final Iterator<ExceptionQueuedEvent> it = getUnhandledExceptionQueuedEvents().iterator(); it.hasNext();) {
            Throwable throwable = it.next().getContext().getException();
            logger.error("Error to be handled ...", throwable);
        }

        getWrapped().handle();
        logger.debug("handle() - end");
    }
}
