package org.fk.library.dto;

import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.fk.database2.public_.tables.dtos.AuthorDto;
import org.fk.database2.public_.tables.interfaces.IAuthor;

import java.io.Serial;
import java.util.List;

import org.fk.database2.public_.tables.records.AuthorRecord;
import org.jooq.Record2;

/**
 * AuthorDTO
 */
@Schema(name = "AuthorDTO", description = "Represents an author")
public class AuthorDTO extends AuthorDto<AuthorDTO> {

    @Serial
    private static final long serialVersionUID = 1L;

    // -------------------------------------------------------------------------
    // Non-Database-Fields (please define your additional fields here)
    // -------------------------------------------------------------------------

    private List<BookDTO> books;

    // -------------------------------------------------------------------------
    // Constructor(s)
    // -------------------------------------------------------------------------

    public AuthorDTO() {}

    public AuthorDTO(IAuthor value) { this.from(value); }

    public static AuthorDTO create(Record2<AuthorRecord, List<BookDTO>> r) {
        return new AuthorDTO(r.value1())
            .setBooks(r.value2());
    }

    // -------------------------------------------------------------------------
    // Non-Database-Fields Setters/Getters (please define here)
    // -------------------------------------------------------------------------

    public List<BookDTO> getBooks() {
        return books;
    }

    public AuthorDTO setBooks(List<BookDTO> books) {
        this.books = books;
        this.keeper.touch("books");
        return this;
    }
}
