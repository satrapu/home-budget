package ro.satrapu.homebudget.ui.model;

import javax.enterprise.context.ConversationScoped;
import javax.inject.Named;
import ro.satrapu.homebudget.services.persistence.entities.Category;

/**
 *
 * @author satrapu
 */
@Named
@ConversationScoped
public class CategoryHome
        extends EntityHome<Category> {

    private static final long serialVersionUID = 1L;

    @Override
    public Category getInstance() {
        return super.getInstance();
    }
}
