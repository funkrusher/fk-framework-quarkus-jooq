/*
 * This file is generated by jOOQ.
 */
package org.fk.codegen.testshop.tables.dtos;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

import org.fk.codegen.dto.AbstractDTO;
import org.fk.codegen.testshop.tables.interfaces.IDatabasechangelog;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class Databasechangelog extends AbstractDTO implements IDatabasechangelog {

    private static final long serialVersionUID = 1L;

    private String ID;
    private String AUTHOR;
    private String FILENAME;
    private LocalDateTime DATEEXECUTED;
    private Integer ORDEREXECUTED;
    private String EXECTYPE;
    private String MD5SUM;
    private String DESCRIPTION;
    private String COMMENTS;
    private String TAG;
    private String LIQUIBASE;
    private String CONTEXTS;
    private String LABELS;
    private String DEPLOYMENT_ID;

    public Databasechangelog() {}

    public Databasechangelog(IDatabasechangelog value) {
        this.ID = value.getID();
        this.AUTHOR = value.getAUTHOR();
        this.FILENAME = value.getFILENAME();
        this.DATEEXECUTED = value.getDATEEXECUTED();
        this.ORDEREXECUTED = value.getORDEREXECUTED();
        this.EXECTYPE = value.getEXECTYPE();
        this.MD5SUM = value.getMD5SUM();
        this.DESCRIPTION = value.getDESCRIPTION();
        this.COMMENTS = value.getCOMMENTS();
        this.TAG = value.getTAG();
        this.LIQUIBASE = value.getLIQUIBASE();
        this.CONTEXTS = value.getCONTEXTS();
        this.LABELS = value.getLABELS();
        this.DEPLOYMENT_ID = value.getDEPLOYMENT_ID();
    }

    public Databasechangelog(
        String ID,
        String AUTHOR,
        String FILENAME,
        LocalDateTime DATEEXECUTED,
        Integer ORDEREXECUTED,
        String EXECTYPE,
        String MD5SUM,
        String DESCRIPTION,
        String COMMENTS,
        String TAG,
        String LIQUIBASE,
        String CONTEXTS,
        String LABELS,
        String DEPLOYMENT_ID
    ) {
        this.ID = ID;
        this.AUTHOR = AUTHOR;
        this.FILENAME = FILENAME;
        this.DATEEXECUTED = DATEEXECUTED;
        this.ORDEREXECUTED = ORDEREXECUTED;
        this.EXECTYPE = EXECTYPE;
        this.MD5SUM = MD5SUM;
        this.DESCRIPTION = DESCRIPTION;
        this.COMMENTS = COMMENTS;
        this.TAG = TAG;
        this.LIQUIBASE = LIQUIBASE;
        this.CONTEXTS = CONTEXTS;
        this.LABELS = LABELS;
        this.DEPLOYMENT_ID = DEPLOYMENT_ID;
    }

    /**
     * Getter for <code>testshop.DATABASECHANGELOG.ID</code>.
     */
    @NotNull
    @Size(max = 255)
    @Override
    public String getID() {
        return this.ID;
    }

    /**
     * Setter for <code>testshop.DATABASECHANGELOG.ID</code>.
     */
    @Override
    public void setID(String ID) {
        this.ID = ID;
        this.touch();
    }

    /**
     * Getter for <code>testshop.DATABASECHANGELOG.AUTHOR</code>.
     */
    @NotNull
    @Size(max = 255)
    @Override
    public String getAUTHOR() {
        return this.AUTHOR;
    }

    /**
     * Setter for <code>testshop.DATABASECHANGELOG.AUTHOR</code>.
     */
    @Override
    public void setAUTHOR(String AUTHOR) {
        this.AUTHOR = AUTHOR;
        this.touch();
    }

    /**
     * Getter for <code>testshop.DATABASECHANGELOG.FILENAME</code>.
     */
    @NotNull
    @Size(max = 255)
    @Override
    public String getFILENAME() {
        return this.FILENAME;
    }

    /**
     * Setter for <code>testshop.DATABASECHANGELOG.FILENAME</code>.
     */
    @Override
    public void setFILENAME(String FILENAME) {
        this.FILENAME = FILENAME;
        this.touch();
    }

    /**
     * Getter for <code>testshop.DATABASECHANGELOG.DATEEXECUTED</code>.
     */
    @NotNull
    @Override
    public LocalDateTime getDATEEXECUTED() {
        return this.DATEEXECUTED;
    }

    /**
     * Setter for <code>testshop.DATABASECHANGELOG.DATEEXECUTED</code>.
     */
    @Override
    public void setDATEEXECUTED(LocalDateTime DATEEXECUTED) {
        this.DATEEXECUTED = DATEEXECUTED;
        this.touch();
    }

    /**
     * Getter for <code>testshop.DATABASECHANGELOG.ORDEREXECUTED</code>.
     */
    @NotNull
    @Override
    public Integer getORDEREXECUTED() {
        return this.ORDEREXECUTED;
    }

    /**
     * Setter for <code>testshop.DATABASECHANGELOG.ORDEREXECUTED</code>.
     */
    @Override
    public void setORDEREXECUTED(Integer ORDEREXECUTED) {
        this.ORDEREXECUTED = ORDEREXECUTED;
        this.touch();
    }

    /**
     * Getter for <code>testshop.DATABASECHANGELOG.EXECTYPE</code>.
     */
    @NotNull
    @Size(max = 10)
    @Override
    public String getEXECTYPE() {
        return this.EXECTYPE;
    }

    /**
     * Setter for <code>testshop.DATABASECHANGELOG.EXECTYPE</code>.
     */
    @Override
    public void setEXECTYPE(String EXECTYPE) {
        this.EXECTYPE = EXECTYPE;
        this.touch();
    }

    /**
     * Getter for <code>testshop.DATABASECHANGELOG.MD5SUM</code>.
     */
    @Size(max = 35)
    @Override
    public String getMD5SUM() {
        return this.MD5SUM;
    }

    /**
     * Setter for <code>testshop.DATABASECHANGELOG.MD5SUM</code>.
     */
    @Override
    public void setMD5SUM(String MD5SUM) {
        this.MD5SUM = MD5SUM;
        this.touch();
    }

    /**
     * Getter for <code>testshop.DATABASECHANGELOG.DESCRIPTION</code>.
     */
    @Size(max = 255)
    @Override
    public String getDESCRIPTION() {
        return this.DESCRIPTION;
    }

    /**
     * Setter for <code>testshop.DATABASECHANGELOG.DESCRIPTION</code>.
     */
    @Override
    public void setDESCRIPTION(String DESCRIPTION) {
        this.DESCRIPTION = DESCRIPTION;
        this.touch();
    }

    /**
     * Getter for <code>testshop.DATABASECHANGELOG.COMMENTS</code>.
     */
    @Size(max = 255)
    @Override
    public String getCOMMENTS() {
        return this.COMMENTS;
    }

    /**
     * Setter for <code>testshop.DATABASECHANGELOG.COMMENTS</code>.
     */
    @Override
    public void setCOMMENTS(String COMMENTS) {
        this.COMMENTS = COMMENTS;
        this.touch();
    }

    /**
     * Getter for <code>testshop.DATABASECHANGELOG.TAG</code>.
     */
    @Size(max = 255)
    @Override
    public String getTAG() {
        return this.TAG;
    }

    /**
     * Setter for <code>testshop.DATABASECHANGELOG.TAG</code>.
     */
    @Override
    public void setTAG(String TAG) {
        this.TAG = TAG;
        this.touch();
    }

    /**
     * Getter for <code>testshop.DATABASECHANGELOG.LIQUIBASE</code>.
     */
    @Size(max = 20)
    @Override
    public String getLIQUIBASE() {
        return this.LIQUIBASE;
    }

    /**
     * Setter for <code>testshop.DATABASECHANGELOG.LIQUIBASE</code>.
     */
    @Override
    public void setLIQUIBASE(String LIQUIBASE) {
        this.LIQUIBASE = LIQUIBASE;
        this.touch();
    }

    /**
     * Getter for <code>testshop.DATABASECHANGELOG.CONTEXTS</code>.
     */
    @Size(max = 255)
    @Override
    public String getCONTEXTS() {
        return this.CONTEXTS;
    }

    /**
     * Setter for <code>testshop.DATABASECHANGELOG.CONTEXTS</code>.
     */
    @Override
    public void setCONTEXTS(String CONTEXTS) {
        this.CONTEXTS = CONTEXTS;
        this.touch();
    }

    /**
     * Getter for <code>testshop.DATABASECHANGELOG.LABELS</code>.
     */
    @Size(max = 255)
    @Override
    public String getLABELS() {
        return this.LABELS;
    }

    /**
     * Setter for <code>testshop.DATABASECHANGELOG.LABELS</code>.
     */
    @Override
    public void setLABELS(String LABELS) {
        this.LABELS = LABELS;
        this.touch();
    }

    /**
     * Getter for <code>testshop.DATABASECHANGELOG.DEPLOYMENT_ID</code>.
     */
    @Size(max = 10)
    @Override
    public String getDEPLOYMENT_ID() {
        return this.DEPLOYMENT_ID;
    }

    /**
     * Setter for <code>testshop.DATABASECHANGELOG.DEPLOYMENT_ID</code>.
     */
    @Override
    public void setDEPLOYMENT_ID(String DEPLOYMENT_ID) {
        this.DEPLOYMENT_ID = DEPLOYMENT_ID;
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
        final Databasechangelog other = (Databasechangelog) obj;
        if (this.ID == null) {
            if (other.ID != null)
                return false;
        }
        else if (!this.ID.equals(other.ID))
            return false;
        if (this.AUTHOR == null) {
            if (other.AUTHOR != null)
                return false;
        }
        else if (!this.AUTHOR.equals(other.AUTHOR))
            return false;
        if (this.FILENAME == null) {
            if (other.FILENAME != null)
                return false;
        }
        else if (!this.FILENAME.equals(other.FILENAME))
            return false;
        if (this.DATEEXECUTED == null) {
            if (other.DATEEXECUTED != null)
                return false;
        }
        else if (!this.DATEEXECUTED.equals(other.DATEEXECUTED))
            return false;
        if (this.ORDEREXECUTED == null) {
            if (other.ORDEREXECUTED != null)
                return false;
        }
        else if (!this.ORDEREXECUTED.equals(other.ORDEREXECUTED))
            return false;
        if (this.EXECTYPE == null) {
            if (other.EXECTYPE != null)
                return false;
        }
        else if (!this.EXECTYPE.equals(other.EXECTYPE))
            return false;
        if (this.MD5SUM == null) {
            if (other.MD5SUM != null)
                return false;
        }
        else if (!this.MD5SUM.equals(other.MD5SUM))
            return false;
        if (this.DESCRIPTION == null) {
            if (other.DESCRIPTION != null)
                return false;
        }
        else if (!this.DESCRIPTION.equals(other.DESCRIPTION))
            return false;
        if (this.COMMENTS == null) {
            if (other.COMMENTS != null)
                return false;
        }
        else if (!this.COMMENTS.equals(other.COMMENTS))
            return false;
        if (this.TAG == null) {
            if (other.TAG != null)
                return false;
        }
        else if (!this.TAG.equals(other.TAG))
            return false;
        if (this.LIQUIBASE == null) {
            if (other.LIQUIBASE != null)
                return false;
        }
        else if (!this.LIQUIBASE.equals(other.LIQUIBASE))
            return false;
        if (this.CONTEXTS == null) {
            if (other.CONTEXTS != null)
                return false;
        }
        else if (!this.CONTEXTS.equals(other.CONTEXTS))
            return false;
        if (this.LABELS == null) {
            if (other.LABELS != null)
                return false;
        }
        else if (!this.LABELS.equals(other.LABELS))
            return false;
        if (this.DEPLOYMENT_ID == null) {
            if (other.DEPLOYMENT_ID != null)
                return false;
        }
        else if (!this.DEPLOYMENT_ID.equals(other.DEPLOYMENT_ID))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.ID == null) ? 0 : this.ID.hashCode());
        result = prime * result + ((this.AUTHOR == null) ? 0 : this.AUTHOR.hashCode());
        result = prime * result + ((this.FILENAME == null) ? 0 : this.FILENAME.hashCode());
        result = prime * result + ((this.DATEEXECUTED == null) ? 0 : this.DATEEXECUTED.hashCode());
        result = prime * result + ((this.ORDEREXECUTED == null) ? 0 : this.ORDEREXECUTED.hashCode());
        result = prime * result + ((this.EXECTYPE == null) ? 0 : this.EXECTYPE.hashCode());
        result = prime * result + ((this.MD5SUM == null) ? 0 : this.MD5SUM.hashCode());
        result = prime * result + ((this.DESCRIPTION == null) ? 0 : this.DESCRIPTION.hashCode());
        result = prime * result + ((this.COMMENTS == null) ? 0 : this.COMMENTS.hashCode());
        result = prime * result + ((this.TAG == null) ? 0 : this.TAG.hashCode());
        result = prime * result + ((this.LIQUIBASE == null) ? 0 : this.LIQUIBASE.hashCode());
        result = prime * result + ((this.CONTEXTS == null) ? 0 : this.CONTEXTS.hashCode());
        result = prime * result + ((this.LABELS == null) ? 0 : this.LABELS.hashCode());
        result = prime * result + ((this.DEPLOYMENT_ID == null) ? 0 : this.DEPLOYMENT_ID.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Databasechangelog (");

        sb.append(ID);
        sb.append(", ").append(AUTHOR);
        sb.append(", ").append(FILENAME);
        sb.append(", ").append(DATEEXECUTED);
        sb.append(", ").append(ORDEREXECUTED);
        sb.append(", ").append(EXECTYPE);
        sb.append(", ").append(MD5SUM);
        sb.append(", ").append(DESCRIPTION);
        sb.append(", ").append(COMMENTS);
        sb.append(", ").append(TAG);
        sb.append(", ").append(LIQUIBASE);
        sb.append(", ").append(CONTEXTS);
        sb.append(", ").append(LABELS);
        sb.append(", ").append(DEPLOYMENT_ID);

        sb.append(")");
        return sb.toString();
    }

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    @Override
    public void from(IDatabasechangelog from) {
        setID(from.getID());
        setAUTHOR(from.getAUTHOR());
        setFILENAME(from.getFILENAME());
        setDATEEXECUTED(from.getDATEEXECUTED());
        setORDEREXECUTED(from.getORDEREXECUTED());
        setEXECTYPE(from.getEXECTYPE());
        setMD5SUM(from.getMD5SUM());
        setDESCRIPTION(from.getDESCRIPTION());
        setCOMMENTS(from.getCOMMENTS());
        setTAG(from.getTAG());
        setLIQUIBASE(from.getLIQUIBASE());
        setCONTEXTS(from.getCONTEXTS());
        setLABELS(from.getLABELS());
        setDEPLOYMENT_ID(from.getDEPLOYMENT_ID());
    }

    @Override
    public <E extends IDatabasechangelog> E into(E into) {
        into.from(this);
        return into;
    }
}
