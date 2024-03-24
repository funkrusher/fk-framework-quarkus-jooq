package org.fk.core.dao;

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
    private Map<K, List<Record>> filterRecordsByUniqueFieldsAndGroupByIdField(List<Record> records) {
        final Map<String, Record> uniqueRecordMap = new LinkedHashMap<>();
        for (Record record : records) {
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
        final List<Record> uniqueRecords = uniqueRecordMap.values().stream().toList();
        final Map<K, List<Record>> result = new LinkedHashMap<>();
        for (Record record : uniqueRecords) {
            K key = record.getValue(groupField);
            if(result.containsKey(key)){
                List<Record> list = result.get(key);
                list.add(record);
            } else {
                List<Record> list = new ArrayList<Record>();
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
    private List<E> convertRecordsToView(List<Record> records) {
        List<E> viewDTOs = new ArrayList<>();
        for (Record record : records) {
            try {
                // warning: we must always do "into" tableRecord first, before we do "into" dtoRecord.
                // otherwise we will get the problems of field-overrides in joined tables for fields that
                // have the same name. See: https://stackoverflow.com/questions/52185092/jooq-record-intopojo-class-with-same-fields-name-problem
                I tableRecord = record.into(recordClazz.getDeclaredConstructor().newInstance());
                E dto = tableRecord.into(clazz.getDeclaredConstructor().newInstance());
                viewDTOs.add(dto);
            } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        }
        return viewDTOs;
    }

    public List<E> extractRecords(List<Record> records) throws RuntimeException {
        if (records.isEmpty()) {
            return new ArrayList<>();
        }
        Map<K, List<Record>> map = filterRecordsByUniqueFieldsAndGroupByIdField(records);
        List<Record> filteredRecords = new ArrayList<>();
        for (List<Record> v : map.values()) {
            // we expect that we always have exactly 1 record in this case per key.
            filteredRecords.add(v.getFirst());
        }
        return convertRecordsToView(filteredRecords);
    }
    public Map<K, List<E>> extractRecordsGroupedBy(List<Record> records) throws RuntimeException {
        if (records.isEmpty()) {
            return new LinkedHashMap<>();
        }
        Map<K, List<Record>> map = filterRecordsByUniqueFieldsAndGroupByIdField(records);
        Map<K, List<E>> convertedMap = new LinkedHashMap<>();
        for (Map.Entry<K, List<Record>> entry : map.entrySet() ) {
            List<E> viewDTOs = convertRecordsToView(entry.getValue());
            convertedMap.put(entry.getKey(), viewDTOs);
        }
        return convertedMap;
    }
}