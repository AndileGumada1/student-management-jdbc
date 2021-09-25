create database studentdb;

use studentdb;

create table student(
 id INT PRIMARY KEY AUTO_INCREMENT,
 name VARCHAR(255) NOT NULL,
 address VARCHAR(255) NOT NULL,
 mobile VARCHAR(255) NOT NULL,
 email VARCHAR(255) NOT NULL
);

INSERT INTO student(name,address,mobile,email)VALUES("Andile","23793 Orange Street","0614723334","andilegumada@gmail.com");
