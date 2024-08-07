/*
 * This file is generated by jOOQ.
 */
package org.fk.database1.testshop.tables.records;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

import org.fk.database1.testshop.tables.QrtzSimpropTriggers;
import org.fk.database1.testshop.tables.interfaces.IQrtzSimpropTriggers;
import org.jooq.Record3;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class QrtzSimpropTriggersRecord extends UpdatableRecordImpl<QrtzSimpropTriggersRecord> implements IQrtzSimpropTriggers {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>testshop.QRTZ_SIMPROP_TRIGGERS.SCHED_NAME</code>.
     */
    @Override
    public QrtzSimpropTriggersRecord setSCHED_NAME(String value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>testshop.QRTZ_SIMPROP_TRIGGERS.SCHED_NAME</code>.
     */
    @NotNull
    @Size(max = 120)
    @Override
    public String getSCHED_NAME() {
        return (String) get(0);
    }

    /**
     * Setter for <code>testshop.QRTZ_SIMPROP_TRIGGERS.TRIGGER_NAME</code>.
     */
    @Override
    public QrtzSimpropTriggersRecord setTRIGGER_NAME(String value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>testshop.QRTZ_SIMPROP_TRIGGERS.TRIGGER_NAME</code>.
     */
    @NotNull
    @Size(max = 190)
    @Override
    public String getTRIGGER_NAME() {
        return (String) get(1);
    }

    /**
     * Setter for <code>testshop.QRTZ_SIMPROP_TRIGGERS.TRIGGER_GROUP</code>.
     */
    @Override
    public QrtzSimpropTriggersRecord setTRIGGER_GROUP(String value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for <code>testshop.QRTZ_SIMPROP_TRIGGERS.TRIGGER_GROUP</code>.
     */
    @NotNull
    @Size(max = 190)
    @Override
    public String getTRIGGER_GROUP() {
        return (String) get(2);
    }

    /**
     * Setter for <code>testshop.QRTZ_SIMPROP_TRIGGERS.STR_PROP_1</code>.
     */
    @Override
    public QrtzSimpropTriggersRecord setSTR_PROP_1(String value) {
        set(3, value);
        return this;
    }

    /**
     * Getter for <code>testshop.QRTZ_SIMPROP_TRIGGERS.STR_PROP_1</code>.
     */
    @Size(max = 512)
    @Override
    public String getSTR_PROP_1() {
        return (String) get(3);
    }

    /**
     * Setter for <code>testshop.QRTZ_SIMPROP_TRIGGERS.STR_PROP_2</code>.
     */
    @Override
    public QrtzSimpropTriggersRecord setSTR_PROP_2(String value) {
        set(4, value);
        return this;
    }

    /**
     * Getter for <code>testshop.QRTZ_SIMPROP_TRIGGERS.STR_PROP_2</code>.
     */
    @Size(max = 512)
    @Override
    public String getSTR_PROP_2() {
        return (String) get(4);
    }

    /**
     * Setter for <code>testshop.QRTZ_SIMPROP_TRIGGERS.STR_PROP_3</code>.
     */
    @Override
    public QrtzSimpropTriggersRecord setSTR_PROP_3(String value) {
        set(5, value);
        return this;
    }

    /**
     * Getter for <code>testshop.QRTZ_SIMPROP_TRIGGERS.STR_PROP_3</code>.
     */
    @Size(max = 512)
    @Override
    public String getSTR_PROP_3() {
        return (String) get(5);
    }

    /**
     * Setter for <code>testshop.QRTZ_SIMPROP_TRIGGERS.INT_PROP_1</code>.
     */
    @Override
    public QrtzSimpropTriggersRecord setINT_PROP_1(Integer value) {
        set(6, value);
        return this;
    }

    /**
     * Getter for <code>testshop.QRTZ_SIMPROP_TRIGGERS.INT_PROP_1</code>.
     */
    @Override
    public Integer getINT_PROP_1() {
        return (Integer) get(6);
    }

    /**
     * Setter for <code>testshop.QRTZ_SIMPROP_TRIGGERS.INT_PROP_2</code>.
     */
    @Override
    public QrtzSimpropTriggersRecord setINT_PROP_2(Integer value) {
        set(7, value);
        return this;
    }

    /**
     * Getter for <code>testshop.QRTZ_SIMPROP_TRIGGERS.INT_PROP_2</code>.
     */
    @Override
    public Integer getINT_PROP_2() {
        return (Integer) get(7);
    }

    /**
     * Setter for <code>testshop.QRTZ_SIMPROP_TRIGGERS.LONG_PROP_1</code>.
     */
    @Override
    public QrtzSimpropTriggersRecord setLONG_PROP_1(Long value) {
        set(8, value);
        return this;
    }

    /**
     * Getter for <code>testshop.QRTZ_SIMPROP_TRIGGERS.LONG_PROP_1</code>.
     */
    @Override
    public Long getLONG_PROP_1() {
        return (Long) get(8);
    }

    /**
     * Setter for <code>testshop.QRTZ_SIMPROP_TRIGGERS.LONG_PROP_2</code>.
     */
    @Override
    public QrtzSimpropTriggersRecord setLONG_PROP_2(Long value) {
        set(9, value);
        return this;
    }

    /**
     * Getter for <code>testshop.QRTZ_SIMPROP_TRIGGERS.LONG_PROP_2</code>.
     */
    @Override
    public Long getLONG_PROP_2() {
        return (Long) get(9);
    }

    /**
     * Setter for <code>testshop.QRTZ_SIMPROP_TRIGGERS.DEC_PROP_1</code>.
     */
    @Override
    public QrtzSimpropTriggersRecord setDEC_PROP_1(BigDecimal value) {
        set(10, value);
        return this;
    }

    /**
     * Getter for <code>testshop.QRTZ_SIMPROP_TRIGGERS.DEC_PROP_1</code>.
     */
    @Override
    public BigDecimal getDEC_PROP_1() {
        return (BigDecimal) get(10);
    }

    /**
     * Setter for <code>testshop.QRTZ_SIMPROP_TRIGGERS.DEC_PROP_2</code>.
     */
    @Override
    public QrtzSimpropTriggersRecord setDEC_PROP_2(BigDecimal value) {
        set(11, value);
        return this;
    }

    /**
     * Getter for <code>testshop.QRTZ_SIMPROP_TRIGGERS.DEC_PROP_2</code>.
     */
    @Override
    public BigDecimal getDEC_PROP_2() {
        return (BigDecimal) get(11);
    }

    /**
     * Setter for <code>testshop.QRTZ_SIMPROP_TRIGGERS.BOOL_PROP_1</code>.
     */
    @Override
    public QrtzSimpropTriggersRecord setBOOL_PROP_1(String value) {
        set(12, value);
        return this;
    }

    /**
     * Getter for <code>testshop.QRTZ_SIMPROP_TRIGGERS.BOOL_PROP_1</code>.
     */
    @Size(max = 1)
    @Override
    public String getBOOL_PROP_1() {
        return (String) get(12);
    }

    /**
     * Setter for <code>testshop.QRTZ_SIMPROP_TRIGGERS.BOOL_PROP_2</code>.
     */
    @Override
    public QrtzSimpropTriggersRecord setBOOL_PROP_2(String value) {
        set(13, value);
        return this;
    }

    /**
     * Getter for <code>testshop.QRTZ_SIMPROP_TRIGGERS.BOOL_PROP_2</code>.
     */
    @Size(max = 1)
    @Override
    public String getBOOL_PROP_2() {
        return (String) get(13);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record3<String, String, String> key() {
        return (Record3) super.key();
    }

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    @Override
    public void from(IQrtzSimpropTriggers from) {
        setSCHED_NAME(from.getSCHED_NAME());
        setTRIGGER_NAME(from.getTRIGGER_NAME());
        setTRIGGER_GROUP(from.getTRIGGER_GROUP());
        setSTR_PROP_1(from.getSTR_PROP_1());
        setSTR_PROP_2(from.getSTR_PROP_2());
        setSTR_PROP_3(from.getSTR_PROP_3());
        setINT_PROP_1(from.getINT_PROP_1());
        setINT_PROP_2(from.getINT_PROP_2());
        setLONG_PROP_1(from.getLONG_PROP_1());
        setLONG_PROP_2(from.getLONG_PROP_2());
        setDEC_PROP_1(from.getDEC_PROP_1());
        setDEC_PROP_2(from.getDEC_PROP_2());
        setBOOL_PROP_1(from.getBOOL_PROP_1());
        setBOOL_PROP_2(from.getBOOL_PROP_2());
        resetChangedOnNotNull();
    }

    @Override
    public <E extends IQrtzSimpropTriggers> E into(E into) {
        into.from(this);
        return into;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached QrtzSimpropTriggersRecord
     */
    public QrtzSimpropTriggersRecord() {
        super(QrtzSimpropTriggers.QRTZ_SIMPROP_TRIGGERS);
    }

    /**
     * Create a detached, initialised QrtzSimpropTriggersRecord
     */
    public QrtzSimpropTriggersRecord(String SCHED_NAME, String TRIGGER_NAME, String TRIGGER_GROUP, String STR_PROP_1, String STR_PROP_2, String STR_PROP_3, Integer INT_PROP_1, Integer INT_PROP_2, Long LONG_PROP_1, Long LONG_PROP_2, BigDecimal DEC_PROP_1, BigDecimal DEC_PROP_2, String BOOL_PROP_1, String BOOL_PROP_2) {
        super(QrtzSimpropTriggers.QRTZ_SIMPROP_TRIGGERS);

        setSCHED_NAME(SCHED_NAME);
        setTRIGGER_NAME(TRIGGER_NAME);
        setTRIGGER_GROUP(TRIGGER_GROUP);
        setSTR_PROP_1(STR_PROP_1);
        setSTR_PROP_2(STR_PROP_2);
        setSTR_PROP_3(STR_PROP_3);
        setINT_PROP_1(INT_PROP_1);
        setINT_PROP_2(INT_PROP_2);
        setLONG_PROP_1(LONG_PROP_1);
        setLONG_PROP_2(LONG_PROP_2);
        setDEC_PROP_1(DEC_PROP_1);
        setDEC_PROP_2(DEC_PROP_2);
        setBOOL_PROP_1(BOOL_PROP_1);
        setBOOL_PROP_2(BOOL_PROP_2);
        resetChangedOnNotNull();
    }

    /**
     * Create a detached, initialised QrtzSimpropTriggersRecord
     */
    public QrtzSimpropTriggersRecord(org.fk.database1.testshop.tables.pojos.QrtzSimpropTriggers value) {
        super(QrtzSimpropTriggers.QRTZ_SIMPROP_TRIGGERS);

        if (value != null) {
            setSCHED_NAME(value.getSCHED_NAME());
            setTRIGGER_NAME(value.getTRIGGER_NAME());
            setTRIGGER_GROUP(value.getTRIGGER_GROUP());
            setSTR_PROP_1(value.getSTR_PROP_1());
            setSTR_PROP_2(value.getSTR_PROP_2());
            setSTR_PROP_3(value.getSTR_PROP_3());
            setINT_PROP_1(value.getINT_PROP_1());
            setINT_PROP_2(value.getINT_PROP_2());
            setLONG_PROP_1(value.getLONG_PROP_1());
            setLONG_PROP_2(value.getLONG_PROP_2());
            setDEC_PROP_1(value.getDEC_PROP_1());
            setDEC_PROP_2(value.getDEC_PROP_2());
            setBOOL_PROP_1(value.getBOOL_PROP_1());
            setBOOL_PROP_2(value.getBOOL_PROP_2());
            resetChangedOnNotNull();
        }
    }
}
