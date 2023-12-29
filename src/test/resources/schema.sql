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
    password VARCHAR(255) NOT NULL,

    CONSTRAINT fk_address
        FOREIGN KEY (fk_address_id)
            REFERENCES address (address_id)
);

CREATE TABLE IF NOT EXISTS "admin"
(
    admin_id SERIAL PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS "item"
(
    item_id      SERIAL PRIMARY KEY,
    name         VARCHAR(255)   NOT NULL,
    description  VARCHAR(255)   NOT NULL,
    price        DECIMAL(10, 2) NOT NULL,
    stock_amount INTEGER        NOT NULL
);

CREATE TABLE IF NOT EXISTS "eurder"
(
    eurder_id   SERIAL PRIMARY KEY,
    customer_id INTEGER                             NOT NULL,
    order_date  TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    total_price DECIMAL(10, 2)                      NOT NULL,

    CONSTRAINT fk_customer
        FOREIGN KEY (customer_id)
            REFERENCES customer (id)
);

CREATE TABLE IF NOT EXISTS "item_group"
(
    item_group_id SERIAL PRIMARY KEY,
    eurder_id     INTEGER        NOT NULL,
    item_id       INTEGER        NOT NULL,
    amount        INTEGER        NOT NULL,
    total_price   DECIMAL(10, 2) NOT NULL,
    shipping_date TIMESTAMP      NOT NULL,

    CONSTRAINT fk_eurder
        FOREIGN KEY (eurder_id)
            REFERENCES eurder (eurder_id),

    CONSTRAINT fk_item
        FOREIGN KEY (item_id)
            REFERENCES item (item_id)

);