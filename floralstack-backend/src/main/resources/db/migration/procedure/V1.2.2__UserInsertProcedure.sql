CREATE OR REPlACE PROCEDURE INSERT_USER (p_first_name IN VARCHAR2,
                                          p_last_name IN VARCHAR2,
                                          p_birth_date IN DATE,
                                          p_user_role IN VARCHAR2,
                                          p_email IN VARCHAR2,
                                          p_user_password IN VARCHAR2)
    IS
BEGIN
    INSERT INTO "USER" ("ID", "FIRST_NAME", "LAST_NAME", "BIRTH_DATE", "USER_ROLE", "EMAIL", "USER_PASSWORD")
    VALUES (user_IDs.nextval, p_first_name, p_last_name, p_birth_date, p_user_role, p_email, p_user_password);
    COMMIT;
END INSERT_USER;