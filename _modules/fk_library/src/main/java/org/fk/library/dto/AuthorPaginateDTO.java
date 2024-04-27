package org.fk.library.dto;


import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.util.List;

@Schema(name = "AuthorPaginate", description = "Represents the pagination result of authors")

public class AuthorPaginateDTO {

    private List<AuthorDTO> authors;

    private Integer count;

    public AuthorPaginateDTO() {
        super();
    }

    public List<AuthorDTO> getAuthors() {
        return authors;
    }

    public void setAuthors(List<AuthorDTO> products) {
        this.authors = products;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
