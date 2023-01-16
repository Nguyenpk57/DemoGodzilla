CREATE TABLE product
(
    product_id   INT          NULL,
    product_code VARCHAR(30)  NULL,
    product_name VARCHAR(100) NULL,
    description  VARCHAR(500),
    price        DECIMAL(15,4),
    discount     INT(3)       NULL,
    create_user  VARCHAR(90)  NULL,
    create_time  DATETIME,
    update_user  VARCHAR(90),
    update_time  DATETIME
);

CREATE TABLE category
(
    category_id   INT          NULL,
    category_name VARCHAR(100) NULL,
    description   VARCHAR(500),
    create_user   VARCHAR(90)  NULL,
    create_time   DATETIME     NULL,
    update_user   VARCHAR(90),
    update_time   DATETIME

);

CREATE TABLE product_category -- Associative table.
(
    product_id  INT NULL,
    category_id INT NULL,
    create_user VARCHAR(90),
    create_time DATETIME,
    update_user VARCHAR(90),
    update_time DATETIME
);


create table image
(
    image_id    INT NULL,
    product_id  INT NULL,
    image_type  VARCHAR(10),
    image_size  INT,
    image_name  VARCHAR(50),
    image_path  VARCHAR(256),
    create_user VARCHAR(90),
    create_time DATETIME,
    update_user VARCHAR(90),
    update_time DATETIME
);
