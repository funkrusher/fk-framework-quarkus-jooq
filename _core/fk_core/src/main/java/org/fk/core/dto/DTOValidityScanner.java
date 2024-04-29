package org.fk.core.dto;

import jakarta.enterprise.context.ApplicationScoped;
import org.jboss.logging.Logger;
import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static io.vertx.core.cli.impl.ReflectionUtils.isSetter;

/**
 * DTOValidityScanner
 * <p>
 * We need to fail-fast, if the definition of one of our DTOs is not as expected,
 * because we use non-typesafe strings for our bookKeeper.
 * <p>
 * Expectations:
 * - All Setter-Calls need to cope with NULL-Value and all fields need to be of Object-Type
 * - All Setter-Calls need to set the field into the bookKeeper (touched)
 * - After all Setters have been called, the bookKeeper must contain exactly the same amount of fields.
 * - All touch() calls within the setters need to be called with exactly the same name as the setters field-name.
 */
@ApplicationScoped
public class DTOValidityScanner {

    public static final Logger LOGGER = Logger.getLogger(DTOValidityScanner.class);

    /**
     * Scans the validity of all DTOs of the Project.
     *
     * @return true if valid, false otherwise
     */
    public boolean scanValidity() {
        LOGGER.debug("validating all DTOs...");

        boolean areValid = true;

        Reflections reflections = new Reflections("org.fk"); // base package to look in
        Set<Class<? extends DTO>> dtoClasses = reflections.getSubTypesOf(DTO.class);
        for (Class<? extends DTO> dtoClazz : dtoClasses) {
            try {
                LOGGER.debug("validating: " + dtoClazz.getName());
                DTO dtoInstance = dtoClazz.getDeclaredConstructor().newInstance();
                Method[] methods = dtoClazz.getDeclaredMethods();

                List<Method> setterMethods = new ArrayList<>();
                Set<String> uniqueSetterMethodNames = new HashSet<>();
                for (Method method : methods) {
                    if (isSetter(method)) {
                        // we only look at direct setter-methods, defined directly on the dto (not on superclasses)
                        setterMethods.add(method);
                        uniqueSetterMethodNames.add(method.getName());
                    }
                }
                for (Method setterMethod : setterMethods) {
                    // Invoke the setter method
                    try {
                        setterMethod.invoke(dtoInstance, (Object) null);
                    } catch (InvocationTargetException e) {
                        LOGGER.error(setterMethod.getName(), e.getTargetException());
                        areValid = false;
                    } catch (Exception e) {
                        LOGGER.error(setterMethod.getName(), e);
                        areValid = false;
                    }
                }
                if (dtoInstance.getBookKeeper().touched().size() != uniqueSetterMethodNames.size()) {
                    LOGGER.error("missing field in bookKeeper! maybe a touch() call was forgotten in the DTO! " + dtoClazz.getName());
                    areValid = false;
                }
            } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
                LOGGER.error("constructor not found!", e);
                areValid = false;
            }
        }
        return areValid;
    }
}
