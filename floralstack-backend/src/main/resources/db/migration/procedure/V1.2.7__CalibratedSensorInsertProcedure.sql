CREATE OR REPlACE PROCEDURE INSERT_CALIBRATED_SENSOR (p_name IN sensor.NAME%TYPE,
                                                  p_description IN sensor.DESCRIPTION%TYPE,
                                                  p_priority IN sensor.PRIORITY%TYPE,
                                                  p_output_identifier IN sensor.OUTPUT_IDENTIFIER%TYPE,
                                                  p_unit_of_measurement IN sensor.UNIT_OF_MEASUREMENT%TYPE,
                                                  p_last_measurement_value IN sensor.LAST_MEASUREMENT_VALUE%TYPE,
                                                  p_threshold_type IN sensor.THRESHOLD_TYPE%TYPE,
                                                  p_actuator_id IN sensor.ACTUATOR_ID%TYPE,
                                                  p_max_value IN CALIBRATED_SENSOR.MAX_VALUE%TYPE,
                                                  p_min_value IN CALIBRATED_SENSOR.MIN_VALUE%TYPE,
                                                  p_percentage_threshold IN CALIBRATED_SENSOR.PERCENTAGE_THRESHOLD%TYPE)
    IS
    p_generated_id static_sensor.ID%TYPE;
BEGIN
    INSERT_SENSOR(p_name, p_description, p_priority, p_output_identifier, p_unit_of_measurement,p_last_measurement_value, p_threshold_type, p_actuator_id, p_generated_id);
    INSERT INTO CALIBRATED_SENSOR ("ID", "MAX_VALUE", "MIN_VALUE", "PERCENTAGE_THRESHOLD")
    VALUES (p_generated_id, p_max_value, p_min_value, p_percentage_threshold );
    COMMIT;
END INSERT_CALIBRATED_SENSOR;