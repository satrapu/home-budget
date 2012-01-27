package ro.satrapu.homebudget.ui.exceptionhandler;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

/**
 *
 * @author satrapu
 */
public class ExceptionPrettyPrinter {

    public static String prettyPrint(Throwable throwable) {
        Writer writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        throwable.printStackTrace(printWriter);
        return writer.toString();
    }
}
