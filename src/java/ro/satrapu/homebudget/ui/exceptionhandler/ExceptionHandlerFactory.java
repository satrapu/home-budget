package ro.satrapu.homebudget.ui.exceptionhandler;

import javax.faces.context.ExceptionHandler;

/**
 * Creates {@link ro.satrapu.homebudget.ui.exceptionhandler.ExceptionHandler} instances.
 * <br/>
 * This class is based on an article found <a href="http://javalabor.blogspot.com/2011/09/jsf-2-global-exception-handling.html">here</a>.
 * @author satrapu
 */
public class ExceptionHandlerFactory extends javax.faces.context.ExceptionHandlerFactory {

    private final javax.faces.context.ExceptionHandlerFactory parent;

    public ExceptionHandlerFactory(final javax.faces.context.ExceptionHandlerFactory parent) {
        this.parent = parent;
    }

    @Override
    public ExceptionHandler getExceptionHandler() {
        return new ro.satrapu.homebudget.ui.exceptionhandler.ExceptionHandler(parent.getExceptionHandler());
    }
}
