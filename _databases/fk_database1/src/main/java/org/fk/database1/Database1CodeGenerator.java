package org.fk.database1;

import org.fk.framework.jooq.FkGeneratorStrategy;
import org.fk.provider.testcontainers.FkMariaDb;
import org.jooq.codegen.GenerationTool;
import org.jooq.meta.jaxb.*;
import org.jooq.meta.mariadb.MariaDBDatabase;
import org.mariadb.jdbc.Driver;

public class Database1CodeGenerator {
    public static void main(String[] args) throws Exception {
        try (final Database1Testcontainer databaseTestcontainer = new Database1Testcontainer()) {
            final FkMariaDb fkMariaDb = databaseTestcontainer.getFkMariadb();
            GenerationTool.generate(new Configuration()
                .withJdbc(new Jdbc()
                    .withDriver(Driver.class.getName())
                    .withUrl(fkMariaDb.getJdbcUrl())
                    .withUser(fkMariaDb.getUsername())
                    .withPassword(fkMariaDb.getPassword()))
                .withGenerator(new Generator()
                    .withStrategy(
                        new Strategy()
                            .withName("org.fk.framework.jooq.FkGeneratorStrategy")
                    )
                    .withGenerate(new Generate()
                        .withInterfaces(true)
                        .withFluentSetters(true)
                        .withValidationAnnotations(true)
                    )
                    .withDatabase(new Database()
                        .withName(MariaDBDatabase.class.getName())
                        .withIncludes("testshop.*|testshop2.*")
                        .withExcludes("")
                        .withForcedTypes(new ForcedType()
                            .withName("BOOLEAN")
                            .withIncludeTypes("(?i:TINYINT\\(1\\))"))
                    )
                    .withTarget(new Target()
                        .withPackageName("org.fk.database1")
                        .withDirectory("src/main/generated"))));
        }
    }
}