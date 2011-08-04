package ro.satrapu.homebudget.ui.model;

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

    @PostConstruct
    public void init() {
        instance = new Category();
    }

    public Category getInstance() {
        return instance;
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
}
