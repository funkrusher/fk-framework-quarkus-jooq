package org.fk.product.init;

import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.fk.database1.Database1ConfigurationFactory;
import org.fk.database1.testshop.tables.records.InitRecord;
import org.fk.product.type.ProductTypeId;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;

import java.math.BigDecimal;
import java.util.Optional;

import static org.fk.database1.testshop.tables.Client.CLIENT;
import static org.fk.database1.testshop.tables.Init.INIT;
import static org.fk.database1.testshop2.tables.Product.PRODUCT;
import static org.fk.database1.testshop.tables.Lang.LANG;
import static org.fk.database1.testshop2.tables.ProductLang.PRODUCT_LANG;
import static org.fk.database1.testshop.tables.Role.ROLE;

@ApplicationScoped
public class ProductInit {

    @ConfigProperty(name = "init.product", defaultValue = "false")
    boolean initProduct;

    @Inject
    Database1ConfigurationFactory configurationFactory;

    void startup(@SuppressWarnings("java:S1172") @Observes StartupEvent event) {
        if (initProduct) {
            DSLContext dsl = DSL.using(configurationFactory.getConfiguration());
            init(dsl);
        }
    }

    public void init(DSLContext dsl) {
        Optional<InitRecord> maybeInit = dsl.selectFrom(INIT).where(INIT.INITIALIZED.eq("product")).fetchOptional();
        if (maybeInit.isEmpty()) {
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

                tx1.dsl().insertInto(PRODUCT, PRODUCT.PRODUCTID, PRODUCT.CLIENTID, PRODUCT.PRICE, PRODUCT.TYPEID)
                        .values(1L, 1, new BigDecimal("10.20"), ProductTypeId.CLOTHING.getValue())
                        .values(2L, 1, new BigDecimal("99.99"), ProductTypeId.BOOK.getValue())
                        .execute();

                tx1.dsl().insertInto(PRODUCT_LANG, PRODUCT_LANG.PRODUCTID, PRODUCT_LANG.LANGID, PRODUCT_LANG.NAME, PRODUCT_LANG.DESCRIPTION)
                        .values(1L, 1, "Isotherm-Tasche fÃ¼r Lebensmittel", "Halten Sie Ihr Picknick schÃ¶n kÃ¼hl oder warm! Schicke, isolierte Tasche fÃ¼r den BÃ¼ro-Lunch oder AusflÃ¼ge. Innen mit Aluminium-Folie. Oberes Abteil 25 x 16 x H 15 cm. Unteres Abteil 25 x 16 x H 7 cm. H total 24 cm. OberflÃ¤che wasserabperlend. Leicht glÃ¤nzendes Perl ...")
                        .values(1L, 3, "Sac repas isotherme", "Gardez votre lunch bien chaud ou bien frais avec un sac isotherme chic pour le bureau ou les excursions ! DoublÃ© avec de la feuille d''aluminium. Compartiment du dessus 25 x 16 x H 15 cm. Compartiment du dessous 25 x 16 x H 7 cm. H totale 24 cm. RevÃªtement dÃ©perlant. Gris perle, lÃ©gÃ¨rement brillant.")
                        .values(2L, 1, "Mira Eck Glas USB A", "Eck-Steckdosenelement, 2-fach und Doppel USB Charger (Ladestation). Frontseite in bedrucktem und kratzfestem Glas, fÃ¼r 90Â° Ecke.")
                        .values(2L, 2, "Mira corner111 glass USB A", "Corner socket element, 2-fold and double USB charger (charging station). Front printed and scratch-resistant glass. for 90Â° corner.")
                        .execute();

                tx1.dsl().insertInto(ROLE, ROLE.ROLEID)
                        .values("ADMIN")
                        .values("EDITOR")
                        .values("VIEWER")
                        .execute();

                tx1.dsl().execute("" +
                        "-- Generate 15000 random products for client IDs 1, 2, and 3\n" +
                        "            INSERT INTO testshop2.product (clientId, price, typeId)\n" +
                        "            SELECT\n" +
                        "                RAND()*2+1 AS clientId, -- random client ID between 1 and 3\n" +
                        "                ROUND(RAND() * 100, 2) AS price,    -- random price between 0 and 100 with 2 decimal places\n" +
                        "                '" + ProductTypeId.BOOK.getValue() + "' \n" +
                        "            FROM\n" +
                        "                    (SELECT 1 UNION SELECT 2 UNION SELECT 3 UNION SELECT 4 UNION SELECT 5) AS nums\n" +
                        "                        CROSS JOIN\n" +
                        "                    (SELECT 1 UNION SELECT 2 UNION SELECT 3 UNION SELECT 4 UNION SELECT 5) AS nums2\n" +
                        "                        CROSS JOIN\n" +
                        "                    (SELECT 1 UNION SELECT 2 UNION SELECT 3 UNION SELECT 4 UNION SELECT 5) AS nums3\n" +
                        "                        CROSS JOIN\n" +
                        "                    (SELECT 1 UNION SELECT 2 UNION SELECT 3 UNION SELECT 4 UNION SELECT 5) AS nums4\n" +
                        "                        CROSS JOIN\n" +
                        "                    (SELECT 1 UNION SELECT 2 UNION SELECT 3 UNION SELECT 4 UNION SELECT 5) AS nums5\n" +
                        "                        CROSS JOIN\n" +
                        "                    (SELECT 1 UNION SELECT 2 UNION SELECT 3 UNION SELECT 4 UNION SELECT 5) AS nums6;");

                tx1.dsl().execute("" +
                        " -- Generate product_lang entries for the new products\n" +
                        "            INSERT INTO testshop2.product_lang (productId, langId, name, description)\n" +
                        "            SELECT\n" +
                        "                p.productId,\n" +
                        "                l.langId,\n" +
                        "                CONCAT('Product ', p.productId, ' (', l.code, ')') AS name,\n" +
                        "                CONCAT('This is the ', l.description, ' description for product ', p.productId) AS description\n" +
                        "            FROM\n" +
                        "                testshop2.product p\n" +
                        "                    CROSS JOIN testshop.lang l\n" +
                        "            WHERE\n" +
                        "                NOT EXISTS (\n" +
                        "                        SELECT 1\n" +
                        "                        FROM testshop2.product_lang pl\n" +
                        "                        WHERE pl.productId = p.productId AND pl.langId = l.langId\n" +
                        "                    );");

                // finally set init!
                tx1.dsl().insertInto(INIT, INIT.INITIALIZED)
                        .values("product")
                        .execute();
            });
        }
    }
}
