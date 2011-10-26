package ro.satrapu.homebudget.ui.model;

import javax.enterprise.context.ConversationScoped;
import javax.inject.Named;
import ro.satrapu.homebudget.services.persistence.model.Category;

/**
 *
 * @author satrapu
 */
@Named
@ConversationScoped
public class CategoryHome extends EntityEditor<Category> {

    @Override
    public Category getInstance() {
        return super.getInstance();
    }

    @Override
    protected String getOutcome() {
        return "/admin/categories/list";
    }
}
