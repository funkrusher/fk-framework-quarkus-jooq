package org.fk.database2;

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
                            .withGenerate(new Generate()
                                    .withFluentSetters(true)
                                    .withValidationAnnotations(true)
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
        }
    }
}