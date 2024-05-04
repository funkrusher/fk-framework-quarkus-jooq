package org.fk.library.init;

import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.fk.database2.Database2ConfigurationFactory;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;

import org.fk.database2.public_.tables.records.InitRecord;

import java.util.Optional;

import static org.fk.database2.public_.Tables.BOOK;
import static org.fk.database2.public_.tables.Author.AUTHOR;
import static org.fk.database2.public_.tables.Init.INIT;

@ApplicationScoped
public class LibraryInit {

    @ConfigProperty(name = "init.library", defaultValue = "false")
    Boolean initLibrary;

    @Inject
    Database2ConfigurationFactory configurationFactory;

    void startup(@SuppressWarnings("java:S1172") @Observes StartupEvent event) {
        if (initLibrary) {
            DSLContext dsl = DSL.using(configurationFactory.getConfiguration());
            init(dsl);
        }
    }

    public void init(DSLContext dsl) {
        Optional<InitRecord> maybeInit = dsl.selectFrom(INIT).where(INIT.INITIALIZED.eq("library")).fetchOptional();
        if (maybeInit.isEmpty()) {
            dsl.transaction(tx1 -> {
                tx1.dsl().insertInto(AUTHOR, AUTHOR.AUTHOR_ID, AUTHOR.NAME)
                        .values(1, "Fjodor Dostojewski")
                        .execute();

                tx1.dsl().insertInto(BOOK, BOOK.AUTHOR_ID, BOOK.TITLE)
                        .values(1, "Crime and Punishment")
                        .execute();

                // finally set init!
                tx1.dsl().insertInto(INIT, INIT.INITIALIZED)
                        .values("library")
                        .execute();
            });
        }
    }
}
