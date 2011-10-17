/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.satrapu.homebudget.ui.model;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import ro.satrapu.homebudget.services.persistence.Entity;

/**
 *
 * @param <T> 
 * @author satrapu
 */
public class EntityManager<T extends Entity>
        implements Serializable {

    private static final long serialVersionUID = 1L;
    private Class<T> entityType;

    @SuppressWarnings("unchecked")
    protected Class<T> getEntityType() {
        if (entityType == null) {
            ParameterizedType parameterizedType = (ParameterizedType) getClass().getGenericSuperclass();
            entityType = (Class<T>) parameterizedType.getActualTypeArguments()[0];
        }

        return entityType;
    }
}
