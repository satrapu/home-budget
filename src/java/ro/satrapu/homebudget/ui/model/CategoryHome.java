package ro.satrapu.homebudget.ui.model;

import java.text.MessageFormat;
import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import ro.satrapu.homebudget.services.persistence.PersistenceService;
import ro.satrapu.homebudget.services.persistence.entities.Category;

/**
 *
 * @author satrapu
 */
@Model
public class CategoryHome {

    @Inject
    private PersistenceService persistenceService;
    private Category instance;
    private int instanceId;

    @PostConstruct
    public void init() {
        instance = new Category();
    }

    public Category getInstance() {
        return instance;
    }

    public void setInstance(Category instance) {
        this.instance = instance;
    }

    public int getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(int instanceId) {
        this.instanceId = instanceId;
    }
    
    public boolean hasInstance(){
        return instance != null;
    }

    public void find() {
        instance = persistenceService.find(Category.class, instanceId);

        if (instance == null) {
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                                         MessageFormat.format("Could not find category using id {0}",
                                                                              instanceId), "");
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        }
    }

    public void persist() {
        try {
            persistenceService.persist(instance);
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Category was successfully saved", "");
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        } catch (Exception ex) {
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Category could not be saved",
                                                         ex.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        }
    }

    public void remove() {
        try {
            persistenceService.remove(instance);
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Category was successfully removed", "");
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        } catch (Exception ex) {
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Category could not be removed",
                                                         ex.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        }
    }

    public void update() {
        try {
            persistenceService.merge(instance);
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Category was successfully updated", "");
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        } catch (Exception ex) {
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Category could not be updated",
                                                         ex.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        }
    }
}
