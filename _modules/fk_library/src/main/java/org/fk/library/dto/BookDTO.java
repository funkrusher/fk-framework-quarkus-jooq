package org.fk.library.dto;

import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.fk.database2.public_.tables.interfaces.IBook;
import org.fk.database2.public_.tables.dtos.BookDto;

/**
 * BookDTO
 */
@Schema(name = "Book", description = "Represents a book")
public class BookDTO extends BookDto implements IBook {
    public BookDTO() {
        super();
    }
}
