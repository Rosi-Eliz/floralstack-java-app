CREATE OR REPlACE PROCEDURE ATTACH_ACTUATOR (
                                             p_sensor_id IN sensor.ID%TYPE,
                                             p_actuator_id IN actuator.ID%TYPE
                                             )
    IS
BEGIN
    INSERT INTO SENSOR_ACTUATOR("SENSOR_ID", "ACTUATOR_ID")
    VALUES (p_sensor_id, p_actuator_id);
    COMMIT;
END ATTACH_ACTUATOR;