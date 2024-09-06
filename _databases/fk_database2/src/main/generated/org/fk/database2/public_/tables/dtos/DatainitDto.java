package org.fk.database2.public_.tables.dtos;

import org.fk.core.dto.DTO;
import org.fk.core.dto.BookKeeper;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import jakarta.xml.bind.annotation.XmlTransient;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.fk.core.dto.AbstractDTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

import org.fk.database2.public_.tables.interfaces.IDatainit;

/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class DatainitDto<T extends DatainitDto> extends AbstractDTO implements IDatainit {

    private static final long serialVersionUID = 1L;

    // -------------------------------------------------------------------------
    // Database-Fields (must exist in the associated database table)
    // -------------------------------------------------------------------------
 
    private String datainitid;
    @Schema(example = "1618312800000", type = SchemaType.NUMBER, format = "date-time", description = "Timestamp in milliseconds since 1970-01-01T00:00:00Z")
    private LocalDateTime createdat;

    // -------------------------------------------------------------------------
    // Constructor(s)
    // -------------------------------------------------------------------------
 
    public DatainitDto() {}

    public DatainitDto(IDatainit value) { this.from(value); }

    // -------------------------------------------------------------------------
    // Database-Fields Setters/Getters
    // -------------------------------------------------------------------------
 
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
    public T setDatainitid(String datainitid) {
        this.datainitid = datainitid;
        this.keeper.touch("datainitid");
        return (T) this;
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
    public T setCreatedat(LocalDateTime createdat) {
        this.createdat = createdat;
        this.keeper.touch("createdat");
        return (T) this;
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
