CREATE OR REPlACE PROCEDURE INSERT_STATIC_SENSOR (p_name IN sensor.NAME%TYPE,
                                                  p_description IN sensor.DESCRIPTION%TYPE,
                                                  p_priority IN sensor.PRIORITY%TYPE,
                                                  p_output_identifier IN sensor.OUTPUT_IDENTIFIER%TYPE,
                                                  p_unit_of_measurement IN sensor.UNIT_OF_MEASUREMENT%TYPE,
                                                  p_last_measurement_value IN sensor.LAST_MEASUREMENT_VALUE%TYPE,
                                                  p_threshold_type IN sensor.THRESHOLD_TYPE%TYPE,
                                                  p_threshold_offset IN STATIC_SENSOR.THRESHOLD_OFFSET%TYPE)
    IS
    p_generated_id static_sensor.ID%TYPE;
BEGIN
    INSERT_SENSOR(p_name, p_description, p_priority, p_output_identifier, p_unit_of_measurement,p_last_measurement_value, p_threshold_type, p_generated_id);
    INSERT INTO STATIC_SENSOR ("ID", "THRESHOLD_OFFSET")
    VALUES (p_generated_id, p_threshold_offset);
    COMMIT;
END INSERT_STATIC_SENSOR;