package org.fk.core.mapper;

import org.fk.codegen.dto.AbstractDTO;
import org.jooq.Field;
import org.jooq.Record;
import org.jooq.UpdatableRecord;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class RecordToViewMapper<E extends AbstractDTO, I extends UpdatableRecord<I>, K extends Serializable> {

    private final Class<E> clazz;

    private final Class<I> recordClazz;
    private final Field<K> groupField;
    private final List<Field<?>> uniqueFields;

        public RecordToViewMapper(
            Class<E> clazz,
            Class<I> recordClazz,
            Field<K> groupField,
            List<Field<?>> uniqueFields) {
        this.clazz = clazz;
        this.recordClazz = recordClazz;
        this.groupField = groupField;
        this.uniqueFields = uniqueFields;
    }

    /**
     * Helper-function that filters the given records to return a list,
     * that contains each record that fulfills the unique-criteria only once.
     *
     * @param records records
     * @return filtered records.
     */
    private Map<K, List<I>> filterRecordsByUniqueFieldsAndGroupByIdField(List<I> records) {
        final Map<String, I> uniqueRecordMap = new LinkedHashMap<>();
        for (I record : records) {
            boolean searchNotFound = false;
            StringBuilder key = new StringBuilder();
            for (Field<?> uniqueField : uniqueFields) {
                Object found = record.getValue(uniqueField);
                if (found == null) {
                    searchNotFound = true;
                    break;
                } else {
                    String test = found.toString();
                    key.append(test);
                }
            }
            if (!searchNotFound) {
                uniqueRecordMap.put(key.toString(), record);
            }
        }
        final List<I> uniqueRecords = uniqueRecordMap.values().stream().toList();
        final Map<K, List<I>> result = new LinkedHashMap<>();
        for (I record : uniqueRecords) {
            K key = record.getValue(groupField);
            if(result.containsKey(key)){
                List<I> list = result.get(key);
                list.add(record);
            } else {
                List<I> list = new ArrayList<I>();
                list.add(record);
                result.put(key, list);
            }
        }
        return result;
    }

    /**
     * Convert the given records to the expected DTO class type
     * @param records records
     * @return dtos
     */
    private List<E> convertRecordsToView(List<I> records) {
        List<E> viewDTOs = new ArrayList<>();
        for (I record : records) {
            try {
                E dto = record.into(clazz.getDeclaredConstructor().newInstance());
                viewDTOs.add(dto);
            } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        }
        return viewDTOs;
    }

    public List<E> extractRecords(List<I> records) throws RuntimeException {
        if (records.isEmpty()) {
            return new ArrayList<>();
        }
        Map<K, List<I>> map = filterRecordsByUniqueFieldsAndGroupByIdField(records);
        List<I> filteredRecords = new ArrayList<>();
        for (List<I> v : map.values()) {
            // we expect that we always have exactly 1 record in this case per key.
            filteredRecords.add(v.getFirst());
        }
        return convertRecordsToView(filteredRecords);
    }
    public Map<K, List<E>> extractRecordsGroupedBy(List<I> records) throws RuntimeException {
        if (records.isEmpty()) {
            return new LinkedHashMap<>();
        }
        Map<K, List<I>> map = filterRecordsByUniqueFieldsAndGroupByIdField(records);
        Map<K, List<E>> convertedMap = new LinkedHashMap<>();
        for (Map.Entry<K, List<I>> entry : map.entrySet() ) {
            List<E> viewDTOs = convertRecordsToView(entry.getValue());
            convertedMap.put(entry.getKey(), viewDTOs);
        }
        return convertedMap;
    }
}