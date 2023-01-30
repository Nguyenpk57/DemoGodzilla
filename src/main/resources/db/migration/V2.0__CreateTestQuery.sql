CREATE TABLE product
(
     product_id       INT      NOT NULL AUTO_INCREMENT,
     product_code     VARCHAR(30) NOT NULL,
     product_name     VARCHAR(100) NOT NULL,
     description      VARCHAR(500),
	 price 			  DECIMAL (15,4),
	 discount		  INT(3),
	 
	 create_user      VARCHAR(90),
     create_time 	  DATETIME,
	 update_user      VARCHAR(90),
     update_time 	  DATETIME,
     PRIMARY KEY (product_id)
);

CREATE TABLE category
(
    category_id       INT      NOT NULL  AUTO_INCREMENT,
    category_name     VARCHAR(100) NOT NULL,  
    description       VARCHAR(500),
	
	create_user       VARCHAR(90),
    create_time       DATETIME,
	update_user       VARCHAR(90),
    update_time 	  DATETIME,
    PRIMARY KEY (category_id)
    
);

CREATE TABLE product_category -- Associative table.
(
    product_id        INT      NOT NULL,
    category_id       INT      NOT NULL,
	
	create_user       VARCHAR(90),
    create_time       DATETIME,
	update_user       VARCHAR(90),
    update_time 	  DATETIME,
    PRIMARY KEY (`product_id`, `category_id`)
);


create table image (
    image_id        INT  NOT NULL AUTO_INCREMENT,
	product_id      INT  NOT NULL,
    image_type      VARCHAR(10),
    image_size      INT,
    image_name      VARCHAR(50),
	image_path	 	VARCHAR(256),
	
	create_user     VARCHAR(90),
    create_time     DATETIME,
	update_user     VARCHAR(90),
    update_time 	DATETIME,
    PRIMARY KEY (`image_id`)
);


