CREATE OR REPlACE PROCEDURE INSERT_PLANT_STATIC_SENSOR (p_plant_id IN PLANT_STATIC_SENSOR.PLANT_ID%TYPE,
                                                        p_static_sensor_id IN PLANT_STATIC_SENSOR.STATIC_SENSOR_ID%TYPE
)
    IS
BEGIN
    INSERT INTO PLANT_STATIC_SENSOR ("PLANT_ID", "STATIC_SENSOR_ID")
    VALUES (p_plant_id, p_static_sensor_id);
    COMMIT;
END INSERT_PLANT_STATIC_SENSOR;