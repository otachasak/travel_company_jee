
DELETE FROM customer WHERE id=0;

INSERT INTO customer(id, name, email, password)
  VALUES(0, 'root','none','Y6nw6nu5gFB5a2SehUgYRQ==');

DROP VIEW users_roles;
DROP VIEW users_login;

CREATE VIEW users_login(username, passwd) AS
  SELECT name as username, password as passwd FROM customer;


DROP TABLE fake_users_roles;
CREATE TABLE fake_users_roles(username VARCHAR(64), role VARCHAR(32));
INSERT INTO fake_users_roles(username, role) VALUES('root', 'root');


CREATE VIEW users_roles(username, role) AS
    SELECT username, 'customer' as role
    FROM users_login
    WHERE username != 'root'
  UNION
    SELECT username, username as role
    FROM users_login
    WHERE username = 'root';
