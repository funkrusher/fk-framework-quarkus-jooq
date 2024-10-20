package org.fk.provider.cognito;

import org.jboss.logging.Logger;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.cognitoidentityprovider.CognitoIdentityProviderClient;
import software.amazon.awssdk.services.cognitoidentityprovider.model.*;

import java.net.URI;
import java.util.Arrays;

import static org.fk.framework.auth.CredentialsAugmentor.FK_CLAIM;

public class CognitoLocalSetup {

    public static final Logger LOGGER = Logger.getLogger(CognitoLocalSetup.class);

    public static final String COGNITO_LOCAL_ENDPOINT = "http://localhost:9229";

    public static final Region COGNITO_LOCAL_REGION = Region.EU_CENTRAL_1;

    public static final String ACCESSKEY = "accesskey";

    public static final String SECRETKEY = "secretkey";

    public static final String POOLNAME = "fkpool";

    public static final String CLIENTNAME = "fkpool-client";

    public static void main(String[] args) {
        // Create a CognitoIdentityProviderClient using the AWS SDK for Java
        try (CognitoIdentityProviderClient cognitoClient = CognitoIdentityProviderClient.builder()
                .region(COGNITO_LOCAL_REGION)
                .endpointOverride(URI.create(COGNITO_LOCAL_ENDPOINT))
                .credentialsProvider(() -> AwsBasicCredentials.create(ACCESSKEY, SECRETKEY))
                .build()) {

            // Create a new user pool
            CreateUserPoolRequest poolRequest = CreateUserPoolRequest.builder()
                    .poolName(POOLNAME)
                    .schema(
                            SchemaAttributeType.builder()
                                    .name(FK_CLAIM)
                                    .attributeDataType(AttributeDataType.STRING)
                                    .developerOnlyAttribute(false)
                                    .mutable(true)
                                    .required(false)
                                    .stringAttributeConstraints(d -> d.maxLength("2048"))
                                    .build())
                    .build();
            CreateUserPoolResponse poolResponse = cognitoClient.createUserPool(poolRequest);
            String userPoolId = poolResponse.userPool().id();

            // Create a user pool client
            CreateUserPoolClientRequest clientRequest = CreateUserPoolClientRequest.builder()
                    .userPoolId(userPoolId)
                    .clientName(CLIENTNAME)
                    .generateSecret(true)
                    .readAttributes(Arrays.asList(FK_CLAIM)) //Add this line to set the read attributes
                    .writeAttributes(Arrays.asList(FK_CLAIM)) //Add this line to set the write attributes
                    .build();
            CreateUserPoolClientResponse clientResponse = cognitoClient.createUserPoolClient(clientRequest);
            String userPoolClientId = clientResponse.userPoolClient().clientId();
            String userPoolClientSecret = clientResponse.userPoolClient().clientSecret();

            // print all relevant information to the console, so the user can copy them.
            LOGGER.info("cognitolocal.userpoolid=" + userPoolId);
            LOGGER.info("cognitolocal.userpoolclientid=" + userPoolClientId);
            LOGGER.info("cognitolocal.userpoolclientsecret=" + userPoolClientSecret);
        }
    }
}
