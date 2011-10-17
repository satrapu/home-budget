package ro.satrapu.homebudget.ui.model;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import ro.satrapu.homebudget.services.persistence.model.Category;

/**
 *
 * @author satrapu
 */
@Named
@RequestScoped
public class CategoryList
        extends EntityList<Category> {

    private static final long serialVersionUID = 1L;
}
