package org.fk.library.dao;

import org.fk.core.dao.AbstractDAO;
import org.fk.database2.public_.tables.Book;
import org.fk.database2.public_.tables.interfaces.IBook;
import org.fk.database2.public_.tables.records.BookRecord;
import org.jooq.DSLContext;

/**
 * BookDAO
 */
public class BookDAO extends AbstractDAO<BookRecord, IBook, Integer> {

    public BookDAO(DSLContext dsl) {
        super(dsl, Book.BOOK);
    }

    @Override
    public Integer getId(BookRecord object) {
        return object.getBook_id();
    }
}
