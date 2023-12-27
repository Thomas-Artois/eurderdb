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
    eurder_id     INTEGER        NOT NULL,
    item_id       INTEGER        NOT NULL,
    amount        INTEGER        NOT NULL,
    total_price   DECIMAL(10, 2) NOT NULL,
    shipping_date TIMESTAMP      NOT NULL,

    FOREIGN KEY (eurder_id) REFERENCES eurder (eurder_id),
    FOREIGN KEY (item_id) REFERENCES item (item_id)
);
--
-- INSERT INTO eurder(customer_id)
-- VALUES (1);
--
-- INSERT INTO item_group(eurder_id, item_id, amount, total_price, shipping_date)
-- VALUES (1, 1, 2, (SELECT 2 * price FROM item WHERE item_id = 1), CURRENT_TIMESTAMP);
--
-- UPDATE eurder
-- SET total_price = (SELECT COALESCE(SUM(total_price), 0)
--                    FROM item_group
--                    WHERE eurder_id = 1)
-- WHERE eurder_id = 1;
