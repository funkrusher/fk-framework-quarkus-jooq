/*
 * This file is generated by jOOQ.
 */
package org.fk.codegen.testshop.tables.dtos;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

import org.fk.codegen.dto.AbstractDTO;
import org.fk.codegen.testshop.tables.interfaces.IQrtzSimpropTriggers;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class QrtzSimpropTriggers extends AbstractDTO implements IQrtzSimpropTriggers {

    private static final long serialVersionUID = 1L;

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

    public QrtzSimpropTriggers() {}

    public QrtzSimpropTriggers(IQrtzSimpropTriggers value) {
        this.SCHED_NAME = value.getSCHED_NAME();
        this.TRIGGER_NAME = value.getTRIGGER_NAME();
        this.TRIGGER_GROUP = value.getTRIGGER_GROUP();
        this.STR_PROP_1 = value.getSTR_PROP_1();
        this.STR_PROP_2 = value.getSTR_PROP_2();
        this.STR_PROP_3 = value.getSTR_PROP_3();
        this.INT_PROP_1 = value.getINT_PROP_1();
        this.INT_PROP_2 = value.getINT_PROP_2();
        this.LONG_PROP_1 = value.getLONG_PROP_1();
        this.LONG_PROP_2 = value.getLONG_PROP_2();
        this.DEC_PROP_1 = value.getDEC_PROP_1();
        this.DEC_PROP_2 = value.getDEC_PROP_2();
        this.BOOL_PROP_1 = value.getBOOL_PROP_1();
        this.BOOL_PROP_2 = value.getBOOL_PROP_2();
    }

    public QrtzSimpropTriggers(
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
        this.SCHED_NAME = SCHED_NAME;
        this.TRIGGER_NAME = TRIGGER_NAME;
        this.TRIGGER_GROUP = TRIGGER_GROUP;
        this.STR_PROP_1 = STR_PROP_1;
        this.STR_PROP_2 = STR_PROP_2;
        this.STR_PROP_3 = STR_PROP_3;
        this.INT_PROP_1 = INT_PROP_1;
        this.INT_PROP_2 = INT_PROP_2;
        this.LONG_PROP_1 = LONG_PROP_1;
        this.LONG_PROP_2 = LONG_PROP_2;
        this.DEC_PROP_1 = DEC_PROP_1;
        this.DEC_PROP_2 = DEC_PROP_2;
        this.BOOL_PROP_1 = BOOL_PROP_1;
        this.BOOL_PROP_2 = BOOL_PROP_2;
    }

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
    public void setSCHED_NAME(String SCHED_NAME) {
        this.SCHED_NAME = SCHED_NAME;
        this.touch();
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
    public void setTRIGGER_NAME(String TRIGGER_NAME) {
        this.TRIGGER_NAME = TRIGGER_NAME;
        this.touch();
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
    public void setTRIGGER_GROUP(String TRIGGER_GROUP) {
        this.TRIGGER_GROUP = TRIGGER_GROUP;
        this.touch();
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
    public void setSTR_PROP_1(String STR_PROP_1) {
        this.STR_PROP_1 = STR_PROP_1;
        this.touch();
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
    public void setSTR_PROP_2(String STR_PROP_2) {
        this.STR_PROP_2 = STR_PROP_2;
        this.touch();
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
    public void setSTR_PROP_3(String STR_PROP_3) {
        this.STR_PROP_3 = STR_PROP_3;
        this.touch();
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
    public void setINT_PROP_1(Integer INT_PROP_1) {
        this.INT_PROP_1 = INT_PROP_1;
        this.touch();
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
    public void setINT_PROP_2(Integer INT_PROP_2) {
        this.INT_PROP_2 = INT_PROP_2;
        this.touch();
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
    public void setLONG_PROP_1(Long LONG_PROP_1) {
        this.LONG_PROP_1 = LONG_PROP_1;
        this.touch();
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
    public void setLONG_PROP_2(Long LONG_PROP_2) {
        this.LONG_PROP_2 = LONG_PROP_2;
        this.touch();
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
    public void setDEC_PROP_1(BigDecimal DEC_PROP_1) {
        this.DEC_PROP_1 = DEC_PROP_1;
        this.touch();
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
    public void setDEC_PROP_2(BigDecimal DEC_PROP_2) {
        this.DEC_PROP_2 = DEC_PROP_2;
        this.touch();
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
    public void setBOOL_PROP_1(String BOOL_PROP_1) {
        this.BOOL_PROP_1 = BOOL_PROP_1;
        this.touch();
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
    public void setBOOL_PROP_2(String BOOL_PROP_2) {
        this.BOOL_PROP_2 = BOOL_PROP_2;
        this.touch();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final QrtzSimpropTriggers other = (QrtzSimpropTriggers) obj;
        if (this.SCHED_NAME == null) {
            if (other.SCHED_NAME != null)
                return false;
        }
        else if (!this.SCHED_NAME.equals(other.SCHED_NAME))
            return false;
        if (this.TRIGGER_NAME == null) {
            if (other.TRIGGER_NAME != null)
                return false;
        }
        else if (!this.TRIGGER_NAME.equals(other.TRIGGER_NAME))
            return false;
        if (this.TRIGGER_GROUP == null) {
            if (other.TRIGGER_GROUP != null)
                return false;
        }
        else if (!this.TRIGGER_GROUP.equals(other.TRIGGER_GROUP))
            return false;
        if (this.STR_PROP_1 == null) {
            if (other.STR_PROP_1 != null)
                return false;
        }
        else if (!this.STR_PROP_1.equals(other.STR_PROP_1))
            return false;
        if (this.STR_PROP_2 == null) {
            if (other.STR_PROP_2 != null)
                return false;
        }
        else if (!this.STR_PROP_2.equals(other.STR_PROP_2))
            return false;
        if (this.STR_PROP_3 == null) {
            if (other.STR_PROP_3 != null)
                return false;
        }
        else if (!this.STR_PROP_3.equals(other.STR_PROP_3))
            return false;
        if (this.INT_PROP_1 == null) {
            if (other.INT_PROP_1 != null)
                return false;
        }
        else if (!this.INT_PROP_1.equals(other.INT_PROP_1))
            return false;
        if (this.INT_PROP_2 == null) {
            if (other.INT_PROP_2 != null)
                return false;
        }
        else if (!this.INT_PROP_2.equals(other.INT_PROP_2))
            return false;
        if (this.LONG_PROP_1 == null) {
            if (other.LONG_PROP_1 != null)
                return false;
        }
        else if (!this.LONG_PROP_1.equals(other.LONG_PROP_1))
            return false;
        if (this.LONG_PROP_2 == null) {
            if (other.LONG_PROP_2 != null)
                return false;
        }
        else if (!this.LONG_PROP_2.equals(other.LONG_PROP_2))
            return false;
        if (this.DEC_PROP_1 == null) {
            if (other.DEC_PROP_1 != null)
                return false;
        }
        else if (!this.DEC_PROP_1.equals(other.DEC_PROP_1))
            return false;
        if (this.DEC_PROP_2 == null) {
            if (other.DEC_PROP_2 != null)
                return false;
        }
        else if (!this.DEC_PROP_2.equals(other.DEC_PROP_2))
            return false;
        if (this.BOOL_PROP_1 == null) {
            if (other.BOOL_PROP_1 != null)
                return false;
        }
        else if (!this.BOOL_PROP_1.equals(other.BOOL_PROP_1))
            return false;
        if (this.BOOL_PROP_2 == null) {
            if (other.BOOL_PROP_2 != null)
                return false;
        }
        else if (!this.BOOL_PROP_2.equals(other.BOOL_PROP_2))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.SCHED_NAME == null) ? 0 : this.SCHED_NAME.hashCode());
        result = prime * result + ((this.TRIGGER_NAME == null) ? 0 : this.TRIGGER_NAME.hashCode());
        result = prime * result + ((this.TRIGGER_GROUP == null) ? 0 : this.TRIGGER_GROUP.hashCode());
        result = prime * result + ((this.STR_PROP_1 == null) ? 0 : this.STR_PROP_1.hashCode());
        result = prime * result + ((this.STR_PROP_2 == null) ? 0 : this.STR_PROP_2.hashCode());
        result = prime * result + ((this.STR_PROP_3 == null) ? 0 : this.STR_PROP_3.hashCode());
        result = prime * result + ((this.INT_PROP_1 == null) ? 0 : this.INT_PROP_1.hashCode());
        result = prime * result + ((this.INT_PROP_2 == null) ? 0 : this.INT_PROP_2.hashCode());
        result = prime * result + ((this.LONG_PROP_1 == null) ? 0 : this.LONG_PROP_1.hashCode());
        result = prime * result + ((this.LONG_PROP_2 == null) ? 0 : this.LONG_PROP_2.hashCode());
        result = prime * result + ((this.DEC_PROP_1 == null) ? 0 : this.DEC_PROP_1.hashCode());
        result = prime * result + ((this.DEC_PROP_2 == null) ? 0 : this.DEC_PROP_2.hashCode());
        result = prime * result + ((this.BOOL_PROP_1 == null) ? 0 : this.BOOL_PROP_1.hashCode());
        result = prime * result + ((this.BOOL_PROP_2 == null) ? 0 : this.BOOL_PROP_2.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("QrtzSimpropTriggers (");

        sb.append(SCHED_NAME);
        sb.append(", ").append(TRIGGER_NAME);
        sb.append(", ").append(TRIGGER_GROUP);
        sb.append(", ").append(STR_PROP_1);
        sb.append(", ").append(STR_PROP_2);
        sb.append(", ").append(STR_PROP_3);
        sb.append(", ").append(INT_PROP_1);
        sb.append(", ").append(INT_PROP_2);
        sb.append(", ").append(LONG_PROP_1);
        sb.append(", ").append(LONG_PROP_2);
        sb.append(", ").append(DEC_PROP_1);
        sb.append(", ").append(DEC_PROP_2);
        sb.append(", ").append(BOOL_PROP_1);
        sb.append(", ").append(BOOL_PROP_2);

        sb.append(")");
        return sb.toString();
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
}
