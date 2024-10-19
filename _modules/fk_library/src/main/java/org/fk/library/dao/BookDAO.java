package org.fk.library.dao;

import org.fk.framework.dao.AbstractDAO;
import org.fk.database2.public_.tables.Book;
import org.fk.database2.public_.tables.records.BookRecord;
import org.jooq.DSLContext;

/**
 * BookDAO
 */
public class BookDAO extends AbstractDAO<BookRecord, Integer> {
    public BookDAO(DSLContext dsl) {
        super(dsl, Book.BOOK);
    }
}
