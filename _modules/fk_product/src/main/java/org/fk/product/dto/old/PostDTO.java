package org.fk.product.dto.old;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.f4b6a3.ulid.Ulid;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.fk.database1.testshop.tables.dtos.PostDto;
import org.fk.database1.testshop.tables.interfaces.IPost;
import org.fk.database1.testshop.tables.records.PostRecord;
import org.jooq.Record1;

/**
 * PostDTO
 */
public class PostDTO extends PostDto<PostDTO> {

    // ULID string of the UUID id (readable for user)
    @Schema(writeOnly = true)
    private String idEnc;

    // -------------------------------------------------------------------------
    // Constructor(s)
    // -------------------------------------------------------------------------

    public PostDTO() { super(); }

    public PostDTO(IPost value) { super(value); }

    public static PostDTO create(Record1<PostRecord> r) {
        return new PostDTO(r.value1());
    }

    @JsonProperty
    String getIdEnc() {
        return Ulid.from(this.getId()).toString();
    }

}