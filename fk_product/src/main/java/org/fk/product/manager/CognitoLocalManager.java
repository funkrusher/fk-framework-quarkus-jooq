package org.fk.product.manager;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Default;
import jakarta.inject.Inject;
import org.fk.core.jooq.DSLFactory;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.fk.codegen.testshop.tables.records.UserRoleRecord;
import org.fk.core.manager.AbstractManager;
import org.fk.product.dao.DAOFactory;
import org.fk.product.dao.UserDAO;
import org.fk.product.dao.UserRoleDAO;
import org.fk.product.dto.UserDTO;
import org.fk.product.dto.UserRoleDTO;
import org.fk.core.auth.FkClaim;
import org.fk.core.util.request.RequestContext;
import org.jboss.logging.Logger;
import org.jooq.DSLContext;
import software.amazon.awssdk.services.cognitoidentityprovider.CognitoIdentityProviderClient;
import software.amazon.awssdk.services.cognitoidentityprovider.model.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * CognitoLocalManager
 */
@ApplicationScoped
public class CognitoLocalManager extends AbstractManager {
    private static final Logger LOGGER = Logger.getLogger(CognitoLocalManager.class);

    @ConfigProperty(name = "cognitolocal.userpoolid")
    String userPoolId;

    @ConfigProperty(name = "cognitolocal.userpoolclientid")
    String userPoolClientId;

    @Inject
    @Default
    private CognitoIdentityProviderClient cognitoIpc;

    @Inject
    DSLFactory dslFactory;

    @Inject
    DAOFactory daoFactory;

    @Inject
    ObjectMapper objectMapper;

    /**
     * Signup a new user into the pool
     *
     * @param clientId clientId
     * @param email    email
     * @param password password
     * @param firstname firstname
     * @param lastname lastname
     * @param roleId roleId
     * @return userSub
     */
    public String signup(
            Integer clientId,
            String email,
            String password,
            String firstname,
            String lastname,
            String roleId) {

        RequestContext request = new RequestContext(clientId, 1);
        DSLContext dsl = dslFactory.create(request);
        UserDAO userRecordDAO = daoFactory.createUserDAO(dsl);
        UserRoleDAO userRoleRecordDAO = daoFactory.createUserRoleDAO(dsl);

        UserDTO user = new UserDTO();
        user.setClientId(clientId);
        user.setEmail(email);
        user.setFirstname(firstname);
        user.setLastname(lastname);

        UserDTO createdUser = null;
        UserRoleDTO createdUserRole = null;
        String userSub = null;
        try {
            createdUser = userRecordDAO.insertAndReturnDTO(user);

            UserRoleDTO userRole = new UserRoleDTO();
            userRole.setUserId(createdUser.getUserId());
            userRole.setRoleId(roleId);
            createdUserRole = userRoleRecordDAO.insertAndReturnDTO(userRole);

            List<String> roles = new ArrayList<>();
            roles.add(roleId);

            FkClaim fkClaim = new FkClaim(clientId, createdUser.getUserId(), roles);
            String fkClaimStr = objectMapper.writeValueAsString(fkClaim);

            SignUpRequest signUpRequest = SignUpRequest.builder()
                    .clientId(userPoolClientId)
                    .username(email)
                    .password(password)
                    .userAttributes(
                            AttributeType.builder().name("email").value(email).build(),
                            AttributeType.builder().name("custom:fk").value(fkClaimStr).build()
                    )
                    .build();
            SignUpResponse response = cognitoIpc.signUp(signUpRequest);
            userSub = response.userSub();

            AdminConfirmSignUpRequest confirmRequest = AdminConfirmSignUpRequest.builder()
                    .userPoolId(userPoolId)
                    .username(email)
                    .build();
            AdminConfirmSignUpResponse confirmResponse = cognitoIpc.adminConfirmSignUp(confirmRequest);

        } catch (Exception e) {
            // delete database entries in case of error (manual rollback)
            if (createdUserRole != null) {
                userRoleRecordDAO.delete(createdUserRole.into(new UserRoleRecord()));
            }
            if (createdUser != null) {
                userRecordDAO.deleteById(createdUser.getUserId());
            }
        }
        return userSub;
    }

    /**
     * Signin with an existing user.
     *
     * @param email    email
     * @param password password
     * @return jwtTokens
     * @throws NotAuthorizedException
     */
    public Map<String, String> signin(String email, String password) throws NotAuthorizedException {
        InitiateAuthResponse response = cognitoIpc.initiateAuth(
                InitiateAuthRequest.builder()
                        .clientId(userPoolClientId)
                        .authFlow(AuthFlowType.USER_PASSWORD_AUTH)
                        .authParameters(
                                Map.of(
                                        "USERNAME", email,
                                        "PASSWORD", password
                                )
                        )
                        .build()
        );
        AuthenticationResultType result = response.authenticationResult();

        Map<String, String> jwtTokens = new HashMap<>();
        jwtTokens.put("access_token", result.accessToken());
        jwtTokens.put("refresh_token", result.refreshToken());
        jwtTokens.put("id_token", result.idToken());
        return jwtTokens;
    }

    /**
     * Find and return an existing user.
     *
     * @param email email
     * @return user-attributes
     * @throws UserNotFoundException
     */
    public Map<String, String> find(String email) throws UserNotFoundException {
        AdminGetUserRequest request = AdminGetUserRequest.builder()
                .userPoolId(userPoolId)
                .username(email)
                .build();
        AdminGetUserResponse response = cognitoIpc.adminGetUser(request);

        List<AttributeType> userAttributes = response.userAttributes();
        Map<String, String> attributesMap = new HashMap<>();
        for (AttributeType attribute : userAttributes) {
            attributesMap.put(attribute.name(), attribute.value());
        }
        // The attributes of the user can be accessed through the attributesMap object.
        return attributesMap;
    }


}
