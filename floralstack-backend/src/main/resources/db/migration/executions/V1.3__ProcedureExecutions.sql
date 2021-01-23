BEGIN
    INSERT_USER('First', 'Last', date '2021-01-16', 'admin','email@abv.bg', 'password');
    INSERT_ENVIRONMENT('Environment name', 'Envrionment description');
    INSERT_PLANT('Test plant', 'Description', 1, 1, date '2020-07-07');
    INSERT_PLANT('Test3', 'Desc', 1, 1, date '2020-12-12');
    INSERT_ACTUATOR('Actuator name', 'Actuator description', 'Low priority', 'an6M17');
    INSERT_ACTUATOR('Fancy ACtuator', 'Alternative Actuator', 'High priority', 'an12M27');
    INSERT_STATIC_SENSOR('test name', 'static sensor desc', 'high priority', 'an4M17', 'ml', 23.754, 'static', 34.3 );
    INSERT_STATIC_SENSOR('test name 2', 'alternative static sensor desc', 'normal priority', 'an5M2', 'ml', 11.4, 'static', 17.233 );
    INSERT_PLANT_STATIC_SENSOR(1, 1);
    INSERT_PLANT_STATIC_SENSOR(1, 2);
    INSERT_CALIBRATED_SENSOR('First calibrated sensor', 'calibrated sensor desc', 'high priority', 'an6M17', 'ml', 23.754, 'calibrated', 99.5, 0.532, 16);
    INSERT_CALIBRATED_SENSOR('Second calibrated sensor', 'alternative calibrated sensor desc', 'normal priority', 'an7M2', 'ml', 11.4, 'calibrated', 87.363, 10.4, 22.5);
    INSERT_PLANT_CALIBRATED_SENSOR(2, 3);
    INSERT_PLANT_CALIBRATED_SENSOR(2, 4);
END;