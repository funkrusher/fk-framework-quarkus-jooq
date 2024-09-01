package org.fk.product.api;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.f4b6a3.ulid.Ulid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlTransient;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.fk.core.dto.BookKeeper;
import org.fk.core.dto.DTO;
import org.fk.database1.testshop.tables.dtos.PostDto;
import org.fk.database1.testshop.tables.interfaces.IPost;

import java.io.Serial;
import java.util.UUID;

/**
 * PostAPI
 */
public class PostAPI extends PostDto {

    // ULID string of the UUID id (readable for user)
    @Schema(writeOnly = true)
    private String idEnc;

    @JsonProperty
    String getIdEnc() {
        return Ulid.from(this.getId()).toString();
    }

}