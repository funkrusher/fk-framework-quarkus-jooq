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

To compile and run this demo you will need:

- GraalVM JDK 17+
- Mariadb database
- Optional: Quarkus Plugin in Intellij-IDEA

The project has been set up specifically with Intellij IDEA compatibility in mind.

### Configuring GraalVM JDK 17+

Download the Java17 GraalVM installer for your platform from:
- https://github.com/graalvm/graalvm-ce-builds/releases

Make sure that both the `GRAALVM_HOME` and `JAVA_HOME` environment variables have
been set, and that a JDK 17+ `java` command is on the path. This is also important if you use Intellij IDEA.

See the [Building a Native Executable guide](https://quarkus.io/guides/building-native-image)
for help setting up your environment.

### Configuring application.properties

Copy the file `acme-xxx-backend/src/main/resources/application-template.properties` to `application.properties`.

Edit the file `acme-xxx-backend/src/main/resources/application.properties` in your editor of choice and set the following settings for a connection with your Mariadb database (replace `xxx` with either `jooq` or `panache`):
```code
quarkus.datasource.jdbc.url=jdbc:mariadb://localhost:3306/xxx_testshop
quarkus.datasource.username=xxx
quarkus.datasource.password=xxx
```

### Creating the database

Connect with your mariadb-database and create the database (replace `xxx` with either `jooq` or `panache`):
```
CREATE DATABASE xxx_testhop
```

## Running the demo

Start the Demo from the Console with following command:
```code
./gradlew --console=plain quarkusDev
```
You can then navigate your webbrowser directly to the swagger-ui:
- http://localhost:8080/q/swagger-ui/

## Running the jOOQ Code-Generator

Start the jOOQ Code-Generator from the Console with following command:
```code
gradlew generateJooqCode
```
The generated code will reside in the folder `acme-jooq-code-generator/src/main/generated`. The generator will fire up a mariadb-testcontainer automatically, apply the liquibase-migrations to it and will then generate the code from this database-schema. Afterwards the testcontainer is stopped again. 

## Running the Unit-Tests

Start the Unit-Tests from the Console with following command:
```code
gradlew test
```
The testing-framework will fire up a mariadb-testcontainer automatically and will apply the liquibase-migrations to it. 
This way the Unit-Tests can expect a real database to be available behind the tested code, and with the help of jOOQ the expected database-content can be validated after each test.

## Rollback for Liquibase-Migrations in Local-Dev

The Liquibase-Migrations are automatically applied when the Quarkus-Application is started (as defined in `application.properties` with the `quarkus.liquibase.migrate-at-start=true` parameter)

It is often convenient in local-dev, to be able to rollback to a specific tag, if you want to switch your git-branch, that you are working on.
For this use-case a gradle-task is provided, that helps you to rollback your database to a specific changeset. This will automatically execute all rollbacks of already applied changesets until the tag-changeset is reached.   
```code
gradlew liquibaseRollback -ProllbackTag=feature-1122
```
This example would rollback the following tags, in this order:
- feature-1321
- feature-1122

The typical workflow would consider of first rolling back your changesets by rolling back to the latest changeSet-tag in the dev-branch. Then you would switch branches to an other feature branch, and start the quarkus-application, so all changeSets of this branch are applied to your database.

Note: this is only relevant/helpful for local-dev, you never! want to use this with any other environment (staging, production).

## Dockerizing the application

Start the Native Build from the Console with following command:
```shell script
gradlew clean buildNative "-Dquarkus.profile=dockerized"
```
It will use the `am-backend/src/main/resources/application-dockerized.properties` as configuration and will do a native build with it. 
The build produces the `quarkus-run.jar` file in the `build/quarkus-app/` directory along with other files.

If you want to execute the app as a docker-container just start it as follows, after running the build.
```shell script
cd ./acme-jooq-backend
docker-compose up --build
```
This will start up a docker-container build with the `acme-jooq-backend/src/main/docker/Dockerfile.jvm` which will use the `build/quarkus-app` directory, we have created with our build and start up the `quarkus-run.jar`
After the docker-container has started we can open a rest-route in our webbrowser and it should work:
- http://localhost:8080/products/1

## Intellij IDEA

All the described operations can also be started up from within the Intellij IDEA.
1. Make sure that the project does not contain the subfolders: `.idea` and `.gradle`, and also delete all `build` folders within the projects subdirectories. 
2. Now open the project via `File`->`Open`.
3. The project should now be build automatically.

## AWS Cognito Authentication/Authorization with OIDC

The example wants to show how to do Authentication and Authorization with an OIDC Provider.
To make it easy for local development, we will use an offline emulator for Amazon Cognito here.
The official offline emulator (`localstack`) can not be used, because most features would require a professional license.
Therefor we will use the `cognito-local` offline emulator:
- https://github.com/jagregory/cognito-local

We will first start `cognito-local` as a docker-container running on port 9229:
```
docker-compose -f cognito-local-docker-compose.yml up --build -d
```

Start the cognitoLocalSetup task from the Console with following command:
```code
gradlew cognitoLocalSetup
```
please note down the following three outputs of this task:
- cognitolocal.userpoolid
- cognitolocal.userpoolclientid
- cognitolocal.userpoolclientsecret

copy those three outputs directly into your `acme-jooq-backend/src/main/resources/application.properties` file.
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
quarkus.oidc.roles.role-claim-path=custom:acme_roles
```

You should now start your quarkus application, and navigate to the swagger-ui endpoint:
- http://localhost:8080/q/swagger-ui/

Call the following REST-Endpoint and give it an email+password to create a new user in the pool. Also make sure to give "ADMIN" as the roleId, and the clientId=1 (which we assume as a master-tenant-id that has privileged access)
- `/api/v1/cognitoLocal/signup`

Call the following REST-Endpoint and give it the same email+password, to obtain an access_token as response (and in the cookies)
- `/api/v1/cognitoLocal/signin`

Click the `Authorize`-Button in swagger-ui and enter the field `access_token (http, Bearer)` with the content of the response of the `/api/v1/cognitoLocal/signin` call. Note that the return of the signin-call does contain the id-token. This is necessary because aws-cognito does not provide all the information we need directly in the access-token. We can/need to use the id-token instead.

You have now setup swagger-ui to always provide this `access_token` as an Authorization.

Now you can finally call the following REST-Endpoint:
- `/api/v1/cognitoLocal/protected-by-quarkus`

Quarkus will automatically check the Authorization HTTP-Header in the request, which contains the access_token.
Quarkus will then validate this access_token with help of the `quarkus.oidc.jwks-path` you provided in the `application.properties` file.
This REST-Endpoint will only return Success, if the JWT-Verify is successful. 

The REST-Endpoint will also make sure that the user must have the "ADMIN" Role (RBAC), and the clientId=1 (Master-Tenant),
for access to be allowed.

## Third-Party Versions Balancing

The used versions of third-party libraries must be balanced with each other. 

we need to use jOOQ 3.16 instead of the latest jOOQ, because testcontainers only provides a mariadb 10.6 in its latest version,
and mariadb 10.6 is only supported by jOOQ 3.16. See:
- https://www.jooq.org/download/support-matrix

We need to use Quarkus 3.0.0.Beta, because the jOOQ Code Generator already creates the "jakarta.*" package imports for stuff that was in "javax.*" before.
Quarkus only starts to support this migration of Jakarta, beginning with 3.0.0.

We also can check conflicting dependencies, with gradlew. For example. The following command would check the dependency `validation-api` in our module `acme-jooq-backend` and show as all versions of this (possibly transitive) dependency in the runtime classpath: 
```code
gradlew -p acme-jooq-backend dependencyInsight --dependency validation-api --configuration runtimeClasspath
```

## Related Guides
- GraalVM ([installation-guide](https://www.graalvm.org/latest/docs/getting-started/windows/), [release-download](https://github.com/graalvm/graalvm-ce-builds/releases)): GraalVM Installation
- Liquibase ([guide](https://docs.liquibase.com/concepts/home.html)): Handle your database schema migrations
- Gradle+Quarkus ([guide](https://quarkus.io/guides/gradle-tooling)): Building quarkus apps with gradle
- Gradle+IDEA ([gradle-guide](https://docs.gradle.org/current/userguide/idea_plugin.html), [idea-guide](https://www.jetbrains.com/help/idea/work-with-gradle-projects.html#project_encodings)): Setting up gradle with IDEA
- jOOQ ([guide](https://www.jooq.org/doc/3.18/manual/)): Handle your database querying
    - Insert/Update Only Changed-Values ([read](https://blog.jooq.org/orms-should-update-changed-values-not-just-modified-ones/)): Read about the topic why ORMs should update "changed" values, not just "modified" ones.
- Cognito-Local ([guide](https://github.com/jagregory/cognito-local)) Free Offline AWS-Cognito Emulator. 
- Cognito with OIDC ([guide](https://levelup.gitconnected.com/securing-micro-services-in-quarkus-with-aws-cognito-387990c04100)): Setup Quarkus to use OIDC for JWT-Verify of Cognito-Created JWTs.

## License

This project is licensed under the MIT License - see the LICENSE.md file for details.