-- liquibase formatted sql

-- changeset bernd:feature-1122

CREATE TABLE testshop.role
(
    roleId VARCHAR(50) NOT NULL,
    PRIMARY KEY (roleId)
);

CREATE TABLE testshop.user
(
    userId     INT NOT NULL AUTO_INCREMENT,
    clientId   INT NOT NULL,
    email      VARCHAR(255) NOT NULL,
    firstname  VARCHAR(255) NOT NULL,
    lastname   VARCHAR(255) NOT NULL,
    PRIMARY KEY (userId),
    CONSTRAINT fk_user_clientId FOREIGN KEY (clientId) REFERENCES testshop.client (clientId) ON DELETE CASCADE
);

CREATE TABLE testshop.user_role
(
    userId     INT NOT NULL,
    roleId     VARCHAR(50) NOT NULL,
    PRIMARY KEY (userId, roleId),
    CONSTRAINT fk_user_role_userId FOREIGN KEY (userId) REFERENCES testshop.user (userId) ON DELETE CASCADE,
    CONSTRAINT fk_user_role_roleId FOREIGN KEY (roleId) REFERENCES testshop.role (roleId) ON DELETE CASCADE
);

--rollback DROP TABLE testshop.user_role;
--rollback DROP TABLE testshop.user;
--rollback DROP TABLE testshop.role;