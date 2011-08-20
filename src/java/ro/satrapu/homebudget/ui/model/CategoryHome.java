package ro.satrapu.homebudget.ui.model;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import ro.satrapu.homebudget.services.persistence.PersistenceService;
import ro.satrapu.homebudget.services.persistence.entities.Category;
import ro.satrapu.homebudget.ui.resources.Messages;

/**
 *
 * @author satrapu
 */
@Named
@ConversationScoped
public class CategoryHome
        implements Serializable {

    private static final long serialVersionUID = 1L;
    private Category instance;
    private Integer instanceId;
    private boolean managedInstance;
    @Inject
    private Conversation conversation;
    @Inject
    private PersistenceService persistenceService;
    @Inject
    private Messages messages;

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
            messages.info("entities.category.events.persisted.successful");
            return "/admin/categories/list";
        } catch (Exception ex) {
            messages.error("entities.category.events.persisted.failed");
        }

        return null;
    }

    public String remove() {
        try {
            persistenceService.remove(instance);
            messages.info("entities.category.events.removed.successful");
            return "/admin/categories/list";
        } catch (Exception ex) {
            messages.error("entities.category.events.removed.failed");
        }

        return null;
    }

    public String update() {
        try {
            persistenceService.merge(instance);
            messages.info("entities.category.events.updated.successful");
            return "/admin/categories/list";
        } catch (Exception ex) {
            messages.error("entities.category.events.updated.failed");
        }

        return null;
    }
}
