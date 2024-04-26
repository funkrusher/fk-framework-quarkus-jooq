package org.fk.database2;

import org.fk.core.jooq.codegen.FkGeneratedFilesPostProcessor;
import org.fk.core.jooq.codegen.FkGeneratorStrategy;
import org.fk.core.jooq.codegen.FkJavaGenerator;
import org.fk.core.testcontainers.FkMariaDb;
import org.fk.core.testcontainers.FkPostgres;
import org.jooq.codegen.GenerationTool;
import org.jooq.meta.jaxb.*;
import org.jooq.meta.postgres.PostgresDatabase;
import org.postgresql.Driver;

public class Database2CodeGenerator {
    public static void main(String[] args) throws Exception {
        try (final Database2Testcontainer databaseTestcontainer = new Database2Testcontainer()) {
            final FkPostgres fkPostgres = databaseTestcontainer.getFkPostgres();
            GenerationTool.generate(new Configuration()
                    .withJdbc(new Jdbc()
                            .withDriver(Driver.class.getName())
                            .withUrl(fkPostgres.getJdbcUrl())
                            .withUser(fkPostgres.getUsername())
                            .withPassword(fkPostgres.getPassword()))
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
                                    .withName(PostgresDatabase.class.getName())
                                    .withIncludes("public.*")
                                    .withExcludes("")
                                    .withForcedTypes(new ForcedType()
                                            .withName("BOOLEAN")
                                            .withIncludeTypes("(?i:TINYINT\\(1\\))"))
                            )
                            .withTarget(new Target()
                                    .withPackageName("org.fk.database2")
                                    .withDirectory("src/main/generated"))));

            FkGeneratedFilesPostProcessor.processDTOFiles("src/main/generated");
            FkGeneratedFilesPostProcessor.processInterfaceFiles("src/main/generated");
        };
    }
}