-- liquibase formatted sql

-- changeset bernd:coreTestDatabase

CREATE TABLE Basic1
(
    autoIncId   INT           NOT NULL AUTO_INCREMENT,
    string1     VARCHAR(50)   NULL,
    string2     VARCHAR(50)   NULL,
    integer1    INT           NULL,
    long1       BIGINT(255)   NULL,
    decimal1    DECIMAL(10,2) NULL,
    dateTime1   DATETIME      NULL,
    clientId    INT           NOT NULL,
    PRIMARY KEY (autoIncId)
);
CREATE TABLE Basic2
(
    uuidId      UUID         NOT NULL,
    string1     VARCHAR(50)  NULL,
    string2     VARCHAR(50)  NULL,
    clientId    INT          NOT NULL,
    PRIMARY KEY (uuidId)
);
CREATE TABLE Nested1
(
    autoIncId   INT          NOT NULL,
    uuidId      UUID         NOT NULL,
    string1     VARCHAR(50)  NULL,
    string2     VARCHAR(50)  NULL,
    integer1    INT           NULL,
    long1       BIGINT(255)   NULL,
    decimal1    DECIMAL(10,2) NULL,
    dateTime1   DATETIME      NULL,
    PRIMARY KEY (autoIncId, uuidId)
);

--rollback DROP TABLE Nested1;
--rollback DROP TABLE Basic2;
--rollback DROP TABLE Basic1;