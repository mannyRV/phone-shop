CREATE DATABASE shopdb;

USE shopdb;

CREATE TABLE customers(
id int PRIMARY KEY AUTO_INCREMENT,
username varchar(100) NOT NULL UNIQUE,
password varchar(100) NOT NULL,
balance double
);

CREATE TABLE employees(
id int PRIMARY KEY AUTO_INCREMENT,
username varchar(100) NOT NULL UNIQUE,
password varchar(100) NOT NULL,
pay_rate double
);

CREATE TABLE managers(
id int PRIMARY KEY AUTO_INCREMENT,
username varchar(100) NOT NULL UNIQUE,
password varchar(100) NOT NULL
);

CREATE TABLE shopproducts(
id int PRIMARY KEY AUTO_INCREMENT,
name varchar(100) NOT NULL UNIQUE,
price double NOT NULL,
quantity int,
description longtext,
image_path longtext
);

CREATE TABLE ownedproducts(
id int PRIMARY KEY AUTO_INCREMENT,
name varchar(100) NOT NULL UNIQUE,
description longtext,
image_path longtext,
customer_id int REFERENCES customers,
order_id int REFERENCES orders
);

CREATE TABLE orders(
id int PRIMARY KEY AUTO_INCREMENT,
customer_id int REFERENCES customers,
total double,
date DATE
);

CREATE TABLE reviews(
id int PRIMARY KEY AUTO_INCREMENT,
stars int,
customer_id int REFERENCES customers,
description longtext,
product_id REFERENCES shopproducts
);