package org.fk.core.dto;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

public class BookKeeper {

    private final Map<String, Object> touchedFields = new HashMap<>();

    private final Object trackingObj;

    private final List<Class<?>> clazzHierarchy = new ArrayList<>();

    public BookKeeper(final Object trackingObj) {
        this.trackingObj = trackingObj;
        Class<?> currentClass = trackingObj.getClass();
        clazzHierarchy.add(currentClass);
        while (!currentClass.equals(Object.class)) {
            currentClass = currentClass.getSuperclass();
            clazzHierarchy.add(currentClass);
        }
    }

    public void touch (final String fieldName) {
        boolean found = false;
        try {
            for (Class<?> clazz : clazzHierarchy) {
                Field field = null;
                try {
                    field = clazz.getDeclaredField(fieldName);
                } catch (Exception e) {
                    // not found in this clazz, go up to next hierarchy.
                    continue;
                }
                String getterName = "get"
                        + Character.toUpperCase(field.getName().charAt(0))
                        + field.getName().substring(1);

                Method getterMethod = clazz.getMethod(getterName);

                Object value = getterMethod.invoke(this.trackingObj);

                this.touchedFields.put(field.getName(), value);

                found = true;
                break;
            }
        } catch (Exception e){
            throw new RuntimeException("Error while searching field in DTO: " + fieldName);
        }
        if (!found) {
            throw new RuntimeException("Field not found in DTO: " + fieldName);
        }
    }

    public void resetTouched() {
        this.touchedFields.clear();
    }

    public Map<String, Object> touched() {
        return this.touchedFields;
    }

    public boolean touchedEquals(DTO other) {
        // Get the touched fields map from the other BookKeeper instance
        Map<String, Object> otherTouchedFields = other.getBookKeeper().touched();

        // Compare sizes of touchedFields maps
        if (this.touchedFields.size() != otherTouchedFields.size()) {
            return false;
        }

        // Iterate over the touchedFields map of the current instance
        for (Map.Entry<String, Object> entry : this.touchedFields.entrySet()) {
            String fieldName = entry.getKey();
            Object value = entry.getValue();

            // Check if the other BookKeeper instance has the same field name
            if (!otherTouchedFields.containsKey(fieldName)) {
                return false;
            }

            // Check if the values for the same field name are equal
            Object otherValue = otherTouchedFields.get(fieldName);
            if (value == null) {
                if (otherValue != null) {
                    return false;
                }
            } else if (!value.equals(otherValue)) {
                return false;
            }
        }

        // All fields are equal
        return true;
    }

    public int touchedHashCode() {
        final int prime = 31;
        int result = 1;

        // Iterate over the touchedFields map
        for (Map.Entry<String, Object> entry : this.touchedFields.entrySet()) {
            String fieldName = entry.getKey();
            Object value = entry.getValue();

            // Calculate hash code for each field name and value pair
            int fieldValueHashCode = (value == null) ? 0 : value.hashCode();
            result = prime * result + fieldValueHashCode;
        }
        return result;
    }

    public String touchedToString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{\n");
        toStringHelper(sb, this.touchedFields, 1);
        sb.append("}");
        return sb.toString();
    }

    public void toStringHelper(StringBuilder sb, Map<String, Object> map, int level) {
        String indentation = "    ".repeat(level);
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            sb.append(indentation).append(entry.getKey()).append(": ");
            Object value = entry.getValue();
            if (value instanceof Map) {
                sb.append("{\n");
                toStringHelper(sb, (Map<String, Object>) value, level + 1);
                sb.append(indentation).append("}\n");
            } else if (value instanceof List) {
                sb.append("[\n");
                List<Object> list = (List<Object>) value;
                for (Object obj : list) {
                    if (obj instanceof DTO) {
                        ((DTO) obj).getBookKeeper().toStringHelper(sb, ((DTO) obj).getBookKeeper().touchedFields, level + 1);
                    } else {
                        sb.append(indentation).append("    ").append(obj).append(",\n");
                    }
                }
                sb.append(indentation).append("]\n");
            } else {
                sb.append(value).append(",\n");
            }
        }
    }
}
