CREATE DATABASE pronosticos_deportivos;

CREATE USER 'pronosticos_user'@'localhost' IDENTIFIED BY 'User1234';
GRANT ALL PRIVILEGES ON pronosticos_deportivos.* TO 'pronosticos_user'@'localhost';
FLUSH PRIVILEGES;

SELECT User, Host FROM mysql.user;
