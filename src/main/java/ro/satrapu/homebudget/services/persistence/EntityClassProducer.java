package ro.satrapu.homebudget.services.persistence;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Named;
import javax.inject.Singleton;

/**
 *
 * @author satrapu
 */
@Named
@Singleton
public class EntityClassProducer {

    /**
     *
     * @param injectionPoint
     * @return
     */
    @Produces
    @EntityClass
    public static Class getEntityClass(InjectionPoint injectionPoint) {
        return getEntityClass(injectionPoint.getBean().getBeanClass());
    }

    /**
     * See http://www.andygibson.net/blog/tutorial/pattern-for-conversational-crud-in-java-ee-6/, section Our bean home, point 5.
     * @param clazz
     * @return
     */
    public static Class<?> getEntityClass(Class<?> clazz) {
        Type genericSuperclass = clazz.getGenericSuperclass();

        if (ParameterizedType.class.isAssignableFrom(genericSuperclass.getClass())) {
            ParameterizedType parameterizedType = (ParameterizedType) genericSuperclass;
            return (Class<?>) parameterizedType.getActualTypeArguments()[0];
        }

        return getEntityClass(clazz.getSuperclass());
    }
}