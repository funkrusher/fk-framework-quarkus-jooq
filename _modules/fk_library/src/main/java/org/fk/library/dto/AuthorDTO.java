package org.fk.library.dto;

import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.fk.database2.public_.tables.dtos.Author;
import org.fk.database2.public_.tables.interfaces.IAuthor;

import java.util.List;

/**
 * AuthorDTO
 */
@Schema(name = "Author", description = "Represents an author")
public class AuthorDTO extends Author implements IAuthor {

    private List<BookDTO> books;

    public AuthorDTO() {
        super();
    }

    public AuthorDTO(IAuthor value) {
        super(value);
    }


    public List<BookDTO> getBooks() {
        return books;
    }

    public void setBooks(List<BookDTO> books) {
        this.books = books;
        touch();
    }
}
