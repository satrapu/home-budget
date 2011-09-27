package ro.satrapu.homebudget.ui.exceptionhandler;

import javax.faces.context.ExceptionHandler;

/**
 *
 * @author satrapu
 */
public class ExceptionHandlerFactory
        extends javax.faces.context.ExceptionHandlerFactory {

    private final javax.faces.context.ExceptionHandlerFactory parent;

    public ExceptionHandlerFactory(final javax.faces.context.ExceptionHandlerFactory parent) {
        this.parent = parent;
    }

    @Override
    public ExceptionHandler getExceptionHandler() {
        return new ro.satrapu.homebudget.ui.exceptionhandler.ExceptionHandler(this.parent.getExceptionHandler());
    }
}
