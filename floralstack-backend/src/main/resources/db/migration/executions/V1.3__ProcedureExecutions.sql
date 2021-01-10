BEGIN
    INSERT_USER('First', 'Last', date '2021-01-16', 'admin','email@abv.bg', 'password');
    INSERT_ENVIRONMENT('Environment name', 'Envrionment description');
    INSERT_PLANT('Test3', 'Desc', 1, 1, date '2020-12-12');
    INSERT_STATIC_SENSOR('test name', 'static sensor desc', 'high priority', 'an4M17', 'ml', 23.754, 'static', 34.2);
    INSERT_STATIC_SENSOR('test name', 'alternative static sensor desc', 'normal priority', 'an5M2', 'ml', 11.4, 'static', 17.323);
    INSERT_PLANT_STATIC_SENSOR(1, 1);
    INSERT_PLANT_STATIC_SENSOR(1, 2);
END;