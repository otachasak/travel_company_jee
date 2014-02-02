
DROP VIEW users_login;

CREATE VIEW users_login(username, passwd) AS
  SELECT name as username, password as passwd FROM customer
   UNION
   SELECT 'root', 'Y6nw6nu5gFB5a2SehUgYRQ==' FROM customer;
  --password is root


DROP TABLE users_roles;

CREATE TABLE users_roles(username VARCHAR(64), role VARCHAR(32));

INSERT INTO users_roles
   SELECT username, 'customer' as role
   FROM users_login
   WHERE username != 'root';

INSERT INTO users_roles(username, role) VALUES(
   'root', 'root'
);
