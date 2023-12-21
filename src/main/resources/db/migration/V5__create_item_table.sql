CREATE TABLE IF NOT EXISTS item
(
    item_id      SERIAL PRIMARY KEY,
    name         VARCHAR(255)   NOT NULL,
    description  VARCHAR(255)   NOT NULL,
    price        DECIMAL(10, 2) NOT NULL,
    stock_amount INTEGER        NOT NULL
);