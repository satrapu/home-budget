package ro.satrapu.homebudget.ui.model;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
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
public class CategoryList
        implements Serializable {

    private static final long serialVersionUID = 1L;
    @Inject
    private PersistenceService persistenceService;
    @Inject
    private Conversation currentConversation;
    private List<Category> categories;
    private Category selectedCategory;

    @PostConstruct
    public void init() {
        if (currentConversation.isTransient()) {
            currentConversation.begin();
            loadCategories();
        }
    }

    private void loadCategories() {
        categories = persistenceService.findAll(Category.class);
    }

    public List<Category> getCategories() {
        return categories;
    }

    public Category getSelectedCategory() {
        return selectedCategory;
    }

    public void setSelectedCategory(Category selectedCategory) {
        this.selectedCategory = selectedCategory;
    }

    public String remove(ActionEvent event) {
//        try {
//            persistenceService.remove(selectedCategory);
//            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Category was successfully removed", "");
//            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
//        } catch (Exception ex) {
//            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Category could not be removed",
//                                                         ex.getMessage());
//            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
//        }
        FacesContext.getCurrentInstance().getExternalContext().getFlash().put("category", selectedCategory);
        return "/admin/categories/delete?faces-redirect=true";

    }
}
