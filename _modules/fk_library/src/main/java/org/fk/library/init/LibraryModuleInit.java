package org.fk.library.init;

import io.quarkus.runtime.Startup;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.fk.core.init.ModuleInitializer;
import org.fk.database2.Database2ConfigurationFactory;
import org.fk.database2.public_.Public;
import org.jboss.logging.Logger;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;

import static org.fk.database2.public_.Tables.BOOK;
import static org.fk.database2.public_.tables.Author.AUTHOR;

@Startup
@ApplicationScoped
public class LibraryModuleInit {
    private static final String LIBRARY_MODULE_ID = "library";
    public static final Logger LOGGER = Logger.getLogger(LibraryModuleInit.class);

    @Inject
    LibraryModuleInit(
            final ModuleInitializer moduleInitializer,
            final Database2ConfigurationFactory configurationFactory) {
        final DSLContext dsl = DSL.using(configurationFactory.getConfiguration());

        LOGGER.info("LibraryModuleInit ...");

        moduleInitializer.init(dsl, LIBRARY_MODULE_ID, Public.PUBLIC.getQualifiedName(), tx1 -> {
            tx1.dsl().insertInto(AUTHOR, AUTHOR.AUTHOR_ID, AUTHOR.NAME)
                    .values(1, "Fjodor Dostojewski")
                    .execute();

            tx1.dsl().insertInto(BOOK, BOOK.AUTHOR_ID, BOOK.TITLE)
                    .values(1, "Crime and Punishment")
                    .execute();
        });
    }
}
