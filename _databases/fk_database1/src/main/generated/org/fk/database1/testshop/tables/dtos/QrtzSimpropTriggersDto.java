package org.fk.database1.testshop.tables.dtos;

import org.fk.core.dto.DTO;
import org.fk.core.dto.BookKeeper;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import jakarta.xml.bind.annotation.XmlTransient;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

import org.fk.database1.testshop.tables.interfaces.IQrtzSimpropTriggers;

/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class QrtzSimpropTriggersDto implements IQrtzSimpropTriggers, DTO {

    private static final long serialVersionUID = 1L;

    // -------------------------------------------------------------------------
    // Database-Fields (must exist in the associated database table)
    // -------------------------------------------------------------------------
 
    private String SCHED_NAME;
    private String TRIGGER_NAME;
    private String TRIGGER_GROUP;
    private String STR_PROP_1;
    private String STR_PROP_2;
    private String STR_PROP_3;
    private Integer INT_PROP_1;
    private Integer INT_PROP_2;
    private Long LONG_PROP_1;
    private Long LONG_PROP_2;
    private BigDecimal DEC_PROP_1;
    private BigDecimal DEC_PROP_2;
    private String BOOL_PROP_1;
    private String BOOL_PROP_2;

    // -------------------------------------------------------------------------
    // Non-Database-Fields (please define your additional fields here)
    // -------------------------------------------------------------------------
 
    // -------------------------------------------------------------------------
    // Constructor(s)
    // -------------------------------------------------------------------------
 
    public QrtzSimpropTriggersDto() {}

    public QrtzSimpropTriggersDto(IQrtzSimpropTriggers value) {
        this.setSCHED_NAME(value.getSCHED_NAME());
        this.setTRIGGER_NAME(value.getTRIGGER_NAME());
        this.setTRIGGER_GROUP(value.getTRIGGER_GROUP());
        this.setSTR_PROP_1(value.getSTR_PROP_1());
        this.setSTR_PROP_2(value.getSTR_PROP_2());
        this.setSTR_PROP_3(value.getSTR_PROP_3());
        this.setINT_PROP_1(value.getINT_PROP_1());
        this.setINT_PROP_2(value.getINT_PROP_2());
        this.setLONG_PROP_1(value.getLONG_PROP_1());
        this.setLONG_PROP_2(value.getLONG_PROP_2());
        this.setDEC_PROP_1(value.getDEC_PROP_1());
        this.setDEC_PROP_2(value.getDEC_PROP_2());
        this.setBOOL_PROP_1(value.getBOOL_PROP_1());
        this.setBOOL_PROP_2(value.getBOOL_PROP_2());
    }

    public QrtzSimpropTriggersDto(
        String SCHED_NAME,
        String TRIGGER_NAME,
        String TRIGGER_GROUP,
        String STR_PROP_1,
        String STR_PROP_2,
        String STR_PROP_3,
        Integer INT_PROP_1,
        Integer INT_PROP_2,
        Long LONG_PROP_1,
        Long LONG_PROP_2,
        BigDecimal DEC_PROP_1,
        BigDecimal DEC_PROP_2,
        String BOOL_PROP_1,
        String BOOL_PROP_2
    ) {
        this.setSCHED_NAME(SCHED_NAME);
        this.setTRIGGER_NAME(TRIGGER_NAME);
        this.setTRIGGER_GROUP(TRIGGER_GROUP);
        this.setSTR_PROP_1(STR_PROP_1);
        this.setSTR_PROP_2(STR_PROP_2);
        this.setSTR_PROP_3(STR_PROP_3);
        this.setINT_PROP_1(INT_PROP_1);
        this.setINT_PROP_2(INT_PROP_2);
        this.setLONG_PROP_1(LONG_PROP_1);
        this.setLONG_PROP_2(LONG_PROP_2);
        this.setDEC_PROP_1(DEC_PROP_1);
        this.setDEC_PROP_2(DEC_PROP_2);
        this.setBOOL_PROP_1(BOOL_PROP_1);
        this.setBOOL_PROP_2(BOOL_PROP_2);
    }

    // -------------------------------------------------------------------------
    // Database-Fields Setters/Getters
    // -------------------------------------------------------------------------
 
    /**
     * Getter for <code>testshop.QRTZ_SIMPROP_TRIGGERS.SCHED_NAME</code>.
     */
    @NotNull
    @Size(max = 120)
    @Override
    public String getSCHED_NAME() {
        return this.SCHED_NAME;
    }

    /**
     * Setter for <code>testshop.QRTZ_SIMPROP_TRIGGERS.SCHED_NAME</code>.
     */
    @Override
    public QrtzSimpropTriggersDto setSCHED_NAME(String SCHED_NAME) {
        this.SCHED_NAME = SCHED_NAME;
        this.keeper.touch("SCHED_NAME");
        return this;
    }

    /**
     * Getter for <code>testshop.QRTZ_SIMPROP_TRIGGERS.TRIGGER_NAME</code>.
     */
    @NotNull
    @Size(max = 190)
    @Override
    public String getTRIGGER_NAME() {
        return this.TRIGGER_NAME;
    }

    /**
     * Setter for <code>testshop.QRTZ_SIMPROP_TRIGGERS.TRIGGER_NAME</code>.
     */
    @Override
    public QrtzSimpropTriggersDto setTRIGGER_NAME(String TRIGGER_NAME) {
        this.TRIGGER_NAME = TRIGGER_NAME;
        this.keeper.touch("TRIGGER_NAME");
        return this;
    }

    /**
     * Getter for <code>testshop.QRTZ_SIMPROP_TRIGGERS.TRIGGER_GROUP</code>.
     */
    @NotNull
    @Size(max = 190)
    @Override
    public String getTRIGGER_GROUP() {
        return this.TRIGGER_GROUP;
    }

    /**
     * Setter for <code>testshop.QRTZ_SIMPROP_TRIGGERS.TRIGGER_GROUP</code>.
     */
    @Override
    public QrtzSimpropTriggersDto setTRIGGER_GROUP(String TRIGGER_GROUP) {
        this.TRIGGER_GROUP = TRIGGER_GROUP;
        this.keeper.touch("TRIGGER_GROUP");
        return this;
    }

    /**
     * Getter for <code>testshop.QRTZ_SIMPROP_TRIGGERS.STR_PROP_1</code>.
     */
    @Size(max = 512)
    @Override
    public String getSTR_PROP_1() {
        return this.STR_PROP_1;
    }

    /**
     * Setter for <code>testshop.QRTZ_SIMPROP_TRIGGERS.STR_PROP_1</code>.
     */
    @Override
    public QrtzSimpropTriggersDto setSTR_PROP_1(String STR_PROP_1) {
        this.STR_PROP_1 = STR_PROP_1;
        this.keeper.touch("STR_PROP_1");
        return this;
    }

    /**
     * Getter for <code>testshop.QRTZ_SIMPROP_TRIGGERS.STR_PROP_2</code>.
     */
    @Size(max = 512)
    @Override
    public String getSTR_PROP_2() {
        return this.STR_PROP_2;
    }

    /**
     * Setter for <code>testshop.QRTZ_SIMPROP_TRIGGERS.STR_PROP_2</code>.
     */
    @Override
    public QrtzSimpropTriggersDto setSTR_PROP_2(String STR_PROP_2) {
        this.STR_PROP_2 = STR_PROP_2;
        this.keeper.touch("STR_PROP_2");
        return this;
    }

    /**
     * Getter for <code>testshop.QRTZ_SIMPROP_TRIGGERS.STR_PROP_3</code>.
     */
    @Size(max = 512)
    @Override
    public String getSTR_PROP_3() {
        return this.STR_PROP_3;
    }

    /**
     * Setter for <code>testshop.QRTZ_SIMPROP_TRIGGERS.STR_PROP_3</code>.
     */
    @Override
    public QrtzSimpropTriggersDto setSTR_PROP_3(String STR_PROP_3) {
        this.STR_PROP_3 = STR_PROP_3;
        this.keeper.touch("STR_PROP_3");
        return this;
    }

    /**
     * Getter for <code>testshop.QRTZ_SIMPROP_TRIGGERS.INT_PROP_1</code>.
     */
    @Override
    public Integer getINT_PROP_1() {
        return this.INT_PROP_1;
    }

    /**
     * Setter for <code>testshop.QRTZ_SIMPROP_TRIGGERS.INT_PROP_1</code>.
     */
    @Override
    public QrtzSimpropTriggersDto setINT_PROP_1(Integer INT_PROP_1) {
        this.INT_PROP_1 = INT_PROP_1;
        this.keeper.touch("INT_PROP_1");
        return this;
    }

    /**
     * Getter for <code>testshop.QRTZ_SIMPROP_TRIGGERS.INT_PROP_2</code>.
     */
    @Override
    public Integer getINT_PROP_2() {
        return this.INT_PROP_2;
    }

    /**
     * Setter for <code>testshop.QRTZ_SIMPROP_TRIGGERS.INT_PROP_2</code>.
     */
    @Override
    public QrtzSimpropTriggersDto setINT_PROP_2(Integer INT_PROP_2) {
        this.INT_PROP_2 = INT_PROP_2;
        this.keeper.touch("INT_PROP_2");
        return this;
    }

    /**
     * Getter for <code>testshop.QRTZ_SIMPROP_TRIGGERS.LONG_PROP_1</code>.
     */
    @Override
    public Long getLONG_PROP_1() {
        return this.LONG_PROP_1;
    }

    /**
     * Setter for <code>testshop.QRTZ_SIMPROP_TRIGGERS.LONG_PROP_1</code>.
     */
    @Override
    public QrtzSimpropTriggersDto setLONG_PROP_1(Long LONG_PROP_1) {
        this.LONG_PROP_1 = LONG_PROP_1;
        this.keeper.touch("LONG_PROP_1");
        return this;
    }

    /**
     * Getter for <code>testshop.QRTZ_SIMPROP_TRIGGERS.LONG_PROP_2</code>.
     */
    @Override
    public Long getLONG_PROP_2() {
        return this.LONG_PROP_2;
    }

    /**
     * Setter for <code>testshop.QRTZ_SIMPROP_TRIGGERS.LONG_PROP_2</code>.
     */
    @Override
    public QrtzSimpropTriggersDto setLONG_PROP_2(Long LONG_PROP_2) {
        this.LONG_PROP_2 = LONG_PROP_2;
        this.keeper.touch("LONG_PROP_2");
        return this;
    }

    /**
     * Getter for <code>testshop.QRTZ_SIMPROP_TRIGGERS.DEC_PROP_1</code>.
     */
    @Override
    public BigDecimal getDEC_PROP_1() {
        return this.DEC_PROP_1;
    }

    /**
     * Setter for <code>testshop.QRTZ_SIMPROP_TRIGGERS.DEC_PROP_1</code>.
     */
    @Override
    public QrtzSimpropTriggersDto setDEC_PROP_1(BigDecimal DEC_PROP_1) {
        this.DEC_PROP_1 = DEC_PROP_1;
        this.keeper.touch("DEC_PROP_1");
        return this;
    }

    /**
     * Getter for <code>testshop.QRTZ_SIMPROP_TRIGGERS.DEC_PROP_2</code>.
     */
    @Override
    public BigDecimal getDEC_PROP_2() {
        return this.DEC_PROP_2;
    }

    /**
     * Setter for <code>testshop.QRTZ_SIMPROP_TRIGGERS.DEC_PROP_2</code>.
     */
    @Override
    public QrtzSimpropTriggersDto setDEC_PROP_2(BigDecimal DEC_PROP_2) {
        this.DEC_PROP_2 = DEC_PROP_2;
        this.keeper.touch("DEC_PROP_2");
        return this;
    }

    /**
     * Getter for <code>testshop.QRTZ_SIMPROP_TRIGGERS.BOOL_PROP_1</code>.
     */
    @Size(max = 1)
    @Override
    public String getBOOL_PROP_1() {
        return this.BOOL_PROP_1;
    }

    /**
     * Setter for <code>testshop.QRTZ_SIMPROP_TRIGGERS.BOOL_PROP_1</code>.
     */
    @Override
    public QrtzSimpropTriggersDto setBOOL_PROP_1(String BOOL_PROP_1) {
        this.BOOL_PROP_1 = BOOL_PROP_1;
        this.keeper.touch("BOOL_PROP_1");
        return this;
    }

    /**
     * Getter for <code>testshop.QRTZ_SIMPROP_TRIGGERS.BOOL_PROP_2</code>.
     */
    @Size(max = 1)
    @Override
    public String getBOOL_PROP_2() {
        return this.BOOL_PROP_2;
    }

    /**
     * Setter for <code>testshop.QRTZ_SIMPROP_TRIGGERS.BOOL_PROP_2</code>.
     */
    @Override
    public QrtzSimpropTriggersDto setBOOL_PROP_2(String BOOL_PROP_2) {
        this.BOOL_PROP_2 = BOOL_PROP_2;
        this.keeper.touch("BOOL_PROP_2");
        return this;
    }

    // -------------------------------------------------------------------------
    // Non-Database-Fields Setters/Getters (please define here)
    // -------------------------------------------------------------------------
 
    // -------------------------------------------------------------------------
    // ToString, Equals, HashCode
    // -------------------------------------------------------------------------
 
    @Override
    public String toString() {
        return keeper.touchedToString();
    }
 
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final DTO other = (DTO) obj;
        return this.keeper.touchedEquals(other);
    }
 
    @Override
    public int hashCode() {
        return this.keeper.touchedHashCode();
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
    }
    @Override
    public <E extends IQrtzSimpropTriggers> E into(E into) {
        into.from(this);
        return into;
    }

    // -------------------------------------------------------------------------
    // BookKeeper (Patching Updates Support)
    // -------------------------------------------------------------------------
     
    @JsonIgnore
    @XmlTransient
    protected transient BookKeeper keeper = new BookKeeper(this);
 
    @JsonIgnore
    @XmlTransient
    public BookKeeper getBookKeeper() {
        return keeper;
    }
}
