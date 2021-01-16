CREATE OR REPlACE PROCEDURE INSERT_ACTUATOR (p_name IN actuator.NAME%TYPE,
                                           p_description IN actuator.DESCRIPTION%TYPE,
                                           p_priority IN actuator.PRIORITY%TYPE,
                                           p_input_identifier IN actuator.INPUT_IDENTIFIER%TYPE)
    IS
BEGIN
    INSERT INTO ACTUATOR ("NAME", "DESCRIPTION", "PRIORITY", "INPUT_IDENTIFIER")
    VALUES (p_name, p_description, p_priority, p_input_identifier);
    COMMIT;
END INSERT_ACTUATOR;