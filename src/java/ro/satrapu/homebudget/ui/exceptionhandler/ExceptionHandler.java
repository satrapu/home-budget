package ro.satrapu.homebudget.ui.exceptionhandler;

import java.util.Iterator;
import java.util.Map;
import javax.faces.FacesException;
import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
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
        for (final Iterator<ExceptionQueuedEvent> it = getUnhandledExceptionQueuedEvents().iterator(); it.hasNext();) {
            Throwable throwable = it.next().getContext().getException();
            logger.error("Caught an unexpected exception", throwable);

            FacesContext facesContext = FacesContext.getCurrentInstance();
            Map<String, Object> requestMap = facesContext.getExternalContext().getRequestMap();
            NavigationHandler navigationHandler = facesContext.getApplication().getNavigationHandler();

            try {
                String unexpectedErrorDetails = ExceptionPrettyPrinter.prettyPrint(throwable);
                requestMap.put("unexpectedErrorDetails", unexpectedErrorDetails);

                navigationHandler.handleNavigation(facesContext, null, "unexpectedException");
                facesContext.renderResponse();
            } catch (Exception ex) {
                logger.error("Could not redirect user to error page", ex);
            } finally {
                it.remove();
            }
        }

        getWrapped().handle();
    }
}
