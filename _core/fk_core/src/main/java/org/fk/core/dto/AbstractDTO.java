package org.fk.core.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.xml.bind.annotation.XmlTransient;
import org.jooq.UpdatableRecord;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static java.lang.StackWalker.Option.RETAIN_CLASS_REFERENCE;

/**
 * A DTO abstraction for all DTOs of the application
 * <p>All generated DTO classes extend this DTO to provide common functionality.</p>
 * <p>
 * defines modified fields, that keep track of all changes on the fields of the dto.
 * - in unit-tests this map can be used, to serialize only the set fields of the dto to the body of the rest-request.
 * - in database-operations this map can be used, to resolve only the modified fields to the database-operations.
 * </p>
 */
public abstract class AbstractDTO {

    // inspired by: https://blog.jooq.org/orms-should-update-changed-values-not-just-modified-ones/
    @JsonIgnore
    @XmlTransient
    private final Map<String, Object> modifiedFields = new HashMap<>();

    public AbstractDTO() {
    }

    @JsonIgnore
    @XmlTransient
    public void touch () {
        StackWalker walker = StackWalker.getInstance(RETAIN_CLASS_REFERENCE);
        StackWalker.StackFrame frame = walker.walk(frames -> {
            Iterator<StackWalker.StackFrame> it = frames.iterator();
            it.next();
            return it.next();
        });
        String methodName = frame.getMethodName();
        try {
            String withoutSet = methodName.substring(3);
            String fieldName = Character.toLowerCase(withoutSet.charAt(0)) + withoutSet.substring(1);
            Class<?> declaringClass = frame.getDeclaringClass();
            Field field = frame.getDeclaringClass().getDeclaredField(fieldName);
            String getterName = "get" + withoutSet;
            Method getter = declaringClass.getMethod(getterName);
            Object value = getter.invoke(this);
            this.modifiedFields.put(field.getName(), value);
        } catch (Exception e) {
            throw new RuntimeException("unable to find field!");
        }
    }

    @JsonIgnore
    @XmlTransient
    public void resetModifiedFields() {
        this.modifiedFields.clear();
    }

    @JsonIgnore
    @XmlTransient
    public Map<String, Object> getModifiedFields() {
        return this.modifiedFields;
    }
}
