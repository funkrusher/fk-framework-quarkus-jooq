package org.fk.coreTestDatabase;

import org.fk.core.jooq.codegen.FkGeneratorStrategy;
import org.fk.core.jooq.codegen.FkJavaGenerator;
import org.fk.core.testcontainers.FkMariaDb;
import org.jooq.codegen.GenerationTool;
import org.jooq.meta.jaxb.*;
import org.jooq.meta.mariadb.MariaDBDatabase;
import org.mariadb.jdbc.Driver;

public class CoreTestDatabaseCodeGenerator {
    public static void main(String[] args) throws Exception {
        try (final CoreTestDatabaseTestcontainer databaseTestcontainer = new CoreTestDatabaseTestcontainer()) {
            final FkMariaDb fkMariaDb = databaseTestcontainer.getFkMariadb();
            GenerationTool.generate(new Configuration()
                    .withJdbc(new Jdbc()
                            .withDriver(Driver.class.getName())
                            .withUrl(fkMariaDb.getJdbcUrl())
                            .withUser(fkMariaDb.getUsername())
                            .withPassword(fkMariaDb.getPassword()))
                    .withGenerator(new Generator()
                            .withName(FkJavaGenerator.class.getName())
                            .withGenerate(new Generate()
                                    .withInterfaces(true)
                                    .withSerializableInterfaces(true)
                                    .withPojos(true)
                                    .withValidationAnnotations(true)
                                    .withPojosEqualsAndHashCode(true)
                            )
                            .withStrategy(new Strategy()
                                    .withName(FkGeneratorStrategy.class.getName())
                            )
                            .withDatabase(new Database()
                                    .withName(MariaDBDatabase.class.getName())
                                    .withIncludes("coreTestDatabase.*")
                                    .withExcludes("")
                                    .withForcedTypes(new ForcedType()
                                            .withName("BOOLEAN")
                                            .withIncludeTypes("(?i:TINYINT\\(1\\))"))
                            )
                            .withTarget(new Target()
                                    .withPackageName("org.fk.coreTestDatabase")
                                    .withDirectory("src/main/generated"))));
        };
    }
}