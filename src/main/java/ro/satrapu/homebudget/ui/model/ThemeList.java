package ro.satrapu.homebudget.ui.model;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

/**
 *
 * @author Satrapu
 */
@Named
@ApplicationScoped
public class ThemeList {

    private List<String> themes;

    public ThemeList() {
    }

    @PostConstruct
    public void init() {
        themes = new ArrayList<>();
    }

    public List<String> getThemes() {
        return themes;
    }
}
