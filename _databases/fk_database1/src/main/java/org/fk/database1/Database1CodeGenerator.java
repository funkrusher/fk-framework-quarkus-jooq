package org.fk.database1;

import org.fk.core.jooq.FkGeneratedFilesPostProcessor;
import org.fk.core.jooq.FkGeneratorStrategy;
import org.fk.core.testcontainers.FkMariaDb;
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
                            .withGenerate(new Generate()
                                    .withInterfaces(true)
                                    .withSerializableInterfaces(true)
                                    .withPojos(true)
                                    .withFluentSetters(true)
                                    .withValidationAnnotations(true)
                                    .withPojosEqualsAndHashCode(false)
                            )
                            .withStrategy(new Strategy()
                                    .withName(FkGeneratorStrategy.class.getName())
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

            new FkGeneratedFilesPostProcessor().processFiles("src/main/generated");
        }
    }
}