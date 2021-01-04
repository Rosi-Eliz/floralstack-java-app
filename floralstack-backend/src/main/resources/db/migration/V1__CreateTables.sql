CREATE TABLE environment
(
    id INTEGER GENERATED ALWAYS AS IDENTITY (START WITH 1 INCREMENT BY 1) NOT NULL,
    name VARCHAR2(40) NOT NULL,
    description VARCHAR2(100),
    PRIMARY KEY (id)
);

CREATE SEQUENCE user_IDs
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 100000
    NOCYCLE;

CREATE TABLE "USER"
(
    id INTEGER NOT NULL,
    first_name VARCHAR2(30),
    last_name VARCHAR(30),
    birth_date DATE,
    user_role VARCHAR(30),
    email VARCHAR(40) UNIQUE,
    user_password VARCHAR(40) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE plant
(
    id               INTEGER GENERATED ALWAYS AS IDENTITY (START WITH 1 INCREMENT BY 1) NOT NULL,
    name             VARCHAR2(30) NOT NULL,
    description      VARCHAR2(100),
    environment_id   INTEGER,
    owner_id         INTEGER,
    creation_date    DATE,
    PRIMARY KEY (id),
    FOREIGN KEY (environment_id) REFERENCES environment (id) ON DELETE CASCADE,
    FOREIGN KEY (owner_id) REFERENCES "USER" (id) ON DELETE CASCADE
);

CREATE OR REPLACE TRIGGER user_id_trigger
    BEFORE INSERT ON "USER"
    FOR EACH ROW
BEGIN
    :new.id := user_IDs.nextval;
END;