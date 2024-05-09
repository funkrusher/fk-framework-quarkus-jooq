package org.fk.library.init;

import org.fk.core.init.DataInit;
import org.jooq.DSLContext;
import static org.fk.database2.public_.Tables.BOOK;
import static org.fk.database2.public_.tables.Author.AUTHOR;

public class LibraryBasicDataInit implements DataInit {
    @Override
    public String getDataInitId() {
        return "library_basic_data";
    }
    @Override
    public void execute(DSLContext dsl) {
        dsl.insertInto(AUTHOR, AUTHOR.AUTHOR_ID, AUTHOR.NAME)
                .values(1, "Fjodor Dostojewski")
                .execute();

        dsl.insertInto(BOOK, BOOK.AUTHOR_ID, BOOK.TITLE)
                .values(1, "Crime and Punishment")
                .execute();
    }
}
