/*
 * This file is generated by jOOQ.
 */
package org.fk.database2.public_;


import org.fk.database2.public_.tables.Author;
import org.fk.database2.public_.tables.Book;
import org.fk.database2.public_.tables.Databasechangeloglock;
import org.fk.database2.public_.tables.records.AuthorRecord;
import org.fk.database2.public_.tables.records.BookRecord;
import org.fk.database2.public_.tables.records.DatabasechangeloglockRecord;
import org.jooq.ForeignKey;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.Internal;


/**
 * A class modelling foreign key relationships and constraints of tables in
 * public.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class Keys {

    // -------------------------------------------------------------------------
    // UNIQUE and PRIMARY KEY definitions
    // -------------------------------------------------------------------------

    public static final UniqueKey<AuthorRecord> AUTHOR_PKEY = Internal.createUniqueKey(Author.AUTHOR, DSL.name("author_pkey"), new TableField[] { Author.AUTHOR.AUTHOR_ID }, true);
    public static final UniqueKey<BookRecord> BOOK_PKEY = Internal.createUniqueKey(Book.BOOK, DSL.name("book_pkey"), new TableField[] { Book.BOOK.BOOK_ID }, true);
    public static final UniqueKey<DatabasechangeloglockRecord> DATABASECHANGELOGLOCK_PKEY = Internal.createUniqueKey(Databasechangeloglock.DATABASECHANGELOGLOCK, DSL.name("databasechangeloglock_pkey"), new TableField[] { Databasechangeloglock.DATABASECHANGELOGLOCK.ID }, true);

    // -------------------------------------------------------------------------
    // FOREIGN KEY definitions
    // -------------------------------------------------------------------------

    public static final ForeignKey<BookRecord, AuthorRecord> BOOK__BOOK_AUTHOR_ID_FKEY = Internal.createForeignKey(Book.BOOK, DSL.name("book_author_id_fkey"), new TableField[] { Book.BOOK.AUTHOR_ID }, Keys.AUTHOR_PKEY, new TableField[] { Author.AUTHOR.AUTHOR_ID }, true);
}
