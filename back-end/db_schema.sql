--Don't use the below schema. It should be up to date but if you don't have the database created, let Hibernate do it for you
--by changing the auto property in application.properties to create.

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

CREATE TABLE cust_roles(
customer_id int REFERENCES customers,
authorities varchar(255)
);

CREATE TABLE emp_roles(
employee_id int REFERENCES customers,
authorities varchar(255)
);

CREATE TABLE man_roles(
manager_id int REFERENCES customers,
authorities varchar(255)
);

--Don't use the above schema. It should be up to date but if you don't have the database created, let Hibernate do it for you
--by changing the auto property in application.properties to create.

--EXAMPLE PRODUCTS:
INSERT INTO shopproducts (name, price, quantity, description, image_path) values ("iPhone 99 Pro ImageLite",
777.77,14,"Newest model of the beloved iPhone line with only the best in cutting edge phone technology!","../assets/iPhone_99.jpg");

INSERT INTO shopproducts (name, price, quantity, description, image_path) values ("Android Blueberry Deluxe Premium",
55.55,8,"Newest model of the Android line from Google using optimized software and user-friendly apps","../assets/Android_Blueberry.jpg");

INSERT INTO shopproducts (name, price, quantity, description, image_path) values ("LoveCases Sweet! Candy Fit",
17.43,52,"Show your phone how much you love it with a sweet LoveCases formfitting case!","../assets/LoveCases.jpg");