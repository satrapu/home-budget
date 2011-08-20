package ro.satrapu.homebudget.ui.model;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import ro.satrapu.homebudget.services.persistence.PersistenceService;
import ro.satrapu.homebudget.services.persistence.entities.Category;

/**
 *
 * @author satrapu
 */
@Named
@ConversationScoped
public class CategoryHome
        implements Serializable {

    private static final long serialVersionUID = 1L;
    @Inject
    private PersistenceService persistenceService;
    private Category instance;
    private Integer instanceId;
    private boolean managedInstance;
    @Inject
    private Conversation conversation;

    @PostConstruct
    public void init() {
        if (conversation.isTransient()) {
            conversation.begin();
            prepareInstance();
        }
    }

    public Category getInstance() {
        return instance;
    }

    public void setInstance(Category instance) {
        this.instance = instance;
    }

    public Integer getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(Integer instanceId) {
        this.instanceId = instanceId;
        prepareInstance();
    }

    public boolean isManagedInstance() {
        return managedInstance;
    }

    private void prepareInstance() {
        if (instanceId != null) {
            instance = persistenceService.find(Category.class, instanceId);
        }

        managedInstance = instance != null;

        if (!managedInstance) {
            instance = new Category();
        }
    }

    public String persist() {
        try {
            persistenceService.persist(instance);
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Category was successfully saved", "");
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
            conversation.end();
            return "/admin/categories/list";
        } catch (Exception ex) {
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Category could not be saved",
                                                         ex.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        }

        return null;
    }

    public String remove() {
        try {
            persistenceService.remove(instance);
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Category was successfully removed", "");
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
            conversation.end();
            return "/admin/categories/list";
        } catch (Exception ex) {
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Category could not be removed",
                                                         ex.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        }

        return null;
    }

    public String update() {
        try {
            persistenceService.merge(instance);
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Category was successfully updated", "");
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
            conversation.end();
            return "/admin/categories/list";
        } catch (Exception ex) {
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Category could not be updated",
                                                         ex.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        }

        return null;
    }
}
