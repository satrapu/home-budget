package ro.satrapu.homebudget.ui.exceptionhandler;

import java.util.Iterator;
import java.util.Map;
import java.util.UUID;
import javax.faces.FacesException;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ExceptionQueuedEvent;
import org.slf4j.Logger;
import ro.satrapu.homebudget.services.logging.LoggerProducer;

/**
 * Handles uncaught exceptions. <br/> This class is based on an article found <a
 * href="http://javalabor.blogspot.com/2011/09/jsf-2-global-exception-handling.html"> here</a>.
 *
 * @author satrapu
 */
public class ExceptionHandler extends javax.faces.context.ExceptionHandlerWrapper {

    private static final Logger logger = LoggerProducer.produce(ExceptionHandler.class);
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
            ExternalContext externalContext = facesContext.getExternalContext();

            Map<String, Object> sessionMap = externalContext.getSessionMap();
            sessionMap.put("errorDetails", ExceptionPrettyPrinter.prettyPrint(throwable));
            sessionMap.put("errorToken", UUID.randomUUID().toString());

            try {
                String url = externalContext.getRequestContextPath() + "/faces/errors/error.xhtml";
                externalContext.redirect(url);
            } catch (Exception ex) {
                logger.error("Could not redirect user to error page", ex);
            } finally {
                it.remove();
            }
        }

        getWrapped().handle();
    }
}
