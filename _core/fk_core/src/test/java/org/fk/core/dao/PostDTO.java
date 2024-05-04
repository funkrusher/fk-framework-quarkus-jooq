package org.fk.core.dao;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.f4b6a3.ulid.Ulid;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import org.fk.core.test.database.coretestdatabase.tables.interfaces.IPost;
import org.fk.core.test.database.coretestdatabase.tables.dtos.PostDto;

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
