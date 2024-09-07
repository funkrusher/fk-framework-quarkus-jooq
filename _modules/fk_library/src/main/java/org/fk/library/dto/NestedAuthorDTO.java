package org.fk.library.dto;

import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.fk.database2.public_.tables.dtos.AuthorDto;
import org.fk.database2.public_.tables.interfaces.IAuthor;

import java.util.List;

import org.fk.database2.public_.tables.records.AuthorRecord;
import org.jooq.Record2;

/**
 * NestedAuthorDTO
 */
@Schema(name = "NestedAuthorDTO", description = "Represents an author")
public class NestedAuthorDTO extends AuthorDto<NestedAuthorDTO> {

    // -------------------------------------------------------------------------
    // Non-Database-Fields (please define your additional fields here)
    // -------------------------------------------------------------------------

    private List<BookDTO> books;

    // -------------------------------------------------------------------------
    // Constructor(s)
    // -------------------------------------------------------------------------

    public NestedAuthorDTO() {}

    public NestedAuthorDTO(IAuthor value) { this.from(value); }

    public static NestedAuthorDTO create(Record2<AuthorRecord, List<BookDTO>> r) {
        return new NestedAuthorDTO(r.value1())
            .setBooks(r.value2());
    }

    // -------------------------------------------------------------------------
    // Non-Database-Fields Setters/Getters (please define here)
    // -------------------------------------------------------------------------

    public List<BookDTO> getBooks() {
        return books;
    }

    public NestedAuthorDTO setBooks(List<BookDTO> books) {
        this.books = books;
        this.keeper.touch("books");
        return this;
    }
}
