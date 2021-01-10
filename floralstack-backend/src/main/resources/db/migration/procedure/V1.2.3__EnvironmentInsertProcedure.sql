CREATE OR REPlACE PROCEDURE INSERT_ENVIRONMENT (p_name IN environment.name%TYPE,
                                                p_description IN environment.description%TYPE)
    IS
BEGIN
    INSERT INTO ENVIRONMENT ("NAME", "DESCRIPTION")
    VALUES (p_name, p_description);
    COMMIT;
END INSERT_ENVIRONMENT;
