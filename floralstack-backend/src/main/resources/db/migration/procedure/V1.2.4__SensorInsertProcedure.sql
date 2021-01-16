CREATE OR REPlACE PROCEDURE INSERT_SENSOR (p_name IN sensor.NAME%TYPE,
                                           p_description IN sensor.DESCRIPTION%TYPE,
                                           p_priority IN sensor.PRIORITY%TYPE,
                                           p_output_identifier IN sensor.OUTPUT_IDENTIFIER%TYPE,
                                           p_unit_of_measurement IN sensor.UNIT_OF_MEASUREMENT%TYPE,
                                           p_last_measurement_value IN sensor.LAST_MEASUREMENT_VALUE%TYPE,
                                           p_threshold_type IN sensor.THRESHOLD_TYPE%TYPE,
                                           p_actuator_id IN sensor.ACTUATOR_ID%TYPE,
                                           p_id OUT sensor.ID%TYPE)
    IS
BEGIN
    INSERT INTO SENSOR ("NAME", "DESCRIPTION", "PRIORITY", "OUTPUT_IDENTIFIER", "UNIT_OF_MEASUREMENT", "LAST_MEASUREMENT_VALUE", "THRESHOLD_TYPE", "ACTUATOR_ID")
    VALUES (p_name, p_description, p_priority, p_output_identifier, p_unit_of_measurement,p_last_measurement_value, p_threshold_type, p_actuator_id)
    RETURNING id INTO p_id;
    COMMIT;
END INSERT_SENSOR;