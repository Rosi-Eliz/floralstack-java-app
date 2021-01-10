BEGIN
    INSERT_PLANT('Test3', 'Desc', NULL, NULL, NULL);
    INSERT_USER('First', 'Last', date '2021-01-16', 'admin','email@abv.bg', 'password');
    INSERT_ENVIRONMENT('Environment name', 'Envrionment description');
    INSERT_STATIC_SENSOR('test name', 'static sensor desc', 'high priority', 'an4M17', 'ml', 23.754, 'static', 34.2);
END;