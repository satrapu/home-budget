package ro.satrapu.homebudget.services.logging;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Set;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.spi.AnnotatedConstructor;
import javax.enterprise.inject.spi.AnnotatedField;
import javax.enterprise.inject.spi.AnnotatedMethod;
import javax.enterprise.inject.spi.AnnotatedType;
import javax.enterprise.inject.spi.Extension;
import javax.enterprise.inject.spi.ProcessAnnotatedType;
import org.slf4j.Logger;

/**
 * CDI extensions used for injecting {@link Traced} interceptor binding to all classes of the application.
 *
 * @author satrapu
 */
public class TraceExtension implements Extension {

    private static final String DEFAULT_PACKAGE_PREFIX = "ro.satrapu.homebudget.";
    private Class<?> thisClazz = getClass();
    static Logger logger = LoggerProducer.produce(TraceExtension.class);

    protected <T> void processAnnotatedType(@Observes ProcessAnnotatedType<T> processAnnotatedType) {
        final AnnotatedType<T> type = processAnnotatedType.getAnnotatedType();
        Class<?> clazz = type.getJavaClass();

        if (thisClazz.equals(clazz) || clazz.isAnnotation() || clazz.isArray() || clazz.isInterface() || clazz.isPrimitive()
                || !clazz.getPackage().getName().startsWith(DEFAULT_PACKAGE_PREFIX)) {
            return;
        }

        logger.debug("Injecting trace capabilities to class: {}", clazz.getCanonicalName());
        AnnotatedType<T> wrappedType = new AnnotatedType<T>() {

            @Override
            public boolean isAnnotationPresent(Class<? extends Annotation> annotationType) {
                return annotationType.equals(Traced.class) ? true : type.isAnnotationPresent(annotationType);
            }

            @Override
            public Class<T> getJavaClass() {
                return type.getJavaClass();
            }

            @Override
            public Set<AnnotatedConstructor<T>> getConstructors() {
                return type.getConstructors();
            }

            @Override
            public Set<AnnotatedMethod<? super T>> getMethods() {
                return type.getMethods();
            }

            @Override
            public Set<AnnotatedField<? super T>> getFields() {
                return type.getFields();
            }

            @Override
            public Type getBaseType() {
                return type.getBaseType();
            }

            @Override
            public Set<Type> getTypeClosure() {
                return type.getTypeClosure();
            }

            @Override
            public <T extends Annotation> T getAnnotation(Class<T> annotationType) {
                return type.getAnnotation(annotationType);
            }

            @Override
            public Set<Annotation> getAnnotations() {
                return type.getAnnotations();
            }
        };

        processAnnotatedType.setAnnotatedType(wrappedType);
    }
}
