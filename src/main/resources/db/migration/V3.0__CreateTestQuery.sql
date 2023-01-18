
ALTER TABLE `godzilladbdemo`.`product` 
ADD PRIMARY KEY (`product_id`);

ALTER TABLE `godzilladbdemo`.`image` 
ADD PRIMARY KEY (`image_id`);

ALTER TABLE `godzilladbdemo`.`category` 
ADD PRIMARY KEY (`category_id`);

ALTER TABLE `godzilladbdemo`.`product_category` 
ADD PRIMARY KEY (`product_id`, `category_id`);
;

INSERT INTO godzilladbdemo.product (product_id, product_code,product_name) VALUES (1, 'PC0001', 'case');
INSERT INTO godzilladbdemo.product (product_id, product_code,product_name) VALUES (2, 'PC0002', 'computer');
INSERT INTO godzilladbdemo.product (product_id, product_code,product_name) VALUES (3, 'PC0003', 'mouse');
INSERT INTO godzilladbdemo.product (product_id, product_code,product_name) VALUES (4, 'PC0004', 'computer 2');
