CREATE TABLE IF NOT EXISTS admin
(
    admin_id SERIAL PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL
);

INSERT INTO admin(username, password)
VALUES
('admin', 'adminPassword');