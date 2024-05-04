package org.fk.core.test;

import jakarta.enterprise.context.ApplicationScoped;
import org.jooq.DSLContext;

import static org.fk.core.test.database.coretestdatabase.Tables.CLIENT;
import static org.fk.core.test.database.coretestdatabase.Tables.LANG;

@ApplicationScoped
public class CoreInit {

    public void init(DSLContext dsl) {
        dsl.transaction(tx1 -> {
            tx1.dsl().insertInto(LANG, LANG.LANGID, LANG.CODE, LANG.DESCRIPTION)
                    .values(1, "de", "Deutsch")
                    .values(2, "en", "English")
                    .values(3, "fr", "FranÃ§ais")
                    .values(4, "pt", "PortuguÃªs")
                    .execute();

            tx1.dsl().insertInto(CLIENT, CLIENT.CLIENTID)
                    .values(1)
                    .values(2)
                    .values(3)
                    .execute();
        });
    }
}
