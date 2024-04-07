/*
 * This file is generated by jOOQ.
 */
package org.fk.database2.public_.tables.dtos;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import org.fk.core.dto.AbstractDTO;
import org.fk.database2.public_.tables.interfaces.IInit;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class Init extends AbstractDTO implements IInit {

    private static final long serialVersionUID = 1L;

    private String initialized;

    public Init() {}

    public Init(IInit value) {
        this.initialized = value.getInitialized();
    }

    public Init(
        String initialized
    ) {
        this.initialized = initialized;
    }

    /**
     * Getter for <code>public.init.initialized</code>.
     */
    @NotNull
    @Size(max = 255)
    @Override
    public String getInitialized() {
        return this.initialized;
    }

    /**
     * Setter for <code>public.init.initialized</code>.
     */
    @Override
    public void setInitialized(String initialized) {
        this.initialized = initialized;
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
        final Init other = (Init) obj;
        if (this.initialized == null) {
            if (other.initialized != null)
                return false;
        }
        else if (!this.initialized.equals(other.initialized))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.initialized == null) ? 0 : this.initialized.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Init (");

        sb.append(initialized);

        sb.append(")");
        return sb.toString();
    }

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    @Override
    public void from(IInit from) {
        setInitialized(from.getInitialized());
    }

    @Override
    public <E extends IInit> E into(E into) {
        into.from(this);
        return into;
    }
}