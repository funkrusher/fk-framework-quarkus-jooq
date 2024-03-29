# Quarkus demo: jOOQ with Liquibase, RESTEasy, Gradle, Testcontainers

This is a minimal CRUD service exposing a couple of endpoints over REST. 

Instead of using the typical Hibernate/ORM, we will explore jOOQ Object Oriented Querying and the possible benefits of using such a more dynamic approach to database operations in a multi-tenancy + multi-language context.

Under the hood, the code is using:
- Quarkus Framework
  - RESTEasy to expose the REST endpoints
  - JUnit5 Unit-Testing
  - Db-Transactions safely coordinated by the Narayana Transaction Manager
  - Swagger-UI Support
- jOOQ Framework
  - Object Oriented Querying on the database
  - Db-Schema-To-Code-Generator
- Liquibase Db-Migrations
- Mariadb-Testcontainer for Unit-Tests and Code-Generator
- Gradle Build
  - Multi-Module project for shared-libraries approach 
- Customizable Helpers
    - own DAO-Abstraction that you can extend from and fine tune.
    - own Pojo-Abstraction with Modified-Fields detection support.
    - own RemotePagination Pojo to use for remote pagination

In the folder `./docs` you can find specific documentations about the different concepts this small seed-project implements, so you can benefit from them.

## Requirements

### Installations

To compile and run this demo you will need:
- JDK 21+ (Eclipse Temurin)
  - Note: GraalVM is currently not supported for native-build, because some libraries have compatibility-issues
    - awssdk
    - jooq
- Mariadb database (best: 10.6.*, because of default Mariadb-Version of Linux-Mint)
- Optional: Quarkus Plugin in Intellij-IDEA

The project has been set up specifically with Intellij IDEA compatibility in mind.

### Configuring JDK 21+

Download the Eclipse Temurin JDK 21 installer for your platform from:
- https://adoptium.net/installation/

Make sure that the `JAVA_HOME` environment variables have
been set, and that a JDK 21+ `java` command is on the path. This is also important if you use Intellij IDEA.
It is recommended to use a tool like sdkman for easy JDK-selection. 

## Setup/run server for local development

### Create database

Connect with your mariadb-database and create the database `testshop`
```
CREATE DATABASE testshop
```

### Create application.properties

Within the folder `fk_backend/src/main/resources/`, copy the file `./templates/application.properties` to `./application.properties`, 
then edit this newly created file `fk_backend/src/main/resources/application.properties` in your editor of choice 
and replace the following settings ([port], [username], [password]) for a connection with your mariadb-database with your settings.

```code
quarkus.datasource.jdbc.url=jdbc:mariadb://localhost:[port]/testshop?useCursorFetch=true&rewriteBatchedStatements=true
quarkus.datasource.username=[username]
quarkus.datasource.password=[password]
```

### Setup/run Cognito-Simulator

The example wants to show how to do Authentication and Authorization with an OIDC Provider.
To make it easy for local development, we will use an offline emulator for Amazon Cognito here.
The official offline emulator (`localstack`) can not be used, because most features would require a professional license.
Therefor we will use the `cognito-local` offline emulator:
- https://github.com/jagregory/cognito-local

We will first start `cognito-local` as a docker-container running on port 9229. Open up a console within the root-folder
of this project and enter following command:
```
docker-compose -f cognito-local-docker-compose.yml up --build -d
```

Start the cognitoLocalSetup task from the Console with following command:
```code
./gradlew cognitoLocalSetup
```
please note down the following three outputs of this task:
- cognitolocal.userpoolid
- cognitolocal.userpoolclientid
- cognitolocal.userpoolclientsecret

copy those three outputs directly into your `fk_backend/src/main/resources/application.properties` file.
For example:
```
# cognito-local
cognitolocal.userpoolid=local_7GsYn8Qh
cognitolocal.userpoolclientid=67jqekw6w9193e8khcu9d5slh
cognitolocal.userpoolclientsecret=6sjqzo1wyemkrjecj4qlqembt
```

Quarkus will take care of the JWT-Verify, for the JWT that has been created by a successful AWS-Cognito Authentication.
We need to tell it where to get the OIDC configuration. So make sure that your `application.properties` file also contains the following configurations from the template
(please insert the correct value for <cognitolocal.userpoolid>):
```
# quarkus oidc
quarkus.oidc.auth-server-url=http://localhost:9229/<cognitolocal.userpoolid>
quarkus.oidc.discovery-enabled=false
quarkus.oidc.jwks-path=http://localhost:9229/<cognitolocal.userpoolid>/.well-known/jwks.json
quarkus.oidc.roles.role-claim-path=custom:fk_roles
```

### Run the server

Start the Server from the Console with following command:
```code
./gradlew --console=plain quarkusDev
```
You can then navigate your webbrowser directly to the swagger-ui or dev-ui:
- http://localhost:8080/q/swagger-ui/
- http://localhost:8080/q/dev-ui/

## Setup/run Unit-Tests for local development

### Create application.properties

Within the folder `fk_backend/src/test/resources/`, copy the file `./templates/application.properties` to `./application.properties`,
then edit this newly created file `fk_backend/src/test/resources/application.properties` in your editor of choice
and copy the cognito and oidc settings from your `fk_backend/src/main/resources/application.properties` into your 
`fk_backend/src/test/resources/application.properties`, which are the following:
```
# cognito-local
cognitolocal.userpoolid=[userpoolid]
cognitolocal.userpoolclientid=[userpoolclientid]
cognitolocal.userpoolclientsecret=[userpoolclientsecret]

# quarkus oidc
quarkus.oidc.auth-server-url=http://localhost:9229/[userpoolid]
quarkus.oidc.discovery-enabled=false
quarkus.oidc.jwks-path=http://localhost:9229/[userpoolid]/.well-known/jwks.json
```

### Run the Unit-Tests

In Intellij you can just start the Unit-Tests as usual. 
Alternatively you can start them via the console with following command:

```code
./gradlew test
```
The testing-framework will fire up a mariadb-testcontainer automatically and will apply the liquibase-migrations to it.
This way the Unit-Tests can expect a real database to be available behind the tested code, 
and with the help of jOOQ the expected database-content can be validated after each test.

## Maintaining Database-Migrations

### Apply Liquibase-Migrations

You can place the liquibase-migrations in the folder `fk_backend/src/main/resources/liquibase`.
For each new migration you can add a new file `feature-xxxx.xml` with replacing xxx with your ticket-id from your 
version-control system (gitlab), to relate your database-migrations to your tickets.

You also need to add this identifier in the file `changelog.xml` in the same folder, to make it clear to 
liquibase in which sequence the migration-files need to be applied (latest at the bottom).

The Liquibase-Migrations are automatically applied when the Quarkus-Application is started 
(as defined in `application.properties` with the `quarkus.liquibase.migrate-at-start=true` parameter)

It is often convenient in local-dev, to be able to rollback to a specific tag, if you want to switch your git-branch, that you are working on.
For this use-case a gradle-task is provided, that helps you to rollback your database to a specific changeset. 
This will automatically execute all rollbacks of already applied changesets until the tag-changeset is reached.
```code
./gradlew liquibaseRollback -ProllbackTag=feature-1122
```
This example would rollback the following tags, in this order:
- feature-1321
- feature-1122

The typical workflow would consider of first rolling back your changesets by rolling back to the latest changeSet-tag in the dev-branch. 
Then you would switch branches to an other feature branch, and start the quarkus-application, 
so all changeSets of this branch are applied to your database.

Note: this is only relevant/helpful for local-dev, you never! want to use this with any other environment (staging, production).

### Running the jOOQ Code-Generator

After all database-changes via liquibase-migrations, the codegen must be executed, to recreate the database-specific code. 

Start the jOOQ Code-Generator from the Console with following command:
```code
./gradlew generateJooqCode
```
The generated code will reside in the folder `fk_codegen/src/main/generated`. 
The generator will fire up a mariadb-testcontainer automatically, apply the liquibase-migrations to it 
and will then generate the code from this database-schema. Afterwards the testcontainer is stopped again.

You also need to commit this generated code into your version-control system, as it is used within your code.

## Dockerizing the application

The first step is, to prepare the `fk_backend/src/main/resources/application.properties` file to be ready for the deploy
within the docker-container. Note that, normally you would prepare this file within a ci-pipeline (like in gitlab for example),
so it is already prepared with the correct settings for the live-environment.

For testing it, we can replace all `localhost` occurrences with an ip-address that would be reachable from within
the docker-container.

Next, build the JAR-files with following command. It will execute the tests and build everything.
```shell script
./gradlew build
```
The build produces the `quarkus-run.jar` file in the `fk_backend/build/quarkus-app/` directory along with other files,
and also with our prepared `application.properties`. 

Finally, we can build the docker-image and start it as docker-container by executing the docker-compose file as follows:
```shell script
docker-compose up --build
```
This will start up a docker-container build with the `fk_backend/src/main/docker/Dockerfile.jvm` which will use the `fk_backend/build/quarkus-app/` directory, we have created with our build and start up the `quarkus-run.jar`
After the docker-container has started we can open a rest-route in our webbrowser and it should work:
- http://localhost:8080/products/1

## Intellij IDEA

All the described operations can also be started up from within the Intellij IDEA.
1. Make sure that the project does not contain the subfolders: `.idea` and `.gradle`, and also delete all `build` folders within the projects subdirectories. 
2. Now open the project via `File`->`Open`.
3. The project should now be build automatically.

## Third-Party Versions Balancing

The used versions of third-party libraries must be balanced with each other. 

Use the jOOQ Version, that is fitting for your database or upgrade your database. See:
- https://www.jooq.org/download/support-matrix

We also can check conflicting dependencies, with gradlew. For example. The following command would check the dependency `validation-api` in our module `fk_backend` and show as all versions of this (possibly transitive) dependency in the runtime classpath: 
```code
./gradlew -p fk_backend dependencyInsight --dependency validation-api --configuration runtimeClasspath
```

## Quartz

Table-SQLs for your database type (mariadb, ...) are hidden in the quartz repositories. See:
- https://github.com/quartz-scheduler/quartz/blob/quartz-2.3.x/quartz-core/src/main/resources/org/quartz/impl/jdbcjobstore/tables_mysql_innodb.sql

## Related Guides
- Liquibase ([guide](https://docs.liquibase.com/concepts/home.html)): Handle your database schema migrations
- Gradle+Quarkus ([guide](https://quarkus.io/guides/gradle-tooling)): Building quarkus apps with gradle
- Gradle+IDEA ([gradle-guide](https://docs.gradle.org/current/userguide/idea_plugin.html), [idea-guide](https://www.jetbrains.com/help/idea/work-with-gradle-projects.html#project_encodings)): Setting up gradle with IDEA
- jOOQ ([guide](https://www.jooq.org/doc/3.18/manual/)): Handle your database querying
    - Insert/Update Only Changed-Values ([read](https://blog.jooq.org/orms-should-update-changed-values-not-just-modified-ones/)): Read about the topic why ORMs should update "changed" values, not just "modified" ones.
- Cognito-Local ([guide](https://github.com/jagregory/cognito-local)) Free Offline AWS-Cognito Emulator. 
- Cognito with OIDC ([guide](https://levelup.gitconnected.com/securing-micro-services-in-quarkus-with-aws-cognito-387990c04100)): Setup Quarkus to use OIDC for JWT-Verify of Cognito-Created JWTs.

## License

This project is licensed under the MIT License - see the LICENSE.md file for details.