/*
 * This file is generated by jOOQ.
 */
package org.fk.database2.public_.tables.pojos;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

import org.fk.database2.public_.tables.interfaces.IDatainit;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class Datainit implements IDatainit {

    private static final long serialVersionUID = 1L;

    private String datainitid;
    private LocalDateTime createdat;

    public Datainit() {}

    public Datainit(IDatainit value) {
        this.datainitid = value.getDatainitid();
        this.createdat = value.getCreatedat();
    }

    public Datainit(
        String datainitid,
        LocalDateTime createdat
    ) {
        this.datainitid = datainitid;
        this.createdat = createdat;
    }

    /**
     * Getter for <code>public.datainit.datainitid</code>.
     */
    @NotNull
    @Size(max = 255)
    @Override
    public String getDatainitid() {
        return this.datainitid;
    }

    /**
     * Setter for <code>public.datainit.datainitid</code>.
     */
    @Override
    public Datainit setDatainitid(String datainitid) {
        this.datainitid = datainitid;
        return this;
    }

    /**
     * Getter for <code>public.datainit.createdat</code>.
     */
    @Override
    public LocalDateTime getCreatedat() {
        return this.createdat;
    }

    /**
     * Setter for <code>public.datainit.createdat</code>.
     */
    @Override
    public Datainit setCreatedat(LocalDateTime createdat) {
        this.createdat = createdat;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Datainit (");

        sb.append(datainitid);
        sb.append(", ").append(createdat);

        sb.append(")");
        return sb.toString();
    }

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    @Override
    public void from(IDatainit from) {
        setDatainitid(from.getDatainitid());
        setCreatedat(from.getCreatedat());
    }

    @Override
    public <E extends IDatainit> E into(E into) {
        into.from(this);
        return into;
    }
}
