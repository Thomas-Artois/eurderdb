INSERT INTO admin(username, password)
VALUES
('adminTest', 'adminPasswordTest');

INSERT INTO address(street_name, street_number, location)
VALUES
('TestingStreetName', 'TestingStreetNumber', 'TestingLocation');

INSERT INTO customer(first_name, last_name, email, phone_number, fk_address_id)
VALUES
('TestingFirstName', 'TestingLastName', 'TestingEmail@gmail.com', '0444444444', 1);

INSERT INTO item(name, description, price, stock_amount)
VALUES
('TestingName', 'TestingDescription', 42.42, 9),
('TestingNameTwo', 'TestingDescriptionTwo', 42.42, 0);