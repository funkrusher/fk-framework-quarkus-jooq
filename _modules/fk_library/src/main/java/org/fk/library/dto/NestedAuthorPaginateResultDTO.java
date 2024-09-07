package org.fk.library.dto;


import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.util.List;

@Schema(name = "NestedAuthorPaginateResultDTO", description = "Represents the pagination result of authors")
public class NestedAuthorPaginateResultDTO {

    private List<NestedAuthorDTO> authors;

    private Integer count;

    public NestedAuthorPaginateResultDTO() {
        super();
    }

    public List<NestedAuthorDTO> getAuthors() {
        return authors;
    }

    public void setAuthors(List<NestedAuthorDTO> products) {
        this.authors = products;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
