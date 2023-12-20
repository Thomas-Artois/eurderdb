INSERT INTO address(street_name, street_number, location)
VALUES
    ('Bondgenotenlaan', '25A', 'Leuven'),
    ('Keizerstraat', '2', 'Antwerpen'),
    ('Justitielaan', '277', 'Brussel');

INSERT INTO customer(first_name, last_name, email, phone_number, fk_address_id)
VALUES
    ('Maarten', 'Dejonge', 'maarten@gmail.com', '0465454545', 1),
    ('Louise', 'De Oude', 'louise@gmail.com', '0465454545', 2),
    ('Olaf', 'De Sneeuwman', 'olaf@gmail.com', '0465454545', 3);