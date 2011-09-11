package ro.satrapu.homebudget.ui.resources;

import java.text.MessageFormat;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 * Helper class used for displaying {@link FacesMessage} instances.
 * @author satrapu
 */
@Model
public class Messages {

    private static final long serialVersionUID = 1L;
    private static final String MISSING_MESSAGE_KEY_PATTERN = "???{0}???";
    private static final String BUNDLE_NAME = "msg";
    private ResourceBundle bundle;

    @PostConstruct
    public void init() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        bundle = facesContext.getApplication().getResourceBundle(facesContext, BUNDLE_NAME);
    }

    public void info(String messageKey) {
        add(FacesMessage.SEVERITY_INFO, messageKey);
    }

    public void warning(String messageKey) {
        add(FacesMessage.SEVERITY_WARN, messageKey);
    }

    public void error(String messageKey) {
        add(FacesMessage.SEVERITY_ERROR, messageKey);
    }

    public void fatal(String messageKey) {
        add(FacesMessage.SEVERITY_FATAL, messageKey);
    }

    public void add(FacesMessage.Severity severity, String summaryMessageKey) {
        FacesMessage facesMessage = new FacesMessage();
        facesMessage.setSeverity(severity);
        facesMessage.setSummary(getValueFor(summaryMessageKey));
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
    }

    public void add(FacesMessage.Severity severity, String summaryMessageKey, String detailMessageKey) {
        FacesMessage facesMessage = new FacesMessage();
        facesMessage.setSeverity(severity);
        facesMessage.setSummary(getValueFor(summaryMessageKey));
        facesMessage.setDetail(getValueFor(detailMessageKey));
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
    }

    public String getValueFor(String messageKey) {
        if (!bundle.containsKey(messageKey)) {
            return MessageFormat.format(MISSING_MESSAGE_KEY_PATTERN, messageKey);
        }

        return bundle.getString(messageKey);
    }
}
