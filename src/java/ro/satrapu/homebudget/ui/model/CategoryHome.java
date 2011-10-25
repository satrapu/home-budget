package ro.satrapu.homebudget.ui.model;

import javax.enterprise.context.ConversationScoped;
import javax.inject.Named;
import ro.satrapu.homebudget.services.persistence.Category;

/**
 *
 * @author satrapu
 */
@Named
@ConversationScoped
public class CategoryHome extends EntityHome<Category> {

    @Override
    public Category getInstance() {
        return super.getInstance();
    }
}
