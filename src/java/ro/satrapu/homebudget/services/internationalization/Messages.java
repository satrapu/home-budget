package ro.satrapu.homebudget.services.internationalization;

import java.text.MessageFormat;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import org.slf4j.Logger;

/**
 * Helper class used for displaying {@link FacesMessage} instances.
 * @author satrapu
 */
@Model
public class Messages {

    static final String MISSING_MESSAGE_KEY_PATTERN = "???{0}???";
    static final String BUNDLE_NAME = "msg";
    ResourceBundle bundle;
    @Inject
    Logger logger;

    @PostConstruct
    public void init() {
        logger.debug("Messages will be fetched using bundle name: {}", BUNDLE_NAME);
        FacesContext facesContext = FacesContext.getCurrentInstance();
        bundle = facesContext.getApplication().getResourceBundle(facesContext, BUNDLE_NAME);
    }

    public void info(String messageKey) {
        logger.debug("Info message using key: {}", messageKey);
        add(FacesMessage.SEVERITY_INFO, messageKey);
    }

    public void warning(String messageKey) {
        logger.debug("Warning message using key: {}", messageKey);
        add(FacesMessage.SEVERITY_WARN, messageKey);
    }

    public void error(String messageKey) {
        logger.debug("Error message using key: {}", messageKey);
        add(FacesMessage.SEVERITY_ERROR, messageKey);
    }

    public void fatal(String messageKey) {
        logger.debug("Fatal message using key: {}", messageKey);
        add(FacesMessage.SEVERITY_FATAL, messageKey);
    }

    public void add(FacesMessage.Severity severity, String summaryMessageKey) {
        logger.debug("Adding FacesMessage using severity: {} and summary key: {}", severity, summaryMessageKey);
        FacesMessage facesMessage = new FacesMessage();
        facesMessage.setSeverity(severity);
        facesMessage.setSummary(getValueFor(summaryMessageKey));
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
    }

    public void add(FacesMessage.Severity severity, String summaryMessageKey, String detailMessageKey) {
        logger.debug("Adding FacesMessage using severity: {}, summary key: {} and detail key: {}",
                new Object[]{severity, summaryMessageKey, detailMessageKey});
        FacesMessage facesMessage = new FacesMessage();
        facesMessage.setSeverity(severity);
        facesMessage.setSummary(getValueFor(summaryMessageKey));
        facesMessage.setDetail(getValueFor(detailMessageKey));
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
    }

    public String getValueFor(String messageKey) {
        logger.debug("Trying to get message using key: {}", messageKey);
        String value;

        if (!bundle.containsKey(messageKey)) {
            value = MessageFormat.format(MISSING_MESSAGE_KEY_PATTERN, messageKey);
        } else {
            value = bundle.getString(messageKey);
        }

        logger.debug("Found message: {} for key: {}", value, messageKey);
        return value;
    }
}
