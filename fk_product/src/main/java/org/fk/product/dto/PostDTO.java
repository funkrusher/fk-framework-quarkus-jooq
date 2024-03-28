package org.fk.product.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import io.hypersistence.tsid.TSID;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.fk.codegen.testshop.tables.dtos.Post;
import org.fk.codegen.testshop.tables.dtos.Task;
import org.fk.codegen.testshop.tables.interfaces.IPost;
import org.fk.codegen.testshop.tables.interfaces.ITask;

/**
 * PostDTO
 */
public class PostDTO extends Post implements IPost {

    // product encoded with Crockford Base 32 encoded for readability in the UI.
    @Schema(writeOnly = true)
    private String idEnc;

    @JsonProperty
    String getIdEnc() {
        return TSID.from(this.getId()).toString();
    }
}
