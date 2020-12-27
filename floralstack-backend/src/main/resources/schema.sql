DROP TABLE IF EXISTS plant;
CREATE TABLE plant
(
    id               GENERATED ALWAYS AS IDENTITY (START WITH 1 INCREMENT BY 1) NOT NULL,
    name             VARCHAR2(30) NOT NULL,
    description      VARCHAR2(30),
    environment_ID   INTEGER,
    owner_ID         INTEGER,
    action_record_ID INTEGER,
    PRIMARY KEY (id)
--     FOREIGN KEY (environment_ID) REFERENCES environment (id) ON DELETE CASCADE,
--     FOREIGN KEY (owner_ID) REFERENCES user (id) ON DELETE CASCADE
);