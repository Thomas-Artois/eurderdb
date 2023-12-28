
INSERT INTO item_group(eurder_id, item_id, amount, total_price, shipping_date)
VALUES (10, 1, 2, (SELECT 2 * price FROM item WHERE item_id = 1), CURRENT_TIMESTAMP);

UPDATE eurder
SET total_price = (SELECT COALESCE(SUM(total_price), 0)
                   FROM item_group
                   WHERE eurder_id = 10)
WHERE eurder_id = 10;