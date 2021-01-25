CREATE OR REPLACE TRIGGER delete_plant_sensor
    BEFORE DELETE ON plant
    FOR EACH ROW
BEGIN
    DELETE FROM plant_static_sensor where plant_id = :old.id;
    DELETE FROM plant_calibrated_sensor where plant_id = :old.id;
END;
