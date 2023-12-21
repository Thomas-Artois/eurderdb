CREATE TABLE IF NOT EXISTS "address"
(
    address_id    SERIAL PRIMARY KEY,
    street_name   VARCHAR(255) NOT NULL,
    street_number VARCHAR(255) NOT NULL,
    location      VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS "customer"
(
    id            SERIAL PRIMARY KEY,
    first_name    VARCHAR(255) NOT NULL,
    last_name     VARCHAR(255) NOT NULL,
    email         VARCHAR(255) NOT NULL,
    phone_number  VARCHAR(255) NOT NULL,
    fk_address_id INTEGER      NOT NULL,

    CONSTRAINT fk_address
        FOREIGN KEY (fk_address_id)
            REFERENCES address (address_id)
);

CREATE TABLE IF NOT EXISTS "admin"
(
    admin_id SERIAL PRIMARY KEY ,
    username VARCHAR(255) NOT NULL ,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS "item"
(
    item_id SERIAL PRIMARY KEY ,
    name         VARCHAR(255)   NOT NULL,
    description  VARCHAR(255)   NOT NULL,
    price        DECIMAL(10, 2) NOT NULL,
    stock_amount INTEGER        NOT NULL
);