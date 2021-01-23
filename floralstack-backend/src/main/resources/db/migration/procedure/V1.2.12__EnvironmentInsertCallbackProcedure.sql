CREATE OR REPlACE PROCEDURE INSERT_ENVIRONMENT_CALLBACK(p_name IN environment.name%TYPE,
                                                p_description IN environment.description%TYPE,
                                                p_error_code OUT NUMBER)
    IS
BEGIN
    INSERT INTO ENVIRONMENT ("NAME", "DESCRIPTION")
    VALUES (p_name, p_description);
    p_error_code := SQL%ROWCOUNT;
    IF (p_error_code = 1)
    THEN
        COMMIT;
    ELSE
        ROLLBACK;
    END IF;
EXCEPTION
    WHEN OTHERS
        THEN
            p_error_code := SQLCODE;
END INSERT_ENVIRONMENT_CALLBACK;
