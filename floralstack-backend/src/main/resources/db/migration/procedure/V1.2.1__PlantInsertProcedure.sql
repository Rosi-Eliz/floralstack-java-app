CREATE OR REPlACE PROCEDURE INSERT_PLANT (p_name IN plant.name%TYPE,
                                           p_description IN plant.description%TYPE,
                                           p_environment_id plant.environment_id%TYPE,
                                           p_owner_id IN plant.owner_id%TYPE,
                                           p_creation_date IN plant.creation_date%TYPE)
    IS
BEGIN
    INSERT INTO plant ("NAME", "DESCRIPTION", "ENVIRONMENT_ID", "OWNER_ID", "CREATION_DATE")
    VALUES (p_name, p_description, p_environment_id, p_owner_id, p_creation_date);
    COMMIT;
END INSERT_PLANT;