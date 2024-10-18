package org.fk.library.dao;

import org.fk.core.dao.AbstractDAO;
import org.fk.database2.public_.tables.Author;
import org.fk.database2.public_.tables.interfaces.IAuthor;
import org.fk.database2.public_.tables.records.AuthorRecord;
import org.jooq.DSLContext;

/**
 * AuthorDAO
 */
public class AuthorDAO extends AbstractDAO<AuthorRecord, Integer> {
    public AuthorDAO(DSLContext dsl) {
        super(dsl, Author.AUTHOR);
    }
}