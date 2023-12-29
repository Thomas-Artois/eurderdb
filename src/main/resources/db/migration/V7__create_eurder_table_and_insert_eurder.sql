CREATE TABLE IF NOT EXISTS eurder
(
    eurder_id   SERIAL PRIMARY KEY,
    customer_id INTEGER                             NOT NULL,
    order_date  TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    total_price DECIMAL(10, 2),

    FOREIGN KEY (customer_id) REFERENCES customer (id)

);

CREATE TABLE IF NOT EXISTS item_group
(
    item_group_id SERIAL PRIMARY KEY,
    eurder_id     INTEGER,
    item_id       INTEGER        ,
    amount        INTEGER        NOT NULL,
    total_price   DECIMAL(10, 2) NOT NULL,
    shipping_date TIMESTAMP      NOT NULL,

    FOREIGN KEY (eurder_id) REFERENCES eurder (eurder_id),
    FOREIGN KEY (item_id) REFERENCES item (item_id)
);

