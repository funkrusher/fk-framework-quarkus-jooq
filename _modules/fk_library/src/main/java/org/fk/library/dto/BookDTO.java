package org.fk.library.dto;

import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.fk.database2.public_.tables.dtos.BookDto;
import org.fk.database2.public_.tables.interfaces.IBook;

import org.fk.database2.public_.tables.records.BookRecord;
import org.jooq.Record1;

import java.io.Serial;

/**
 * BookDTO
 */
@Schema(name = "BookDTO", description = "Represents a book")
public class BookDTO extends BookDto<BookDTO> {

    public BookDTO() {}

    public BookDTO(IBook value) { this.from(value); }

    public static BookDTO create(Record1<BookRecord> r) {
        return new BookDTO(r.value1());
    }
}