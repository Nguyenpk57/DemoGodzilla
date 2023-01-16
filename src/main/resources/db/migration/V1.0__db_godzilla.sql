CREATE TABLE dbo_product
(
    product_id   INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
    product_code VARCHAR(30)  NOT NULL,
    product_name VARCHAR(100) NOT NULL,
    description  VARCHAR(500),
    price        DECIMAL(15, 4) DEFAULT 0,
    discount     INT(3)         DEFAULT 0,
    created_by   VARCHAR(90)    DEFAULT '1',
    create_date  DATE           DEFAULT(CURRENT_DATE()),
    updated_by   VARCHAR(90)    DEFAULT '1',
    updated_date DATE           DEFAULT(CURRENT_DATE())
);

CREATE TABLE dbo_category
(
    category_id   INT          NULL,
    category_name VARCHAR(100) NULL,
    description   VARCHAR(500),
    created_by    VARCHAR(90) DEFAULT '1',
    create_date   DATE        DEFAULT(CURRENT_DATE()),
    updated_by    VARCHAR(90) DEFAULT '1',
    updated_date  DATE        DEFAULT(CURRENT_DATE())

);

CREATE TABLE dbo_product_category -- Associative table.
(
    product_id   INT NULL,
    category_id  INT NULL,
    created_by   VARCHAR(90) DEFAULT '1',
    create_date  DATE        DEFAULT(CURRENT_DATE()),
    updated_by   VARCHAR(90) DEFAULT '1',
    updated_date DATE        DEFAULT(CURRENT_DATE())
);


CREATE TABLE dbo_product_image
(
    image_id     INT NOT NULL,
    product_id   INT NOT NULL,
    image_path   VARCHAR(500),
    created_by   VARCHAR(90) DEFAULT '1',
    create_date  DATE        DEFAULT(CURRENT_DATE()),
    updated_by   VARCHAR(90) DEFAULT '1',
    updated_date DATE        DEFAULT(CURRENT_DATE())
);
