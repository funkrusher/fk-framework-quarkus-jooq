package org.fk.library.dto;

import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.fk.database2.public_.tables.interfaces.IAuthor;
import org.fk.database2.public_.tables.pojos.AuthorDto;

import java.util.List;

/**
 * AuthorDTO
 */
@Schema(name = "Author", description = "Represents an author")
public class AuthorDTO extends AuthorDto implements IAuthor {

    private List<BookDTO> books;

    public AuthorDTO() {
        super();
    }


    public List<BookDTO> getBooks() {
        return books;
    }

    public void setBooks(List<BookDTO> books) {
        this.books = books;
        this.keeper.touch("books");
    }
}
