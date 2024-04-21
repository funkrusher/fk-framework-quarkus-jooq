package org.fk.core.dao;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.f4b6a3.ulid.Ulid;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import org.fk.coreTestDatabase.coretestdatabase.tables.dtos.Post;
import org.fk.coreTestDatabase.coretestdatabase.tables.interfaces.IPost;

/**
 * PostDTO
 */
public class PostDTO extends Post implements IPost {

    // ULID string of the UUID id (readable for user)
    @Schema(writeOnly = true)
    private String idEnc;

    @JsonProperty
    String getIdEnc() {
        return Ulid.from(this.getId()).toString();
    }
}
