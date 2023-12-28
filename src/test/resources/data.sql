INSERT INTO admin(username, password)
VALUES ('adminTest', 'adminPasswordTest');

INSERT INTO address(street_name, street_number, location)
VALUES ('TestingStreetName', 'TestingStreetNumber', 'TestingLocation');

INSERT INTO customer(first_name, last_name, email, phone_number, fk_address_id)
VALUES ('TestingFirstName', 'TestingLastName', 'TestingEmail@gmail.com', '0444444444', 1);

INSERT INTO item(name, description, price, stock_amount)
VALUES ('TestingName', 'TestingDescription', 20.00, 9),
       ('TestingNameTwo', 'TestingDescriptionTwo', 42.42, 2),
       ('TestingNameThree', 'TestingDescriptionThree', 88, 0);

INSERT INTO eurder(customer_id, total_price)
VALUES (1, 0.00);

INSERT INTO item_group(eurder_id, item_id, amount, total_price, shipping_date)
VALUES (1, 1, 2, 40.00, CURRENT_TIMESTAMP),
       (1, 2, 2, 84.84, CURRENT_TIMESTAMP);


UPDATE eurder
SET total_price = (SELECT COALESCE(SUM(total_price), 0.00)
                   FROM item_group
                   WHERE eurder_id = 1)
WHERE eurder_id = 1;

