DROP TABLE IF EXISTS estate;
create table estate
(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    type VARCHAR(250) NOT NULL,
    city VARCHAR(250) NOT NULL,
    street VARCHAR(250) NOT NULL,
    number INT(11) NOT NULL,
    owner VARCHAR(250) NOT NULL,
    size DOUBLE NOT NULL,
    value INT(11) NOT NULL,
    quantity INT(11) NOT NULL,
    tax DOUBLE NOT NULL);
INSERT INTO estate (type,city,street,number,owner,size,value,quantity,tax)VALUES
('House', 'Dublin','MainStreet',5,'John',80.5,100000,1,156);
