package org.fk.product.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.f4b6a3.ulid.Ulid;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.fk.database1.testshop.tables.interfaces.IPost;
import org.fk.database1.testshop.tables.pojos.PostDto;

/**
 * PostDTO
 */
public class PostDTO extends PostDto implements IPost {

    public PostDTO() {
        super();
    }

    // ULID string of the UUID id (readable for user)
    @Schema(writeOnly = true)
    private String idEnc;

    @JsonProperty
    String getIdEnc() {
        return Ulid.from(this.getId()).toString();
    }
}
