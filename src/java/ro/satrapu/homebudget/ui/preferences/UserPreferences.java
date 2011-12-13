package ro.satrapu.homebudget.ui.preferences;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import lombok.Data;

/**
 *
 * @author Satrapu
 */
@Named
@SessionScoped
@Data
public class UserPreferences implements Serializable {

    private static final long serialVersionUID = 1L;
    private String theme = "cupertino";

    @PostConstruct
    public void init() {
    }
}
