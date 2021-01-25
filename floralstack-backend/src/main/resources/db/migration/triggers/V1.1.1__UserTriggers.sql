CREATE OR REPLACE TRIGGER user_id_trigger
    BEFORE INSERT ON "USER"
    FOR EACH ROW
BEGIN
    :new.id := user_IDs.nextval;
END;
