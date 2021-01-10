-- TABLES

CREATE TABLE environment
(
    id INTEGER GENERATED ALWAYS AS IDENTITY (START WITH 1 INCREMENT BY 1) NOT NULL,
    name VARCHAR2(40) NOT NULL,
    description VARCHAR2(100),
    PRIMARY KEY (id)
);

CREATE SEQUENCE user_IDs
    START WITH 0
    INCREMENT BY 1
    MINVALUE 0
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

CREATE TABLE sensor
(
    id INTEGER GENERATED ALWAYS AS IDENTITY (START WITH 1 INCREMENT BY 1) NOT NULL,
    name VARCHAR2(30) NOT NULL,
    description VARCHAR2(100),
    priority VARCHAR2(30),
    output_identifier VARCHAR2(30) UNIQUE,
    CHECK (REGEXP_LIKE(output_identifier,'(an([1-9][0-9]?|100)M([1-9][0-9]?|100)$)|(di([1-9][0-9]?|100)$)')),
    unit_of_measurement VARCHAR(30) NOT NULL,
    last_measurement_value NUMBER,
    threshold_type VARCHAR(30),
    PRIMARY KEY (id)
);

CREATE TABLE calibrated_sensor
(
    id                   INTEGER,
    max_value            NUMERIC(6, 4),
    min_value            NUMERIC(6, 4),
    percentage_threshold NUMERIC(3, 2) CHECK (percentage_threshold BETWEEN 0 AND 100),
    PRIMARY KEY (id),
    FOREIGN KEY (id) REFERENCES sensor (id) ON DELETE CASCADE
);

CREATE TABLE static_sensor
(
    id INTEGER,
    threshold_offset NUMERIC(6,4) DEFAULT 0.0,
    PRIMARY KEY (id),
    FOREIGN KEY (id) REFERENCES sensor (id) ON DELETE CASCADE
);

CREATE TABLE plant_static_sensor
(
    plant_id  INTEGER UNIQUE,
    static_sensor_id INTEGER UNIQUE,
    PRIMARY KEY (plant_id, static_sensor_id),
    FOREIGN KEY (plant_id) REFERENCES plant (id),
    FOREIGN KEY (static_sensor_id) REFERENCES static_sensor (id)
);

CREATE TABLE plant_calibrated_sensor
(
    plant_id  INTEGER UNIQUE,
    calibrated_sensor_id INTEGER UNIQUE,
    PRIMARY KEY (plant_id, calibrated_sensor_id),
    FOREIGN KEY (plant_id) REFERENCES plant (id),
    FOREIGN KEY (calibrated_sensor_id) REFERENCES calibrated_sensor (id)
);