package org.fk.library.dto;

import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.fk.database2.public_.tables.dtos.Book;
import org.fk.database2.public_.tables.interfaces.IBook;

import java.util.List;

/**
 * BookDTO
 */
@Schema(name = "Book", description = "Represents a book")
public class BookDTO extends Book implements IBook {

}
